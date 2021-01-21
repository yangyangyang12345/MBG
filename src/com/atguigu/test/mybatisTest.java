package com.atguigu.test;



import com.atguigu.mapper.UserMapper;
import com.atguigu.model.User;
import com.atguigu.model.UserExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class mybatisTest {
    SqlSessionFactory factory = null;
    @Before
    public void init(){
//        获取配置文件名
        String fileName = "mybatis-config.xml";
//        获得工厂建造者
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//        获取工厂的输入流
        try {
            InputStream inputStream = Resources.getResourceAsStream(fileName);
//            获得工厂
            factory = builder.build(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void t1(){
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        new 查询条件的对象
        UserExample example = new UserExample();
//        Criteria是具体条件的内部类对象
        UserExample.Criteria criteria = example.createCriteria();
//        向具体条件的对象里放条件
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(5);
        list.add(7);
        list.add(9);

        criteria.andIdIn(list);
       /* criteria.andLastNameLike("l%");
        UserExample.Criteria criteria1 = example.createCriteria();
        criteria1.andSexEqualTo(1);
        example.or(criteria1);*/
        List<User> users = mapper.selectByExample(example);
        System.out.println(users);
        sqlSession.close();

    }
}
