package Aogu.Genes.Controller;
import Aogu.Genes.Domain.*;
import Aogu.Genes.Service.LabService;
import Aogu.Genes.utils.ExecuteResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hx on 2016/8/17 0017.
 * 实验室控制器
 */

@Controller
@RequestMapping("/admin/lab")
public class LabController {

    private Log logger= LogFactory.getLog(LabController.class);
    private ExecuteResult executeResult=new ExecuteResult();

    @Autowired
    private LabService labService;

    //获取实验室列表信息，并查询获取订单数量和用户数量
    @RequestMapping("/listByPage")
    public String getLabList(@RequestParam(value = "pageCurrent",required = false)Integer pageCurrent,
                             @RequestParam(value = "pageSize",required = false)Integer pageSize,
                             Model model)
    {
        try {
            logger.info("获取实验室列表");
            Page<TblOrgLabEntity> labEntitys=null;
            if (pageCurrent==null||pageCurrent.equals("")){
                pageCurrent=0;
            }else {
                pageCurrent=pageCurrent-1;
            }
            if (pageSize==null||pageSize.equals("")){
                pageSize=10;
            }
            PageRequest pageRequest=new PageRequest(pageCurrent,pageSize);;
            labEntitys=labService.findLabEntitysByPage(pageRequest);
            for (TblOrgLabEntity lab:labEntitys)
            {
                Integer orderNum=0;
                for(TblOrgUserEntity user:lab.getUsersByLabid()){
                    orderNum=orderNum+user.getOrderDetailsesByUserid().size();
                }
                lab.setOrderNum(orderNum);
            }
            model.addAttribute("labentityspage",labEntitys);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/backpage/lab/labList";
    }

    @RequestMapping("/addPage")
    //转到增添实验室页面
    public String updateLabInfo(TblOrgLabEntity labEntity, Model model)
    {
        model.addAttribute("labentity",labEntity);
        return "/backpage/lab/labAdd";
    }

    //增添实验室
    @ResponseBody
    @RequestMapping("/add")
    public Object addLab(TblOrgLabEntity labEntity)
    {
        try {
            logger.info("开始添加实验室");
            labEntity.setIsvalid("0");
//            labEntity.setUsernumber(0);
//            labEntity.setOrdernumber(0);
            labService.add(labEntity);
            return executeResult.jsonReturn(200);
        }catch (Exception e){
            e.printStackTrace();
            return  executeResult.jsonReturn(300,e.getMessage());
        }
    }

    @RequestMapping("/editPage")
    //转到查看修改实验室页面
    public String addPage(@RequestParam(value="labid")String labId,
                          Model model)
    {
        TblOrgLabEntity labEntity=labService.findLabById(Integer.parseInt(labId));
        model.addAttribute("labentity",labEntity);
        return "/backpage/lab/labEdit";
    }

    //修改实验室
    @ResponseBody
    @RequestMapping("/edit")
    public Object editLab(TblOrgLabEntity labEntity)
    {
        try {
            logger.info("开始修改实验室");
            labService.add(labEntity);
            return executeResult.jsonReturn(200);
        }catch (Exception e){
            e.printStackTrace();
            return  executeResult.jsonReturn(300,e.getMessage());
        }
    }

    //删除实验室
    @ResponseBody
    @RequestMapping("/delete")
    public Object deleteLab(@RequestParam(value="labid")String labId)
    {
        try {
            logger.info("开始删除实验室");
            TblOrgLabEntity labEntity=labService.findLabById(Integer.parseInt(labId));
            labEntity.setIsvalid("1");
            labService.add(labEntity);
            return executeResult.jsonReturn(200);
        }catch (Exception e){
            e.printStackTrace();
            return  executeResult.jsonReturn(300,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/checkAccount")
    public Object checkAccount( String labname){
        System.out.println(labname);
        TblOrgLabEntity lab= labService.findOneByLabname(labname);
        Map<String,String> message=new HashMap<String,String>();
        Map<String,Object> jsonObj=new HashMap<String,Object>();
        if(lab == null){
            jsonObj.put("ok","");
            return jsonObj;
        }else {
            message.put("error","实验室名已存在！");
            jsonObj.put("data",message);
            return jsonObj;
        }
    }

    @ResponseBody
    @RequestMapping("/checkAccount1")
    public Object checkAccount( String labname,String labid){
        TblOrgLabEntity lab=labService.findOneById(Integer.parseInt(labid));
        if(lab.getLabname().equals(labname)){
            Map<String,String> jsonObj=new HashMap<String,String>();
            jsonObj.put("ok","");
            return jsonObj;
        }else {
            return checkAccount(labname);
        }
    }
}


