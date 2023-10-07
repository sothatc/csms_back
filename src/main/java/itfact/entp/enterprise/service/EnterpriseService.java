package itfact.entp.enterprise.service;

import itfact.entp.enterprise.dto.EnterpriseAtchDTO;
import itfact.entp.enterprise.dto.EnterpriseCustDTO;
import itfact.entp.enterprise.dto.EnterpriseDTO;
import itfact.entp.enterprise.dto.EnterpriseSvcDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EnterpriseService {
    public List<EnterpriseDTO> getEnterpriseList(EnterpriseDTO reqDTO);

//    public boolean setEnterpriseInfo(EnterpriseDTO reqDTO, List<MultipartFile> files);
    public boolean setEnterpriseInfo(EnterpriseDTO enterpriseDTO, EnterpriseCustDTO enterpriseCustDTO, List<MultipartFile> files, List<EnterpriseSvcDTO> enterpriseSvcDTOList);

    public EnterpriseDTO getEnterprise(int reqDTO);

    public List<EnterpriseCustDTO> getEnterpriseCustListInfo(int entp_unq);

    public List<EnterpriseSvcDTO> getEnterpriseSvcListInfo(int entpUnq);

    public List<EnterpriseAtchDTO> getEnterpriseAtchList(int entpUnq);

}
