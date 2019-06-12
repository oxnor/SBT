package ru.shaa.sbt.shoppingmngr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.entities.TaskRunOnce;
import ru.shaa.sbt.shoppingmngr.repositories.ITaskRepository;
import ru.shaa.sbt.shoppingmngr.repositories.TaskRunOnceRepository;

@SpringBootTest
public class TaskRunOnceRepositoryTests {
    @Autowired
    TaskRunOnceRepository taskRepository;

    @Test
    void testGetById()
    {
        TaskRunOnce task = null;
        try {
            task = taskRepository.getById(4);
        } catch (ITaskRepository.ScheduleTypeUnknownException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(task);
    }
}
