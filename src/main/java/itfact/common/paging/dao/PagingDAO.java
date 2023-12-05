package itfact.common.paging.dao;


import itfact.common.paging.dto.PagingDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PagingDAO {
    private SqlSessionTemplate sqlSessionTemplate;

    public PagingDAO(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    private static final String NAMESPACE = "common.";


    public PagingDTO selectPaging(PagingDTO reqDTO) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "selectPaging", reqDTO);
    }
}
