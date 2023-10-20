package itfact.entp.task.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskMembDTO {
    private int    memb_unq;
    private String memb_dept_nm;
    private String memb_nm;
    private String memb_pst_nm;
    private String memb_tel;
    private String memb_email;
    private String reg_usr_id;
    private String reg_dtm;
    private String chg_usr_id;
    private String chg_dtm;
    private String del_yn;
}
