package com.yyu.mapper;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class EmployeeDynaSqlProvider {
    public String selectWithParam(final Map<String, Object> param){
        return new SQL(){
            {
                SELECT("*");
                FROM("tb_employee");
                if (param.get("id") != null){
                    WHERE(" id = #{id} ");
                }
                if (param.get("loginname") != null){
                    WHERE(" loginname = #{loginname} ");
                }
            }
        }.toString();
    }
}
