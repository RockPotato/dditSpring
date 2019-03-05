package kr.or.ddit.prod.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.test.LogicTestConfig;

public class ProdServiceImplTest extends LogicTestConfig{

	@Resource(name="prodService")
	private IProdService prodService;
	
	@Test
	public void testGetAllProd() {
		List<ProdVO> allProd = prodService.getAllProd();
		assertNotNull(allProd);
	}
	
	@Test
	public void testSelectProd() {
		List<ProdVO> selectProd = prodService.selectProd("p101");
		assertNotNull(selectProd);
		
	}

}
