package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVO;

public class UserDaoTest extends LogicTestConfig{
	
	@Resource(name="userDao")
	private IUserDao userDao;

	@Test
	public void testGetUser() {
		List<UserVO> allUser = userDao.getAllUser();
		assertNotNull(allUser);
		assertTrue(allUser.size()>100);
	}

}
