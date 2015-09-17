package com.queue.dao;

import com.queue.model.User;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by JUSTIN on 2015/7/18.
 */
public class UserDao extends BaseDao{

    public boolean checkUserExistsWithName(String username){
        String hql = "from User u where u.username = ?";
        Query query = getSession().createQuery(hql).setString(0,username);
        List<User> users = query.list();
        if(users != null && users.size() > 0) {
            return true;
        }
        return false;
    }
    public boolean checkUsernumberExists(String usernumber){
        String hql = "from User u where u.usernumber = ?";
        Query query = getSession().createQuery(hql).setString(0,usernumber);
        List<User> users = query.list();
        if(users != null && users.size() > 0){
            return true;
        }
        return false;
    }
    public void add(User user){
        getSession().saveOrUpdate(user);
    }
    public boolean checkThisNameAndPassword(User user){

 //       List<User> users = getSession().createSQLQuery(hql).list();
//        List users = getSession().createCriteria(User.class).add(Example.create(user)).list();
        String hql = "from User u where u.username='"+user.getUsername()+"'and u.password = '"+user.getPassword()+"'";
        List<User> users = getSession().createQuery(hql).list();
        if( users!=null && users.size()>0 ) {
            return true;
        }
        return false;
    }

    //通过用户名找到这个用户
    public User findUserByName(String username){
        String hql = "from User u where u.username = ?";
        return (User)getSession().createQuery(hql).setString(0,username).uniqueResult();
    }
}
