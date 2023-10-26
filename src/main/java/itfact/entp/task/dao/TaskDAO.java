package itfact.entp.task.dao;


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

    public void insertTaskInfo(TaskDTO taskDTO) {
        sqlSessionTemplate.insert(NAMESPACE + "insertTaskInfo", taskDTO);
    }
}
