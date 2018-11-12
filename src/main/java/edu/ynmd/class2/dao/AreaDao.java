package edu.ynmd.class2.dao;

import edu.ynmd.class2.model.ArsfcGeoDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AreaDao extends JpaRepository<ArsfcGeoDistrict,Integer> {

    @Query("select a.name from ArsfcGeoDistrict a where a.adcode=:qx")
    String getAreaNameByQxCode(@Param("qx")String qx);

}
