package Aogu.Genes.Repository;

import Aogu.Genes.Domain.TblOrderInstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by wuxm on 2016/8/16.
 */
public interface OrderInstanceRepository extends JpaRepository<TblOrderInstanceEntity,String>,JpaSpecificationExecutor<TblOrderInstanceEntity>{

}
