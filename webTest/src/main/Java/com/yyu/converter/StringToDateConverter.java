package com.yyu.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {
    private String datePattern;
    public void setDatePattern(String datePattern)
    {
        this.datePattern = datePattern;
    }

    @Override
    public Date convert(String date)
    {
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat(this.datePattern);
            return dateFormat.parse(date);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("日期转换失败！");
            return null;
        }
    }
}
