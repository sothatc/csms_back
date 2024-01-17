package itfact.main.user.dao;


import itfact.main.user.dto.UserDTO;
import itfact.main.user.dto.UserTryLoginCntDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
    private SqlSessionTemplate sqlSessionTemplate;

    public UserDAO(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    private static final String NAMESPACE = "UserDAO.";

    public int selectUserInfoCnt(UserDTO userDTO) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "selectUserInfoCnt", userDTO);
    }

    public UserDTO selectUserById(UserDTO userDTO) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "selectUserById", userDTO);
    }

    public int insertUserTryLoginCnt(UserTryLoginCntDTO userTryLoginCntDTO) {
        sqlSessionTemplate.insert(NAMESPACE + "insertUserTryLoginCnt", userTryLoginCntDTO);
        return userTryLoginCntDTO.getPwd_err_cnt();
    }

    public int insertLoginInfo(UserDTO userDTO) {
        return sqlSessionTemplate.insert(NAMESPACE + "insertLoginInfo", userDTO);
    }

    public UserDTO selectLoginInfo(UserDTO userDTO) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "selectLoginInfo", userDTO);
    }

    public void insertUserInfo(UserDTO userDTO) {
        sqlSessionTemplate.insert(NAMESPACE + "insertUserInfo", userDTO);
    }
}
