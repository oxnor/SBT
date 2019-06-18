package ru.shaa.sbt.shoppingmngr.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.entities.Owner;

@SpringBootTest
public class IOwnerRepositoryTests {
    @Autowired
    IOwnerRepository OwnerRepository;

    @Test
    void testGetById()
    {
        Owner Owner = null;
        Owner = OwnerRepository.getById(1);
        Assertions.assertNotNull(Owner);
        Assertions.assertNotNull(Owner.getId());
        Assertions.assertNotNull(Owner.getCaption());
    }
}
