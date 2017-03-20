package com.kaishengit.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by lenovo on 2017/3/17.
 */
public class HelloAction extends ActionSupport implements
        SessionAware,ServletRequestAware,ServletResponseAware,ServletContextAware,ApplicationAware {

    private String code;
    private Map<String,Object> session;


    public String execute(){
            //1，session
        /*ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
       // session.put("s1","k1");
        session.put("s1","k1k1k1");*/
       /* //2，获取原生的request，response
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        HttpServletResponse response = ServletActionContext.getResponse();*/
        //3，Application
       // Map<String,Object> application = ActionContext.getContext().getApplication();
       // ServletContext application = ServletActionContext.getServletContext();



        System.out.println("Hello~~~~");
        return SUCCESS;
    }
    public String list(){
        code = "1009";
        System.out.println("List~~~~");
        return "success";
    }

    //get  set
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setSession(Map<String, Object> map) {//类似与Spring里面的依赖注入
        this.session = map;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {

    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {

    }

    @Override
    public void setApplication(Map<String, Object> map) {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {

    }
}
