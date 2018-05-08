package com.lieqihezi.www.service;

import com.lieqihezi.www.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    public Map saveUser(User user);

    public List<User> findByName(String name);

}
