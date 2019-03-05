package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.test.LogicTestConfig;

public class ProdDaoImplTest extends LogicTestConfig{

	private SqlSession openSession;
	@Resource(name="prodDao")
	private IProdDao prodDao;

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
	public void testGetAllProd() {
		List<ProdVO> allProd = prodDao.getAllProd();
		assertNotNull(allProd);
	}
	
	@Test
	public void testSelectProd() {
		List<ProdVO> selectProd = prodDao.selectProd("p101");
		assertNotNull(selectProd);
		
	}

}
