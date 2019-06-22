package ru.shaa.sbt.shoppingmngr.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import ru.shaa.sbt.shoppingmngr.entities.PlannedPurchase;

import javax.sql.DataSource;
import java.util.Map;

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
    public PlannedPurchase getById(int id) {
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


        private void create(PlannedPurchase purchaseList) {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("pchmgr").withProcedureName("PrchPlanCreate");

            MapSqlParameterSource params = new MapSqlParameterSource();

            params.addValue("Id_List", purchaseList.getId());
            params.addValue("Id_Good", purchaseList.getGoods().getId());
            params.addValue("Id_Task", purchaseList.getTask().getId());
            params.addValue("IsCompleted", purchaseList.isCompleted());
            params.addValue("IsDeleted", purchaseList.isDeleted());

            Map<String, Object> out = jdbcCall.execute(params);

            purchaseList.setId((Integer) out.get("ID"));
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
    public void delete(PlannedPurchase task) {
        throw new UnsupportedOperationException();
    }
}
