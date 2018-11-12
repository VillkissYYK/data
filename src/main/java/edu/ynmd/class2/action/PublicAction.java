package edu.ynmd.class2.action;

import edu.ynmd.class2.dao.AreaDao;
import edu.ynmd.class2.dao.CityDao;
import edu.ynmd.class2.dao.ProvinceDao;
import edu.ynmd.class2.dao.StudentDao;
import edu.ynmd.class2.model.Student;
import edu.ynmd.class2.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@Controller
public class PublicAction {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private AreaDao areaDao;



    @GetMapping("testDao")
    @ResponseBody
    public boolean testDao() throws Exception{
        //添加
//        Student student=new Student();
//        student.setStunum("201621447101");
//        student.setName("姓名");
//        student.setSex("F");
//        student.setBirthday(new Timestamp(System.currentTimeMillis()));
//        student.setNativeplace("云南玉溪");
//        studentDao.save(student);

        //修改
//        Student student=new Student();
//        student.setStudentid("36859734-3d0d-41f7-b6f5-38a06e5da0c8");
//        student.setStunum("201621447102");
//        student.setName("姓名修改");
//
//        studentDao.save(student);

        //查询单个
//        Student student=studentDao.findOne("36859734-3d0d-41f7-b6f5-38a06e5da0c8");
//        student.setSex("M");
//        student.setBirthday(new Timestamp(System.currentTimeMillis()));
//        student.setNativeplace("红塔区");
//        studentDao.save(student);

        //删除

        Student student=new Student();
        student.setStudentid("36859734-3d0d-41f7-b6f5-38a06e5da0c8");
        studentDao.delete(student);

        return true;



    }



    @GetMapping("getAnObject")
    @ResponseBody
    public Student getAnObject() throws Exception{

        List<Student> sl=studentDao.findAll();
        if(sl.size()>0){
            return  sl.get(0);
        }
        else{
            return null;
        }


    }







    @PostMapping("saveStudentInfo")
    @ResponseBody
    public Map saveStudentInfo(@RequestBody StudentVo studentVo) throws Exception{
        Map m=new HashMap();

        String sf=studentVo.getSfzh().substring(0,2);

        String sfname=provinceDao.getProvinceNameBySfCode(sf+"0000");

        String zs=studentVo.getSfzh().substring(2,4);
        String zsname=cityDao.getCityNameByZsCode(sf+zs+"00");

        String qx=studentVo.getSfzh().substring(4,6);
        String qxname=areaDao.getAreaNameByQxCode(sf+zs+qx);



        String csrq=studentVo.getSfzh().substring(6,14);
        Timestamp csrqtime=this.getBirthTimeByStr(csrq);
        String xb=studentVo.getSfzh().substring(16,17);
        String xbname=this.getXbByCode(xb);

        Student student=new Student();
        student.setNativeplace(sfname+zsname+qxname);
        student.setBirthday(csrqtime);
        student.setSex(xbname);
        student.setName(studentVo.getXm());
        student.setStunum(studentVo.getXh());

        studentDao.save(student);


        m.put("province",sfname);
        m.put("city",zsname);
        m.put("area",qxname);
        m.put("csrq",csrqtime);
        m.put("xb",xbname);

        return m;
    }



    private String getXbByCode(String code){
        int sexi=Integer.parseInt(code);

        if(sexi%2==0){
            return "F";
        }
        else{
            return "M";
        }

    }


    private Timestamp getBirthTimeByStr(String brithStr){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(brithStr, pos);
        return new Timestamp( strtodate.getTime());


    }






}
