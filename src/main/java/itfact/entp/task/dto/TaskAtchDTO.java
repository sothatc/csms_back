package itfact.entp.task.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import itfact.common.util.CommonConstant;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskAtchDTO {
    private int    atch_file_unq;
    private String entp_unq;
    private int    task_unq;
    private String atch_file_nm;
    private String atch_file_org_nm;
    private String atch_file_path;
    private long   atch_file_size;
    private String gnr_memo;
    private String del_yn;
    private String reg_usr_id;
    private String reg_dtm;
    private String chg_usr_id;
    private String chg_dtm;
    private String uploadFileDir = CommonConstant.DEFAULT_UPLOAD_ENTERPRISE_DIR;

    public TaskAtchDTO(String atch_file_nm, String atch_file_org_nm, String atch_file_path, long atch_file_size) {
        this.atch_file_nm     = atch_file_nm;
        this.atch_file_org_nm = atch_file_org_nm;
        this.atch_file_path   = atch_file_path;
        this.atch_file_size   = atch_file_size;
    }

    public TaskAtchDTO() {

    }
}
