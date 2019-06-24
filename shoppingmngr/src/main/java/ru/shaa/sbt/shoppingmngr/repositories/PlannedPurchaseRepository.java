package ru.shaa.sbt.shoppingmngr.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import ru.shaa.sbt.shoppingmngr.entities.PlannedPurchase;
import ru.shaa.sbt.shoppingmngr.entities.PurchaseList;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Component
public class PlannedPurchaseRepository implements IPlannedPurchaseRepository {
    protected DataSource dataSource;
    private IGoodsRepository goodsRepository;
    private ITaskRepository taskRepository;

    @Autowired
    public PlannedPurchaseRepository(DataSource dataSource, IGoodsRepository goodsRepository, ITaskRepository taskRepository)
    {
        this.dataSource = dataSource;
        this.goodsRepository = goodsRepository;
        this.taskRepository = taskRepository;
    }



    @Override
    public PlannedPurchase getById(int id, PurchaseList purchaseList){
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("pchmgr").withProcedureName("ex_PrchPlans");
        jdbcCall.returningResultSet("rs"
                , ((rs, i) -> {
                    if (!purchaseList.getId().equals(rs.getInt("Id_List")))
                        throw new ViolationСonsistencyException("Идентификатор переданного списка и списка покупки не совпадает");

                    return new PlannedPurchase(
                            rs.getInt("ID")
                            , purchaseList
                            , goodsRepository.getById(rs.getInt("Id_Good"))
                            , taskRepository.getById(rs.getInt("Id_Task"))
                            , rs.getBoolean("IsCompleted")
                            , rs.getBoolean("IsDeleted")
                    );
                }
                )
        );

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", id );

        Map<String, Object> out = jdbcCall.execute(params);
        List<PlannedPurchase> plannedPurchaseList = (List<PlannedPurchase>)out.get("rs");
        if (plannedPurchaseList != null && !plannedPurchaseList.isEmpty())
        {
            return plannedPurchaseList.get(0);
        }
        return null;
    }

    @Override
    public void save(PlannedPurchase plannedPurchase) {
        goodsRepository.save(plannedPurchase.getGoods());
        taskRepository.save(plannedPurchase.getTask());

        if (plannedPurchase.getId() == null)
            create(plannedPurchase);
  /*      else
            update(plannedPurchase);*/
    }


        private void create(PlannedPurchase plannedPurchase) {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("pchmgr").withProcedureName("PrchPlanCreate");

            MapSqlParameterSource params = new MapSqlParameterSource();

            params.addValue("Id_List", plannedPurchase.getPurchaseList().getId());
            params.addValue("Id_Good", plannedPurchase.getGoods().getId());
            params.addValue("Id_Task", plannedPurchase.getTask().getId());
            params.addValue("IsCompleted", plannedPurchase.isCompleted());
            params.addValue("IsDeleted", plannedPurchase.isDeleted());

            Map<String, Object> out = jdbcCall.execute(params);

            plannedPurchase.setId((Integer) out.get("ID"));
        }

        /*
        private void update(PurchaseList purchaseList) {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("pchmgr").withProcedureName("PrchListUpdate");

            MapSqlParameterSource params = new MapSqlParameterSource();

            params.addValue("ID", purchaseList.getId());
            params.addValue("Caption", purchaseList.getCaption());
            params.addValue("Id_Owner", purchaseList.getOwner().getId());

            jdbcCall.execute(params);
        }

    }
*/
    @Override
    public void delete(PlannedPurchase plannedPurchase) {
        throw new UnsupportedOperationException();
    }

}
