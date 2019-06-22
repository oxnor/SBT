package ru.shaa.sbt.shoppingmngr.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import ru.shaa.sbt.shoppingmngr.entities.Owner;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Component
public class OwnerRepository implements IOwnerRepository {
    protected DataSource dataSource;

    @Autowired
    public OwnerRepository(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public Owner getById(int id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("pchmgr").withProcedureName("ex_Owners");
        jdbcCall.returningResultSet("rs"
                , ((rs, i) -> new Owner(
                        rs.getInt("ID")
                        , rs.getString("Caption")
                )
                )
        );

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", id );

        Map<String, Object> out = jdbcCall.execute(params);
        List<Owner> ownerList = (List<Owner>)out.get("rs");
        if (ownerList != null && !ownerList.isEmpty())
        {
            return ownerList.get(0);
        }
        return null;
    }
}
