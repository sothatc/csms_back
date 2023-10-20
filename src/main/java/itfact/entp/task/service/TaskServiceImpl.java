package itfact.entp.task.service;

import itfact.entp.task.dao.TaskDAO;
import itfact.entp.task.dto.TaskMembDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskDAO taskDAO;
    public List<TaskMembDTO> getTaskMembList() {
        return taskDAO.selectTaskMembList();
    }
}
