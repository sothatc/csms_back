package itfact.entp.enterprise.service;

import itfact.common.file.dto.FileDTO;
import itfact.common.file.service.FileService;
import itfact.common.util.CommonConstant;
import itfact.common.util.StringUtils;
import itfact.entp.enterprise.dao.EnterpriseDAO;
import itfact.entp.enterprise.dto.EnterpriseAtchDTO;
import itfact.entp.enterprise.dto.EnterpriseCustDTO;
import itfact.entp.enterprise.dto.EnterpriseDTO;
import itfact.entp.enterprise.dto.EnterpriseSvcDTO;
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
        return enterpriseDAO.selectEnterpriseList(enterpriseDTO);
    }

    public EnterpriseDTO getEnterprise(int entp_unq) {
        return enterpriseDAO.selectEnterprise(entp_unq);
    }

    public List<EnterpriseCustDTO> getEnterpriseCustListInfo(int entp_unq) {
        return enterpriseDAO.getEnterpriseCustListInfo(entp_unq);
    }

    public EnterpriseCustDTO getEnterpriseCustOneInfo(int custUnq) {
        return enterpriseDAO.getEnterpriseCustOneInfo(custUnq);
    }

    public List<EnterpriseSvcDTO> getEnterpriseSvcListInfo(int entp_unq) {
        return enterpriseDAO.getEnterpriseSvcListInfo(entp_unq);
    }

    public List<EnterpriseAtchDTO> getEnterpriseAtchList(EnterpriseAtchDTO enterpriseAtchDTO) {
        return enterpriseDAO.getEnterpriseAtchList(enterpriseAtchDTO);
    }

    public boolean setEnterpriseInfo(EnterpriseDTO enterpriseDTO, EnterpriseCustDTO enterpriseCustDTO, List<MultipartFile> files, List<EnterpriseSvcDTO> enterpriseSvcDTOList) {
        try{
            EnterpriseAtchDTO enterpriseAtchDTO;
            EnterpriseSvcDTO  enterpriseSvcDTO;

            if (StringUtils.equals(enterpriseDTO.getFlag(), "I")) { //신규저장

                int enterpriseNo = enterpriseDAO.insertEnterpriseInfo(enterpriseDTO);

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

                for(EnterpriseSvcDTO item : enterpriseSvcDTOList) {
                    enterpriseSvcDTO = new EnterpriseSvcDTO();
                    enterpriseSvcDTO.setEntp_unq     (enterpriseNo           );
                    enterpriseSvcDTO.setSvr_hst      (item.getSvr_hst()      );
                    enterpriseSvcDTO.setSvr_ip       (item.getSvr_ip()       );
                    enterpriseSvcDTO.setSvr_cont     (item.getSvr_cont()     );
                    enterpriseSvcDTO.setUse_flag     (item.getUse_flag()     );
                    enterpriseSvcDTO.setResc_use_flag(item.getResc_use_flag());
                    enterpriseSvcDTO.setTrn_use_flag (item.getTrn_use_flag() );
                    enterpriseSvcDTO.setOs_vers      (item.getOs_vers()      );
                    enterpriseSvcDTO.setKern_vers    (item.getKern_vers()    );
                    enterpriseSvcDTO.setCpu_cnt      (item.getCpu_cnt()      );
                    enterpriseSvcDTO.setTotal_mem_sz (item.getTotal_mem_sz() );
                    enterpriseSvcDTO.setUsed_mem_sz  (item.getUsed_mem_sz()  );
                    enterpriseSvcDTO.setGpu_model    (item.getGpu_model()    );
                    enterpriseSvcDTO.setTotal_disk_sz(item.getTotal_disk_sz());
                    enterpriseSvcDTO.setUsed_disk_sz (item.getUsed_disk_sz() );
                    enterpriseSvcDTO.setBase_path    (item.getBase_path()    );
                    enterpriseSvcDTO.setLog_path     (item.getLog_path()     );
                    enterpriseSvcDTO.setGnr_memo     (item.getGnr_memo()     );
//                    enterpriseSvcDTO.setReg_usr_id(item.getReg_usr_id());
//                    enterpriseSvcDTO.setReg_dtm(item.getReg_dtm());

                    enterpriseDAO.insertEnterpriseSvcInfo(enterpriseSvcDTO);
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

                for(EnterpriseSvcDTO item : enterpriseSvcDTOList) {
                    enterpriseSvcDTO = new EnterpriseSvcDTO();
                    enterpriseSvcDTO.setEntp_unq     (enterpriseDTO.getEntp_unq());
                    enterpriseSvcDTO.setSvr_unq      (item.getSvr_unq()          );
                    enterpriseSvcDTO.setSvr_hst      (item.getSvr_hst()          );
                    enterpriseSvcDTO.setSvr_ip       (item.getSvr_ip()           );
                    enterpriseSvcDTO.setSvr_cont     (item.getSvr_cont()         );
                    enterpriseSvcDTO.setUse_flag     (item.getUse_flag()         );
                    enterpriseSvcDTO.setResc_use_flag(item.getResc_use_flag()    );
                    enterpriseSvcDTO.setTrn_use_flag (item.getTrn_use_flag()     );
                    enterpriseSvcDTO.setOs_vers      (item.getOs_vers()          );
                    enterpriseSvcDTO.setKern_vers    (item.getKern_vers()        );
                    enterpriseSvcDTO.setCpu_cnt      (item.getCpu_cnt()          );
                    enterpriseSvcDTO.setTotal_mem_sz (item.getTotal_mem_sz()     );
                    enterpriseSvcDTO.setUsed_mem_sz  (item.getUsed_mem_sz()      );
                    enterpriseSvcDTO.setGpu_model    (item.getGpu_model()        );
                    enterpriseSvcDTO.setTotal_disk_sz(item.getTotal_disk_sz()    );
                    enterpriseSvcDTO.setUsed_disk_sz (item.getUsed_disk_sz()     );
                    enterpriseSvcDTO.setBase_path    (item.getBase_path()        );
                    enterpriseSvcDTO.setLog_path     (item.getLog_path()         );
                    enterpriseSvcDTO.setGnr_memo     (item.getGnr_memo()         );
//                    enterpriseSvcDTO.setReg_usr_id(item.getReg_usr_id());
//                    enterpriseSvcDTO.setReg_dtm(item.getReg_dtm());

                    enterpriseDAO.updateSvcInfo(enterpriseSvcDTO);
                }

            }

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean insertEnterpriseCustInfo(EnterpriseCustDTO enterpriseCustDTO) {

        try {
            enterpriseDAO.insertEnterpriseCustInfo(enterpriseCustDTO);

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

}
