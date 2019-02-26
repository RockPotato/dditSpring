package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-placeholder.xml")
public class DbPropertiesTest {

	private Logger logger = LoggerFactory.getLogger(DbPropertiesTest.class);
	
	@Resource(name="dbProperties")
	private DbProperties dbProperties;
	
	/**
	* Method : testPlaceHolder
	* 작성자 : PC04
	* 변경이력 :
	* Method 설명 : property placeholder 테스트
	*/
	@Test
	public void testPlaceHolder() {
		/***Given***/
		
		/***When***/

		String url = dbProperties.getUrl();
		String driverClassName = dbProperties.getDriverClassName();
		String username = dbProperties.getUsername();
		String password = dbProperties.getPassword();
		/***Then***/
		
		logger.debug("url : {}",url);
		logger.debug("driver : {}",driverClassName);
		logger.debug("username : {}",username);
		logger.debug("password : {}",password);
		
		assertEquals("oracle.jdbc.driver.OracleDriver",driverClassName);
		assertEquals("jdbc:oracle:thin:@localhost:1521:XE",url);
		assertEquals("PC04_PC",username);
		assertEquals("java",password);
	}

}
