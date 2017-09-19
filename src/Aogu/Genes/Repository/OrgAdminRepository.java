package Aogu.Genes.Repository;

import Aogu.Genes.Domain.TblOrgAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/8/16.
 */
@Repository
public interface OrgAdminRepository extends JpaRepository<TblOrgAdminEntity,Integer>,JpaSpecificationExecutor<TblOrgAdminEntity> {
    @Query("select orgAdmin from TblOrgAdminEntity orgAdmin where orgAdmin.adminname = :adminname ")
    public TblOrgAdminEntity findAdminByadminname(@Param("adminname") String adminname);
}
