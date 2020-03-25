package com.lwy.dubboconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lwy.pojo.Student;
import com.lwy.service.StudentScoreService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
@RestController
@RequestMapping(value = "/student")
@Slf4j
public class StudentController {


    Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Reference
    StudentScoreService sss;

    @RequestMapping("/find")
    @ResponseBody
    public String find(@RequestParam("name") String name, @RequestParam("value") String value){
//        String name = "aaa";
        logger.info("进入 find 方法");
        String byname = sss.findByname(name);
        System.out.println(byname+":"+value);
        return byname+":"+value;
    }

    @RequestMapping("/insert")
    public String insert(@RequestParam("name") String name,@RequestParam("age")Integer age,
                         @RequestParam("six")String six){


        logger.info("进入 insert 方法");
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        char[] chars = six.toCharArray();
        student.setSix(chars[0]);
        sss.insert(student);

        return "success";
    }


    @RequestMapping("/findAll")
    public List<Student> findAll(@RequestParam("key") String key,@RequestParam("value")String value){

        log.info("Slf4j log日志");
        logger.info("进入 findAll 方法  info");
        logger.debug("进入 findAll 方法  debug");
        logger.error("进入 findAll 方法  error");
        logger.warn("进入 findAll 方法  warn");
        List<Student> list = sss.findAll(key,value);
        return list;
    }

    @RequestMapping("/delete")
    public void delete(@RequestParam("key") String key,@RequestParam("value")String value){
        logger.info("进入 delete 方法");
        sss.delete(key,value);
    }


    @RequestMapping("/update")
    public void update(@RequestParam("key") String key,@RequestParam("value")String value){
        logger.info("进入 update 方法");
        sss.update(key,value);
    }
}
