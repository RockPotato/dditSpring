package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.prod.model.ProdVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

@Repository("prodDao")
public class ProdDaoImpl implements IProdDao{

	@Override
	public List<ProdVO> getAllProd() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		List<ProdVO> list =openSession.selectList("prod.getAllProd");
		return list;
	}

	@Override
	public List<ProdVO> selectProd(String prod_lgu) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		List<ProdVO> list =openSession.selectList("prod.selectProd",prod_lgu);
		return list;
	}

}
