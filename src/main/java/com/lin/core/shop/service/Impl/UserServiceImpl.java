package com.lin.core.shop.service.Impl;

import com.lin.core.shop.bean.User;
import com.lin.core.shop.bean.UserExample;
import com.lin.core.shop.dao.UserMapper;
import com.lin.core.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yuanbin.lin
 * @date 2018/11/13
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        UserExample userExample=new UserExample();
        List<User> users = userMapper.selectByExample(userExample);
        return CollectionUtils.isEmpty(users) ? new ArrayList<User>() : users;
    }

    @Override
    public boolean insert(User user) {
        return userMapper.insertSelective(user) > 0;
    }
}
