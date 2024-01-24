package itfact.main.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import itfact.common.paging.dto.PagingDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchUserDTO {
    private String user_id;
    private String use_yn;
    private String user_nm;
    private String conn_ip;

    private PagingDTO paging;
}
