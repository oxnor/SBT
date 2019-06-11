package ru.shaa.sbt.shoppingmngr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.Entities.TaskBase;
import ru.shaa.sbt.shoppingmngr.Repositories.ITaskRepository;

@SpringBootTest
public class ITaskRepositoryTests {
    @Autowired
    ITaskRepository taskRepository;

    @Test
    void testGetById()
    {
        TaskBase task = taskRepository.GetById(7);
        Assertions.assertNotNull(task);
    }
}
