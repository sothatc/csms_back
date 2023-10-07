package itfact.entp.enterprise.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnterpriseDTO {
    private int    entp_unq;
    private String entp_tp;
    private String entp_nm;
    private String svc_tp;
    private String gnr_memo;
    private int    stt_month_cnt;
    private int    stt_day_cnt;
    private String reg_usr_id;
    private String reg_dtm;
    private String chg_usr_id;
    private String chg_dtm;
    private String sup_nm;
    private String del_yn;

    private String memb_nm;
    private String memb_tel;
    private String memb_email;
    private String memb_pst_nm;
    private String memb_dept_nm;
    private String principal_tp;

    private String file_nm;

    private String delAtchFileNum;

    //flag : I 신규저장 , U 수정 , D 삭제 , S 조회수
    private String flag;
    private List<EnterpriseDTO> enterpriseAtchList;
    private boolean atch_file_bool;
//    private int atch_file_unq; //파일이 존재하는지만 조회 ( limit 1 )

}
