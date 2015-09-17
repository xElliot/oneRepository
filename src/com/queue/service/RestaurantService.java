package com.queue.service;

import com.queue.dao.RestaurantDao;
import com.queue.model.Comments;
import com.queue.model.Order;
import com.queue.model.Restaurant;
import com.queue.model.User;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by JUSTIN on 2015/7/21.
 */
public class RestaurantService {
    private RestaurantDao restaurantDao;

    public void setRestaurantDao(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    //生成订单号
    private String createOrder() {

        Random random = new Random();
        byte[] number = new byte[3];
        number[0] = (byte) ('A' + random.nextInt(25));
        number[1] = (byte) ('0' + random.nextInt(10));
        number[2] = (byte) ('0' + random.nextInt(10));

        String numberName = new String(number);
        return numberName;

    }

    //登陆完成推荐菜单
    public List<Map> loginAlready(){
        List<Map> result = new ArrayList<Map>(4);
        List<List> list = restaurantDao.searchForStars();
        Double[] commends = {0.0,0.0,0.0,0.0};
        Double min = 0.0;
        int jmin = 0;
        for(int i=0;i<list.size();i++){
            int id = (Integer) list.get(i).get(0);
            int orderAmount = restaurantDao.searchForOrderAmount(id);
            Double commended = (Integer)list.get(i).get(1) * 0.4 + orderAmount * 0.6;
            min = commends[0];
            if(i>=4) {
                for(int j = 0;j<4;j++){
                    if(min>commends[j]){
                        min=commends[j];
                        jmin = j;
                    }
                }
                if(commended>min){
                    commends[jmin] = commended;
                    result.set(jmin, restaurantDao.searchForLogin(id).get(0));
                }
            }else {
                commends[i] = commended;
                result.add(restaurantDao.searchForLogin(id).get(0));

            }
        }
        return result;

    }
    //登陆界面图片返回
    public List<Map> loginNotYet(){
        List<Map> list = restaurantDao.searchFirstRecommend();
        Random random = new Random();
        List<Map> result = new ArrayList<Map>();
        for(int i= 0;i<3;i++){
            int which = random.nextInt(list.size());
            result.add(list.get(which));
            list.remove(which);
        }
        return result;
    }
    //餐厅详细信息
    public String[] imgTELAddressCommentsOfThisRestaurant(Integer id){
        Restaurant restaurant = restaurantDao.searchOneRestaurant(id);
        List<List> comment = restaurantDao.searchComments(restaurant);
        String[] imgs = new String[6];
        imgs[0] = restaurant.getImg();
        imgs[1] = restaurant.getTel();
        imgs[2] = restaurant.getAddress();
        if (comment!=null&&comment.size()>0) {
            User user = restaurantDao.searchNickname((Integer) comment.get(0).get(0));
            imgs[3] = (String) comment.get(0).get(1);
            imgs[4] = ""+ comment.get(0).get(2);
            imgs[5] = user.getNickname();
        }else {
            imgs[3] = "";
            imgs[4] = "";
            imgs[5] = "";
        }
        return imgs;
    }

    //获得推荐菜单
    public List<Map> getRecommendMenu(Integer id){
        List<Map> result = new ArrayList<Map>();
        List<Map> list = restaurantDao.searchRecommendMenu(id);
        if(list!=null&&list.size()>0) {
            Random random = new Random();
            for (int i = 0; i < 3; i++) {
                int which = random.nextInt(list.size());
                result.add(list.get(which));
                list.remove(which);
            }
        }else {
            for (int i=0;i<3;i++) {
                Map map = new HashMap();
                map.put("","");
                map.put("","");
                result.add(map);
            }
        }
        return result;
    }


    public List<Map> getOrderNumberOfTables(Integer id){
        Random random = new Random();
        List<Map> list = new ArrayList<Map>();
        for (int i=0;i<3;i++) {
            Map map = new HashMap();
            int eachOrder = random.nextInt(3)+4;
            int amount = restaurantDao.searchForTables(i + 1, id);
            map.put("time",amount*eachOrder);
            map.put("amount",amount);
            list.add(i,map);
        }
        return list;
    }

    /**
     * state:0-等待处理，1-处理完成，2-用户退单
     */
    public List orderTable(Integer id, int table,String username){
        int userId = restaurantDao.searchUserIdByName(username).getId();
        Order order = new Order();
        Restaurant restaurant = restaurantDao.searchOneRestaurant(id);
        order.setRestaurant(restaurant);
        order.setDate(new Timestamp(System.currentTimeMillis()));
        order.setState(0);
        order.setDesk(table);
        String ordernumber = "1";
        while (true){
            ordernumber = createOrder();
            if(restaurantDao.checkOrdernumberExists(ordernumber)){
                continue;
            }
            break;
        }
        order.setOrdernumber(ordernumber);
        User user = restaurantDao.searchOneUser(userId);
        order.setUser(user);
        restaurantDao.saveOrder(order);
        Random random = new Random();
        int amount = restaurantDao.searchForTables(table,id)-1;
        if(amount<0){
            amount = 0;
        }
        List list = new ArrayList(3);
        list.add(0,ordernumber);
        list.add(1,amount);
        list.add(2,amount*(random.nextInt(4)+3));
        return list;
    }

    //查询用户订单，返回
    public List<Map> getOrderForm1(String username){
        Random random = new Random();
        int id = restaurantDao.searchUserIdByName(username).getId();
        List<Map> list = restaurantDao.searchForUserOrder(id);
        List<Map> result = new ArrayList<Map>();
        for(int i=0;i<list.size();i++) {
            Map map = list.get(i);
            Restaurant restaurant = (Restaurant) list.get(i).get("restaurant");
            int restaurantId = restaurant.getId();
            int size = (Integer) map.get("desk");
            int amount = restaurantDao.searchAmountOfOrderById(restaurantId, size);
            int countdown = 0;
            if (size == 1) {
                countdown = amount / restaurant.getTinyTable();
            } else if (size == 2) {
                countdown = amount / restaurant.getMiddleTable();
            } else {
                countdown = amount / restaurant.getBigTable();
            }

            map.put("id",restaurant.getId());
            map.put("restaurant", restaurant.getName());
            map.put("countdown", countdown*(random.nextInt(3)+19));
            map.remove("desk");
            result.add(i, map);
        }
        List<Map> list1 = new ArrayList<Map>();
        List<Map> list2 = new ArrayList<Map>();
        for(int i=0;i<result.size();i++){
            if (((Integer)result.get(i).get("status"))==0){
                list1.add(result.get(i));
            }else {
                list2.add(result.get(i));
            }
        }
        list1.addAll(list2);
        return list1;
    }

    //当前订单
    public List<Map> getOrderForm2(String username){
        Random random = new Random();
        int id = restaurantDao.searchUserIdByName(username).getId();
        List<Map> list = restaurantDao.searchForUserOrder(id);
        List<Map> result = new ArrayList<Map>();
        for(int i=0;i<list.size();i++) {
            Map map = list.get(i);
            Restaurant restaurant = (Restaurant) list.get(i).get("restaurant");
            int restaurantId = restaurant.getId();
            int size = (Integer) map.get("desk");
            int amount = restaurantDao.searchAmountOfOrderById(restaurantId, size);
            int countdown = 0;
            if (size == 1) {
                countdown = amount / restaurant.getTinyTable();
            } else if (size == 2) {
                countdown = amount / restaurant.getMiddleTable();
            } else {
                countdown = amount / restaurant.getBigTable();
            }

            map.put("id",restaurant.getId());
            map.put("restaurant", restaurant.getName());
            map.put("countdown", countdown*(random.nextInt(3)+19));
            map.remove("desk");
            if((Integer)map.get("status")==0) {
                result.add(map);
            }
        }
        return result;

    }

    //以往订单
    public List<Map> getOrderForm3(String username){
        Random random = new Random();
        int id = restaurantDao.searchUserIdByName(username).getId();
        List<Map> list = restaurantDao.searchForUserOrder(id);
        List<Map> result = new ArrayList<Map>();
        for(int i=0;i<list.size();i++) {
            Map map = list.get(i);
            Restaurant restaurant = (Restaurant) list.get(i).get("restaurant");
            int restaurantId = restaurant.getId();
            int size = (Integer) map.get("desk");
            int amount = restaurantDao.searchAmountOfOrderById(restaurantId, size);
            int countdown = 0;
            if (size == 1) {
                countdown = amount / restaurant.getTinyTable();
            } else if (size == 2) {
                countdown = amount / restaurant.getMiddleTable();
            } else {
                countdown = amount / restaurant.getBigTable();
            }

            map.put("id",restaurant.getId());
            map.put("restaurant", restaurant.getName());
            map.put("countdown", countdown*(random.nextInt(3)+19));
            map.remove("desk");
            if((Integer)map.get("status")!=0) {
                result.add(map);
            }
        }
        return result;

    }

    //退订逻辑
    public boolean cancel(int id,String note){
        Order order = restaurantDao.searchOrderById(id);
        order.setNote(note);
        order.setState(2);
        restaurantDao.updateOrder(order);
        return true;

    }

    //餐厅搜索
    public List<Map> getListOfSearch(String note,Double longitude,Double latitude){
        List<Map> list = restaurantDao.searchForSearch(note);
        Double distanceUtil = 29200/((120.154794-120.32037)*(120.154794-120.32037)+(30.298645-30.509951)*(30.298645-30.509951));
        if(list != null && list.size()>0){
            return list;
        }
        int length = note.length();
        String[] myString = new String[length];
        for(int i=0;i<length;i++){
            myString[i] = note.substring(i,i+1);
        }
        list = restaurantDao.searchForSearch(myString[0]);
        if(length>1){
            for(int i=1;i<length;i++){
                list.addAll(restaurantDao.searchForSearch(myString[i]));
            }
        }
        Set uniqueSet = new HashSet(list);
        int[] times = new int[uniqueSet.size()];
        int setLength=0;
        for(Object temp:uniqueSet){
            times[setLength++] = Collections.frequency(list,temp);
        }
        Arrays.sort(times);
        List<Map> result = new ArrayList<Map>(times[times.length-1]);

        for (int j=times.length-1;j>-1;j--){
            for (Object temp:uniqueSet) {
                Map map = (Map)temp;
                if (Collections.frequency(list, temp)==times[j]){
                    Double longitudeOfRestaurant = Double.parseDouble(map.get("longitude").toString());
                    Double latitudeOfRestaurant = Double.parseDouble(map.get("latitude").toString());
                    Double distance = ((longitudeOfRestaurant-longitude)*(longitudeOfRestaurant-longitude)+(latitudeOfRestaurant-latitude)*(latitudeOfRestaurant-latitude))*distanceUtil;
                    map.put("distance",distance);
                    map.remove("longitude");
                    map.remove("latitude");
                    result.add(map);
                    uniqueSet.remove(temp);
                    break;
                }
            }
        }

        return result;
    }

    //查询地图上 radius=半径
    public List<Map> getSearchOfMap(Double longitude,Double latitude){
        List<Map> list = restaurantDao.searchForMap();
        List<Map> result = new ArrayList<Map>();
        Double radius2 = (120.154794-120.32037)*(120.154794-120.32037)+(30.298645-30.509951)*(30.298645-30.509951);
        for(int i=0;i<list.size();i++){
            Double longitudeFromList = Double.parseDouble(list.get(i).get("longitude").toString());
            Double latitudeFromList = Double.parseDouble(list.get(i).get("latitude").toString());
            Double radius2FromList = (longitude-longitudeFromList)*(longitude-longitudeFromList)+(latitude-latitudeFromList)*(latitude-latitudeFromList);

            if(radius2FromList<=radius2){
                int distance = (int) (29200/radius2*radius2FromList);
                list.get(i).put("distance",distance);
                result.add(list.get(i));
            }
        }
        return result;
    }

    //通过username与id查出用户与餐厅的实体，将餐厅添加入用户的属性
    public void updateUser(String username, Integer id){
        User user = restaurantDao.searchUserIdByName(username);
        Restaurant restaurant = restaurantDao.searchOneRestaurant(id);
        user.getRestaurant().add(restaurant);
        restaurantDao.saveOrUpdate(user);
    }

    //拿到用户实体，得到收藏餐厅
    public List<Map> getRestaurantOfUser(String username){
        User user = restaurantDao.searchRestaurantOfUser(username);
        int length = user.getRestaurant().size();
        List<Map> result = new ArrayList<Map>(length);
        for(Object temp: user.getRestaurant()){
            Restaurant restaurant = (Restaurant)temp;
            Map<String, Object> map = new HashMap<String, Object>(6);
            map.put("id",restaurant.getId());
            map.put("consumption",restaurant.getConsumption());
            map.put("time",restaurant.getExpected());
            map.put("stars",restaurant.getStars());
            map.put("name",restaurant.getName());
            map.put("img",restaurant.getImg());
            result.add(map);
        }
        return result;
    }

    //添加或修改评论
    public void saveOrUpdate(String username,Integer id,String note,Integer stars){
        Comments comments = new Comments();
        comments.setComments(note);
        Restaurant restaurant = restaurantDao.searchOneRestaurant(id);
        comments.setRestaurant(restaurant);
        User user = restaurantDao.searchUserIdByName(username);
        comments.setUser(user);
        comments.setStars(stars);
        comments.setDate(new Timestamp(System.currentTimeMillis()));
        restaurantDao.saveOrUpdateComment(comments);

    }

    //查看评论
    public List<Map> getComments(Integer id){
        Restaurant restaurant = restaurantDao.searchOneRestaurant(id);
        List<List> list = restaurantDao.searchComments(restaurant);
        List<Map> result = new ArrayList<Map>(list.size());
        for(List temp:list){

            Map map = new HashMap(3);
            map.put("comment",temp.get(1));
            map.put("stars",temp.get(2));
            map.put("nickname",((User)temp.get(3)).getNickname());
            map.put("date",temp.get(4));
            result.add(map);

        }
        return result;
    }

    //通过地区搜索餐厅
    public List<Map> getRestaurantsOfThisBlock(String note){
        return restaurantDao.searchRestaurantByBlock(note);
    }

    //只有一个字段的模糊查询
    public List<Map> getRestaurantLike(String restau){
        return restaurantDao.searchForSearch(restau);
    }
}
