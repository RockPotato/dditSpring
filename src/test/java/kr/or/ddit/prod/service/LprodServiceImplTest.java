package kr.or.ddit.prod.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.prod.model.LprodVO;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.util.model.PageVO;

public class LprodServiceImplTest extends LogicTestConfig{

	@Resource(name="lprodService")
	ILprodService lprodService;
	
	@Test
	public void testGetAllLprod() {
		List<LprodVO> allProd = lprodService.getAllLprod();
		assertNotNull(allProd);
	}
	
	@Test
	public void testSelectLprod() {
		
		LprodVO selectLprod = lprodService.selectLprod("1");
		assertEquals("컴퓨터제품",selectLprod.getLprod_nm());
	}
	
	@Test
	public void testLprodpageList() {
		/*** Given ***/

		PageVO pageVo = new PageVO(1, 10);

		/*** When ***/
		Map<String, Object> lprodpageList = lprodService.lpordpageList(pageVo);

		/*** Then ***/
		assertNotNull(lprodpageList);
		assertEquals(2, lprodpageList.size());
	}

}
