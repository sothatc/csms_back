package itfact.entp.task.service;


import itfact.entp.task.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TaskService {


    List<TaskMembDTO> getTaskMembList();

    boolean setTaskInfo(TaskDTO taskDTO, List<MultipartFile> files);

    List<TaskDTO> getTaskList(SearchTaskDTO reqDTO);

    TaskDTO getTaskInfo(int taskUnq);

    List<TaskAtchDTO> getTaskAtchList(TaskAtchDTO taskAtchDTO);

    boolean deleteTaskInfo(TaskDTO taskDTO);

    boolean insertTaskScheduleInfo(TaskScheduleDTO taskScheduleDTO);

    List<TaskScheduleDTO> getTaskScheduleList(String requestedDate);
}
