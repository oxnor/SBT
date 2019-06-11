package ru.shaa.sbt.shoppingmngr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.Repositories.TaskBaseRepository;

@SpringBootTest
public class TaskBaseRepositoryTests {
    @Autowired
    TaskBaseRepository taskRepository;

    @Test
    void testGetById()
    {
        TaskBaseRepository.TaskDTO task = taskRepository.LoadTaskDTO(7);
        Assertions.assertNotNull(task);
    }
}
