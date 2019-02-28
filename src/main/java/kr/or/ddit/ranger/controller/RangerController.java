package kr.or.ddit.ranger.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.ranger.model.RangerVO;
import kr.or.ddit.ranger.service.IRangerService;

@SessionAttributes({"boardGb","boardGb2"})
@RequestMapping("/ranger")
@Controller
public class RangerController {
	
	private Logger logger = LoggerFactory.getLogger(RangerController.class);
	
	@Resource(name="rangerService")
	private IRangerService rangerService;

	
	//@ModelAttribute 가 적용된 메소드는
	//@RequestMapping이 적용된 메소드가 실행되기 전에
	// 먼저 실행되고 리턴하는 객체를 model객체에 속성으로 넣어준다.
	@ModelAttribute("boardGb")
	public List<String> boardGb(){
		logger.debug("boardGb");
		List<String> boardGbList = new ArrayList<>();
		boardGbList.add("공지사항");
		boardGbList.add("Q&A");
		boardGbList.add("경조사");
		boardGbList.add("영업게시판");
		return boardGbList;
	}
	
	@ModelAttribute("boardGb2")
	public List<String> boardGb2(){
		logger.debug("boardGb");
		List<String> boardGbList = new ArrayList<>();
		boardGbList.add("공지사항2");
		boardGbList.add("Q&A2");
		boardGbList.add("경조사2");
		boardGbList.add("영업게시판2");
		return boardGbList;
	}
	
	
	/**
	* Method : getRangers
	* 작성자 : PC04
	* 변경이력 :
	* @return
	* Method 설명 : 전체 레인저스 리스트를 조회
	*/
//	localhost/ranger/getRangers 라고 요청시 밑의 메소드에서 요청을 처리
	@RequestMapping("/getRangers")
	public String getRangers(Model model) {
		List<String> rangers = rangerService.getRangers();
		
		//request.setAttribute("rangers",rangers);
		model.addAttribute("rangers",rangers);
		
		/*
		 *	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
				<property name="prefix" value="/WEB-INF/views/"/>
				<property name="suffix" value=".jsp"/>
			</bean>
		 */
		return "ranger/rangerList";
	}
	
	/**
	* Method : getRangerMav
	* 작성자 : PC04
	* 변경이력 :
	* @return
	* Method 설명 : 레인저스 전체 정보를 ModelAndView 객체를 통해 리턴
	*/
	@RequestMapping(path="/getRangerMav")
	public ModelAndView getRangerMav(){
	/*	List<String> rangers = rangerService.getRangers();
		model.addAttribute("rangers",rangers);
		return "ranger/rangerList"; 	*/
		
		List<String> rangers = rangerService.getRangers();
		ModelAndView mav = new ModelAndView();
		mav.addObject("rangers", rangers);
		
		mav.setViewName("ranger/rangerList");
		return mav;
	}

//	localhost/ranger/getRanger?listIndex=2 라고 요청시 아래 메소드에서 요청을 처리
	// VO 객체에 파라미터 명과 동일한 이름 필드가 존재하면
	// 파라미터를 해당 필드에 바인딩 시켜준다.
	@RequestMapping("/getRanger")
	public String getRanger(@ModelAttribute("rVo")RangerVO rangerVo,Model model/*, HttpSession session*/) {
//		RangerVO rangerVo = new RangerVO();
//		rangerVo.setListIndex(request.getParameter(listIndex));
//		getRanger(rangerVo,model);
		
		String ranger = rangerService.getRanger(rangerVo.getListIndex());
		model.addAttribute("ranger",ranger);
		//model.addAttribute("rangerVo",rangerVo);
		return "ranger/ranger";
	}
	
//	@RequestMapping("/getRanger")
//	public String getRanger(HttpServletRequest req,Model model) {
//		//
//		int listIndex = Integer.parseInt(req.getParameter("listIndex"));
//		String ranger = rangerService.getRanger(listIndex);
//		model.addAttribute("ranger",ranger);
//		return "ranger/ranger";
//	}
	//@PathVariable : url의 일부 값을 변수로 치환해서 개발자가
	// 					해당 값을 사용하기 쉽게 받을 수 있는 @
	@RequestMapping(path="/pathVariable/{userId}")
	public String pathVariable(@PathVariable("userId")String userId){
		logger.debug("userId : {}",userId);
		return "ranger/ranger";
	}
	
	@RequestMapping(path="/requestHeader")
	public String requestHeader(@RequestHeader("Accept")String acceptHeader){
		logger.debug("acceptHeader : {}",acceptHeader);
		return "ranger/ranger";
	}
	
	@RequestMapping(path="/getRangerParam")
	public String getRangerParam(@RequestParam(name="listIndex", defaultValue="0")int listIndex, Model model){
		
		String ranger = rangerService.getRanger(listIndex);
		model.addAttribute("ranger",ranger);
		return "ranger/ranger";
	}
}
