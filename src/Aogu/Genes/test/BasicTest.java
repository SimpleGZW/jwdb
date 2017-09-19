package Aogu.Genes.test;

import Aogu.Genes.Domain.*;
import Aogu.Genes.Repository.OrderSampleRepository;
import Aogu.Genes.Service.OrderHandleService;
import Aogu.Genes.Service.OrderQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by yang on 2016/3/27.
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = false)
public class BasicTest {

    @Autowired
    private OrderHandleService orderHandleService;
    @Autowired
    private OrderQueryService orderQueryService;

    @Test
    public void test(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        applicationContext.getBean("dataSource");

    }
}
