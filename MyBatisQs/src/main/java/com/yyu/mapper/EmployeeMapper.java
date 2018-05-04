package com.yyu.mapper;

import com.yyu.domain.Employee;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "selectWithParam")
    List<Employee> selectWithParam(Map<String, Object> param);
}
