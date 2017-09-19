package Aogu.Genes.Controller;
import Aogu.Genes.Domain.*;
import Aogu.Genes.Service.OrgAdminService;
import Aogu.Genes.Service.OrgLabService;
import Aogu.Genes.Service.OrgUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by yang on 2016/3/30.
 */
@Controller
public class IndexController {

    private Log logger= LogFactory.getLog(IndexController.class);

    @Autowired
    private OrgLabService orgLabService;

    @Autowired
    private OrgUserService orgUserService;

    @Autowired
    private OrgAdminService orgAdminService;

    @RequestMapping("/loginin")
    public String loginin(@RequestParam(value = "username") String username,
                          @RequestParam(value = "password") String password,
                          @RequestParam(value = "role") String status,
                          HttpSession session, Model model){
        if(status.equals("1")){//管理员是1，普通用户是0
            TblOrgAdminEntity admin=orgAdminService.findAdminByadminname(username);
            if (admin==null) {
                model.addAttribute("message", "该用户名不存在");
                return "pages/login";
            } else if (!password.equals(admin.getPassword())) {
                model.addAttribute("message", "用户名或密码错误");
                return "pages/login";
            }  else if (!admin.getValid().equals("0")) {
                model.addAttribute("message", "该用户不存在");
                return "pages/login";
            } else {
                session.setAttribute("userid",admin.getAdminid());
                session.setAttribute("username",admin.getAdminname());
                session.setAttribute("role",status);
                session.setAttribute("name",admin.getName());
                return "redirect:/queryOrders#/1";
            }
        }else{
            TblOrgUserEntity orgUserEntity = null;
            orgUserEntity = orgUserService.findUserByUsername(username);
            if (orgUserEntity==null) {
                model.addAttribute("message", "该用户名不存在");
                return "pages/login";
            } else if (!password.equals(orgUserEntity.getPassword())) {
                model.addAttribute("message", "用户名或密码错误");
                return "pages/login";
            }  else if (!orgUserEntity.getValid().equals("0")) {
                model.addAttribute("message", "该用户不存在");
                return "pages/login";
            } else {
                session.setAttribute("userid",orgUserEntity.getUserid());
                session.setAttribute("username",orgUserEntity.getUsername());
                session.setAttribute("role",status);
                session.setAttribute("name",orgUserEntity.getName());
                return "redirect:/";
            }
        }


    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        Enumeration em = session.getAttributeNames();
        while(em.hasMoreElements()){
            session.removeAttribute(em.nextElement().toString());
        }
        return "redirect:/";
    }

    @RequestMapping("/register")
    public String register(Model model){
        List<TblOrgLabEntity> labs=orgLabService.findAllIsvalid();
        model.addAttribute("labs",labs);
        return "pages/register";
    }

    @ResponseBody
    @RequestMapping("/registerin")
    public String registerin( String username,String password,  String name,String company ,String phone,String address,
                              String agent,String invoicetitle){
        TblOrgUserEntity user= new TblOrgUserEntity();
        logger.info("开始保存user数据");
        try {
            user.setValid("0");
            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            user.setCompany(company);
            user.setTel(phone);
            user.setAddress(address);
            user.setAgent(agent);
            user.setInvoicetitle(invoicetitle);
            orgUserService.add(user);

            return username;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/checkAccount")
    public Boolean checkAccount( String username){
        System.out.println(username);
        TblOrgUserEntity user= orgUserService.findUserByUsername(username);
        if(user == null){
            return true;
        }else {
            return false;
        }
    }


}
