package com.lin.core.shop.service;

import com.lin.core.shop.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author yuanbin.lin
 * @date 2018/11/13
 */
public interface UserService {

    List<User> getAllUser();
    boolean insert(User user);
}
