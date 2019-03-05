package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.prod.model.ProdVO;

public interface IProdDao {
	public List<ProdVO> getAllProd();
	public List<ProdVO> selectProd(String prod_lgu);
}
