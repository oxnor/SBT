package ru.shaa.sbt.shoppingmngr.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import ru.shaa.sbt.shoppingmngr.entities.PurchaseList;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Component
public class PurchaseListRepository implements IPurchaseListRepository {
    protected DataSource dataSource;
    private IOwnerRepository ownerRepository;

    @Autowired
    public PurchaseListRepository(DataSource dataSource, IOwnerRepository ownerRepository)
    {
        this.dataSource = dataSource;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public PurchaseList getById(int id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("pchmgr").withProcedureName("ex_PrchLists");
        jdbcCall.returningResultSet("rs"
                , ((rs, i) -> new PurchaseList(
                        rs.getInt("ID")
                        , rs.getString("Caption")
                        , ownerRepository.getById(rs.getInt("Id_Owner"))
                )
                )
        );

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", id );

        Map<String, Object> out = jdbcCall.execute(params);
        List<PurchaseList> purchaseListList = (List<PurchaseList>)out.get("rs");
        if (purchaseListList != null && !purchaseListList.isEmpty())
        {
            return purchaseListList.get(0);
        }
        return null;
    }

    @Override
    public void save(PurchaseList purchaseList) {
        if (purchaseList.getId() == null)
            create(purchaseList);
        else
            update(purchaseList);
    }


    private void create(PurchaseList purchaseList) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("pchmgr").withProcedureName("PrchListCreate");

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("Caption", purchaseList.getCaption());
        params.addValue("Id_Owner", purchaseList.getOwner().getId());

        Map<String, Object> out = jdbcCall.execute(params);

        purchaseList.setId((Integer) out.get("ID"));
    }

    private void update(PurchaseList purchaseList) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("pchmgr").withProcedureName("PrchListUpdate");

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("ID", purchaseList.getId());
        params.addValue("Caption", purchaseList.getCaption());
        params.addValue("Id_Owner", purchaseList.getOwner().getId());

        jdbcCall.execute(params);
    }


    @Override
    public void delete(PurchaseList purchaseList) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("pchmgr").withProcedureName("PrchListDelete");

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("ID", purchaseList.getId());

        jdbcCall.execute(params);
    }
}
