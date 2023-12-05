package itfact.common.paging.dto;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class PagingDTO {

    /** 한 페이지당 리스트 수 */
    private int limit;

    /** 현재 페이지 */
    private int offset;

    /** 총 리스트 수 */
    private int list_cnt;

    /** 총 페이지 수 */
    private int page_cnt;
}
