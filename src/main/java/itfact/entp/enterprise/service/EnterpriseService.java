package itfact.entp.enterprise.service;

import itfact.entp.enterprise.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EnterpriseService {
    public List<EnterpriseDTO> getEnterpriseList(EnterpriseDTO reqDTO);

//    public boolean setEnterpriseInfo(EnterpriseDTO enterpriseDTO, EnterpriseCustDTO enterpriseCustDTO, List<MultipartFile> files, List<EnterpriseSvcDTO> enterpriseSvcDTOList);
    public boolean setEnterpriseInfo(EnterpriseDTO enterpriseDTO, EnterpriseCustDTO enterpriseCustDTO, List<MultipartFile> files);

    public EnterpriseDTO getEnterprise(String reqDTO);

    public List<EnterpriseCustDTO> getEnterpriseCustListInfo(String entp_unq);

//    public List<EnterpriseSvrDTO> getEnterpriseSvcListInfo(EnterpriseSvrDTO enterpriseSvrDTO);
    public List<EnterpriseSvrDTO> getEnterpriseSvcListInfo(String enterpriseSvrDTO);

    List<EnterpriseSvrDiskDTO> getDiskListInfo(String enterpriseSvrDTO);

    public List<EnterpriseAtchDTO> getEnterpriseAtchList(EnterpriseAtchDTO enterpriseAtchDTO);

    boolean setEnterpriseCustInfo(List<EnterpriseCustDTO> enterpriseCustDTO);

    boolean deleteCustInfo(EnterpriseCustDTO enterpriseCustDTO);

    boolean deleteEnterpriseInfo(EnterpriseDTO enterpriseDTO);

    EnterpriseCustDTO getEnterpriseCustOneInfo(int custUnq);

    int insertSysServerInfo(EnterpriseSvrDTO reqDTO);

    boolean deleteSvrInfo(EnterpriseSvrDTO enterpriseSvcDTO);

    boolean saveSvrInfo(EnterpriseSvrDTO enterpriseSvrDTO);

    int insertDiskInfo(EnterpriseSvrDiskDTO reqDTO);

    boolean deleteDiskInfo(List<Integer> enterpriseSvrDiskDTO);
}
