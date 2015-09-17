package com.queue.service;

import com.queue.dao.BusinessDao;
import com.queue.model.Business;
import com.queue.model.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by JUSTIN on 2015/7/30.
 */
@Service
public class BusinessService {
    @Resource
    BusinessDao businessDao;
    public boolean exists(Business business){
        return businessDao.checkUser(business);
    }

    public List<Order> getAll(){
        return businessDao.getAll();
    }

    public void saveOrUpdate(Integer id){
        Order order = businessDao.getOneOrder(id);
        order.setState(1);
        businessDao.saveOrder(order);
    }
}
