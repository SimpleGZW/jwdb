package Aogu.Genes.Service;
import Aogu.Genes.Domain.*;
import Aogu.Genes.Repository.OrgLabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/8/19.
 */
@Service
public class OrgLabService {
    @Autowired
    private OrgLabRepository orgLabRepository;

    public List<TblOrgLabEntity> findAllIsvalid(){
        List<TblOrgLabEntity> labs =orgLabRepository.findAllIsvalid();
        return labs;
    }


}
