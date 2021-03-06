package kr.or.ddit.ranger.service;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.test.LogicTestConfig;



public class RangerServiceTest extends LogicTestConfig{

	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	@Test
	public void testGetRanger_minusIndex() {
		/***Given***/
		int index = -1;
		/***When***/
		String ranger = rangerService.getRanger(index);
		/***Then***/
		assertEquals("brown", ranger);
	}
	@Test
	public void testGetRanger_overFlowIndex() {
		/***Given***/
		int index = 5;
		/***When***/
		String ranger = rangerService.getRanger(index);
		/***Then***/
		assertEquals("james", ranger);
	}

}
