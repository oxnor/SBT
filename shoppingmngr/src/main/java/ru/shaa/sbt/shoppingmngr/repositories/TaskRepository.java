package ru.shaa.sbt.shoppingmngr.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.shaa.sbt.shoppingmngr.entities.TaskBase;

@Component
public class TaskRepository implements ITaskRepository{
    private ApplicationContext ctx = null;

    @Autowired
    public TaskRepository(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    @Override
    public TaskBase getById(int id){
        TaskBaseRepository taskBaseRepository = (TaskBaseRepository)ctx.getBean("TaskBase", ITaskRepository.class);
        TaskBaseRepository.TaskDTO taskDTO = taskBaseRepository.loadTaskDTO(id);
        if (taskDTO == null) return null;
        ITaskRepository taskRepository = ctx.getBean(taskDTO.schType, ITaskRepository.class);
        return taskRepository.getById(id);
    }

    @Override
    public void save(TaskBase task) {
        ITaskRepository taskRepository = ctx.getBean(task.getScheduleType().getCode(), ITaskRepository.class);
        taskRepository.save(task);
    }

    @Override
    public void delete(TaskBase task) {
        ITaskRepository taskRepository = ctx.getBean(task.getScheduleType().getCode(), ITaskRepository.class);
        taskRepository.delete(task);
    }
}
