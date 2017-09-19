package Aogu.Genes.Controller;

import Aogu.Genes.Service.OrderQueryService;
import Aogu.Genes.utils.ExecuteResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import Aogu.Genes.Service.OrderHandleService;
import Aogu.Genes.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by wuxm on 2016/8/17.
 */
@Controller
public class AdminOrdersController {
    @Autowired
    OrderHandleService orderHandleService;
    @Autowired
    OrderQueryService orderQueryService;

    private Log logger= LogFactory.getLog(AdminOrdersController.class);

    private ExecuteResult executeResult=new ExecuteResult();

    @RequestMapping("/queryOrders")
    public String queryOrders(Model model){
        List<TblOrderDetailsEntity> processedOrders= null;
        List<TblOrderDetailsEntity> finishedOrders= null;
        processedOrders = orderQueryService.findProcessedOrders();
        finishedOrders = orderQueryService.findFinishedOrders();
        model.addAttribute("processedOrders",processedOrders);
        model.addAttribute("finishedOrders",finishedOrders);
        return "/pages/my-order";
    }

    @RequestMapping("/getOrderDetails")
    public String getOrderDetails(@RequestParam("orderid")String orderid,Model model) {
        TblOrderDetailsEntity orderDetails= null;
        orderDetails = orderQueryService.findOrderById(Integer.parseInt(orderid));
        model.addAttribute("orderDetails",orderDetails);
        String orderNextStatue=orderQueryService.findOrderNextStatue(orderDetails);
        model.addAttribute("orderNextStatue",orderNextStatue);
        List<TblOrderDefineEntity> orderStatusByType  = orderQueryService.findOrderStatusByType(orderDetails.getOrdertype().getOrdertype());
        model.addAttribute("orderStatusByType",orderStatusByType);
        boolean nextStatueIsEnd = orderQueryService.nextStatueIsEnd(orderDetails);
        model.addAttribute("nextStatueIsEnd",nextStatueIsEnd);
        return "/pages/order-detail";
    }

    @RequestMapping("/handleOrder")
    public String handleOrder(@RequestParam("orderid")String orderid, HttpSession session) {
        try{
            TblOrderDetailsEntity orderDetails= null;
            orderDetails = orderQueryService.findOrderById(Integer.parseInt(orderid));
            //取session中的用户信息
            int optid=(Integer) session.getAttribute("userid");
            String optname=(String)session.getAttribute("name");
            String role=(String)session.getAttribute("role");
            orderHandleService.nextStep(orderDetails,optid,optname,role);
            logger.info("the result of handleOrder method is success!");
        } catch (Exception e) {
            logger.info("the result of handleOrder method is failure!");
        }
        return "redirect:/queryOrders#/1";
    }

    @RequestMapping("/finishOrder")
    public String resultUpload(TblOrderSampleEntityModel samplelist, @RequestParam(value = "filelist", required = false) MultipartFile[] filelist, String orderid, HttpSession session, HttpServletRequest request) {
        logger.info("resultUpload begin");
        try {
            int i = 0;
            for (MultipartFile file : filelist) {
                if (!file.isEmpty()) {
                    String path = request.getSession().getServletContext().getRealPath("upload");
                    String OriginalFilename = file.getOriginalFilename();
                    //重命名上传后的文件名,使用时间戳作为文件名称
                    Random random = new Random();
                    String extName = OriginalFilename.substring(OriginalFilename.lastIndexOf(".")).toLowerCase();
                    String fileName = System.currentTimeMillis() + String.valueOf(random.nextInt(10000)) + extName;
                    File targetFile = new File(path, fileName);
                    if (!targetFile.exists()) {
                        targetFile.mkdirs();
                    }
                    //保存文件至服务器
                    file.transferTo(targetFile);
                    //为样本信息中文件名和文件路径赋值
                    String fileUrl = request.getSession().getServletContext().getRealPath("upload") + "/" + fileName;
                    logger.info(fileUrl);
                    logger.info(OriginalFilename);
                    samplelist.getSamplelist().get(i).setResultpath(fileUrl);
                    samplelist.getSamplelist().get(i).setResultfilename(OriginalFilename);
                    logger.info("the result of resultUpload method is success!");
                    i++;
                }
            }
            //添加样本结果信息
            orderHandleService.saveSampleList(samplelist.getSamplelist());
            //取session中的用户信息
            int optid=(Integer) session.getAttribute("userid");
            String optname=(String)session.getAttribute("name");
            String role=(String)session.getAttribute("role");
            //结束当前环节
            TblOrderDetailsEntity orderDetails= null;
            orderDetails = orderQueryService.findOrderById(Integer.parseInt(orderid));
            orderHandleService.nextStep(orderDetails,optid,optname,role);
            return "redirect:/queryOrders#/2";
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("the result of resultUpload method is failure!");
            return "redirect:/queryOrders#/2";
        }
    }


}
