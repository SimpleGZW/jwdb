package Aogu.Genes.Repository;

import Aogu.Genes.Domain.TblOrgLabEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/8/19.
 */
@Repository
public interface OrgLabRepository extends JpaRepository<TblOrgLabEntity,Integer>,JpaSpecificationExecutor<TblOrgLabEntity> {
   @Query("select o from TblOrgLabEntity o where o.isvalid='0'")
    public List<TblOrgLabEntity> findAllIsvalid();

}
