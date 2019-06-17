package ru.shaa.sbt.shoppingmngr.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.entities.ScheduleType;
import ru.shaa.sbt.shoppingmngr.entities.TaskWeekly;

import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
public class TaskWeeklyRepositoryTests {
    @Autowired
    TaskWeeklyRepository taskRepository;

    @Test
    void testGetById()
    {
        TaskWeekly task = null;
        task = taskRepository.getById(7);
        Assertions.assertNotNull(task);
    }

    @Test
    void testSave()
    {
        LocalTime[] weekDays = new LocalTime[7];
        weekDays[0] = LocalTime.parse("01:00");
        weekDays[1] = LocalTime.parse("01:01");
        weekDays[2] = LocalTime.parse("01:02");
        weekDays[3] = LocalTime.parse("01:03");
        weekDays[4] = LocalTime.parse("01:04");
        weekDays[5] = LocalTime.parse("01:05");
        weekDays[6] = LocalTime.parse("01:06");
        TaskWeekly taskN = new TaskWeekly(null
                                         , LocalDateTime.parse("2019-07-08T00:00")
                                         , LocalDateTime.parse("2019-07-23T00:00")
                                         , new ScheduleType(1, "Weekly", "")
                                         , weekDays
        );

        taskRepository.save(taskN);

        Assertions.assertNotNull(taskN.getId(), "У сохраненного задания отсутствует идентификатор");

        TaskWeekly taskR = taskRepository.getById(taskN.getId());
        Assertions.assertNotNull(taskN.getId(), "Задание отсутствует в базе");
        Assertions.assertEquals(taskN.getBegDate(), taskR.getBegDate(), "Не совпадает BegDate");
        Assertions.assertEquals(taskN.getEndDate(), taskR.getEndDate(), "Не совпадает EndDate");
        Assertions.assertEquals(taskN.getScheduleType().getCode(), taskR.getScheduleType().getCode(), "Не совпадает schType");

        for (int i = 0; i < taskN.getWeekDays().length; i++)
            Assertions.assertEquals(taskN.getWeekDays()[i], taskN.getWeekDays()[i], String.format("Не совпадает день: %d", i));

        taskN.getWeekDays()[3] = null;

        taskRepository.save(taskN);
        taskR = taskRepository.getById(taskN.getId());

        for (int i = 0; i < taskN.getWeekDays().length; i++)
            Assertions.assertEquals(taskN.getWeekDays()[i], taskR.getWeekDays()[i], String.format("Не совпадает день: %d", i));
    }

    @Test
    void testDelete()
    {
        LocalTime[] weekDays = new LocalTime[7];
        weekDays[0] = LocalTime.parse("01:00");
        weekDays[1] = LocalTime.parse("01:01");
        weekDays[2] = LocalTime.parse("01:02");
        weekDays[3] = LocalTime.parse("01:03");
        weekDays[4] = LocalTime.parse("01:04");
        weekDays[5] = LocalTime.parse("01:05");
        weekDays[6] = LocalTime.parse("01:06");
        TaskWeekly taskN = new TaskWeekly(null
                , LocalDateTime.parse("2019-07-08T00:00")
                , LocalDateTime.parse("2019-07-23T00:00")
                , new ScheduleType(1, "Weekly", "")
                , weekDays
        );

        taskRepository.save(taskN);

        Assertions.assertNotNull(taskN.getId(), "У сохраненного задания отсутствует идентификатор");

        taskRepository.delete(taskN);

        TaskWeekly taskR = taskRepository.getById(taskN.getId());
        Assertions.assertNull(taskR, "Удаление не произведено");
    }
}
