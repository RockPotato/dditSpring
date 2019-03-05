package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.model.LprodVO;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.prod.service.ILprodService;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.util.model.PageVO;

@RequestMapping("/prod")
@Controller
public class ProdController {

	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	@Resource(name="prodService")
	private IProdService prodService;
	
	@RequestMapping("/lprodList")
	public String lprodList(Model model){
		List<LprodVO> allLprod = lprodService.getAllLprod();
		model.addAttribute("allLprod",allLprod);
		return "prod/lprodList";
	}
	
	@RequestMapping("/LprodpageList")
	public String LprodpageList(Model model,PageVO pageVo){
		
		Map<String,Object> resultList = lprodService.lpordpageList(pageVo);
		model.addAllAttributes(resultList);
		model.addAttribute("page", pageVo.getPage());
		model.addAttribute("pageSize", pageVo.getPageSize());
		
		return "prod/lprodpageList";
	}
	
	@RequestMapping("/prodList")
	public String prodList(@RequestParam("lprodGu")String lprodGu,Model model){
		List<ProdVO> selectProd = prodService.selectProd(lprodGu);
		model.addAttribute("selectProd",selectProd);
		
		return "prod/prodList";
	}
}
