package ru.shaa.sbt.shoppingmngr.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import ru.shaa.sbt.shoppingmngr.entities.Goods;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Component
public class GoodsRepository implements IGoodsRepository {
    protected DataSource dataSource;

    @Autowired
    public GoodsRepository(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public Goods getById(int id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("pchmgr").withProcedureName("ex_Goods");
        jdbcCall.returningResultSet("rs"
                , ((rs, i) -> new Goods(
                          rs.getInt("ID")
                        , rs.getString("Caption")
                )
                )
        );

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", id );

        Map<String, Object> out = jdbcCall.execute(params);
        List<Goods> GoodsLst = (List<Goods>)out.get("rs");
        if (GoodsLst != null && !GoodsLst.isEmpty())
        {
            return GoodsLst.get(0);
        }
        return null;
    }

    @Override
    public void save(Goods goods) {
        if (goods.getId() == null)
            create(goods);
        else
            update(goods);
    }


    private void create(Goods goods) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("pchmgr").withProcedureName("GoodsCreate");

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("Caption", goods.getCaption());

        Map<String, Object> out = jdbcCall.execute(params);

        goods.setId((Integer) out.get("ID"));
    }

    private void update(Goods goods) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("pchmgr").withProcedureName("GoodsUpdate");

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("ID", goods.getId());
        params.addValue("Caption", goods.getCaption());

        jdbcCall.execute(params);
    }


    @Override
    public void delete(Goods goods) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("pchmgr").withProcedureName("GoodsDelete");

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("ID", goods.getId());

        jdbcCall.execute(params);
    }
}
