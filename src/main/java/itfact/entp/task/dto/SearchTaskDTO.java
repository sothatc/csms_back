package itfact.entp.task.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import itfact.common.paging.dto.PagingDTO;
import lombok.Data;


/**
 * 작업내역 조건 검색만을 위한 DTO
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchTaskDTO {

    private int    task_unq;
    private String task_tp;
    private String entp_nm;
    private String searchDateFrom;
    private String searchDateTo;

    private int    limit_num;

    private PagingDTO paging;

}
