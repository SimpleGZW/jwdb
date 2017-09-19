package Aogu.Genes.Repository;

import Aogu.Genes.Domain.TblOrderDefineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wuxm on 2016/8/16.
 */
public interface OrderDefineRepository extends JpaRepository<TblOrderDefineEntity,String>,JpaSpecificationExecutor<TblOrderDefineEntity>{

    @Query("select o from TblOrderDefineEntity o where o.ordertype.ordertype = :ordertype and o.currstatus.orderstatus = :currentcode and o.param = :param")
    public TblOrderDefineEntity findOrderDefine(@Param("ordertype")String ordertype,@Param("currentcode") String currentcode, @Param("param")String param);

    @Query("select o from TblOrderDefineEntity o where o.ordertype.ordertype = :ordertype")
    public List<TblOrderDefineEntity> findListByOrdertype(@Param("ordertype")String ordertype);

}
