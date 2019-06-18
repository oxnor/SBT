package ru.shaa.sbt.shoppingmngr.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.entities.Goods;

import java.time.LocalDateTime;

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

    @Test
    void testCreate()
    {
        Goods goodsN = new Goods(null, "пассатижи");

        goodsRepository.save(goodsN);

        Assertions.assertNotNull(goodsN.getId(), "У сохраненного товара отсутствует идентификатор");

        Goods goodsR = goodsRepository.getById(goodsN.getId());
        Assertions.assertNotNull(goodsR, "Товар отсутствует в базе");
        Assertions.assertEquals(goodsR.getId(), goodsN.getId(), "Не совпадает идентификатор");
        Assertions.assertEquals(goodsR.getCaption(), goodsN.getCaption(), "Не совпадает Caption");
    }

    @Test
    void testUpdate()
    {
        Goods goodsN = new Goods(null, "пассатижи #7");

        goodsRepository.save(goodsN);

        Assertions.assertNotNull(goodsN.getId(), "У сохраненного товара отсутствует идентификатор");

        Goods goodsR = goodsRepository.getById(goodsN.getId());
        goodsR.setCaption("пассатижи #8");
        goodsRepository.save(goodsR);
        Goods goodsR1 = goodsRepository.getById(goodsN.getId());

        Assertions.assertNotNull(goodsR1, "Товар отсутствует в базе");
        Assertions.assertEquals(goodsR1.getId(), goodsN.getId(), "Не совпадает идентификатор");
        Assertions.assertEquals(goodsR1.getCaption(), goodsR.getCaption(), "Не совпадает Caption");
    }

    @Test
    void testDelete()
    {
        Goods goodsN = new Goods(null, "пассатижи #7");

        goodsRepository.save(goodsN);

        Assertions.assertNotNull(goodsN.getId());

        goodsRepository.delete(goodsN);

        Goods taskR = goodsRepository.getById(goodsN.getId());
        Assertions.assertNull(taskR, "Удаление не произведено");
    }

}
