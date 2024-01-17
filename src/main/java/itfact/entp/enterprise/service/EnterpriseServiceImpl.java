package itfact.entp.enterprise.service;

import itfact.common.file.dto.FileDTO;
import itfact.common.file.service.FileService;
import itfact.common.util.CommonConstant;
import itfact.common.util.StringUtils;
import itfact.entp.enterprise.dao.EnterpriseDAO;
import itfact.entp.enterprise.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

//    @Autowired
//    private EnterpriseService EnterpriseService;

    @Autowired
    private EnterpriseDAO enterpriseDAO;

    @Autowired
    private FileService fileService;

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    public List<EnterpriseDTO> getEnterpriseList(EnterpriseDTO enterpriseDTO) {
        return enterpriseDAO.getEnterpriseList(enterpriseDTO);
    }

    public EnterpriseDTO getEnterprise(String entp_unq) {
        return enterpriseDAO.selectEnterprise(entp_unq);
    }

    public List<EnterpriseCustDTO> getEnterpriseCustListInfo(String entp_unq) {
        return enterpriseDAO.getEnterpriseCustListInfo(entp_unq);
    }

    public EnterpriseCustDTO getEnterpriseCustOneInfo(int custUnq) {
        return enterpriseDAO.getEnterpriseCustOneInfo(custUnq);
    }

