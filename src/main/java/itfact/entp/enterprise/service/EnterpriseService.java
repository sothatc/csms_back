package itfact.entp.enterprise.service;

import itfact.entp.enterprise.dto.EnterpriseAtchDTO;
import itfact.entp.enterprise.dto.EnterpriseCustDTO;
import itfact.entp.enterprise.dto.EnterpriseDTO;
import itfact.entp.enterprise.dto.EnterpriseSvcDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EnterpriseService {
    public List<EnterpriseDTO> getEnterpriseList(EnterpriseDTO reqDTO);

    public boolean setEnterpriseInfo(EnterpriseDTO enterpriseDTO, EnterpriseCustDTO enterpriseCustDTO, List<MultipartFile> files, List<EnterpriseSvcDTO> enterpriseSvcDTOList);

    public EnterpriseDTO getEnterprise(String reqDTO);

    public List<EnterpriseCustDTO> getEnterpriseCustListInfo(String entp_unq);

    public List<EnterpriseSvcDTO> getEnterpriseSvcListInfo(String entpUnq);

    public List<EnterpriseAtchDTO> getEnterpriseAtchList(EnterpriseAtchDTO enterpriseAtchDTO);

    boolean insertEnterpriseCustInfo(EnterpriseCustDTO enterpriseCustDTO);

    boolean deleteCustInfo(EnterpriseCustDTO enterpriseCustDTO);

    boolean deleteEnterpriseInfo(EnterpriseDTO enterpriseDTO);

    EnterpriseCustDTO getEnterpriseCustOneInfo(int custUnq);
}
