package edu.ynmd.class2.dao;

import edu.ynmd.class2.model.ArsfcGeoCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityDao extends JpaRepository<ArsfcGeoCity,Integer> {
    @Query("select c.name from ArsfcGeoCity c where c.adcode=:zs")
    String getCityNameByZsCode(@Param("zs")String zs);
}
