package com.lieqihezi.www.controller;

import com.lieqihezi.www.domain.City;
import com.lieqihezi.www.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/city")
public class CityController {

    @Autowired
    CityService cityServices;

    @RequestMapping(value = "/citys", method = RequestMethod.GET)
    public String allCitys(Model model) {
        List<City> cityList = cityServices.findAllCity();
        if (cityList != null) {
            model.addAttribute("citys", cityList);
        }
        return "cityList";
    };

    @RequestMapping(value = "/addCity", method = RequestMethod.POST)
    public String addToCityList(City city) {
        System.out.println(city);
        cityServices.saveCity(city);
        return "redirect:/api/city/citys";
    }

    @RequestMapping(value = "/{city}", method = RequestMethod.GET)
    public String city(@PathVariable("city") String city, Model model) {
        List<City> cityList = cityServices.findByName(city);
        if (cityList != null) {
            model.addAttribute("citys", cityList);
        }
        return "cityList";
    }
}
