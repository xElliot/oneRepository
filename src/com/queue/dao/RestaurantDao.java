package com.queue.dao;

import com.queue.model.Comments;
import com.queue.model.Order;
import com.queue.model.Restaurant;
import com.queue.model.User;
import org.hibernate.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by JUSTIN on 2015/7/21.
 */
public class RestaurantDao extends BaseDao {

    //登陆界面图片返回
    //因后面没有图片，查询有图片的前四个餐厅
    public List<Map> searchFirstRecommend(){
        String hql = "select new map(r.recommendImg as slideImage, r.recommendWord as slideWord) from Restaurant r where id < 5";
        return getSession().createQuery(hql).list();
    }

    //登陆完成推荐菜单-拿到星级
    public List<List> searchForStars(){
        String hql = "select new List(r.id,r.stars) from Restaurant r";
        return getSession().createQuery(hql).list();
    }

    //登陆完成推荐菜单-拿到订单数目
    public int searchForOrderAmount(Integer id){
        String hql = "select count(o.id) from Order o where o.restaurant.id = ?";
        return ((Number)getSession().createQuery(hql).setInteger(0,id).uniqueResult()).intValue();
    }

    //搜索对应餐厅桌型未处理订单数
    public int searchForTables(int size,Integer id){
        String hql = "select count(o.id) from Order o where o.desk = "+size+" and o.restaurant.id= "+id + " and o.state = 0";
        Query query = getSession().createQuery(hql);
//        query.setInteger(0,size);
//        query.setInteger(1,id);
        return ((Number) query.uniqueResult()).intValue();
    }

    //登陆完成推荐菜单
    public List<Map> searchForLogin(Integer id){
        String hql = "select  new map (r.id as id,r.name as name, r.stars as stars, r.consumption as consumption, r.expected as expected, r.img as img) from Restaurant r where r.id = ?";

        return getSession().createQuery(hql).setInteger(0,id).list()    ;
    }


    //搜索评论
    public List<List> searchComments(Restaurant restaurant){
        String hql = "select new list (c.id,c.comments,c.stars,c.user as user,c.date)from Comments c where c.restaurant= ? ";
        return getSession().createQuery(hql).setParameter(0,restaurant).list();
    }

    //搜索昵称
    public User searchNickname(Integer id){
        String hql = "from User u where u.id = ?";
        return (User)getSession().createQuery(hql).setInteger(0,id).uniqueResult();
    }
    //搜索推荐菜图片
    public List<Map> searchRecommendMenu(Integer id){
        String hql = "select new map(m.dish as name,m.img as pic) from Menu m where m.restaurant = ?";
        return getSession().createQuery(hql).setInteger(0,id).list();
    }

    //检查订单号是否重复
    public boolean checkOrdernumberExists(String ordernumber){
        String hql = "from Order o where o.ordernumber = ?";
        Query query = getSession().createQuery(hql).setString(0,ordernumber);
        List<Order> orders = query.list();
        if(orders != null & orders.size() > 0){
            return true;
        }
        return false;
    }

    //保存订单
    public void saveOrder(Order order){
        getSession().save(order);
    }

    //更新订单
    public void updateOrder(Order order){
        getSession().update(order);
    }
    //通过ID查找餐厅实体
    public Restaurant searchOneRestaurant(Integer id){
        String hql = "from Restaurant r where r.id= ?";
        return (Restaurant)getSession().createQuery(hql).setInteger(0,id).uniqueResult();
    }

    //通过ID查找用户实体
    public User searchOneUser(Integer id){
        String hql = "from User u where u.id= ?";
        return (User)getSession().createQuery(hql).setInteger(0,id).uniqueResult();
    }

    //用户订单
    public List<Map> searchForUserOrder(Integer id){
        String hql = "select new map(o.id as queueNumber,o.ordernumber as orderNumber,o.date as time,o.state as status,o.desk as desk, o.restaurant as restaurant) from Order o left outer join o.restaurant where o.user.id = ? order by o.date desc ";
        return getSession().createQuery(hql).setInteger(0,id).list();
    }

    //通过用户名找用户ID
    public User searchUserIdByName(String username){
        String hql = "from User u where u.username = ?";
        return (User)getSession().createQuery(hql).setString(0,username).uniqueResult();
    }

    //通过ID查找对应餐桌型号未处理订单数目
    public int searchAmountOfOrderById(int id,int size){
        String hql = "select count(o.id) from Order o where o.id = ? and o.desk = ? and o.state = 0";

        return ((Number)getSession().createQuery(hql).setInteger(0,id).setInteger(1,size).uniqueResult()).intValue();
    }

    //通过ID查找订单实体
    public Order searchOrderById(Integer id){
        String hql = "from Order o where o.id = ?";
        return (Order)getSession().createQuery(hql).setInteger(0,id).uniqueResult();
    }

    //查询地图上
    public List<Map> searchForMap(){
        String hql = "select new map(r.id as id,r.name as name,r.longitude as longitude, r.latitude as latitude, r.expected as expected, r.stars as stars, r.consumption as consumption) from Restaurant r";
        return getSession().createQuery(hql).list();
    }

    //创建用户与餐厅的关联
    public void saveOrUpdate(User user){
        getSession().saveOrUpdate(user);
    }

    //查看用户收藏的餐厅
    public User searchRestaurantOfUser(String username){
        String hql = "select u from User u join u.restaurant r where u.username = ?";
        return (User) getSession().createQuery(hql).setString(0,username).uniqueResult();
    }

    //添加或修改评论
    public void saveOrUpdateComment(Comments comments){
        getSession().saveOrUpdate(comments);
    }

//    通过地区搜索餐厅
    public List<Map> searchRestaurantByBlock(String note){
        String hql = "select new map(r.id as id,r.name as name,r.consumption as consumption,r.expected as time,r.stars as stars,r.img as img) from Restaurant r where r.address like ?";
        return getSession().createQuery(hql).setString(0,"%"+note+"%").list();
    }

    //通过模糊查询来查找餐厅
    public List<Map> searchForSearch(String note){
        String hql = "select new map(r.id as id,r.name as name, r.consumption as consumption,r.expected as time, r.stars as stars,r.img as img,r.longitude as longitude,r.latitude as latitude) from Restaurant r where r.name like ?";
        return getSession().createQuery(hql).setString(0,"%"+note+"%").list();
    }


}
