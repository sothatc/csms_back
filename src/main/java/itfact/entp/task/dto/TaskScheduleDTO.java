package itfact.entp.task.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskScheduleDTO {

    private int sch_unq;
    private String sch_title;
    private String sch_contents;
    private String sch_st_dt;
    private String sch_ed_dt;
    private String sch_st_tm;
    private String sch_ed_tm;
    private String reg_dtm;
    private String reg_usr_id;
    private String chg_dtm;
    private String chg_usr_id;

}
