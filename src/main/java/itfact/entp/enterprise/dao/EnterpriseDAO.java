package itfact.entp.enterprise.dao;

import itfact.entp.enterprise.dto.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnterpriseDAO {
    private SqlSessionTemplate sqlSessionTemplate;
    private static final String NAMESPACE = "EnterpriseDAO.";

    public EnterpriseDAO(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<EnterpriseDTO> getEnterpriseList(EnterpriseDTO enterpriseDTO) {
        return sqlSessionTemplate.selectList(NAMESPACE + "getEnterpriseList", enterpriseDTO);
    }

    public EnterpriseDTO selectEnterprise(String entp_unq) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "selectEnterprise", entp_unq);
    }
    public List<EnterpriseCustDTO> getEnterpriseCustListInfo(String entpUnq) {
        return sqlSessionTemplate.selectList(NAMESPACE + "getEnterpriseCustListInfo", entpUnq);
    }

    public EnterpriseCustDTO getEnterpriseCustOneInfo(int custUnq) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getEnterpriseCustOneInfo", custUnq);
    }

//    public List<EnterpriseSvrDTO> getEnterpriseSvcListInfo(EnterpriseSvrDTO enterpriseSvrDTO) {
//        return sqlSessionTemplate.selectList(NAMESPACE + "getEnterpriseSvcListInfo", enterpriseSvrDTO);
//    }
    public List<EnterpriseSvrDTO> getEnterpriseSvcListInfo(String enterpriseSvrDTO) {
        return sqlSessionTemplate.selectList(NAMESPACE + "getEnterpriseSvcListInfo", enterpriseSvrDTO);
    }

//    public List<EnterpriseSvrDiskDTO> getDiskListInfo(EnterpriseSvrDTO enterpriseSvrDTO) {
//        return sqlSessionTemplate.selectList(NAMESPACE + "getDiskListInfo", enterpriseSvrDTO);
//    }
    public List<EnterpriseSvrDiskDTO> getDiskListInfo(String enterpriseSvrDTO) {
        return sqlSessionTemplate.selectList(NAMESPACE + "getDiskListInfo", enterpriseSvrDTO);
    }

    public List<EnterpriseAtchDTO> getEnterpriseAtchList(EnterpriseAtchDTO enterpriseAtchDTO) {
        return sqlSessionTemplate.selectList(NAMESPACE + "getEnterpriseAtchList", enterpriseAtchDTO);
    }

    public String insertEnterpriseInfo(EnterpriseDTO enterpriseDTO) {
        sqlSessionTemplate.insert(NAMESPACE + "insertEnterpriseInfo", enterpriseDTO);
        return enterpriseDTO.getEntp_unq();
    }
    public void insertCustInfo(EnterpriseCustDTO enterpriseCustDTO) {
        sqlSessionTemplate.insert(NAMESPACE + "insertCustInfo", enterpriseCustDTO);
    }

    public int insertEnterpriseAtchInfo(EnterpriseAtchDTO enterpriseAtchDTO) {
        sqlSessionTemplate.insert(NAMESPACE + "insertEnterpriseAtchInfo", enterpriseAtchDTO);
        return enterpriseAtchDTO.getAtch_file_unq();
    }

//    public void insertEnterpriseSvcInfo(EnterpriseSvcDTO enterpriseSvcDTO) {
//        sqlSessionTemplate.insert(NAMESPACE + "insertEnterpriseSvcInfo", enterpriseSvcDTO);
//    }

    public int insertSystemInfo(EnterpriseSvrDTO enterpriseSvrDTO) {
        sqlSessionTemplate.insert(NAMESPACE + "insertSystemInfo", enterpriseSvrDTO);
        return enterpriseSvrDTO.getSvr_unq();
    }

    public int insertDiskInfo(EnterpriseSvrDiskDTO enterpriseSvrDiskDTO) {
        sqlSessionTemplate.insert(NAMESPACE + "insertDiskInfo", enterpriseSvrDiskDTO);
//        return enterpriseSvrDiskDTO.getSvr_unq();
        return enterpriseSvrDiskDTO.getDisk_partition_unq();
    }

    public void saveDiskInfo(EnterpriseSvrDiskDTO EnterpriseSvrDiskDTO) {
        sqlSessionTemplate.insert(NAMESPACE + "saveDiskInfo", EnterpriseSvrDiskDTO);
    }


    public void updateEnterpriseInfo(EnterpriseDTO enterpriseDTO) {
        sqlSessionTemplate.update(NAMESPACE + "updateEnterpriseinfo", enterpriseDTO);
    }

    public void updateCustInfo(EnterpriseCustDTO enterpriseCustDTO) {
        sqlSessionTemplate.update(NAMESPACE + "updateCustInfo", enterpriseCustDTO);
    }

    public void deleteEnterpriseAtchInfo(EnterpriseAtchDTO enterpriseAtchDTO) {
        sqlSessionTemplate.update(NAMESPACE + "deleteEnterpriseAtchInfo", enterpriseAtchDTO);
    }

    public void updateSvrInfo(EnterpriseSvrDTO enterpriseSvrDTO) {
        sqlSessionTemplate.update(NAMESPACE + "updateSvrInfo", enterpriseSvrDTO);
    }

    public void insertEnterpriseCustInfo(EnterpriseCustDTO enterpriseCustDTO) {
        sqlSessionTemplate.insert(NAMESPACE + "insertCustInfo", enterpriseCustDTO);
    }

    public void deleteCustInfo(EnterpriseCustDTO enterpriseCustDTO) {
        sqlSessionTemplate.update(NAMESPACE + "deleteCustInfo", enterpriseCustDTO);
    }

    public void deleteEnterpriseInfo(EnterpriseDTO enterpriseDTO) {
        sqlSessionTemplate.update(NAMESPACE + "deleteEnterpriseInfo", enterpriseDTO);
    }


    public void deleteSvrInfo(EnterpriseSvrDTO enterpriseSvrDTO) {
        sqlSessionTemplate.delete(NAMESPACE + "deleteSvrInfo", enterpriseSvrDTO);
    }

    public void deleteDiskInfo(int enterpriseSvrDiskDTO) {
        sqlSessionTemplate.delete(NAMESPACE + "deleteDiskInfo", enterpriseSvrDiskDTO);
    }
}
