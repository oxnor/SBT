package ru.shaa.sbt.shoppingmngr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.entities.ScheduleType;
import ru.shaa.sbt.shoppingmngr.entities.TaskRunOnce;
import ru.shaa.sbt.shoppingmngr.repositories.TaskBaseRepository;

import java.time.LocalDateTime;

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

    @Test
    void testSave()
    {
        TaskRunOnce task = new TaskRunOnce(null, LocalDateTime.parse("2019-07-08T00:00"), LocalDateTime.parse("2019-07-23T00:00"), new ScheduleType(1, "RUN_ONCE", ""), LocalDateTime.parse("2019-07-08T14:40"));
        taskRepository.save(task);
        Assertions.assertNotNull(task.getId(), "У сохраненного задания отсутствует идентификатор");
    }

    @Test
    void testUpdate()
    {
        TaskRunOnce task = new TaskRunOnce(null, LocalDateTime.parse("2019-07-08T00:00"), LocalDateTime.parse("2019-07-23T00:00"), new ScheduleType(1, "RUN_ONCE", ""), LocalDateTime.parse("2019-07-08T14:40"));
        taskRepository.save(task);
        Assertions.assertNotNull(task.getId());
        task.setBegDate(LocalDateTime.parse("2019-08-08T00:00"));
        task.setEndDate(LocalDateTime.parse("2019-08-23T00:00"));
        task.setScheduleType(new ScheduleType(1, "RUN_ONCE", ""));
        taskRepository.save(task);
    }
}
