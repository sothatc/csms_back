package itfact.main.user.service;

import itfact.main.user.dto.SearchUserDTO;
import itfact.main.user.dto.UserDTO;
import itfact.main.user.dto.UserTryLoginCntDTO;

import java.util.List;

public interface UserService {
    int getUserInfoCnt(UserDTO userDTO);

    UserDTO getLoginInfo(UserDTO userDTO);

    int setUserTryLoginCnt(UserTryLoginCntDTO userTryLoginCntDTO);

    int setLoginInfo(UserDTO userDTO);

    UserDTO selectLoginInfo(UserDTO userDTO);

    boolean setUserInfo(UserDTO userDTO);

    List<UserDTO> getUserList(SearchUserDTO reqDTO);

}
