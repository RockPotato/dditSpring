package kr.or.ddit.user.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

public class UserControllerTest extends WebTestConfig {

	private static final String USER_INSERT_TEST_ID = "sallyTest2";
	@Resource(name="userService")
	private IUserService userService;
	
	@Before
	public void initData(){
		userService.deleteUser(USER_INSERT_TEST_ID);
	}
	
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
	
	@Test
	public void testUserForm() throws Exception{
		MvcResult mvcResult = mockMvc.perform(get("/user/userForm")).andReturn();
		
		assertEquals("user/userForm", mvcResult.getModelAndView().getViewName());
	}

	/**
	* Method : testUserForm_post
	* 작성자 : PC04
	* 변경이력 :
	* Method 설명 : 사용자 등록 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void testUserForm_post_success() throws Exception{
		/***Given***/
		File profileFile =new File("d:\\picture\\sally.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile","sally.png", "image", fis);
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
				.param("userId", USER_INSERT_TEST_ID)
				.param("userNm", "샐리테스트")
				.param("alias", "병아리")
				.param("addr1", "대전시 중구 대흥로 76")
				.param("addr2", "2층 206호")
				.param("zipcode", "34942")
				.param("pass", "testpass"))
				.andExpect(view().name("redirect:/user/userPagingList"))
				.andReturn();
		
		/***When***/
		HttpSession session = mvcResult.getRequest().getSession();
		assertEquals("정상 등록 되었습니다.", session.getAttribute("msg"));
		
		/***Then***/
	}
	
	/**
	* Method : testUserForm_post_fail_duplicateUser
	* 작성자 : PC04
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 등록 요청(중복 사용자로 인한 등록 실패 케이스) 테스트
	*/
	@Test
	public void testUserForm_post_fail_duplicateUser() throws Exception{
		/***Given***/
		File profileFile =new File("d:\\picture\\sally.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile","sally.png", "image", fis);
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
				.param("userId", "sally")
				.param("userNm", "샐리테스트")
				.param("alias", "병아리")
				.param("addr1", "대전시 중구 대흥로 76")
				.param("addr2", "2층 206호")
				.param("zipcode", "34942")
				.param("pass", "testpass"))
				.andExpect(view().name("/user/userForm"))
				.andReturn();
		
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String msg = (String) mav.getModel().get("msg");
		assertEquals("중복체크에 실패 했습니다.", msg);
		
		/***Then***/
	}
	
	
	/**
	* Method : testUserForm_post_fail_insertError
	* 작성자 : PC04
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 등록 (zipcode 사이즈 sql 에러) 테스트
	*/
	@Test
	public void testUserForm_post_fail_insertError() throws Exception{
		/***Given***/
		File profileFile =new File("d:\\picture\\sally.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile","sally.png", "image", fis);
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
				.param("userId", USER_INSERT_TEST_ID)
				.param("userNm", "샐리테스트")
				.param("alias", "병아리")
				.param("addr1", "대전시 중구 대흥로 76")
				.param("addr2", "2층 206호")
				.param("zipcode", "3494234942")
				.param("pass", "testpass"))
				.andExpect(view().name("/user/userForm"))
				.andReturn();
		
		/***When***/
		
		/***Then***/
	}
	
	
	@Test
	public void testUserModifyForm() throws Exception{
		MvcResult mvcResult = mockMvc.perform(get("/user/userModifyForm").param("userId", "james")).andReturn();
		
		assertEquals("user/userModify", mvcResult.getModelAndView().getViewName());
	}
	
	@Test
	public void testUserModifyForm_post() throws Exception{
		/***Given***/
		File profileFile =new File("d:\\picture\\sally.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile","sally.png", "image", fis);
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userModifyForm").file(file)
				.param("userId", "sallyTest")
				.param("userNm", "샐리테스트")
				.param("alias", "병아리")
				.param("addr1", "대전시 중구 대흥로 72")
				.param("addr2", "2층 201호")
				.param("zipcode", "34942")
				.param("pass", "testpass"))
				.andExpect(view().name("user/user"))
				.andReturn();
		
		/***When***/
		
		
		/***Then***/
	}
	@Test
	public void testUserModifyForm_post_fail() throws Exception{
		/***Given***/
		File profileFile =new File("d:\\picture\\sally.png");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile","sally.png", "image", fis);
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userModifyForm").file(file)
				.param("userId", "brown1")
				.param("userNm", "샐리테스트")
				.param("alias", "병아리")
				.param("addr1", "대전시 중구 대흥로 72")
				.param("addr2", "2층 201호")
				.param("zipcode", "34942")
				.param("pass", "testpass"))
				.andExpect(view().name("user/userPagingList"))
				.andReturn();
		
		/***When***/
		
		
		/***Then***/
	}
}
