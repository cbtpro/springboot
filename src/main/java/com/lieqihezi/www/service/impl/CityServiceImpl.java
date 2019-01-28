package com.lieqihezi.www.service.impl;

import com.lieqihezi.www.domain.City;
import com.lieqihezi.www.repository.CityRepository;
import com.lieqihezi.www.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("cityServices")
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;
    @Transactional
    public Map<String, Object> saveCity(City city) {
        boolean isExistUser = cityRepository.findByName(city.getName()).size() > 0;
        Map<String, Object> map = new HashMap<String, Object>();
        if(isExistUser) {
            map.put("message", "City已经存在!");
        } else {
            City newCity = cityRepository.save(city);
            map.put("data", newCity);
            map.put("message", "新增City成功!");
        }
        return map;
    }
    @Override
    public List<City> findByName(String name) {
        return cityRepository.findByName(name);
    }

    @Override
    public List<City> findAllCity() {
        return cityRepository.findAll();
    }
}
