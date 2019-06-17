package ru.shaa.sbt.shoppingmngr.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.entities.ScheduleType;
import ru.shaa.sbt.shoppingmngr.entities.TaskRunOnce;

import java.time.LocalDateTime;

@SpringBootTest
public class TaskRunOnceRepositoryTests {
    @Autowired
    TaskRunOnceRepository taskRepository;

    @Test
    void testGetById()
    {
        TaskRunOnce task = null;
        task = taskRepository.getById(4);
        Assertions.assertNotNull(task);
    }

    @Test
    void testSave()
    {
        TaskRunOnce taskN = new TaskRunOnce(null, LocalDateTime.parse("2019-07-08T00:00"), LocalDateTime.parse("2019-07-23T00:00"), new ScheduleType(1, "RunOnce", ""), LocalDateTime.parse("2019-07-08T14:00"));

        taskRepository.save(taskN);

        Assertions.assertNotNull(taskN.getId(), "У сохраненного задания отсутствует идентификатор");

        TaskRunOnce taskR = taskRepository.getById(taskN.getId());
        Assertions.assertNotNull(taskN.getId(), "Задание отсутствует в базе");
        Assertions.assertEquals(taskN.getBegDate(), taskR.getBegDate(), "Не совпадает BegDate");
        Assertions.assertEquals(taskN.getEndDate(), taskR.getEndDate(), "Не совпадает EndDate");
        Assertions.assertEquals(taskN.getScheduleType().getCode(), taskR.getScheduleType().getCode(), "Не совпадает schType");
        Assertions.assertEquals(taskN.getRunDateTime(), taskR.getRunDateTime(), "RunDateTime");
    }

    @Test
    void testDelete()
    {
        TaskRunOnce taskN = new TaskRunOnce(null, LocalDateTime.parse("2019-07-08T00:00"), LocalDateTime.parse("2019-07-23T00:00"), new ScheduleType(1, "RunOnce", ""), LocalDateTime.parse("2019-07-08T14:00"));

        taskRepository.save(taskN);

        Assertions.assertNotNull(taskN.getId());

        taskRepository.delete(taskN);

        TaskRunOnce taskR = taskRepository.getById(taskN.getId());
        Assertions.assertNull(taskR, "Удаление не произведено");
    }

}
