package itfact.entp.enterprise.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnterpriseCustDTO {
    private int    cust_unq;
    private int    entp_unq;
    private String entp_tp;
    private String memb_dept_nm;
    private String memb_nm;
    private String memb_pst_nm;
    private String memb_tel;
    private String memb_email;
    private String reg_usr_id;
    private String reg_dtm;
    private String chg_usr_id;
    private String chg_dtm;
    private String principal_tp;
}
