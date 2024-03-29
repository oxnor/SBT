package ru.shaa.sbt.shoppingmngr.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shaa.sbt.shoppingmngr.entities.ScheduleType;
import ru.shaa.sbt.shoppingmngr.entities.TaskBase;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskBaseRepositoryTests {
    @Autowired
    @Qualifier("TaskBase")
    TaskBaseRepository taskRepository;

    @Test
    void testLoadTaskDTO()
    {
        TaskBaseRepository.TaskDTO task = taskRepository.loadTaskDTO(7);
        Assertions.assertNotNull(task);

        task = taskRepository.loadTaskDTO(-1);
        Assertions.assertNull(task);
    }

    @Test
    void testSave()
    {
        TaskBase task = Mockito.mock(TaskBase.class);
        ArgumentCaptor<Integer> taskIDCaptor = ArgumentCaptor.forClass(Integer.class);
        when(task.getId()).thenReturn(null);
        when(task.getBegDate()).thenReturn(LocalDateTime.parse("2019-07-08T00:00"));
        when(task.getEndDate()).thenReturn(LocalDateTime.parse("2019-07-23T00:00"));
        when(task.getScheduleType()).thenReturn(new ScheduleType(1, "RunOnce", ""));

        taskRepository.save(task);

        Mockito.verify(task).setId(taskIDCaptor.capture());
        Integer taskId = taskIDCaptor.getValue();
        Assertions.assertNotNull(taskId, "У сохраненного задания отсутствует идентификатор");
    }

    @Test
    void testDelete()
    {
        TaskBase task = Mockito.mock(TaskBase.class);
        ArgumentCaptor<Integer> taskIDCaptor = ArgumentCaptor.forClass(Integer.class);
        when(task.getId()).thenReturn(null);
        when(task.getBegDate()).thenReturn(LocalDateTime.parse("2019-07-08T00:00"));
        when(task.getEndDate()).thenReturn(LocalDateTime.parse("2019-07-23T00:00"));
        when(task.getScheduleType()).thenReturn(new ScheduleType(1, "RunOnce", ""));

        taskRepository.save(task);

        Mockito.verify(task).setId(taskIDCaptor.capture());
        Integer taskId = taskIDCaptor.getValue();
        Assertions.assertNotNull(taskId);

        TaskBaseRepository.TaskDTO taskDTO = taskRepository.loadTaskDTO(taskId);
        Assertions.assertNotNull(taskDTO);

        when(task.getId()).thenReturn(taskId);
        taskRepository.delete(task);

        taskDTO = taskRepository.loadTaskDTO(taskId);
        Assertions.assertNull(taskDTO, "Удаление не произведено");
    }

    @Test
    void testUpdate()
    {
        TaskBase task = Mockito.mock(TaskBase.class);
        ArgumentCaptor<Integer> taskIDCaptor = ArgumentCaptor.forClass(Integer.class);
        when(task.getId()).thenReturn(null);
        when(task.getBegDate()).thenReturn(LocalDateTime.parse("2019-07-08T00:00"));
        when(task.getEndDate()).thenReturn(LocalDateTime.parse("2019-07-23T00:00"));
        when(task.getScheduleType()).thenReturn(new ScheduleType(1, "RunOnce", ""));

        taskRepository.save(task);

        Mockito.verify(task).setId(taskIDCaptor.capture());
        Integer taskId = taskIDCaptor.getValue();
        Assertions.assertNotNull(taskId);

        when(task.getId()).thenReturn(taskId);
        when(task.getBegDate()).thenReturn(LocalDateTime.parse("2019-08-08T00:00"));
        when(task.getEndDate()).thenReturn(LocalDateTime.parse("2019-08-23T00:00"));
        when(task.getScheduleType()).thenReturn(new ScheduleType(1, "Weekly", ""));

        taskRepository.save(task);

        TaskBaseRepository.TaskDTO taskDTO = taskRepository.loadTaskDTO(task.getId());

        Assertions.assertEquals(taskDTO.begDate, task.getBegDate(), "Не совпадает BegDate");
        Assertions.assertEquals(taskDTO.endDate, task.getEndDate(), "Не совпадает EndDate");
        Assertions.assertEquals(taskDTO.schType, task.getScheduleType().getCode(), "Не совпадает schType");

        taskRepository.delete(task);
    }
}
