package Aogu.Genes.Repository;

import Aogu.Genes.Domain.TblOrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by yang on 2016/3/27.
 */
public interface OrderDetailsRepository extends JpaRepository<TblOrderDetailsEntity,String>,JpaSpecificationExecutor<TblOrderDetailsEntity>{

    public TblOrderDetailsEntity findByOrderid(int orderid);

    public TblOrderDetailsEntity findByOrderno(String orderno);

    @Query("select o from TblOrderDetailsEntity o where o.isfinished = :isfinished order by opttime desc")
    public List<TblOrderDetailsEntity> findFinishedOrders(@Param("isfinished") String isfinished);

    @Query("select o from TblOrderDetailsEntity o where o.orderstatus.orderstatus > :orderstatus and o.isfinished = :isfinished order by opttime desc")
    public List<TblOrderDetailsEntity> findProcessedOrders(@Param("orderstatus")String orderstatus,@Param("isfinished")String isfinished);

    @Query("select o from TblOrderDetailsEntity o where o.orderstatus.orderstatus < :orderstatus order by opttime desc")
    public List<TblOrderDetailsEntity> findInvaliedOrders(@Param("orderstatus")String orderstatus);

    @Query("select o from TblOrderDetailsEntity o where o.optinfo.userid = :optid and o.orderno = :orderno")
    public TblOrderDetailsEntity findOrderDetailsByNo(@Param("optid")int optid,@Param("orderno")String orderno);

    @Query("select o from TblOrderDetailsEntity o where o.optinfo.userid = :optid and  o.orderstatus.orderstatus = :orderstatus order by opttime desc")
    public List<TblOrderDetailsEntity> findUntreatedOrdersByUsers(@Param("optid")int optid, @Param("orderstatus")String orderstatus);

    @Query("select o from TblOrderDetailsEntity o where o.optinfo.userid = :optid and  o.orderstatus.orderstatus > :orderstatus and o.isfinished = :isfinished order by opttime desc")
    public List<TblOrderDetailsEntity> findProcessedOrdersByUsers(@Param("optid")int optid,@Param("orderstatus")String orderstatus,@Param("isfinished")String isfinished);

    @Query("select o from TblOrderDetailsEntity o where o.optinfo.userid = :optid and o.isfinished = :isfinished order by opttime desc")
    public List<TblOrderDetailsEntity> findFinishedOrdersByUsers(@Param("optid")int optid,@Param("isfinished")String isfinished);

}
