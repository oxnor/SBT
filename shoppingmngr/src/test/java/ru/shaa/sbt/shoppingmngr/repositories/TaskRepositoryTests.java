package ru.shaa.sbt.shoppingmngr.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.entities.ScheduleType;
import ru.shaa.sbt.shoppingmngr.entities.TaskBase;
import ru.shaa.sbt.shoppingmngr.entities.TaskRunOnce;

import java.time.LocalDateTime;

@SpringBootTest
public class TaskRepositoryTests {
    @Autowired
    TaskRepository taskRepository;

    @Test
    void testGetById()
    {
        TaskBase task = null;
        task = taskRepository.getById(4);
        Assertions.assertNotNull(task, "У сохраненного задания отсутствует идентификатор");
        task = taskRepository.getById(-1);
        Assertions.assertNull(task, "Для несуществующего задания должно возвращаться null");
    }

    @Test
    void testSave()
    {
        TaskRunOnce taskN = new TaskRunOnce(null, LocalDateTime.parse("2019-06-08T00:00"), LocalDateTime.parse("2019-07-23T00:00"), new ScheduleType(1, "RunOnce", ""), LocalDateTime.parse("2019-07-09T14:15"));
        taskRepository.save(taskN);

        Assertions.assertNotNull(taskN.getId(), "У сохраненного задания отсутствует идентификатор");

        TaskBase taskBR = taskRepository.getById(taskN.getId());
        Assertions.assertNotNull(taskBR, "Задание отсутствует в базе");
        Assertions.assertEquals(taskBR.getClass().getName(), taskN.getClass().getName(), "Тип сохранённого и полученного задания не совпадают");

        TaskRunOnce taskR = (TaskRunOnce)taskBR;
        Assertions.assertEquals(taskN.getBegDate(), taskR.getBegDate(), "Не совпадает BegDate");
        Assertions.assertEquals(taskN.getEndDate(), taskR.getEndDate(), "Не совпадает EndDate");
        Assertions.assertEquals(taskN.getScheduleType().getCode(), taskR.getScheduleType().getCode(), "Не совпадает schType");
        Assertions.assertEquals(taskN.getRunDateTime(), taskR.getRunDateTime(), "Не совпадает время запуска RunDateTime");
    }
    @Test
    void testDelete()
    {
        TaskRunOnce taskN = new TaskRunOnce(null, LocalDateTime.parse("2019-07-08T00:00"), LocalDateTime.parse("2019-07-23T00:00"), new ScheduleType(1, "RunOnce", ""), LocalDateTime.parse("2019-07-08T14:00"));

        taskRepository.save(taskN);

        Assertions.assertNotNull(taskN.getId());

        taskRepository.delete(taskN);

        TaskBase taskR = taskRepository.getById(taskN.getId());
        Assertions.assertNull(taskR, "Удаление не произведено");
    }
}