//    public List<EnterpriseSvrDTO> getEnterpriseSvcListInfo(EnterpriseSvrDTO enterpriseSvrDTO) {
//        return enterpriseDAO.getEnterpriseSvcListInfo(enterpriseSvrDTO);
//    }
    public List<EnterpriseSvrDTO> getEnterpriseSvcListInfo(String enterpriseSvrDTO) {
        return enterpriseDAO.getEnterpriseSvcListInfo(enterpriseSvrDTO);
    }

    public List<EnterpriseSvrDiskDTO> getDiskListInfo(String enterpriseSvrDTO) {
        return enterpriseDAO.getDiskListInfo(enterpriseSvrDTO);
    }

    public List<EnterpriseAtchDTO> getEnterpriseAtchList(EnterpriseAtchDTO enterpriseAtchDTO) {
        return enterpriseDAO.getEnterpriseAtchList(enterpriseAtchDTO);
    }

    public boolean setEnterpriseInfo(EnterpriseDTO enterpriseDTO, EnterpriseCustDTO enterpriseCustDTO, List<MultipartFile> files) {
        try{
            EnterpriseAtchDTO enterpriseAtchDTO;

            if (StringUtils.equals(enterpriseDTO.getFlag(), "I")) { //신규저장

                String enterpriseNo = enterpriseDAO.insertEnterpriseInfo(enterpriseDTO);

                enterpriseCustDTO.setEntp_unq(enterpriseNo);
                enterpriseCustDTO.setEntp_tp(enterpriseDTO.getEntp_tp());

                enterpriseDAO.insertCustInfo(enterpriseCustDTO);

                List<FileDTO> attacheFileList = fileService.saveFile(files, CommonConstant.DEFAULT_UPLOAD_ENTERPRISE_DIR);

                for (FileDTO item : attacheFileList) {
                    enterpriseAtchDTO = new EnterpriseAtchDTO();
                    enterpriseAtchDTO.setEntp_unq         (enterpriseNo                 );
                    enterpriseAtchDTO.setReg_usr_id       (enterpriseDTO.getReg_usr_id());
                    enterpriseAtchDTO.setChg_usr_id       (enterpriseDTO.getChg_usr_id());
                    enterpriseAtchDTO.setAtch_file_nm     (item.getAtch_file_nm()       );
                    enterpriseAtchDTO.setAtch_file_org_nm (item.getAtch_file_org_nm()   );
                    enterpriseAtchDTO.setAtch_file_path   (item.getAtch_file_path()     );
                    enterpriseAtchDTO.setAtch_file_size   (item.getAtch_file_size()     );

                    enterpriseDAO.insertEnterpriseAtchInfo(enterpriseAtchDTO);
                }
            }else if(StringUtils.equals(enterpriseDTO.getFlag(), "U")) { //수정

                enterpriseDAO.updateEnterpriseInfo(enterpriseDTO);
                enterpriseDAO.updateCustInfo(enterpriseCustDTO);

                if (StringUtils.isNotEmpty(enterpriseDTO.getDelAtchFileNum())) {
                    String delAtchFileNumList[] = enterpriseDTO.getDelAtchFileNum().split(",");

                    for (int i = 0; i < delAtchFileNumList.length; i++) {
                        enterpriseAtchDTO = new EnterpriseAtchDTO();
                        enterpriseAtchDTO.setEntp_unq(enterpriseDTO.getEntp_unq());
                        enterpriseAtchDTO.setAtch_file_unq(Integer.parseInt(delAtchFileNumList[i].trim()));
                        enterpriseAtchDTO.setChg_usr_id(enterpriseDTO.getChg_usr_id());
                        enterpriseDAO.deleteEnterpriseAtchInfo(enterpriseAtchDTO);
                    }

                }
            }

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean setEnterpriseCustInfo(List<EnterpriseCustDTO> enterpriseCustDTOList) {
        try {
            EnterpriseCustDTO enterpriseCustDTO;

            if (StringUtils.equals(enterpriseCustDTOList.get(0).getFlag(), "I")) {
                enterpriseCustDTO = enterpriseCustDTOList.get(0);
                enterpriseDAO.insertEnterpriseCustInfo(enterpriseCustDTO);

            } else if (StringUtils.equals(enterpriseCustDTOList.get(0).getFlag(), "U")) {
                for (EnterpriseCustDTO item : enterpriseCustDTOList) {
                    enterpriseCustDTO = new EnterpriseCustDTO();
                    enterpriseCustDTO.setCust_unq(item.getCust_unq());
                    enterpriseCustDTO.setMemb_nm(item.getMemb_nm());
                    enterpriseCustDTO.setMemb_dept_nm(item.getMemb_dept_nm());
                    enterpriseCustDTO.setMemb_pst_nm(item.getMemb_pst_nm());
                    enterpriseCustDTO.setMemb_tel(item.getMemb_tel());
                    enterpriseCustDTO.setMemb_email(item.getMemb_email());
                    enterpriseCustDTO.setPrincipal_tp(item.getPrincipal_tp());

                    enterpriseDAO.updateCustInfo(enterpriseCustDTO);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteCustInfo(EnterpriseCustDTO enterpriseCustDTO) {
        try {
            enterpriseDAO.deleteCustInfo(enterpriseCustDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean deleteEnterpriseInfo(EnterpriseDTO enterpriseDTO) {
        try {
            enterpriseDAO.deleteEnterpriseInfo(enterpriseDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public int insertSysServerInfo(EnterpriseSvrDTO reqDTO) {
        int svr_unq = 0;

        try {
            svr_unq = enterpriseDAO.insertSystemInfo(reqDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        return svr_unq;
    }

    public int insertDiskInfo(EnterpriseSvrDiskDTO reqDTO) {
//        int svr_unq = 0;
        int disk_partition_unq = 0;

        try {
            disk_partition_unq = enterpriseDAO.insertDiskInfo(reqDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        return disk_partition_unq;
    }

    public boolean deleteSvrInfo(EnterpriseSvrDTO enterpriseSvrDTO) {
        try {
            enterpriseDAO.deleteSvrInfo(enterpriseSvrDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteDiskInfo(List<Integer> diskUnqList) {
        try {
            for (int item : diskUnqList) {
                enterpriseDAO.deleteDiskInfo(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean saveSvrInfo(EnterpriseSvrDTO enterpriseSvrDTO) {
        try {
            EnterpriseSvrDiskDTO enterpriseSvrDiskDTO;
            enterpriseDAO.updateSvrInfo(enterpriseSvrDTO);

            List<EnterpriseSvrDiskDTO> svrDiskDTOList = enterpriseSvrDTO.getSvrDiskDTOList();
            for (EnterpriseSvrDiskDTO item : svrDiskDTOList) {
                enterpriseSvrDiskDTO = new EnterpriseSvrDiskDTO();
                enterpriseSvrDiskDTO.setSvr_unq(item.getSvr_unq());
                enterpriseSvrDiskDTO.setDisk_partition_unq(item.getDisk_partition_unq());
                enterpriseSvrDiskDTO.setPartition_path(item.getPartition_path());
                enterpriseSvrDiskDTO.setTotal_disk_sz(item.getTotal_disk_sz());
                enterpriseSvrDiskDTO.setUsed_disk_sz(item.getUsed_disk_sz());

                enterpriseDAO.saveDiskInfo(enterpriseSvrDiskDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
