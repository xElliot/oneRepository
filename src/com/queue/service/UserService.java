package com.queue.service;

import com.queue.dao.UserDao;
import com.queue.model.User;

import java.util.Random;

/**
 * Created by JUSTIN on 2015/7/18.
 */
public class UserService {

    private UserDao userDao;
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    public boolean checkLogin(User user){
        return userDao.checkThisNameAndPassword(user);
    }
    //生成独立用户号
    private String createUser() {

        Random random = new Random();
        byte[] number = new byte[6];
        number[0] = (byte) ('A' + random.nextInt(25));
        number[1] = (byte) ('A' + random.nextInt(25));
        number[2] = (byte) ('A' + random.nextInt(25));
        number[3] = (byte) ('0' + random.nextInt(10));
        number[4] = (byte) ('0' + random.nextInt(10));
        number[5] = (byte) ('0' + random.nextInt(10));
        String numberName = new String(number);
        return numberName;

    }
    public void add(User user) {
        String usernumber = "1";
        while (true){
            usernumber = createUser();
            if(userDao.checkUsernumberExists(usernumber)){
                continue;
            }
            break;
        }
        user.setUsernumber(usernumber);
        userDao.add(user);
    }

    public boolean exists(User user) {
        return userDao.checkUserExistsWithName(user.getUsername());
    }

    //通过用户名找到用户
    public User getUserByName(String username){
        return userDao.findUserByName(username);
    }

}
