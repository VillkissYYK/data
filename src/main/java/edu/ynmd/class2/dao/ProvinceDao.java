package edu.ynmd.class2.dao;

import edu.ynmd.class2.model.ArsfcGeoProvince;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProvinceDao extends JpaRepository<ArsfcGeoProvince,Integer> {

    @Query("select p.name from ArsfcGeoProvince p where p.adcode=:sf")
    String getProvinceNameBySfCode(@Param("sf") String sf);


}
