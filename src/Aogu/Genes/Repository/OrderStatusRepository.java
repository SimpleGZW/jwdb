package Aogu.Genes.Repository;

import Aogu.Genes.Domain.TblOrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by wuxm on 2016/8/16.
 */
public interface OrderStatusRepository extends JpaRepository<TblOrderStatusEntity,String>,JpaSpecificationExecutor<TblOrderStatusEntity>{

}
