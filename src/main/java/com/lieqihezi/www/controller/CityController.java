package com.lieqihezi.www.controller;

import com.lieqihezi.www.domain.City;
import com.lieqihezi.www.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CityController {

    @Autowired
    CityService cityServices;

    @GetMapping(value = "allCitys")
    public List<City> allCitys() {
        List<City> cityList = cityServices.findAllCity();
        return cityList;
    };

    @PostMapping(value = "addCity")
    public Map saveCity(@RequestBody City city) {
        return cityServices.saveCity(city);
    }
}
