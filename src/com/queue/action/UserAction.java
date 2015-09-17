package com.queue.action;

import com.opensymphony.xwork2.ActionSupport;
import com.queue.model.User;
import com.queue.service.UserService;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JUSTIN on 2015/7/18.
 */
public class UserAction extends ActionSupport {
    private UserService userService;
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;
    private String password;
    private String nickname;
    private String word;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    private Map<String, Object> dataMap;

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
    public UserAction(){
        dataMap = new HashMap<String, Object>();
    }

    //上传图片
    private File uploadPic;
    private String uploadType;
    private String picName;

    public File getUploadPic() {
        return uploadPic;
    }

    public void setUploadPic(File uploadPic) {
        this.uploadPic = uploadPic;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String registration(){
        dataMap.clear();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);
        if(userService.exists(user)){
            dataMap.put("status","false");
        }else {
            userService.add(user);
            dataMap.put("status","true");
        }
        return SUCCESS;
    }

    public String login(){
        dataMap.clear();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if(userService.checkLogin(user)){
            dataMap.put("status","true");
        }else {
            dataMap.put("status","false");
        }
        return SUCCESS;
    }

    public String details(){
        dataMap.clear();
        if(null==username){
            dataMap.put("status","false");
        }else {
            User user = userService.getUserByName(username);
            user.setNickname(nickname);
            user.setWords(word);
            userService.add(user);
            dataMap.put("status","true");
        }
        return SUCCESS;
    }

 /*   private User model;
    @Override
    public User getModel() {
        return model;
    }*/
}
