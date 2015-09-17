package com.queue.dao;

import com.queue.model.Business;
import com.queue.model.Order;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JUSTIN on 2015/7/30.
 */
@Repository
public class BusinessDao extends SuperDao{
    public boolean checkUser(Business business) {
        String hql = "from Business b where b.username = ?and b.password = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,business.getUsername());
        query.setString(1,business.getPassword());
        List list = query.list();
        if (list != null && list.size()>0){
            return true;
        }
        return false;
    }

    public List<Order> getAll(){
        String hql = "from Order o left outer join fetch o.user where o.restaurant.id = 1";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    public Order getOneOrder(Integer id){
        String hql = "from Order o where o.id = ?";
        return (Order) sessionFactory.getCurrentSession().createQuery(hql).setInteger(0,id).uniqueResult();
    }

    public void saveOrder(Order o){
        sessionFactory.getCurrentSession().saveOrUpdate(o);
    }

}
