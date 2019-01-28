package com.lieqihezi.www.service;

import com.lieqihezi.www.domain.City;

import java.util.List;
import java.util.Map;

public interface CityService {

    public Map<?, ?> saveCity(City city);

    public List<City> findByName(String name);

    public List<City> findAllCity();

}
