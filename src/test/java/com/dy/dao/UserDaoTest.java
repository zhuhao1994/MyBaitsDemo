package com.dy.dao;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.dy.entity.User;

public class UserDaoTest {
    @Test
    public void insertUser(){
        SqlSessionFactory sqlSessionFactory = getSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userMapper = sqlSession.getMapper(UserDao.class);
        User insertUser = new User();
        for(int i = 0 ;i < 20 ; i++)
        {
            insertUser.setId(i);
            insertUser.setName("zhuhao"+i);
            insertUser.setAge(20);
            insertUser.setPassword("zhuhao"+i);
            userMapper.insertUser(insertUser);
        }

    }
	@Test
	public void updateUserById() {
		SqlSessionFactory sqlSessionFactory = getSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userMapper = sqlSession.getMapper(UserDao.class);
        User insertUser = new User();
        insertUser.setId(1);
        insertUser.setName("朱昊");
        insertUser.setAge(20);
        insertUser.setPassword("cccccc");
        userMapper.updateUser(insertUser);
	}

    @Test
    public void findUserById() {
        SqlSessionFactory sqlSessionFactory = getSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //UserDao userMapper = sqlSession.getMapper(UserDao.class);
        //User user = userMapper.findUserById(1);
        //Assert.assertNotNull("没找到数据", user);
    }
	//Mybatis 通过SqlSessionFactory获取SqlSession, 然后才能通过SqlSession与数据库进行交互
	private static SqlSessionFactory getSessionFactory() {  
        SqlSessionFactory sessionFactory = null;  
        String resource = "mybatis-conf.xml";  
        try {  
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        return sessionFactory;


    }

	
}


//		Configuration con = sqlSessionFactory.getConfiguration();
//		Map<String, Class<?>> typeMap = con.getTypeAliasRegistry().getTypeAliases();
//		for(Entry<String, Class<?>> entry: typeMap.entrySet()) {
//			System.out.println(entry.getKey() + " ================> " + entry.getValue().getSimpleName());
//		}