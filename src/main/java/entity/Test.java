package entity;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test {

	public static void main(String[] args) throws IOException {
		//下面4行读取Mybatis配置文件并创建session
		String resource = "mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		String statement = "mapper.userMapper.getUser";//namespace+id找到映射sql串
		User user = sqlSession.selectOne(statement, 2);//执行返回一个唯一对象，第二个参数是查询的参数值
		sqlSession.close();
		System.out.println(user);
		testAdd();

	}

	//创建用户
	public static void testAdd() throws IOException{
		String resource = "mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		String statement = "mapper.userMapper.addUser";
		User user = new User();
		user.setUsername("yy");
		user.setPassword("123456");
		int retResult = sqlSession.insert(statement,user);//SqlSession自带方法
		sqlSession.commit();
		sqlSession.close();
		}

}
