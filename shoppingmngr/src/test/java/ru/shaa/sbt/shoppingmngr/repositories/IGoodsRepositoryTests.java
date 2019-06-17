package ru.shaa.sbt.shoppingmngr.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.entities.Goods;

@SpringBootTest
public class IGoodsRepositoryTests {
    @Autowired
    IGoodsRepository goodsRepository;

    @Test
    void testGetById()
    {
        Goods goods = null;
        goods = goodsRepository.getById(1);
        Assertions.assertNotNull(goods);
        Assertions.assertNotNull(goods.getId());
        Assertions.assertNotNull(goods.getCaption());
    }

}
