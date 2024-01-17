package itfact.entp.enterprise.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnterpriseSvrDTO {
    private int    svr_unq;
    private String entp_unq;      // 업체 PK
    private String svr_hst;       // 서버 호스트
    private String svr_ip;        // 서버ip
    private String svr_cont;      // 서버용도
    private String use_flag;      // 사용여부
    private String resc_use_flag; // 리소스 사용여부
    private String trn_use_flag;  // 학습 사용여부
    private String os_vers;       // 운영체제 버전
    private String kern_vers;     // 커널 버전
    private int    cpu_cnt;       // cpu 코어 수
    private int    used_mem_sz;   // memory 용량
    private int    total_mem_sz;  // memory 용량
    private String gpu_model;     // gpu 모델
    private String base_path;     // 기본 경로
    private String log_path;      // 로그 경로
    private String gnr_memo;      // 기타 메모
    private String reg_usr_id;    // 등록 사용자 id
    private String reg_dtm;       // 등록 일시
    private String chg_usr_id;
    private String chg_dtm;

    List<EnterpriseSvrDiskDTO> svrDiskDTOList;
}
