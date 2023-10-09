package itfact.entp.enterprise.dao;

import itfact.entp.enterprise.dto.EnterpriseAtchDTO;
import itfact.entp.enterprise.dto.EnterpriseCustDTO;
import itfact.entp.enterprise.dto.EnterpriseDTO;
import itfact.entp.enterprise.dto.EnterpriseSvcDTO;
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

    public List<EnterpriseDTO> selectEnterpriseList(EnterpriseDTO enterpriseDTO) {

        return sqlSessionTemplate.selectList(NAMESPACE + "selectEnterpriseList", enterpriseDTO);
    }

    public EnterpriseDTO selectEnterprise(int entp_unq) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "selectEnterprise", entp_unq);
    }
    public List<EnterpriseCustDTO> getEnterpriseCustListInfo(int entpUnq) {
        return sqlSessionTemplate.selectList(NAMESPACE + "getEnterpriseCustListInfo", entpUnq);
    }

    public List<EnterpriseSvcDTO> getEnterpriseSvcListInfo(int entpUnq) {
        return sqlSessionTemplate.selectList(NAMESPACE + "getEnterpriseSvcListInfo", entpUnq);
    }

    public List<EnterpriseAtchDTO> getEnterpriseAtchList(int entpUnq) {
        return sqlSessionTemplate.selectList(NAMESPACE + "getEnterpriseAtchList", entpUnq);
    }

    public int insertEnterpriseInfo(EnterpriseDTO enterpriseDTO) {
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

    public void insertEnterpriseSvcInfo(EnterpriseSvcDTO enterpriseSvcDTO) {
        sqlSessionTemplate.insert(NAMESPACE + "insertEnterpriseSvcInfo", enterpriseSvcDTO);
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

    public void updateSvcInfo(EnterpriseSvcDTO enterpriseSvcDTO) {
        sqlSessionTemplate.update(NAMESPACE + "updateSvcInfo", enterpriseSvcDTO);
    }
}
