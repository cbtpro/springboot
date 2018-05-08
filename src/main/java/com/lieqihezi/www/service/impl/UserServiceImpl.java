package com.lieqihezi.www.service.impl;

import com.lieqihezi.www.domain.User;
import com.lieqihezi.www.repository.UserRepository;
import com.lieqihezi.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userServices")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Transactional
    public Map saveUser(User user) {
        boolean isExistUser = userRepository.findByName(user.getName()).size() > 0;
        Map map = new HashMap();
        if(isExistUser) {
            map.put("message", "用户名已经存在!");
        } else {
            User newUser = userRepository.save(user);
            map.put("message", "新增用户成功!");
        }
        return map;
    }
    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }
}
