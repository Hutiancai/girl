package com.firstboot.service;

import com.firstboot.domain.Girl;
import com.firstboot.enums.ResultEnum;
import com.firstboot.exception.GirlException;
import com.firstboot.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by acer on 2018/1/13.
 */
@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Transactional
    public void insertTwo(){
        Girl girla=new Girl();
        girla.setAge(1);
        girlRepository.save(girla);

        Girl girlb=new Girl();
        girlb.setAge(2);
        girlRepository.save(girlb);

    }

    public void getAge(Integer id) throws Exception{
        Girl girl=girlRepository.findOne(id);
        Integer age =girl.getAge();
        if(age<10){
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if(age>10&&age<16){
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
    }


    public Girl findOne(Integer id){
        return girlRepository.findOne(id);
    }

}
