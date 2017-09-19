package Aogu.Genes.Controller;
import Aogu.Genes.Domain.*;
import Aogu.Genes.Service.OrderHandleService;
import Aogu.Genes.Service.OrderQueryService;

import Aogu.Genes.utils.ExecuteResult;
import Aogu.Genes.utils.UtilClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by wuxm on 2016/8/16.
 */
@Controller
public class UserOrdersController {
    @Autowired
    OrderHandleService orderHandleService;
    @Autowired
    OrderQueryService orderQueryService;

    private Log logger= LogFactory.getLog(UserOrdersController.class);

    private ExecuteResult executeResult=new ExecuteResult();

   @RequestMapping("/findOrder")
    public String findOrder(@RequestParam("orderno")String orderno,Model model, HttpSession session){
        //取session中的用户信息
        int optid=(Integer) session.getAttribute("userid");
        TblOrderDetailsEntity orderDetails= null;
        orderDetails = orderQueryService.findOrderDetailsByNo(optid,orderno);
        if (orderDetails==null){
            //没有搜索到内容
            orderDetails = new TblOrderDetailsEntity();
            orderDetails.setOrderno(orderno);
            model.addAttribute("orderDetails",orderDetails);
            return "/pages/orderinfo-not-found";
        }else {
            //数据分析
            model.addAttribute("orderDetails",orderDetails);
            List<TblOrderDefineEntity> orderStatusByType  = orderQueryService.findOrderStatusByType(orderDetails.getOrdertype().getOrdertype());
            model.addAttribute("orderStatusByType",orderStatusByType);
            return "/pages/order-detail";
        }
    }

    @ResponseBody
    @RequestMapping("/addOrder")
    public Map<String,Object> addOrder(String orderinfo, HttpSession session, HttpServletRequest request){
        logger.info("save orderinfo start!");
        try {
            ObjectMapper mapper = new ObjectMapper();
            TblOrderDetailsEntity orderDetails = mapper.readValue(orderinfo, TblOrderDetailsEntity.class);
            //生成对应的Excel表格
            String realpath= request.getSession().getServletContext().getRealPath("excel");
            String inputpath = realpath + "/TemExample1_step1.xls";
            String outputpath = realpath + "/TemExample1_" + UtilClass.getSequence() + ".xls";
            UtilClass.excelExport(orderDetails,inputpath,outputpath);
            //取session中的用户信息
            int optid=(Integer) session.getAttribute("userid");
            String optname=(String)session.getAttribute("name");
            String role=(String)session.getAttribute("role");
            //添加订单信息
            String orderno = orderHandleService.submit(orderDetails,optid,optname,role);
            logger.info("the result of addOrder method is success!");
            return executeResult.jsonReturn(200,orderno);
        } catch (Exception e) {
            logger.info("the result of addOrder method is failure!");
            return executeResult.jsonReturn(300,e.getMessage());
        }

    }

    @RequestMapping("/submitOrderSuccess")
    public String toDirecation(Model model, HttpSession session){
        List<TblOrderDetailsEntity> untreatedOrders= null;
        List<TblOrderDetailsEntity> processedOrders= null;
        List<TblOrderDetailsEntity> finishedOrders= null;
        //取session中的用户信息
        int optid=(Integer) session.getAttribute("userid");
        untreatedOrders = orderQueryService.findUntreatedOrdersByUsers(optid);
        processedOrders = orderQueryService.findProcessedOrdersByUsers(optid);
        finishedOrders = orderQueryService.findFinishedOrdersByUsers(optid);
        model.addAttribute("untreatedOrders",untreatedOrders);
        model.addAttribute("processedOrders",processedOrders);
        model.addAttribute("finishedOrders",finishedOrders);
        return "/pages/my-order";
    }

    @RequestMapping("/deleteOrder")
    public String deleteOrder(@RequestParam("orderid")String orderid,Model model,HttpServletRequest request) {
        try {
            orderHandleService.setOrderInvalid(Integer.parseInt(orderid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/submitOrderSuccess#/1";
    }

    @RequestMapping("/orderInfo")
    public String orderInfo(@RequestParam("orderid")String orderid,Model model) {
        TblOrderDetailsEntity orderDetails= null;
        orderDetails = orderQueryService.findOrderById(Integer.parseInt(orderid));
        model.addAttribute("orderDetails",orderDetails);
        List<TblOrderDefineEntity> orderStatusByType  = orderQueryService.findOrderStatusByType(orderDetails.getOrdertype().getOrdertype());
        model.addAttribute("orderStatusByType",orderStatusByType);
        return "/pages/order-detail";
    }

    @RequestMapping("/resultDownload")
    public String resultDownload(String fileUrl, String fileName, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        String fileNameEncode = null;
        try {
            fileNameEncode = URLEncoder.encode(fileName, "UTF-8");
            if (fileName.length() > 150) {
                fileNameEncode = new String(fileName.getBytes("gb2312"), "ISO8859-1");
            }
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileNameEncode);
            String path = request.getSession().getServletContext().getRealPath("upload");
            InputStream inputStream = new FileInputStream(new File(fileUrl));
            OutputStream outputStream = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                outputStream.write(b, 0, length);
            }
            outputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
