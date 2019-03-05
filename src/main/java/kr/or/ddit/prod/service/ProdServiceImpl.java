package kr.or.ddit.prod.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.model.ProdVO;

@Service("prodService")
public class ProdServiceImpl implements IProdService{
	
	@Resource(name="prodDao")
	private IProdDao dao;
	

	public ProdServiceImpl() {
	}

	@Override
	public List<ProdVO> getAllProd() {
		return dao.getAllProd();
	}

	@Override
	public List<ProdVO> selectProd(String prod_lgu) {
		return dao.selectProd(prod_lgu);
	}

}
