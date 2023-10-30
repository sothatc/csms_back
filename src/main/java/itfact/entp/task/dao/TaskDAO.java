package itfact.entp.task.dao;


import itfact.entp.task.dto.TaskAtchDTO;
import itfact.entp.task.dto.TaskDTO;
import itfact.entp.task.dto.TaskMembDTO;
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
}
