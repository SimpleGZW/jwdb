package Aogu.Genes.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yang on 2016/3/30.
 */
@Controller
public class RedirectController {

    @RequestMapping("/login")
    public String login(){
        return "/pages/login";
    }

    @RequestMapping("/myorder")
    public String myOrder(){
        return "/pages/my-order";
    }

    @RequestMapping("/headerAdmin")
    public String headerAdmin(){
        return "/include/header-admin";
    }

    @RequestMapping("/place-order")
    public String placeOrder(){
        return "/pages/place-order";
    }

    @RequestMapping("/index")
    public String index(){
        return "/index.jsp";
    }

    @RequestMapping("/header")
    public String header(){
        return "/include/header";
    }

    @RequestMapping("/headerNoSearchbox")
    public String headerNoSearchbox(){
        return "/include/header-no-searchbox";
    }

    @RequestMapping("/footer")
    public String footer(){
        return "/include/footer";
    }


    @RequestMapping("/404")
    public String hello404(){
        return "/pages/404";
    }

}
