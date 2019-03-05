package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.prod.model.LprodVO;
import kr.or.ddit.util.model.PageVO;

public interface ILprodService {
	public List<LprodVO> getAllLprod();
	public LprodVO selectLprod(String lprod_id);
	Map<String,Object> lpordpageList(PageVO pageVo);
}
