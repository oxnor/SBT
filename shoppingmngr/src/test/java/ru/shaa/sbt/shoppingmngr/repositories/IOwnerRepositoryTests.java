package ru.shaa.sbt.shoppingmngr.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.entities.Owner;

@SpringBootTest
public class IOwnerRepositoryTests {
    @Autowired
    IOwnerRepository ownerRepository;

    @Test
    void testGetById()
    {
        Owner owner = null;
        owner = ownerRepository.getById(1);
        Assertions.assertNotNull(owner);
        Assertions.assertNotNull(owner.getId());
        Assertions.assertNotNull(owner.getCaption());
    }
}
