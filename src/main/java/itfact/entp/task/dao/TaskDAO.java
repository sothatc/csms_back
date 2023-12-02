package itfact.entp.task.dao;


import itfact.entp.task.dto.TaskAtchDTO;
import itfact.entp.task.dto.TaskDTO;
import itfact.entp.task.dto.TaskMembDTO;
import itfact.entp.task.dto.TaskScheduleDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDAO {
    private SqlSessionTemplate sqlSessionTemplate;

    private static final String NAMESPACE = "TaskDAO.";

    public TaskDAO(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }


    public List<TaskMembDTO> selectTaskMembList() {
        return sqlSessionTemplate.selectList(NAMESPACE + "selectTaskMembList");
    }

    public int insertTaskInfo(TaskDTO taskDTO) {
        sqlSessionTemplate.insert(NAMESPACE + "insertTaskInfo", taskDTO);
        return taskDTO.getTask_unq();
    }

    public int insertTaskAtchInfo(TaskAtchDTO taskAtchDTO) {
        sqlSessionTemplate.insert(NAMESPACE + "insertTaskAtchInfo", taskAtchDTO);
        return taskAtchDTO.getAtch_file_unq();
    }

    public List<TaskDTO> selectTaskList(TaskDTO taskDTO) {
        return sqlSessionTemplate.selectList(NAMESPACE + "selectTaskList", taskDTO);
    }

    public TaskDTO selectTaskInfo(int task_unq) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "selectTaskInfo", task_unq);
    }

    public List<TaskAtchDTO> getTaskAtchList(TaskAtchDTO taskAtchDTO) {
        return sqlSessionTemplate.selectList(NAMESPACE + "getTaskAtchList", taskAtchDTO);
    }

    public void updateTaskInfo(TaskDTO taskDTO) {
        sqlSessionTemplate.update(NAMESPACE + "updateTaskInfo", taskDTO);
    }

    public void deleteTaskAtchFileInfo(TaskAtchDTO taskAtchDTO) {
        sqlSessionTemplate.update(NAMESPACE + "deleteTaskAtchFileInfo", taskAtchDTO);
    }

    public void deleteTaskInfo(TaskDTO taskDTO) {
        sqlSessionTemplate.update(NAMESPACE + "deleteTaskInfo", taskDTO);
    }

    public void insertTaskScheduleInfo(TaskScheduleDTO taskScheduleDTO) {
        sqlSessionTemplate.insert(NAMESPACE + "insertTaskScheduleInfo", taskScheduleDTO);
    }
}
