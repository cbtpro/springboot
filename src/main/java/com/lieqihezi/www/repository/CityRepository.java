package com.lieqihezi.www.repository;

import com.lieqihezi.www.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, String> {

    List<City> findByName(String name);
}