package Aogu.Genes.Controller;
import Aogu.Genes.Domain.*;
import Aogu.Genes.Service.OrgAdminService;
import Aogu.Genes.Service.OrgUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created by Administrator on 2016/8/16.
 */
    @Controller
    @RequestMapping("/admin")
    public class BackIndexController {
    @Autowired
    private OrgAdminService orgAdminService;
    @Autowired
    private OrgUserService orgUserService;

    @RequestMapping("")
    public String toLogin() {
        return "backpage/login";
    }

    @RequestMapping("/validatecode")
    public String validatecode() {
        return "backpage/validatecode";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "name") String name,
                        @RequestParam(value = "password") String password,
                        @RequestParam(value = "authCode") String authCode,
                        HttpSession session, Model model) {
        String validateCode = (String) session.getAttribute("validateCode");
        TblOrgAdminEntity orgAdminEntity = null;
        System.out.println(name+" "+password);
        orgAdminEntity = orgAdminService.findAdminByadminname(name);
        if (!authCode.equals(validateCode)) {
            model.addAttribute("message", "验证码错误");
            return "backpage/fail";
        }else if (orgAdminEntity==null) {
            model.addAttribute("message", "该用户名不存在");
            return "backpage/fail";
        } else if (!password.equals(orgAdminEntity.getPassword())) {
            model.addAttribute("message", "用户名或密码错误");
            return "backpage/fail";
        }  else if (!orgAdminEntity.getValid().equals("0")) {
            model.addAttribute("message", "该用户不存在");
            return "backpage/fail";
        } else {
            session.setAttribute("login", "success");
            model.addAttribute("admin",orgAdminEntity);
            return "redirect:/admin/index";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        Enumeration em = session.getAttributeNames();
        while(em.hasMoreElements()){
            session.removeAttribute(em.nextElement().toString());
        }
        return "redirect:/admin/index";
    }

    @RequestMapping("/index")
    public String index(HttpSession session,Model model) {
        try {
            String login = (String) session.getAttribute("login");
            if (login.equals("success")) {
                //登录成功

                return "backpage/backIndex";
            } else {
                session.removeAttribute("login");
                return "backpage/login";
            }
        } catch (Exception e) {
            //session login不存在，报错
            return "backpage/login";
        }
    }

}


