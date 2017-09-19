package Aogu.Genes.Repository;

import Aogu.Genes.Domain.TblOrgLabEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by michael on 2016/8/17 0017.
 */
public interface LabRepository extends JpaRepository<TblOrgLabEntity,String>,JpaSpecificationExecutor<TblOrgLabEntity> {

    @Query("select lab from TblOrgLabEntity lab where lab.labname=?1 and lab.isvalid='0' ")
    TblOrgLabEntity findLabByName(String labName);

    @Query("select lab from TblOrgLabEntity lab where lab.labid=?1 and lab.isvalid='0' ")
    TblOrgLabEntity findLabById(int labId);

    @Query("select lab from TblOrgLabEntity lab where lab.isvalid='0' ")
    List<TblOrgLabEntity> findAllLabEntitys();
    @Query("select o from TblOrgLabEntity o where o.labname=:labname")
    TblOrgLabEntity findOneByLabname(@Param("labname")  String labname);
    @Query("select o from TblOrgLabEntity o where lower(o.labid)=lower(:labid) ")
     TblOrgLabEntity findOneById(@Param("labid") int labid);
}


