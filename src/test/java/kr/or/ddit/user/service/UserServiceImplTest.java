package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

public class UserServiceImplTest extends LogicTestConfig{

	@Resource(name="userService")
	IUserService service;
	
	//getAllUser 메소드를 테스트 하는 메소드 작성
	@Test
	public void testGetAllUser() {
		/***Given***/
		/***When***/
		List<UserVO> allUser = service.getAllUser();
		/***Then***/
		for (int i = 0; i < allUser.size(); i++) {
			System.out.println(allUser.get(i));
		}
		
		assertEquals(106, allUser.size());
		assertNotNull(service.getAllUser());
	}
	
	@Test
	public void testSelectUser(){
		UserVO selectUser = service.selectUser("moon");
			System.out.println(selectUser);
		assertEquals("moon",selectUser.getUserId());
	}
	
	@Test
	public void testInsertUser(){
		UserVO userVo = new UserVO();
		userVo.setUserId("haha");
		userVo.setUserNm("haha");
		userVo.setAlias("haha");
		userVo.setPass("haha1234");
		userVo.setAddr1("haha");
		userVo.setAddr2("haha");
		userVo.setZipcode("34942");
		int cnt = service.insertUser(userVo);
		assertEquals(1, cnt);
	}
	
	@Test
	public void testDeleteUser(){
		String userId="haha";
		int cnt = service.deleteUser(userId);
		assertEquals(1, cnt);
	}
	
	@Test
	public void testUpdateUser(){
		UserVO userVo = new UserVO();
		userVo.setUserId("haha");
		userVo.setUserNm("haha");
		userVo.setAlias("haha");
		userVo.setPass("haha1234");
		userVo.setAddr1("haha");
		userVo.setAddr2("haha");
		userVo.setZipcode("12344");
		int cnt = service.updateUser(userVo);
		assertEquals(1, cnt);
	}
//	@Test
//	public void testEncryptPass(){
//		String userId = "userId1";
//		service.encryptPass(userId);
//	}
//	@Test
//	public void testEncryptPassAll(){
//		service.encryptPassAll();
//	}
}
