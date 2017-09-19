package Aogu.Genes.Controller;

import Aogu.Genes.Domain.*;
import Aogu.Genes.Service.LabService;
import Aogu.Genes.Service.OrgLabService;
import Aogu.Genes.Service.OrgUserService;
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
import java.util.List;
import java.util.Map;

/**
 * Created by 25065 on 2016/5/15.
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

    private Log logger= LogFactory.getLog(UserController.class);

    private ExecuteResult executeResult=new ExecuteResult();

    @Autowired
    private OrgUserService orgUserService;

    @Autowired
    private OrgLabService orgLabService;

    @Autowired
    private LabService labService;
//
//    @Autowired
//    private OrderInstanceService orderInstanceService;

    @RequestMapping("/showlist")
    public String showlist(@RequestParam(value = "pageCurrent",required = false)Integer pageCurrent,
                           @RequestParam(value = "pageSize",required = false)Integer pageSize,
                           Model model){
        logger.info("开始展示全部的用户数据");


        try {

            if (pageCurrent==null||pageCurrent.equals("")){
                pageCurrent=0;
            }else {
                pageCurrent=pageCurrent-1;
            }
            if (pageSize==null||pageSize.equals("")){
                pageSize=10;
            }

            PageRequest pageRequest=new PageRequest(pageCurrent,pageSize);
            Page<TblOrgUserEntity> users=orgUserService.userList( pageRequest);

            if (users==null){
                logger.info("没有数据");
            }else {
                logger.info(users.getTotalPages());
                model.addAttribute("users",users);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        return "backpage/user/userlist";
    }

    @RequestMapping("/editpage")
    public String editPageUser(String id,Model model){
            logger.info("开始跳转到编辑页面");
        try {
            TblOrgUserEntity user=orgUserService.findOneById(Integer.parseInt(id));
            List<TblOrgLabEntity> labs=orgLabService.findAllIsvalid();
            model.addAttribute("user",user);
            model.addAttribute("labs",labs);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        return "backpage/user/updateUser";
    }

    @RequestMapping("/addpage")
    public String addPageUser(Model model){
        List<TblOrgLabEntity> labs=orgLabService.findAllIsvalid();
        model.addAttribute("labs",labs);
        return "backpage/user/addUser";
    }

    @ResponseBody
    @RequestMapping("/add")
    public Object addUser(Integer userid, String username, String password, String name,
                          String company, String tel, String address, String agent,
                          String invoicetitle, String valid, String labid){

        logger.info("开始保存user数据");
        try {

            TblOrgLabEntity lab=labService.findOneById(Integer.parseInt(labid));
            TblOrgUserEntity user=new TblOrgUserEntity();
            if(userid!=null){
                user.setUserid(userid);
            }
            user.setUsername(username);
            user.setValid(valid);
            user.setPassword(password);
            user.setInvoicetitle(invoicetitle);
            user.setAgent(agent);
            user.setAddress(address);
            user.setCompany(company);
            user.setLabByLabid(lab);
            user.setName(name);
            user.setTel(tel);
            orgUserService.add(user);
            return executeResult.jsonReturn(200);
        } catch (Exception e) {
            e.printStackTrace();
            return executeResult.jsonReturn(300,e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object deleteuser(String id){
        logger.info("开始跳转到删除页面");
        try {
            TblOrgUserEntity user=orgUserService.findOneById(Integer.parseInt(id));
           user.setValid("1");
           orgUserService.delete(user);
            return executeResult.jsonReturn(200,false);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }

    @ResponseBody
    @RequestMapping("/checkAccount")
    public Object checkAccount( String username){
        System.out.println(username);
        TblOrgUserEntity user= orgUserService.findUserByUsername(username);
        Map<String,String> message=new HashMap<String,String>();
        Map<String,Object> jsonObj=new HashMap<String,Object>();
        if(user == null){
            jsonObj.put("ok","");
            return jsonObj;
        }else {
            message.put("error","用户名已存在！");
            jsonObj.put("data",message);
            return jsonObj;
        }
    }

    @ResponseBody
    @RequestMapping("/checkAccount1")
    public Object checkAccount( String username,String userid){
        TblOrgUserEntity user=orgUserService.findOneById(Integer.parseInt(userid));
        if(user.getUsername().equals(username)){
            Map<String,String> jsonObj=new HashMap<String,String>();
            jsonObj.put("ok","");
            return jsonObj;
        }else {
            return checkAccount(username);
        }
    }
}
