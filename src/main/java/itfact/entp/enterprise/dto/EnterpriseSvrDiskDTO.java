package itfact.entp.enterprise.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnterpriseSvrDiskDTO {

    private int    disk_partition_unq;
    private String entp_unq;
    private int    svr_unq;
    private String partition_path;
    private int    used_disk_sz;
    private int    total_disk_sz;

    List<Integer> diskUnqList;
}
