package com.firstboot.repository;

import com.firstboot.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by acer on 2018/1/13.
 */
public interface GirlRepository extends JpaRepository<Girl,Integer>{


    //通过年龄查询

    public List<Girl> findByAge(Integer age);
}
