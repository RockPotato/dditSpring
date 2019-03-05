package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.prod.model.LprodVO;
import kr.or.ddit.util.model.PageVO;

public interface ILprodDao {
	public List<LprodVO> getAllLprod();
	public LprodVO selectLprod(String lprod_id);
	public List<LprodVO> lprodpageList(PageVO pageVo);
	int getLprodCnt();
}
