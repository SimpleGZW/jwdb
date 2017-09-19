package Aogu.Genes.Service;
import Aogu.Genes.Domain.*;
import Aogu.Genes.Repository.OrgAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/8/16.
 */
@Transactional
@Service
public class OrgAdminService {
    @Autowired
    private OrgAdminRepository orgAdminRepository;

    public TblOrgAdminEntity findAdminByadminname(String adminName ){
        TblOrgAdminEntity orgAdmin=orgAdminRepository.findAdminByadminname(adminName);
        return orgAdmin;
    }
}
