package itfact.entp.task.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDTO {

    private int    task_unq;
    private int    entp_unq;
    private int    cust_unq;
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
    private String reg_dtm;
    private String del_yn;
    private int stt_month_total_cnt;
    private int stt_month_s_cnt;
    private int stt_month_f_cnt;
    private int stt_day_total_cnt;
    private int stt_day_s_cnt;
    private int stt_day_f_cnt;

    private String flag;
}
