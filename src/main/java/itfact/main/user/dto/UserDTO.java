package itfact.main.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import itfact.common.util.AES256;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private String user_id;     // 사용자id
    private String user_pwd;    // 사용자 pw
    private String user_pwd_crypto; // 사용자 pw (암호화)
    private String user_nm;     // 사용자 이름
    private String use_yn;      // id 사용 여부
    private String cell_tel_no; // 휴대전화번호
    private String Email;       // 이메일
    private String org_cd;      // 상위 부서조직 코드
    private String org_ch_cd;   // 하위 부서조직 코드
    private String jnco_dt;     // 입사일
    private String retir_dt;    // 퇴사일
    private String conn_ip;   // 클라이언트 ip
    private String pwd_ini_yn_cd; // 비밀번호 초기화 여부 코드
    private String lgn_yn_cd;   // 로그인 여부 코드
    private String auth_cd;
    private String memo;        // 기타 메모
    private String reg_id;
    private String reg_dtm;
    private String chg_id;
    private String chg_dtm;
    private String access_token;
    private String refresh_token;

    private String log_in_dtm;   // 로그인 시간
    private String log_out_dtm;  // 로그아웃 시간
    private String ps_sta_cd;    // 로그아웃 처리 상태 코드

    private String flag;


    public String getUser_pwd() {
        try {
            return AES256.decrypt(user_pwd_crypto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setUser_pwd(String user_pwd) throws Exception {
        this.user_pwd_crypto = AES256.encrypt(user_pwd);
    }

}
