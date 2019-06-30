package ru.shaa.sbt.shoppingmngr.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.entities.*;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class IFactPurchaseRepositoryTests {
    @Autowired
    IFactPurchaseRepository factPurchaseRepository;

    @Autowired
    IOwnerRepository ownerRepository;

    @Test
    void testCreate()
    {
        Owner owner = ownerRepository.getById(158);
        Goods goods = new Goods(null, "Табурет");
        FactPurchase factPurchase = new FactPurchase(null, owner, goods, LocalDateTime.parse("2018-07-08T14:43"));

        factPurchaseRepository.save(factPurchase);

        Assertions.assertNotNull(factPurchase.getId(), "Отсутствует идентификатор");
    }

    @Test
    void testGetById()
    {
        Owner owner = ownerRepository.getById(158);
        Goods goods = new Goods(null, "Табурет");
        FactPurchase factPurchaseN = new FactPurchase(null, owner, goods, LocalDateTime.parse("2018-07-08T14:43"));

        factPurchaseRepository.save(factPurchaseN);

        FactPurchase factPurchase = factPurchaseRepository.getById(factPurchaseN.getId(), owner);
        Assertions.assertEquals(factPurchaseN.getId(), factPurchase.getId());
    }

    @Test
    void testGetByOwner()
    {
        Owner owner = ownerRepository.getById(158);
        Goods goods = new Goods(null, "Табурет 1");
        FactPurchase factPurchaseN = new FactPurchase(null, owner, goods, LocalDateTime.parse("2018-07-08T14:43"));
        factPurchaseRepository.save(factPurchaseN);

        goods = new Goods(null, "Табурет 2");
        factPurchaseN = new FactPurchase(null, owner, goods, LocalDateTime.parse("2018-07-12T18:15"));
        factPurchaseRepository.save(factPurchaseN);

        List<FactPurchase> l = factPurchaseRepository.getByOwner(owner);
        Assertions.assertEquals(l.size(), 2);
    }

    @Test
    void testGetByPurchaseListIfEmpty()
    {
        Owner owner = ownerRepository.getById(158);

        List<FactPurchase> l = factPurchaseRepository.getByOwner(owner);

        Assertions.assertNotNull(l);
        Assertions.assertEquals(l.size(), 0);
    }
}
