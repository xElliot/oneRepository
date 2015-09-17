package com.queue.action;

import com.queue.dao.UserDao;
import com.queue.model.User;
import com.queue.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserActionTest {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");

    @Test
    public void testExecute(){
//        UserDao userDao = (UserDao) applicationContext.getBean("UserDao");
//        UserAction userAction = (UserAction) applicationContext.getBean("userAction");
        UserService userService = (UserService) applicationContext.getBean("UserService");
        User u = new User();
        u.setUsername("w");
        u.setPassword("w");
        userService.add(u);
    }


    @Test
    public void testLogin() throws Exception {

        //UserService userService = (UserService) applicationContext.getBean("UserService");
        UserDao userDao = (UserDao) applicationContext.getBean("UserDao");
        User user = new User();
        user.setUsername("jack");
        user.setPassword("jack");
        boolean boo = userDao.checkThisNameAndPassword(user);
        Assert.assertTrue(boo);
    }
}