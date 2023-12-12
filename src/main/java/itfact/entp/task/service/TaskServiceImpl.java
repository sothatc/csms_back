package itfact.entp.task.service;

import itfact.common.file.dto.FileDTO;
import itfact.common.file.service.FileService;
import itfact.common.util.CommonConstant;
import itfact.common.util.StringUtils;
import itfact.entp.task.dao.TaskDAO;
import itfact.entp.task.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private FileService fileService;

    public List<TaskDTO> getTaskList(SearchTaskDTO reqDTO) {
        return taskDAO.selectTaskList(reqDTO);
    }

    public List<TaskMembDTO> getTaskMembList() {
        return taskDAO.selectTaskMembList();
    }

    public List<TaskScheduleDTO> getTaskScheduleList(String requestedDate) {
        return taskDAO.selectTaskScheduleList(requestedDate);
    }

    public boolean setTaskInfo(TaskDTO taskDTO, List<MultipartFile> files) {
        try {
            TaskAtchDTO taskAtchDTO;

            if (StringUtils.equals(taskDTO.getFlag(), "I")) {

                int taskNo = taskDAO.insertTaskInfo(taskDTO);

                List<FileDTO> attachFileList = fileService.saveFile(files, CommonConstant.DEFAULT_UPLOAD_TASK_DIR);

                for (FileDTO item : attachFileList) {
                    taskAtchDTO = new TaskAtchDTO();
                    taskAtchDTO.setTask_unq         (taskNo                    );
                    taskAtchDTO.setEntp_unq         (taskDTO.getEntp_unq()     );
                    taskAtchDTO.setReg_usr_id       (taskDTO.getReg_usr_id()   );
                    taskAtchDTO.setChg_usr_id       (taskDTO.getChg_usr_id()   );
                    taskAtchDTO.setAtch_file_nm     (item.getAtch_file_nm()    );
                    taskAtchDTO.setAtch_file_org_nm (item.getAtch_file_org_nm());
                    taskAtchDTO.setAtch_file_path   (item.getAtch_file_path()  );
                    taskAtchDTO.setAtch_file_size   (item.getAtch_file_size()  );

                    taskDAO.insertTaskAtchInfo(taskAtchDTO);
                }
            } else if (StringUtils.equals(taskDTO.getFlag(), "U")) {
                taskDAO.updateTaskInfo(taskDTO);

                if (StringUtils.isNotEmpty(taskDTO.getDelAtchFileNum())) {
                    String delAtchFileNumList[] = taskDTO.getDelAtchFileNum().split(",");

                    for (int i = 0; i < delAtchFileNumList.length; i++) {
                        taskAtchDTO = new TaskAtchDTO();
                        taskAtchDTO.setTask_unq(taskDTO.getTask_unq());
                        taskAtchDTO.setAtch_file_unq(Integer.parseInt(delAtchFileNumList[i].trim()));
                        taskAtchDTO.setChg_usr_id(taskDTO.getChg_usr_id());

                        taskDAO.deleteTaskAtchFileInfo(taskAtchDTO);
                    }
                }

                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public TaskDTO getTaskInfo(int task_unq) {
        return taskDAO.selectTaskInfo(task_unq);
    }

    public List<TaskAtchDTO> getTaskAtchList(TaskAtchDTO taskAtchDTO) {
        return taskDAO.getTaskAtchList(taskAtchDTO);
    }

    public boolean deleteTaskInfo(TaskDTO taskDTO) {
        try {
            taskDAO.deleteTaskInfo(taskDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean setTaskScheduleInfo(TaskScheduleDTO taskScheduleDTO) {
        try {

            if (StringUtils.equals(taskScheduleDTO.getFlag(), "I")) { //신규저장
                taskDAO.insertTaskScheduleInfo(taskScheduleDTO);
            } else if (StringUtils.equals(taskScheduleDTO.getFlag(), "U")) {
                taskDAO.updateTaskScheduleInfo(taskScheduleDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
