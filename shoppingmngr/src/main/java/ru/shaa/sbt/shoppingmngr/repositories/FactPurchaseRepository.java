package ru.shaa.sbt.shoppingmngr.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import ru.shaa.sbt.shoppingmngr.entities.FactPurchase;
import ru.shaa.sbt.shoppingmngr.entities.Owner;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Component
public class FactPurchaseRepository implements IFactPurchaseRepository {
    private DataSource dataSource;
    private IGoodsRepository goodsRepository;

    @Autowired
    public FactPurchaseRepository(DataSource dataSource, IGoodsRepository goodsRepository, ITaskRepository taskRepository)
    {
        this.dataSource = dataSource;
        this.goodsRepository = goodsRepository;
    }

    @Override
    public FactPurchase getById(int id, Owner owner){
        List<FactPurchase> factPurchases = loadFactPurchaseList(id, owner);
        if (factPurchases != null && !factPurchases.isEmpty())
        {
            return factPurchases.get(0);
        }
        return null;
    }

    public List<FactPurchase> getByOwner(Owner owner){
        return loadFactPurchaseList(null, owner);
    }

    private List<FactPurchase> loadFactPurchaseList(Integer id, Owner owner){
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("pchmgr").withProcedureName("ex_PrchFact");
        jdbcCall.returningResultSet("rs"
                , ((rs, i) -> {
                    if (!owner.getId().equals(rs.getInt("Id_Owner")))
                        throw new ViolationСonsistencyException("Идентификатор переданного владельца и владельца покупки не совпадает");

                    return new FactPurchase(
                            rs.getInt("ID")
                            , owner
                            , goodsRepository.getById(rs.getInt("Id_Good"))
                            , rs.getTimestamp("PrchDate").toLocalDateTime()
                    );
                }
                )
        );

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", id );
        params.addValue("Id_Owner", owner.getId() );

        Map<String, Object> out = jdbcCall.execute(params);
        return (List<FactPurchase>)out.get("rs");
    }


    @Override
    public void save(FactPurchase factPurchase) {
        goodsRepository.save(factPurchase.getGoods());
        // Факт покупки обновлению не подлежит. По крайней мере, на текущий момент.
        if (factPurchase.getId() == null)
            create(factPurchase);
    }


    private void create(FactPurchase factPurchase) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("pchmgr").withProcedureName("PrchFactCreate");

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("Id_Owner", factPurchase.getOwner().getId());
        params.addValue("Id_Good", factPurchase.getGoods().getId());
        params.addValue("PrchDate", factPurchase.getPurchaseDate());

        Map<String, Object> out = jdbcCall.execute(params);

        factPurchase.setId((Integer) out.get("ID"));
    }

    @Override
    public void delete(FactPurchase factPurchase) {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("pchmgr").withProcedureName("PrchFactDelete");

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("ID", factPurchase.getId());

            jdbcCall.execute(params);
    }

}
