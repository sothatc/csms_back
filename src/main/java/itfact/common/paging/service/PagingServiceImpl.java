package itfact.common.paging.service;


import itfact.common.paging.dao.PagingDAO;
import itfact.common.paging.dto.PagingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagingServiceImpl implements PagingService{


    @Autowired
    private PagingDAO pagingDAO;

    public PagingDTO getPagingInfo(PagingDTO reqDTO) {
        return pagingDAO.selectPaging(reqDTO);
    }
}
