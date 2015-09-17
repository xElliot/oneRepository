package com.queue.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RestaurantDaoTest {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
    RestaurantDao restaurantDao = (RestaurantDao) applicationContext.getBean("restaurantDao");
    @Test
    public void testSearchForSearch() throws Exception {
        List<Map> list = restaurantDao.searchForSearch("橘子");
        System.out.print(list);
        Assert.assertTrue(list.size()>0);
    }
}