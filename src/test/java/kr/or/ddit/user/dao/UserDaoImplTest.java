package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

public class UserDaoImplTest  extends LogicTestConfig{
	private SqlSession openSession;
	@Resource(name="userDao")
	private IUserDao userDao;

	@Before
	public void setup() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory
				.getSqlSessionFactory();
		openSession = sqlSessionFactory.openSession();
	}

	@After
	public void tearDown() {
		openSession.commit();
		openSession.close();
	}

	// getAllUser 메소드를 테스트 하는 메소드 작성
	@Test
	public void testGetAllUser() {
		/*** Given ***/
		/*** When ***/
		List<UserVO> allUser = userDao.getAllUser(openSession);
		/*** Then ***/
		for (int i = 0; i < allUser.size(); i++) {
			System.out.println(allUser.get(i));
		}
		assertEquals(106, allUser.size());
		assertNotNull(userDao.getAllUser(openSession));
	}

	@Test
	public void testSelectUser() {
		/*** Given ***/
		/*** When ***/
		UserVO selectUser = userDao.selectUser(openSession, "moon");
		System.out.println(selectUser);
		/*** Then ***/
		assertEquals("moon", selectUser.getUserId());
	}

	@Test
	public void testInsertUser() {
		/*** Given ***/
		UserVO userVo = new UserVO();
		userVo.setUserId("haha");
		userVo.setUserNm("haha");
		userVo.setAlias("haha");
		userVo.setPass("haha1234");
		userVo.setAddr1("haha");
		userVo.setAddr2("haha");
		userVo.setZipcode("34942");

		userDao.deleteUser(openSession, "haha");

		/*** When ***/
		int cnt = userDao.insertUser(openSession, userVo);
		/*** Then ***/
		assertEquals(1, cnt);
	}

	@Test
	public void testSelectUserPagingList() {
		/*** Given ***/

		PageVO pageVo = new PageVO(1, 10);

		/*** When ***/
		List<UserVO> userList = userDao
				.selectUserPagingList(openSession, pageVo);

		/*** Then ***/
		assertNotNull(userList);
		assertEquals(10, userList.size());

	}

	@Test
	public void testGetUserCnt() {
		/*** Given ***/

		/*** When ***/
		int userCnt = userDao.getUserCnt(openSession);

		/*** Then ***/
		assertNotNull(userCnt);
		assertEquals(106, userCnt);

	}

	@Test
	public void testPagination() {
		/*** Given ***/
		int userCnt = 105;
		int pageSize = 10;

		/*** When ***/
		int lastPage = userCnt / pageSize + (userCnt % pageSize > 0 ? 1 : 0);

		/*** Then ***/
		assertEquals(11, lastPage);

	}

	@Test
	public void testPagination2() {
		/*** Given ***/
		int userCnt = 105;
		int pageSize = 10;

		/*** When ***/
		int lastPage = userCnt / pageSize + (userCnt % pageSize > 0 ? 1 : 0);

		/*** Then ***/
		assertEquals(11, lastPage);

	}
	
	@Test
	public void testUpdateUser(){
		UserVO vo = new UserVO();
		vo.setUserId("haha");
		vo.setPass("haha");
		vo.setZipcode("45678");
		vo.setAddr1("12345");
		vo.setAddr2("12345");
		vo.setUserNm("12345");
		vo.setAlias("haha");
		
		int cnt = userDao.updateUser(openSession, vo);
		assertEquals(1, cnt);
	}

}
