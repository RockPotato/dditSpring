package kr.or.ddit.prod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import kr.or.ddit.prod.service.ILprodService;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.test.WebTestConfig;

public class ProdControllerTest extends WebTestConfig{

	@Test
	public void testLprodList() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/prod/lprodList")).andReturn();
		assertEquals("prod/lprodList", mvcResult.getModelAndView().getViewName());
	}
	@Test
	public void testLprodpageList() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/prod/LprodpageList")).andReturn();
		assertEquals("prod/lprodpageList", mvcResult.getModelAndView().getViewName());
		
	}
	@Test
	public void testprodList() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/prod/prodList").param("lprodGu", "P101")).andReturn();
		assertEquals("prod/prodList", mvcResult.getModelAndView().getViewName());
		
	}

}
