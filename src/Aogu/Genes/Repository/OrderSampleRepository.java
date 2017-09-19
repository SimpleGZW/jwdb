package Aogu.Genes.Repository;

import Aogu.Genes.Domain.TblOrderSampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by wuxm on 2016/8/16.
 */
public interface OrderSampleRepository extends JpaRepository<TblOrderSampleEntity,String>,JpaSpecificationExecutor<TblOrderSampleEntity>{

    public TblOrderSampleEntity findByResultid(int sampleid);
}
