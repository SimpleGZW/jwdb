package Aogu.Genes.Service;
import Aogu.Genes.Domain.*;
import Aogu.Genes.Repository.OrderDefineRepository;
import Aogu.Genes.Repository.OrderDetailsRepository;
import Aogu.Genes.Repository.OrderStatusRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wuxm on 2016/8/17.
 */
@Transactional
@Service
public class OrderQueryService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private OrderDefineRepository orderDefineRepository;

    private Log logger= LogFactory.getLog(OrderQueryService.class);

    //根据no查询订单详情
    public TblOrderDetailsEntity findOrderDetailsByNo(int optid,String orderno){
        return orderDetailsRepository.findOrderDetailsByNo(optid,orderno);
    }

    //分页查询用户未提交订单
    public List<TblOrderDetailsEntity> findUntreatedOrdersByUsers(int optid){
        return orderDetailsRepository.findUntreatedOrdersByUsers(optid,"0");
    }

    //分页查询用户已提交订单
    public List<TblOrderDetailsEntity> findProcessedOrdersByUsers(int optid){
        return orderDetailsRepository.findProcessedOrdersByUsers(optid,"0","0");
    }

    //分页查询用户已完成订单
    public List<TblOrderDetailsEntity> findFinishedOrdersByUsers(int optid){
        return orderDetailsRepository.findFinishedOrdersByUsers(optid,"1");
    }

    //管理员分页查询进行中订单
    public List<TblOrderDetailsEntity> findProcessedOrders(){
        return orderDetailsRepository.findProcessedOrders("0","0");
    }

    //管理员分页查询已完成订单
    public List<TblOrderDetailsEntity> findFinishedOrders(){
        return orderDetailsRepository.findFinishedOrders("1");
    }

    //管理员分页查询已删除及已关闭订单
    public List<TblOrderDetailsEntity> findInvaliedOrders(){
        return orderDetailsRepository.findInvaliedOrders("0");
    }

    //根据订单类型查询订单定义信息
    public List<TblOrderDefineEntity> findOrderStatusByType(String ordertype){
        return orderDefineRepository.findListByOrdertype(ordertype);
    }

    //查询下一环节订单状态信息，作为页面操作按钮的显示
    public String findOrderNextStatue(TblOrderDetailsEntity orderDetails){
        TblOrderDefineEntity orderDefine = orderDefineRepository.findOrderDefine(orderDetails.getOrdertype().getOrdertype(),orderDetails.getOrderstatus().getOrderstatus(),"0");
        return orderDefine.getNextstatus().getStatusdescription();
    }

    //判断下一环节是否为结束环节，若为结束环节则页面应展示结果录入表单
    public boolean nextStatueIsEnd(TblOrderDetailsEntity orderDetails) {
        boolean flag =false;
        TblOrderDefineEntity currentStatus = orderDefineRepository.findOrderDefine(orderDetails.getOrdertype().getOrdertype(),orderDetails.getOrderstatus().getOrderstatus(),"0");
        if((currentStatus!=null)&&(!currentStatus.equals(null))) {
            TblOrderDefineEntity nextStatus = orderDefineRepository.findOrderDefine(orderDetails.getOrdertype().getOrdertype(), currentStatus.getNextstatus().getOrderstatus(), "0");
            if((nextStatus!=null)&&(!nextStatus.equals(null))) {
                flag = nextStatus.getIsend().equalsIgnoreCase("0") ? false : true;
            }
        }
        return flag;
    }

    //根据订单id查询订单信息
    public TblOrderDetailsEntity findOrderById(int orderid){
        return orderDetailsRepository.findByOrderid(orderid);
    }

}
