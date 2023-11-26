package itfact.entp.task.service;


import itfact.entp.task.dto.TaskAtchDTO;
import itfact.entp.task.dto.TaskDTO;
import itfact.entp.task.dto.TaskMembDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TaskService {


    List<TaskMembDTO> getTaskMembList();

    boolean setTaskInfo(TaskDTO taskDTO, List<MultipartFile> files);

    List<TaskDTO> getTaskList(TaskDTO taskDTO);

    TaskDTO getTaskInfo(int taskUnq);

    List<TaskAtchDTO> getTaskAtchList(TaskAtchDTO taskAtchDTO);
}
