package itfact.entp.task.service;

import itfact.common.file.dto.FileDTO;
import itfact.common.file.service.FileService;
import itfact.common.util.CommonConstant;
import itfact.common.util.StringUtils;
import itfact.entp.enterprise.dto.EnterpriseAtchDTO;
import itfact.entp.task.dao.TaskDAO;
import itfact.entp.task.dto.TaskAtchDTO;
import itfact.entp.task.dto.TaskDTO;
import itfact.entp.task.dto.TaskMembDTO;
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
    public List<TaskMembDTO> getTaskMembList() {
        return taskDAO.selectTaskMembList();
    }

    public boolean setTaskInfo(TaskDTO taskDTO, List<MultipartFile> files) {
        try {
            TaskAtchDTO taskAtchDTO;

            if (StringUtils.equals(taskDTO.getFlag(), "I")) {

                int taskNo = taskDAO.insertTaskInfo(taskDTO);

                List<FileDTO> attachFileList = fileService.saveFile(files, CommonConstant.DEFAULT_UPLOAD_ENTERPRISE_DIR);

                for (FileDTO item : attachFileList) {
                    taskAtchDTO = new TaskAtchDTO();
                    taskAtchDTO.setEntp_unq         (taskNo                 );
                    taskAtchDTO.setReg_usr_id       (taskDTO.getReg_usr_id()   );
                    taskAtchDTO.setChg_usr_id       (taskDTO.getChg_usr_id()   );
                    taskAtchDTO.setAtch_file_nm     (item.getAtch_file_nm()    );
                    taskAtchDTO.setAtch_file_org_nm (item.getAtch_file_org_nm());
                    taskAtchDTO.setAtch_file_path   (item.getAtch_file_path()  );
                    taskAtchDTO.setAtch_file_size   (item.getAtch_file_size()  );

                    taskDAO.insertTaskAtchInfo(taskAtchDTO);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
