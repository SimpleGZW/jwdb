package Aogu.Genes.Service;
import Aogu.Genes.Domain.*;
import Aogu.Genes.Domain.*;
import Aogu.Genes.Repository.*;

import Aogu.Genes.utils.UtilClass;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wuxm on 2016/8/16.
 */
@Transactional
@Service
public class OrderHandleService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private OrderInstanceRepository orderInstanceRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private OrderDefineRepository orderDefineRepository;
    @Autowired
    private OrderSampleRepository orderSampleRepository;

    private Log logger= LogFactory.getLog(OrderHandleService.class);

    //添加订单信息及订单样本信息
    public void save(TblOrderDetailsEntity orderDetails, int optid) {
        //订单信息包含订单基本信息、订单详情信息及订单类型ordertype、typedescription
        //生成订单编号
        String orderno = orderDetails.getOrderno() ;
        while(orderno == null ||orderno.equalsIgnoreCase("")){
            orderno = UtilClass.getSequence();
            //判断生成订单编号是否与数据库中已有数据重复
            if (orderDetailsRepository.findByOrderno(orderno)!=null) {
                orderno = "" ;
            }else{
                orderDetails.setOrderno(orderno);
            }
        }
        logger.info("orderno:"+orderDetails.getOrderno());
        //设置默认属性值
        orderDetails.setIsdelete("0");
        orderDetails.setIsfinished("0");
        TblOrgUserEntity opt = new TblOrgUserEntity();
        opt.setUserid(optid);
        orderDetails.setOptinfo(opt);
        Date date = new Date();
        orderDetails.setOpttime(date);
        TblOrderStatusEntity status = new TblOrderStatusEntity();
        status.setOrderstatus("1");
        orderDetails.setOrderstatus(status);
        TblOrderTypeEntity  type = new TblOrderTypeEntity();
        type.setOrdertype("1");
        orderDetails.setOrdertype(type);
        //添加订单信息
        orderDetailsRepository.save(orderDetails);
        logger.info("save orderDetails finished!");
    }

    //更新订单信息为已提交并向订单实例表中插入一条数据
    public String submit(TblOrderDetailsEntity orderDetails,int optid,String optname,String optrole) {
        //添加订单信息及订单样本信息，若更新订单信息则提交数据应当包含orderid
        save(orderDetails,optid);
        //更新订单信息为已提交
        orderDetails = orderDetailsRepository.findByOrderno(orderDetails.getOrderno());
        //向订单实例表插入一条数据
        saveOrderInstance(orderDetails,optid,optname,optrole);
        return orderDetails.getOrderno();
    }

    //更新订单信息表订单状态并向订单实例表插入一条数据
    public void nextStep(TblOrderDetailsEntity orderDetails,int optid,String optname,String optrole) {
        //orderDetails包含id
        //读取订单状态表定义的订单跳转信息
        TblOrderDefineEntity currentStatus = orderDefineRepository.findOrderDefine(orderDetails.getOrdertype().getOrdertype(),orderDetails.getOrderstatus().getOrderstatus(),"0");
        if((currentStatus!=null)&&(!currentStatus.equals(null))) {
            TblOrderDefineEntity nextStatus = orderDefineRepository.findOrderDefine(orderDetails.getOrdertype().getOrdertype(), currentStatus.getNextstatus().getOrderstatus(), "0");
            if((nextStatus!=null)&&(!nextStatus.equals(null))) {
                //更改订单状态信息
                orderDetails.setOrderstatus(currentStatus.getNextstatus());
                Calendar c = Calendar.getInstance();
                orderDetails.setOpttime(c.getTime());
                orderDetailsRepository.save(orderDetails);
                //向订单实例表插入一条数据
                saveOrderInstance(orderDetails, optid, optname,optrole);
                //如果下一环节为结束环节，则修改订单是否结束字段为已结束
                if ("1".equalsIgnoreCase(nextStatus.getIsend())) {
                    orderDetails.setIsfinished("1");
                    orderDetailsRepository.save(orderDetails);
                    logger.info("this order is finished!");
                }
            }
        }
    }

    //向订单实例表插入一条数据
    public void saveOrderInstance(TblOrderDetailsEntity orderDetails,int optid,String optname,String optrole){
        TblOrderInstanceEntity orderInstance = new TblOrderInstanceEntity();
        orderInstance.setOrder(orderDetails);
        orderInstance.setOrderstatus(orderDetails.getOrderstatus());
        orderInstance.setOptname(optname);
        orderInstance.setOptid(optid);
        orderInstance.setOptrole(optrole);
        orderInstanceRepository.save(orderInstance);
        logger.info("save orderInstance finished!");
        logger.info("orderDetails orderno:"+orderDetails.getOrderno());
        logger.info("orderDetails status:"+orderDetails.getOrderstatus().getStatusdescription());
    }

    //设置订单为无效
    public void setOrderInvalid(int orderid){
        TblOrderDetailsEntity orderDetails = orderDetailsRepository.findByOrderid(orderid);
        TblOrderStatusEntity status  = new TblOrderStatusEntity() ;
        status.setOrderstatus("-1");
        orderDetails.setOrderstatus(status);
        orderDetailsRepository.save(orderDetails);
        logger.info("set order invalid!");
    }

    //关闭订单
    public void closeOrder(int orderid,int optid,String optname,String optrole) {
        //设置订单为无效
        setOrderInvalid(orderid);
        //查询订单信息
        TblOrderDetailsEntity orderDetails = orderDetailsRepository.findByOrderid(orderid);
        //向订单实例表中添加一条记录
        saveOrderInstance(orderDetails,optid,optname,optrole);
    }

    //添加样本结果信息
    public void saveSampleList(List<TblOrderSampleEntity> sampleDataList) {
        for (int i = 0; i < sampleDataList.size(); i++) {
            TblOrderSampleEntity sample = orderSampleRepository.findByResultid(sampleDataList.get(i).getResultid());
            sample.setResultpath(sampleDataList.get(i).getResultpath());
            sample.setResultfilename(sampleDataList.get(i).getResultfilename());
            orderSampleRepository.save(sample);
        }
    }
}
