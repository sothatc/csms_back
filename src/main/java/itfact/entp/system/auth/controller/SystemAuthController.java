package itfact.entp.system.auth.controller;

//@RestController
//@RequestMapping("/system/auth")
//public class SystemAuthController {
//    @GetMapping("/hello")
//    public ResponseDTO<String> hello() {
//
//        JwtUtils authenticator = new JwtUtils();
//
//        /** 사용자 인증 */
//        boolean isAuthenticated = authenticator.authenticateUser("username", "password");
//
//        if (isAuthenticated) {
//            /** JWT 생성 */
//            String jwtToken = authenticator.generateJWT("username");
//            System.out.println("#####################################jwtToken = " +jwtToken );
//
//            /** JWT 검증 */
//            boolean isTokenValid = authenticator.verifyJWT(jwtToken);
//            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@isTokenValid = " + isTokenValid);
//
//            return ResponseUtil.SUCCESS(ResponseCode.SUCCESS_LOGIN);
//        }
//
//        return ResponseUtil.SUCCESS(ResponseCode.FAIL_AUTH);
//    }
//}
