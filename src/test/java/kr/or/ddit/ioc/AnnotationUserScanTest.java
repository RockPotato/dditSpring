package kr.or.ddit.ioc;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.service.IUserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-user.xml")
public class AnnotationUserScanTest {

	private Logger logger = LoggerFactory.getLogger(AnnotationUserScanTest.class);
	
	@Resource(name="userDaoImpl")
	private IUserDao userDao;
	
	@Resource(name="userServiceImpl")
	private IUserService userService;	
	
	@Test
	public void testDaoScan() {
		List<String> allUsers = userDao.getAllUsers();
		for (String str : allUsers) {
			logger.debug("dao User : {}",str);
		}
		assertNotNull(allUsers);
		
	}
	@Test
	public void testServiceScan() {
		List<String> allUsers = userService.getAllUsers();
		assertNotNull(allUsers);
		for (String str : allUsers) {
			logger.debug("service User : {}",str);
		}
		assertNotNull(allUsers);
	}

}
