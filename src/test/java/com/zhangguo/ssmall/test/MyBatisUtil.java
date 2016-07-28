package com.zhangguo.ssmall.test;


import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public abstract class MyBatisUtil {
	
	public static SqlSessionFactory getSqlSessionFactory(){
		// 获得环境配置文件流
		InputStream config = MyBatisUtil.class.getClassLoader().getResourceAsStream("MyBatisCfg.xml");
		// 创建sql会话工厂
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
		return factory;
	}
	
	//获得会话
	public static SqlSession getSession(){
		return getSqlSessionFactory().openSession(true);
	}
	
	/**
	 * 获得得sql会话
	 * @param isAutoCommit 是否自动提交，如果为false则需要sqlSession.commit();rollback();
	 * @return sql会话
	 */
	public static SqlSession getSession(boolean isAutoCommit){
		return getSqlSessionFactory().openSession(isAutoCommit);
	}
	
}
