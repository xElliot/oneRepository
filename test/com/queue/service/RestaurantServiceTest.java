package com.queue.service;

import com.queue.action.RestaurantAction;
import com.queue.dao.RestaurantDao;
import com.queue.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class RestaurantServiceTest {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");

    @Test
    public void testOrderTable() throws Exception {

        RestaurantService restaurantService = (RestaurantService) applicationContext.getBean("restaurantService");
        List list = restaurantService.orderTable(1,2,"jack");
        System.out.print(list);
        Assert.assertTrue(list.size()>0);
    }
   /* @Test
    public void testGetListOfSearch(){
        RestaurantService restaurantService = (RestaurantService) applicationContext.getBean("restaurantService");
        List<Map> list = restaurantService.getListOfSearch("橘子");
        System.out.print(list);
        Assert.assertTrue(list.size()>0);
    }*/

    @Test
    public void testGetComments(){
        RestaurantService restaurantService = (RestaurantService) applicationContext.getBean("restaurantService");
        List<Map> list = restaurantService.getComments(1);
        System.out.print(list);
        Assert.assertTrue(list.size()>0);
    }


}
