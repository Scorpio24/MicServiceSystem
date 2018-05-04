package com.yyu.tst;

import com.yyu.domain.Employee;
import com.yyu.domain.User;
import com.yyu.mapper.EmployeeMapper;
import com.yyu.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTest {
    public static void main(String[] args)throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        EmployeeMapper em = session.getMapper(EmployeeMapper.class);
        MyBatisTest t = new MyBatisTest();
        t.testSelectWithParam(em);

        session.commit();
        session.close();
    }

    public void testSelectWithParam(EmployeeMapper em){
        Map<String , Object> param = new HashMap<String, Object>();
        param.put("loginname", "jack");
        param.put("password", "123456");
        List<Employee> list = em.selectWithParam(param);
        System.out.println(list);
    }
}
