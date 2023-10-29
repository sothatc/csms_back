package itfact.entp.task.service;


import itfact.entp.task.dto.TaskDTO;
import itfact.entp.task.dto.TaskMembDTO;

import java.util.List;

public interface TaskService {


    List<TaskMembDTO> getTaskMembList();

    boolean setTaskInfo(TaskDTO taskDTO);
}