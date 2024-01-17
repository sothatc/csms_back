package itfact.main.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserTryLoginCntDTO {
    private String user_id;
    private int    pwd_err_cnt;
    private String error_code;
}
