package com.firstboot;

import com.firstboot.domain.Girl;
import com.firstboot.service.GirlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by acer on 2018/1/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTest {

    @Autowired
    private GirlService girlService;

    @Test
    public void findOneTest(){
        Girl girl=girlService.findOne(2);
        Assert.assertEquals(new Integer(3),girl.getAge());
    }
}
