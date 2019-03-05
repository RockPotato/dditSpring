package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.prod.model.LprodVO;
import kr.or.ddit.util.model.PageVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

@Repository("lprodDao")
public class LprodDaoImpl implements ILprodDao {

	@Override
	public List<LprodVO> getAllLprod() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory
				.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		List<LprodVO> list = openSession.selectList("lprod.getAllLprod");
		openSession.close();

		return list;
	}

	@Override
	public LprodVO selectLprod(String lprod_id) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory
				.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		LprodVO lprodVo = (LprodVO) openSession.selectOne("lprod.selectLprod",
				lprod_id);
		openSession.close();
		return lprodVo;
	}

	@Override
	public List<LprodVO> lprodpageList(PageVO pageVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory
				.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<LprodVO> lprodVo = sqlSession.selectList(
				"lprod.selectLprodPageList", pageVo);
		sqlSession.close();
		return lprodVo;
	}

	@Override
	public int getLprodCnt() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int LprodCnt = sqlSession.selectOne("lprod.getLprodCnt");
		return LprodCnt;
	}

}
