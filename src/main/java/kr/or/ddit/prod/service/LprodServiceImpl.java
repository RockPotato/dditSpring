package kr.or.ddit.prod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.prod.dao.ILprodDao;
import kr.or.ddit.prod.dao.LprodDaoImpl;
import kr.or.ddit.prod.model.LprodVO;
import kr.or.ddit.util.model.PageVO;

@Service("lprodService")
public class LprodServiceImpl implements ILprodService {

	@Resource(name="lprodDao")
	private ILprodDao dao;

	public LprodServiceImpl() {
	}

	@Override
	public List<LprodVO> getAllLprod() {
		return dao.getAllLprod();
	}

	@Override
	public LprodVO selectLprod(String lprod_id) {
		return dao.selectLprod(lprod_id);
	}

	@Override
	public Map<String, Object> lpordpageList(PageVO pageVo) {
		Map<String, Object> resultList = new HashMap<String, Object>();
		resultList.put("lprodList", dao.lprodpageList(pageVo));
		resultList.put("lprodCnt", dao.getLprodCnt());
		return resultList;
	}
}
