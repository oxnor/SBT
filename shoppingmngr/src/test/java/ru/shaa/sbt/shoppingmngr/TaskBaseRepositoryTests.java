package ru.shaa.sbt.shoppingmngr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.repositories.TaskBaseRepository;

@SpringBootTest
public class TaskBaseRepositoryTests {
    @Autowired
    @Qualifier("TaskBaseRepository")
    TaskBaseRepository taskRepository;

    @Test
    void testGetById()
    {
        TaskBaseRepository.TaskDTO task = taskRepository.loadTaskDTO(7);
        Assertions.assertNotNull(task);
    }
}
