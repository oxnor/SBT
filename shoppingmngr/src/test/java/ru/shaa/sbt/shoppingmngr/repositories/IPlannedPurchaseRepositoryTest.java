package ru.shaa.sbt.shoppingmngr.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.entities.*;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class IPlannedPurchaseRepositoryTest {
    @Autowired
    IPlannedPurchaseRepository plannedPurchaseRepository;

    @Autowired
    IOwnerRepository ownerRepository;

    @Autowired
    IPurchaseListRepository purchaseListRepository;

    @Test
    void testCreate()
    {
        PurchaseList purchaseListN = new PurchaseList(null, "8 марта", ownerRepository.getById(158));
        purchaseListRepository.save(purchaseListN);
        Goods goods = new Goods(null, "Музыкальная открытка");
        TaskRunOnce taskN = new TaskRunOnce(null, LocalDateTime.parse("2019-11-08T00:00"), LocalDateTime.parse("2019-11-15T00:00"), new ScheduleType(1, "RunOnce", ""), LocalDateTime.parse("2019-11-08T18:00"));
        PlannedPurchase plannedPurchase = new PlannedPurchase(null, purchaseListN, goods, taskN, false, false);

        plannedPurchaseRepository.save(plannedPurchase);

        Assertions.assertNotNull(plannedPurchase.getId(), "Отсутствует идентификатор");
    }

    @Test
    void testGetById()
    {
        PurchaseList purchaseListN = new PurchaseList(null, "8 марта", ownerRepository.getById(158));
        purchaseListRepository.save(purchaseListN);
        Goods goods = new Goods(null, "Музыкальная открытка");
        TaskRunOnce taskN = new TaskRunOnce(null, LocalDateTime.parse("2019-11-08T00:00"), LocalDateTime.parse("2019-11-15T00:00"), new ScheduleType(1, "RunOnce", ""), LocalDateTime.parse("2019-11-08T18:00"));
        PlannedPurchase plannedPurchaseN = new PlannedPurchase(null, purchaseListN, goods, taskN, false, false);

        plannedPurchaseRepository.save(plannedPurchaseN);

        PlannedPurchase plannedPurchaseR = plannedPurchaseRepository.getById(plannedPurchaseN.getId(), purchaseListN);
        Assertions.assertEquals(plannedPurchaseN.getId(), plannedPurchaseR.getId());
    }

    @Test
    void testGetByPurchaseList()
    {
        PurchaseList purchaseListN = new PurchaseList(null, "День рождения", ownerRepository.getById(158));
        purchaseListRepository.save(purchaseListN);

        Goods goods = new Goods(null, "Музыкальная открытка №2");
        TaskRunOnce taskN = new TaskRunOnce(null, LocalDateTime.parse("2019-11-08T00:00"), LocalDateTime.parse("2019-11-15T00:00"), new ScheduleType(1, "RunOnce", ""), LocalDateTime.parse("2019-11-08T18:00"));
        PlannedPurchase plannedPurchaseN = new PlannedPurchase(null, purchaseListN, goods, taskN, false, false);

        plannedPurchaseRepository.save(plannedPurchaseN);

        goods = new Goods(null, "Музыкальная открытка №3");
        taskN = new TaskRunOnce(null, LocalDateTime.parse("2019-11-10T00:00"), LocalDateTime.parse("2019-11-10T00:00"), new ScheduleType(1, "RunOnce", ""), LocalDateTime.parse("2019-11-10T18:00"));
        plannedPurchaseN = new PlannedPurchase(null, purchaseListN, goods, taskN, false, false);

        plannedPurchaseRepository.save(plannedPurchaseN);

        List<PlannedPurchase> l = plannedPurchaseRepository.getByPurchaseList(purchaseListN);
        Assertions.assertEquals(l.size(), 2);
    }

    @Test
    void testGetByPurchaseListIfEmpty()
    {
        PurchaseList purchaseListN = new PurchaseList(null, "День рождения", ownerRepository.getById(158));
        purchaseListRepository.save(purchaseListN);

        List<PlannedPurchase> l = plannedPurchaseRepository.getByPurchaseList(purchaseListN);
        Assertions.assertNotNull(l);
        Assertions.assertEquals(l.size(), 0);
    }


    @Test
    void testUpdate()
    {
        PurchaseList purchaseListN = new PurchaseList(null, "8 марта", ownerRepository.getById(158));
        purchaseListRepository.save(purchaseListN);
        Goods goods = new Goods(null, "Музыкальная открытка");
        TaskRunOnce taskN = new TaskRunOnce(null, LocalDateTime.parse("2019-11-08T00:00"), LocalDateTime.parse("2019-11-15T00:00"), new ScheduleType(1, "RunOnce", ""), LocalDateTime.parse("2019-11-08T18:00"));
        PlannedPurchase plannedPurchaseN = new PlannedPurchase(null, purchaseListN, goods, taskN, false, false);

        plannedPurchaseRepository.save(plannedPurchaseN);

        PlannedPurchase plannedPurchaseR = plannedPurchaseRepository.getById(plannedPurchaseN.getId(), purchaseListN);

        TaskRunOnce taskN1 = new TaskRunOnce(null, LocalDateTime.parse("2019-11-16T00:00"), LocalDateTime.parse("2019-11-17T00:00"), new ScheduleType(1, "RunOnce", ""), LocalDateTime.parse("2019-11-16T18:00"));
        plannedPurchaseR.setTask(taskN1);
        plannedPurchaseR.setCompleted(true);
        plannedPurchaseR.setDeleted(true);

        plannedPurchaseRepository.save(plannedPurchaseR);

        PlannedPurchase plannedPurchaseR1 = plannedPurchaseRepository.getById(plannedPurchaseN.getId(), purchaseListN);
        Assertions.assertEquals(plannedPurchaseR.isCompleted(), plannedPurchaseR1.isCompleted());
        Assertions.assertEquals(plannedPurchaseR.isDeleted(), plannedPurchaseR1.isDeleted());
        Assertions.assertEquals(plannedPurchaseR.getTask().getBegDate(),plannedPurchaseR1.getTask().getBegDate()); //TODO реализовать сравнение тасков
    }

}
