package itfact.entp.task.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDTO {

    private int    task_unq;
    private String entp_unq;
    private int    cust_unq;
    private int    sch_unq;
    private String task_usr_unq;
    private String entp_nm;
    private String task_dept;
    private String task_usr_nm;
    private String task_tp;
    private String task_job_tp;
    private String svc_efc;
    private String task_st_dt;
    private String task_st_tm;
    private String task_ed_dt;
    private String task_ed_tm;
    private String task_memo;
    private String reg_usr_id;
    private String reg_dtm;
    private String chg_usr_id;
    private String chg_dtm;
    private int    stt_month_total_cnt;
    private int    stt_month_s_cnt;
    private int    stt_month_f_cnt;
    private int    stt_day_total_cnt;
    private int    stt_day_s_cnt;
    private int    stt_day_f_cnt;
    private String del_yn;

    private String delAtchFileNum;

    private String flag;

    private boolean atch_file_bool;

//    private int    limit_num;
}
