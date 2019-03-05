package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.prod.model.ProdVO;

public interface IProdService {
	public List<ProdVO> getAllProd();
	public List<ProdVO> selectProd(String prod_lgu);
}
