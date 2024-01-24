package itfact.main.user.controller;


import itfact.common.paging.service.PagingService;
import itfact.common.response.dto.ResponseDTO;
import itfact.common.response.enums.ResponseCode;
import itfact.common.util.IPUtils;
import itfact.common.util.ResponseUtil;
import itfact.common.util.StringUtils;
import itfact.main.auth.service.SecurityService;
import itfact.main.user.dto.SearchUserDTO;
import itfact.main.user.dto.UserDTO;
import itfact.main.user.dto.UserTryLoginCntDTO;
import itfact.main.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private final SecurityService securityService;

    @Autowired
    private PagingService pagingService;


//    @PostMapping("/login")
//    public ResponseDTO login(@RequestBody UserDTO userDTO, HttpServletRequest request) throws Exception {
//
//        UserTryLoginCntDTO userTryLoginCntDTO = new UserTryLoginCntDTO();
//
//        UserDTO selectedUserDTO = new UserDTO();
//
//        String ipAddr = IPUtils.getRemoteIP(request);
//
//        /** 사용자가 존재하는지 ID로만 확인 */
//        int userCnt = userService.getUserInfoCnt(userDTO);
//        if (userCnt != 0) {
//            /** ID/PW로 확인된 사용자 정보 */
//            UserDTO user = userService.getLogin(userDTO);
//            if (user == null) {
//                /** 로그인 실패시 */
//                userTryLoginCntDTO.setUser_id(userDTO.getUser_id());
//
//                /** ToDo: 비밀번호 입력오류횟수 제한, 아이디 사용여부 체크, 중복 로그인 등 */
//                int pwd_err_cnt = userService.setUserTryLoginCnt(userTryLoginCntDTO);
//
//                userTryLoginCntDTO.setError_code("PASSWORD_WRONG");
//
//                return ResponseUtil.FAIL(ResponseCode.PASSWORD_WRONG, userTryLoginCntDTO);
//            } else {
//                /** 로그인 성공시 */
////                userDTO.setConn_ip(ipAddr);
//                user.setConn_ip(ipAddr);
//                /** ToDo:중복 ip 로그인 체크 , 로그인 성공시 로그인 실패했던 카운터를 0으로 초기화 */
//
//                //토큰 생성
////                userDTO.setToken("");
//                user.setToken("");
////                userService.setLoginInfo(userDTO);
//                userService.setLoginInfo(user);
//
////                selectedUserDTO = userService.selectLoginInfo(userDTO);
//                selectedUserDTO = userService.selectLoginInfo(user);
//                selectedUserDTO.setToken(user.getToken());
//                selectedUserDTO.setConn_ip(ipAddr);
//
//                /** 세션 설정 */
////                if (session != null) {
////                    //session.setMaxInactiveInterval(60 * 60);
////                    session.setMaxInactiveInterval(loginSessionTimeout); //로그인 성공시 세션 타임아웃 설정
////                    session.setAttribute(SessionConst.USRID, usrDTO.getUsr_id());// userid
////                    session.setAttribute(SessionConst.AUTHCD, usrDTO.getAuth_cd());// 권한 코드
////                    session.setAttribute(SessionConst.TOKEN, usrDTO.getToken());// token
////                    session.setAttribute(SessionConst.CONNIP, usrDTO.getConn_ip());// ip
////                }
//
//                return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_LOGIN, selectedUserDTO);
//            }
//        } else {
//            userTryLoginCntDTO.setError_code("EXISTING_ERROR");
//            return ResponseUtil.FAIL(ResponseCode.FAIL_NOT_EXIST_USER, userTryLoginCntDTO);
//        }
//    }
    @PostMapping("/login")
    public ResponseDTO login(@RequestBody UserDTO userDTO, HttpServletRequest request) throws Exception {

        UserTryLoginCntDTO userTryLoginCntDTO = new UserTryLoginCntDTO();

        UserDTO selectedUserDTO = new UserDTO();

        String ipAddr = IPUtils.getRemoteIP(request);

        /** 사용자가 존재하는지 ID로만 확인 */
        int userCnt = userService.getUserInfoCnt(userDTO);
        if (userCnt != 0) {
            /** ID/PW로 확인된 사용자 정보 */
            UserDTO user = userService.getLoginInfo(userDTO);
            if (user == null) {
                /** 로그인 실패시 */
                userTryLoginCntDTO.setUser_id(userDTO.getUser_id());

                /** ToDo: 비밀번호 입력오류횟수 제한, 아이디 사용여부 체크, 중복 로그인 등 */
                int pwd_err_cnt = userService.setUserTryLoginCnt(userTryLoginCntDTO);

                userTryLoginCntDTO.setError_code("PASSWORD_WRONG");

                return ResponseUtil.FAIL(ResponseCode.PASSWORD_WRONG, userTryLoginCntDTO);
            } else {
                /** 로그인 성공시 */
                // ToDo: 토큰 생성: AccessToken 말고 RefreshToken을 디비에 저장???
                user.setToken("");
                user.setConn_ip(ipAddr);
                /** ToDo:중복 ip 로그인 체크 , 로그인 성공시 로그인 실패했던 카운터를 0으로 초기화 */
                userService.setLoginInfo(user);

                selectedUserDTO = userService.selectLoginInfo(user);
                selectedUserDTO.setToken(securityService.jwtLogin(user.getUser_id(), user.getUser_pwd()));
                selectedUserDTO.setConn_ip(ipAddr);

                /** 세션 설정 */
    //                if (session != null) {
    //                    //session.setMaxInactiveInterval(60 * 60);
    //                    session.setMaxInactiveInterval(loginSessionTimeout); //로그인 성공시 세션 타임아웃 설정
    //                    session.setAttribute(SessionConst.USRID, usrDTO.getUsr_id());// userid
    //                    session.setAttribute(SessionConst.AUTHCD, usrDTO.getAuth_cd());// 권한 코드
    //                    session.setAttribute(SessionConst.TOKEN, usrDTO.getToken());// token
    //                    session.setAttribute(SessionConst.CONNIP, usrDTO.getConn_ip());// ip
    //                }

                return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_LOGIN, selectedUserDTO);
            }
        } else {
            userTryLoginCntDTO.setError_code("EXISTING_ERROR");
            return ResponseUtil.FAIL(ResponseCode.FAIL_NOT_EXIST_USER, userTryLoginCntDTO);
        }
    }

    @PostMapping("/setUserInfo")
    public ResponseDTO setUserInfo(@RequestBody UserDTO userDTO) {
        /** 이미 등록된 ID인지 확인 */
        int userCnt = userService.getUserInfoCnt(userDTO);

        if (userCnt != 0) {
            return ResponseUtil.FAIL(ResponseCode.FAIL_SAVE);

        } else {
            boolean result = userService.setUserInfo(userDTO);

            if (result) {
                if (StringUtils.equals(userDTO.getFlag(), "I") || StringUtils.equals(userDTO.getFlag(), "S")) {
                    return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SAVE);

                } else if (StringUtils.equals(userDTO.getFlag(), "U")) {
                    return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_UPDATE);

                } else if (StringUtils.equals(userDTO.getFlag(), "D")) {
                    return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_DELETE);
                }
            }
            return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SAVE);
        }
    }

    @PostMapping("/getUserInfo")
    public ResponseDTO getUserInfo(@RequestBody UserDTO userDTO) {
        int userCnt = userService.getUserInfoCnt(userDTO);

        if (userCnt != 0) {
            return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SEARCH);
        } else {
            return ResponseUtil.FAIL(ResponseCode.FAIL_SEARCH);
        }

    }

    @PostMapping("/getUserList")
    public ResponseDTO getUserList(@RequestBody SearchUserDTO reqDTO) {
        List<UserDTO> userList = userService.getUserList(reqDTO);

        HashMap<String, Object> userListMap = new HashMap<>();
        userListMap.put("userList", userList);
        userListMap.put("paging", pagingService.getPagingInfo(reqDTO.getPaging()));

        return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_SEARCH, userListMap);
    }
}
