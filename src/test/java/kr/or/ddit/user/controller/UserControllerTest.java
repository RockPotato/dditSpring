package kr.or.ddit.user.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVO;

public class UserControllerTest extends WebTestConfig {

	/**
	 * Method : testUserAllList 작성자 : PC04 변경이력 : Method 설명 : 사용자 전체 조회 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUserAllList() throws Exception {
		/*** Given ***/

		/*** When ***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userAllList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();

		String viewName = mav.getViewName();
		List<UserVO> userList = (List<UserVO>) mav.getModel().get("userList");

		/*** Then ***/
		assertEquals("user/userAllList", viewName);
		assertNotNull(userList);
		assertTrue(userList.size() > 100);
	}

	/**
	* Method : testUserPagingList
	* 작성자 : PC04
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 페이징 리스트 조회 테스트
	*/
	@Test
	public void testUserPagingList() throws Exception {
		/*** Given ***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userPagingList")).andReturn();

		/*** When ***/
		ModelAndView mav = mvcResult.getModelAndView();

		String viewName = mav.getViewName();
		List<UserVO> userList = (List<UserVO>) mav.getModel().get("userList");
		int userCnt = (int) mav.getModel().get("userCnt");
		int page = (int) mav.getModel().get("page");
		int pageSize = (int) mav.getModel().get("pageSize");
		
		/*** Then ***/
		assertEquals("user/userPagingList", viewName);
		assertEquals(1, page);
		assertEquals(10, pageSize);
		assertTrue(userCnt > 0);
		assertNotNull(userList);
		assertTrue(userList.size() == 10);

	}
	/**
	* Method : testUser
	* 작성자 : PC04
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 상세 보기 조회 테스트
	*/
	@Test
	public void testUser() throws Exception {
		/*** Given ***/
		MvcResult mvcResult = mockMvc.perform(get("/user/user").param("userId", "brown")).andReturn();
		
		/*** When ***/
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();
		UserVO userVo =  (UserVO) mav.getModel().get("userVo");
		
		/*** Then ***/
		assertEquals("user/user", viewName);
		assertNotNull(userVo);
		assertEquals("곰돌이",userVo.getAlias());
		
	}
	
//	@Test
//	public void testProfileImg(){}

}
