package com.queue.action;

import com.opensymphony.xwork2.ActionSupport;
import com.queue.model.Comments;
import com.queue.service.RestaurantService;
import org.apache.struts2.interceptor.RequestAware;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JUSTIN on 2015/7/21.
 */
public class RestaurantAction extends ActionSupport{
    private RestaurantService restaurantService;

    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    private Map<String,Object> dataMap;

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;

    private Integer table;

    private String username;

    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTable() {
        return table;
    }

    public void setTable(Integer table) {
        this.table = table;
    }

    public RestaurantAction(){
        dataMap = new HashMap<String, Object>();
    }

    private String restau;

    public String getRestau() {
        return restau;
    }

    public void setRestau(String restau) {
        this.restau = restau;
    }



    public String recommendBeforeLogin(){
        dataMap.clear();
        dataMap.put("slide",restaurantService.loginNotYet());
        return SUCCESS;
    }
    public String execute(){
        dataMap.clear();
        dataMap.put("list",restaurantService.loginAlready());
        dataMap.put("slide",restaurantService.loginNotYet());
        return SUCCESS;
    }

    public String walkIntoThis(){
        dataMap.clear();
        String[] imgs = restaurantService.imgTELAddressCommentsOfThisRestaurant(id);
        dataMap.put("img",imgs[0]);
        dataMap.put("TEL",imgs[1]);
        dataMap.put("address",imgs[2]);
        dataMap.put("comments",imgs[3]);
        dataMap.put("stars",imgs[4]);
        dataMap.put("nickname",imgs[5]);
        dataMap.put("recommend",restaurantService.getRecommendMenu(id));
        return SUCCESS;
    }

    public String tables(){
        dataMap.clear();
        List<Map> list = restaurantService.getOrderNumberOfTables(id);
        dataMap.put("A",list.get(0));
        dataMap.put("B",list.get(1));
        dataMap.put("C",list.get(2));
        return SUCCESS;
    }

    //创建订单
    public String order(){
        dataMap.clear();
        List list = restaurantService.orderTable(id,table,username);
        dataMap.put("order",list.get(0));
        dataMap.put("before",list.get(1));
        dataMap.put("time",list.get(2));
        return SUCCESS;
    }

    //搜索餐厅
    public String guess() throws UnsupportedEncodingException {
        dataMap.clear();
//        String str = new String(getRestau().getBytes("ISO-8859-1"),"utf-8");
//        List<Map> list = restaurantService.getListOfSearch(str);
        Double longitude1 = Double.parseDouble(longitude);
        Double latitude1 = Double.parseDouble(latitude);
        List<Map> list = restaurantService.getListOfSearch(restau,longitude1, latitude1);
        dataMap.put("list",list);
        return SUCCESS;

    }
    //搜索餐厅没有经纬度的
    public String guess2(){
        dataMap.clear();
        List<Map> list = restaurantService.getRestaurantLike(restau);
        dataMap.put("list",list);
        return SUCCESS;
    }

    //全部订单
    public String orderForm1(){
        dataMap.clear();
        dataMap.put("form",restaurantService.getOrderForm1(username));
        return SUCCESS;
    }

    //当前订单
    public String orderForm2(){
        dataMap.clear();
        dataMap.put("form",restaurantService.getOrderForm2(username));
        return SUCCESS;
    }

    //以往订单
    public String orderForm3(){
        dataMap.clear();
        dataMap.put("form",restaurantService.getOrderForm3(username));
        return SUCCESS;
    }

    //退订
    public String unSubscribe() throws UnsupportedEncodingException {

            dataMap.clear();
//            String str = new String(getNote().getBytes("ISO-8859-1"),"utf-8");
//            if(restaurantService.cancel(id,str)) {
        if(restaurantService.cancel(id,note)){
                dataMap.put("status", "true");
            }else {

                dataMap.put("status", "false");
            }

        return SUCCESS;
    }

    //地图显示
    private String longitude;
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


    public String AMap(){
        dataMap.clear();
        Double longitude1 = Double.parseDouble(longitude);
        Double latitude1 = Double.parseDouble(latitude);
        dataMap.put("nearby",restaurantService.getSearchOfMap(longitude1,latitude1));
        return SUCCESS;
    }

    //创建收藏
    public String addFavor(){
        dataMap.clear();
        if (username !=null && id!=null) {
            restaurantService.updateUser(username, id);
            dataMap.put("status", "true");
        }else {
            dataMap.put("status", "false");
        }
        return SUCCESS;
    }

    //查看收藏
    public String whatIsFavor(){
        dataMap.clear();
        dataMap.put("list",restaurantService.getRestaurantOfUser(username));
        return SUCCESS;
    }

    //添加评论
    private Integer stars;

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String addComments(){
        dataMap.clear();
        restaurantService.saveOrUpdate(username,id,note,stars);
        dataMap.put("status","true");
        return SUCCESS;
    }

    //查看评论
    public String whatIsComments(){
        dataMap.clear();
        dataMap.put("comments",restaurantService.getComments(id));
        return SUCCESS;
    }

    //按照地区搜索餐厅
    public String searchByBlock(){
        dataMap.clear();
        dataMap.put("list",restaurantService.getRestaurantsOfThisBlock( note));
        return SUCCESS;
    }

}
