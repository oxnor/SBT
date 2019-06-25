package ru.shaa.sbt.shoppingmngr.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.entities.*;

import java.time.LocalDateTime;

@SpringBootTest
class IPurchaseListRepositoryTests {
    @Autowired
    IPurchaseListRepository purchaseListRepository;

    @Autowired
    IOwnerRepository ownerRepository;

    @Test
    void testGetById()
    {
        PurchaseList purchaseList = null;
        purchaseList = purchaseListRepository.getById(1);
        Assertions.assertNotNull(purchaseList);
        Assertions.assertNotNull(purchaseList.getId());
        Assertions.assertNotNull(purchaseList.getCaption());
    }

    @Test
    void testCreate()
    {
        PurchaseList purchaseListN = new PurchaseList(null, "8 марта", ownerRepository.getById(158));

        purchaseListRepository.save(purchaseListN);

        Assertions.assertNotNull(purchaseListN.getId(), "У сохраненного списка отсутствует идентификатор");

        PurchaseList purchaseListR = purchaseListRepository.getById(purchaseListN.getId());
        Assertions.assertNotNull(purchaseListR, "Список отсутствует в базе");
        Assertions.assertEquals(purchaseListR.getId(), purchaseListN.getId(), "Не совпадает идентификатор");
        Assertions.assertEquals(purchaseListR.getCaption(), purchaseListN.getCaption(), "Не совпадает Caption");
        Assertions.assertEquals(purchaseListR.getOwner(), purchaseListN.getOwner(), "Не совпадает владелец");
    }

    @Test
    void testPlannedPurchases()
    {
        PurchaseList purchaseListN = new PurchaseList(null, "8 марта", ownerRepository.getById(158));

        Goods goods = new Goods(null, "Музыкальная открытка №5");
        TaskRunOnce taskN = new TaskRunOnce(null, LocalDateTime.parse("2019-11-08T00:00"), LocalDateTime.parse("2019-11-15T00:00"), new ScheduleType(1, "RunOnce", ""), LocalDateTime.parse("2019-11-08T18:00"));
        PlannedPurchase plannedPurchaseN = new PlannedPurchase(null, purchaseListN, goods, taskN, false, false);

        purchaseListN.getPlannedPurchases().add(plannedPurchaseN);

        goods = new Goods(null, "Музыкальная открытка №6");
        taskN = new TaskRunOnce(null, LocalDateTime.parse("2019-11-10T00:00"), LocalDateTime.parse("2019-11-10T00:00"), new ScheduleType(1, "RunOnce", ""), LocalDateTime.parse("2019-11-10T18:00"));
        plannedPurchaseN = new PlannedPurchase(null, purchaseListN, goods, taskN, false, false);

        purchaseListN.getPlannedPurchases().add(plannedPurchaseN);

        purchaseListRepository.save(purchaseListN);

        Assertions.assertNotNull(purchaseListN.getId(), "У сохраненного списка отсутствует идентификатор");

        PurchaseList purchaseListR = purchaseListRepository.getById(purchaseListN.getId());
        Assertions.assertEquals(2, purchaseListR.getPlannedPurchases().size());
    }


    @Test
    void testUpdate()
    {
        PurchaseList purchaseListN = new PurchaseList(null, "8 марта", ownerRepository.getById(159));

        purchaseListRepository.save(purchaseListN);

        PurchaseList purchaseListR = purchaseListRepository.getById(purchaseListN.getId());
        purchaseListR.setCaption("23 февраля");
        purchaseListRepository.save(purchaseListR);
        PurchaseList purchaseListR1 = purchaseListRepository.getById(purchaseListN.getId());

        Assertions.assertNotNull(purchaseListR1, "Список отсутствует в базе");
        Assertions.assertEquals(purchaseListR1.getId(), purchaseListN.getId(), "Не совпадает идентификатор");
        Assertions.assertEquals(purchaseListR1.getCaption(), purchaseListR.getCaption(), "Не совпадает Caption");
        Assertions.assertEquals(purchaseListR.getOwner(), purchaseListN.getOwner(), "Не совпадает владелец");
    }

    @Test
    void testDelete()
    {
        PurchaseList purchaseListN = new PurchaseList(null, "Новый год", ownerRepository.getById(158));

        purchaseListRepository.save(purchaseListN);

        Assertions.assertNotNull(purchaseListN.getId());

        purchaseListRepository.delete(purchaseListN);

        PurchaseList taskR = purchaseListRepository.getById(purchaseListN.getId());
        Assertions.assertNull(taskR, "Удаление не произведено");
    }

}
