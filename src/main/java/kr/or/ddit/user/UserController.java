package kr.or.ddit.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.model.PageVO;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Resource(name="userService")
	private IUserService userService;
	
	
	
	
	/**
	* Method : userAllList
	* 작성자 : PC04
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@RequestMapping("/userAllList")
	public String userAllList(Model model){
		//userService 사용자 전체 정보를 조회
		//List<UserVO> userList = userService.getAllUser();
		List<UserVO> userList = userService.getAllUser();
		
		//사용 전체 정보를 request 객체에 속성으로 설정
		// request.setAttribute("userList",userList);
		model.addAttribute("userList",userList);
		
		//webapp/user/userList.jsp 로 forward
		// request.getRequestDispatcher("/user/userAllList.jsp").forward(request,response);
		return "user/userAllList";
	}
	
	@RequestMapping("/userPagingList")
	public String userPagingList(PageVO pageVo,Model model){
		
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);
		
		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize",pageVo.getPageSize());
		model.addAttribute("page",pageVo.getPage());
		
		return "user/userPagingList";
	}
}
