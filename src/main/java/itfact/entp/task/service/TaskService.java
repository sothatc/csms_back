package itfact.entp.task.service;


import itfact.entp.task.dto.TaskAtchDTO;
import itfact.entp.task.dto.TaskDTO;
import itfact.entp.task.dto.TaskMembDTO;
import itfact.entp.task.dto.TaskScheduleDTO;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {


    List<TaskMembDTO> getTaskMembList();

    boolean setTaskInfo(TaskDTO taskDTO, List<MultipartFile> files);

    List<TaskDTO> getTaskList(TaskDTO taskDTO);

    TaskDTO getTaskInfo(int taskUnq);

    List<TaskAtchDTO> getTaskAtchList(TaskAtchDTO taskAtchDTO);

    boolean deleteTaskInfo(TaskDTO taskDTO);

    boolean insertTaskScheduleInfo(TaskScheduleDTO taskScheduleDTO);

    List<TaskScheduleDTO> getTaskScheduleList(String requestedDate);
}
