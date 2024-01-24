package itfact.main.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserTryLoginCntDTO {
    private String user_id;
    private int    pwd_err_cnt;
    private String reg_usr_id;
    private String reg_dtm;
    private String chg_usr_id;
    private String chg_dtm;
    private String error_code;
}
