package ru.shaa.sbt.shoppingmngr.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.entities.*;

import java.time.LocalDateTime;

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
}
