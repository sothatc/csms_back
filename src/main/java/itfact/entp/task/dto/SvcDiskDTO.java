package itfact.entp.task.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SvcDiskDTO {
    private int    disk_partiton_unq;
    private String partition_path;
    private int    used_disk_size;
    private int    total_disk_size;
}
