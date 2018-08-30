package com.firstboot.exception;

import com.firstboot.enums.ResultEnum;

/**
 * Created by acer on 2018/1/13.
 */
public class GirlException extends RuntimeException{

    private Integer code;

    public GirlException(ResultEnum resultEnum)throws Exception{
        super(resultEnum.getMsg());
        this.code=resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
