package kr.or.ddit.prod.dao;

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
import kr.or.ddit.prod.model.LprodVO;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

public class LprodDaoImplTest extends LogicTestConfig{

	private SqlSession openSession;
	@Resource(name="lprodDao")
	private ILprodDao lprodDao;

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
	
	@Test
	public void testGetAllLprod() {
		List<LprodVO> allProd = lprodDao.getAllLprod();
		assertNotNull(allProd);
	}
	
	@Test
	public void testSelectLprod() {
		
		LprodVO selectLprod = lprodDao.selectLprod("1");
		assertEquals("컴퓨터제품",selectLprod.getLprod_nm());
	}
	
	@Test
	public void testLprodpageList() {
		/*** Given ***/

		PageVO pageVo = new PageVO(1, 10);

		/*** When ***/
		List<LprodVO> lprodpageList = lprodDao.lprodpageList(pageVo);

		/*** Then ***/
		assertNotNull(lprodpageList);
		assertEquals(10, lprodpageList.size());
	}

}
