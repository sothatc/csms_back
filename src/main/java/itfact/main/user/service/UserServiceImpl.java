package itfact.main.user.service;


import itfact.main.user.dao.UserDAO;
import itfact.main.user.dto.UserDTO;
import itfact.main.user.dto.UserTryLoginCntDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public int getUserInfoCnt(UserDTO userDTO) {
        return userDAO.selectUserInfoCnt(userDTO);
    }

    @Override
    public UserDTO getLogin(UserDTO userDTO) {
        return userDAO.selectUserById(userDTO);
    }

    @Override
    public int setUserTryLoginCnt(UserTryLoginCntDTO userTryLoginCntDTO) {
        return userDAO.insertUserTryLoginCnt(userTryLoginCntDTO);
    }

    @Override
    public int setLoginInfo(UserDTO userDTO) {
        return userDAO.insertLoginInfo(userDTO);
    }

    @Override
    public UserDTO selectLoginInfo(UserDTO userDTO) {
        return userDAO.selectLoginInfo(userDTO);
    }

    @Override
    public boolean setUserInfo(UserDTO userDTO) {
        try {
            userDAO.insertUserInfo(userDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
