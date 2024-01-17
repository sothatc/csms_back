package itfact.main.user.service;

import itfact.main.user.dto.UserDTO;
import itfact.main.user.dto.UserTryLoginCntDTO;

public interface UserService {
    int getUserInfoCnt(UserDTO userDTO);

    UserDTO getLogin(UserDTO userDTO);

    int setUserTryLoginCnt(UserTryLoginCntDTO userTryLoginCntDTO);

    int setLoginInfo(UserDTO userDTO);

    UserDTO selectLoginInfo(UserDTO userDTO);

    boolean setUserInfo(UserDTO userDTO);
}
