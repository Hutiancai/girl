package com.firstboot.controller;

import com.firstboot.domain.Filter;
import com.firstboot.domain.Girl;
import com.firstboot.domain.Result;
import com.firstboot.repository.GirlRepository;
import com.firstboot.repository.NewGirlRepository;
import com.firstboot.repository.Query;
import com.firstboot.service.GirlService;
import com.firstboot.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 2018/1/13.
 */
@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    @Autowired
    private NewGirlRepository a;

    @GetMapping(value = "/girls")
    public List<Girl> girlList(){
        return girlRepository.findAll();
    }

    @PostMapping(value = "/girls")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return null;
            //return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }

        girl.setCup(girl.getCup());
        girl.setAge(girl.getAge());
        girl.setMoney(girl.getMoney());
        return ResultUtil.success(girlRepository.save(girl));
    }

    @PostMapping(value = "/files")
    public void girlAdd(@Valid byte[] file){

        System.out.println(file);
    }


    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
        return girlRepository.findOne(id);
    }

    @PutMapping(value = "girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("cup") String cup,
                           @RequestParam("age") Integer age){

        Girl girl=new Girl();
        girl.setId(id);
        girl.setCup(cup);
        girl.setAge(age);

        return girlRepository.save(girl);
    }

    @DeleteMapping(value = "girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id){
         girlRepository.delete(id);
    }


    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age){
        return girlRepository.findByAge(age);
    }


    @PostMapping(value = "/girls/two")
    public void girlTwo(){
        girlService.insertTwo();
    }

    @GetMapping(value = "girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id)throws Exception{
        girlService.getAge(id);

    }

    @GetMapping(value="/ok")
    public List findGirlByNameAndId(){
        Query<Girl> q = new Query<Girl>();
        List list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        List list2 = new ArrayList();
        Double a1 = 100.0;
        Double a2 = 190.0;
        list2.add(a1);
        list2.add(a2);
        Filter f1 = new Filter("id","eq","1");
        Filter f2 = new Filter("money","eq","100");
        Filter f3 = new Filter("id","eq","2");
        Filter f4 = new Filter("id","ne","1");
        Filter f5 = new Filter("cup","eq","B");
        Filter f6 = new Filter("cup","eq","D");
        Filter f7 = new Filter("id","in",list1);
        Filter f8 = new Filter("money","in",list2);
        Filter f9 = new Filter("age","eq","19");
        q.and(f1,f2,f5,f9);
        return a.findAll(q);
    }

}
