package ru.shaa.sbt.shoppingmngr.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.entities.PurchaseList;

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
        PurchaseList purchaseListN = new PurchaseList(null, "8 марта", ownerRepository.getById(1));

        purchaseListRepository.save(purchaseListN);

        Assertions.assertNotNull(purchaseListN.getId(), "У сохраненного списка отсутствует идентификатор");

        PurchaseList purchaseListR = purchaseListRepository.getById(purchaseListN.getId());
        Assertions.assertNotNull(purchaseListR, "Список отсутствует в базе");
        Assertions.assertEquals(purchaseListR.getId(), purchaseListN.getId(), "Не совпадает идентификатор");
        Assertions.assertEquals(purchaseListR.getCaption(), purchaseListN.getCaption(), "Не совпадает Caption");
        //TODO Сравнение Owner
    }

    @Test
    void testUpdate()
    {
        PurchaseList purchaseListN = new PurchaseList(null, "8 марта", ownerRepository.getById(2));

        purchaseListRepository.save(purchaseListN);

        PurchaseList purchaseListR = purchaseListRepository.getById(purchaseListN.getId());
        purchaseListR.setCaption("23 февраля");
        purchaseListRepository.save(purchaseListR);
        PurchaseList purchaseListR1 = purchaseListRepository.getById(purchaseListN.getId());

        Assertions.assertNotNull(purchaseListR1, "Список отсутствует в базе");
        Assertions.assertEquals(purchaseListR1.getId(), purchaseListN.getId(), "Не совпадает идентификатор");
        Assertions.assertEquals(purchaseListR1.getCaption(), purchaseListR.getCaption(), "Не совпадает Caption");
    }

    @Test
    void testDelete()
    {
        PurchaseList purchaseListN = new PurchaseList(null, "Новый год", ownerRepository.getById(2));

        purchaseListRepository.save(purchaseListN);

        Assertions.assertNotNull(purchaseListN.getId());

        purchaseListRepository.delete(purchaseListN);

        PurchaseList taskR = purchaseListRepository.getById(purchaseListN.getId());
        Assertions.assertNull(taskR, "Удаление не произведено");
    }

}
