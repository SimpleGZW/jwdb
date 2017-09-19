package Aogu.Genes.utils;

/**
 * Created by Administrator on 2016/8/24.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.apache.struts2.interceptor.DateTextFieldInterceptor.DateWord.y;

/**
 * 登录认证的拦截器
 */
public class LoginInterceptor implements HandlerInterceptor{

    private List<String> uncheckUrls;

    public List<String> getUncheckUrls() {
        return uncheckUrls;
    }

    public void setUncheckUrls(List<String> uncheckUrls) {
        this.uncheckUrls = uncheckUrls;
    }

    /**
     * Handler执行完成之后调用这个方法
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exc)
            throws Exception {

    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * Handler执行之前调用这个方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        //获取请求的URL
        String url = request.getRequestURI();
        //首页请求地址
        String applicationName = "/"
                + request.getContextPath().split("/")[request.getContextPath().split("/").length - 1] + "/";
       //如果是首页请求就放行
        if (url.endsWith(applicationName)) {
            return true;
        }

        //URL:对于/login,/register请求放行
        for(String uncheckUrl:uncheckUrls){
            if(url.contains(uncheckUrl)){
                return true;
            }
        }
        //获取Session
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");

        if(username != null){
            return true;
        }
        //不符合条件的，跳转到登录界面
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);

        return false;
    }

}