package com.queue.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by JUSTIN on 2015/7/30.
 */
@Component
public class SuperDao {
    @Resource
    SessionFactory sessionFactory;
}
