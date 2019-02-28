package kr.or.ddit.ranger.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.test.LogicTestConfig;


public class RangerDaoTest extends LogicTestConfig{

	@Resource(name="rangerDao")
	private IRangerDao rangerDao;
	
	@Test
	public void testGetRanger_minusIndex() {
		/***Given***/
		int index = 5;
		/***When***/
		String ranger = rangerDao.getRanger(index);
		/***Then***/
		assertEquals("james", ranger);
	}

}
