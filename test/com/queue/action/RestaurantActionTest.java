package com.queue.action;

import com.queue.dao.RestaurantDao;
import com.queue.model.Comments;
import com.queue.service.RestaurantService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

public class RestaurantActionTest {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
    @Test
    public void testRecommendBeforeLogin() throws Exception {
        RestaurantDao restaurantDao = (RestaurantDao)applicationContext.getBean("restaurantDao");
        List<Map> list =restaurantDao.searchFirstRecommend();
        System.out.print(list);

    }
   /* @Test
    public void walkIntoThis(){
        RestaurantDao restaurantDao = (RestaurantDao)applicationContext.getBean("restaurantDao");
        List<List> list= restaurantDao.searchComments(2);
        System.out.print(list);
        Assert.assertTrue(list.size()>0);
    }
*/
    @Test
    public void myRandom(){
        Random random = new Random();
        for(int i= 0;i<30;i++){
            int which = random.nextInt(4);
            System.out.println(which);
        }
    }
    @Test
    public void testTables(){
        RestaurantService restaurantService = (RestaurantService) applicationContext.getBean("restaurantService");
        List<Map> list = restaurantService.getOrderNumberOfTables(4);
        System.out.print(list);
        Assert.assertTrue(list.size()>0);
    }
    @Test
    public void testGuess() throws UnsupportedEncodingException {
        RestaurantAction restaurantAction = (RestaurantAction) applicationContext.getBean("restaurantAction");
        restaurantAction.setRestau("橘子");
        restaurantAction.guess();
        System.out.print(restaurantAction.getDataMap());
        Assert.assertTrue(restaurantAction.getDataMap().size()>0);
    }
    @Test
    public void testOrderForm1(){
        RestaurantAction restaurantAction = (RestaurantAction) applicationContext.getBean("restaurantAction");
        restaurantAction.setUsername("jack");
        restaurantAction.orderForm1();

        List<Map> list1 = (List<Map>) restaurantAction.getDataMap().get("form");
        System.out.print(list1.size());
        restaurantAction.orderForm2();
        List<Map> list2 = (List<Map>) restaurantAction.getDataMap().get("form");
        System.out.print(list2.size());
        restaurantAction.orderForm3();
        List<Map> list3 = (List<Map>) restaurantAction.getDataMap().get("form");
        System.out.print(list3.size());
        Assert.assertTrue(list1.size()>0&&list2.size()>0&&list3.size()>0);
    }
}