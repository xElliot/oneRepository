package com.queue.action;

import com.opensymphony.xwork2.ActionSupport;
import com.queue.model.Business;
import com.queue.service.BusinessService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Map;


/**
 * Created by JUSTIN on 2015/7/30.
 */
@Controller
@Namespace("/")
@Results({@Result(name = "success", location = "/restaurant.jsp"), @Result(name = "error", location = "/index.jsp")})
public class BusinessAction extends ActionSupport implements RequestAware{
    private String username;
    private String password;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Resource
    BusinessService businessService;
    @Action("login")
    public String execute() throws Exception{
        Business u = new Business();
        u.setUsername(getUsername());
        u.setPassword(getPassword());
        if (businessService.exists(u)){
            request.put("order",businessService.getAll());
            return "success";
        }
        return "error";
    }

    @Action("change")
    public String changeTheState(){
        businessService.saveOrUpdate(id);
        request.put("order",businessService.getAll());
        return "success";
    }

    private Map<String, Object> request;
    @Override
    public void setRequest(Map<String, Object> stringObjectMap) {
        this.request = stringObjectMap;
    }
}
