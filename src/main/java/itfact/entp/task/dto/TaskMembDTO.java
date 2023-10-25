package itfact.entp.task.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskMembDTO {
    private int    task_usr_unq;
    private String task_dept;
    private String task_usr_nm;
    private String task_usr_pst_nm;
    private String task_usr_tel;
    private String task_usr_email;
    private String reg_usr_id;
    private String reg_dtm;
    private String chg_usr_id;
    private String chg_dtm;
    private String del_yn;
}
