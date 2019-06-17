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
    public void save(Goods task) {

    }

    @Override
    public void delete(Goods task) {

    }
}
