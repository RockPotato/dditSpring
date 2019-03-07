package kr.or.ddit.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
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
//		return "user/userAllList";
		return "userAllListTiles";
	}
	
	/**
	* Method : userPagingList
	* 작성자 : PC04
	* 변경이력 :
	* @param pageVo
	* @param model
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	*/
	@RequestMapping("/userPagingList")
	public String userPagingList(PageVO pageVo,Model model){
		
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);
		
		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize",pageVo.getPageSize());
		model.addAttribute("page",pageVo.getPage());
		
//		return "user/userPagingList";
		return "userPagingListTiles";
	}
	
	/**
	* Method : userPagingListAjaxView
	* 작성자 : PC04
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 페이징 리스트 view
	*/
	@RequestMapping("/userPagingListAjaxView")
	public String userPagingListAjaxView(){
		return "userPagingListAjaxTiles";
	}
	
	/**
	* Method : userPagingListAjax
	* 작성자 : PC04
	* 변경이력 :
	* @param pageVo
	* @param model
	* @return
	* Method 설명 : 사용자 페이지 리스트 ajax 요청처리
	*/
	@RequestMapping("/userPagingListAjax")
	public String userPagingListAjax(PageVO pageVo, Model model) {

		// PageVO pageVo = new PageVO(page, pageSize);

		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);
		model.addAllAttributes(resultMap);

		model.addAttribute("pageSize", pageVo.getPageSize());
		model.addAttribute("page", pageVo.getPage());

		// userList, userCnt, pageSize, page
		// { userList : [ {userId : 'brown', userNm : '브라운}... {userId :
		// 'sally', userNm : '샐리'}]
		// userCnt : "110",
		// pageSize : "10",
		// page : 2}

		return "jsonView";

	}
	
	@RequestMapping("/userPagingListAjaxHtml")
	public String userPagingListAjaxHtml(PageVO pageVo, Model model) {
		
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);
		model.addAllAttributes(resultMap);
		
		model.addAttribute("pageSize", pageVo.getPageSize());
		model.addAttribute("page", pageVo.getPage());
		
		return "user/userPagingListAjaxHtml";
		
	}
		
	/**
	* Method : user
	* 작성자 : PC04
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 상세보기 조회
	*/
	@RequestMapping(path="/user",method=RequestMethod.GET)
	public String user(@RequestParam("userId")String userId,Model model){
		
		UserVO selectUser = userService.selectUser(userId);
		model.addAttribute("userVo",selectUser);
//		return "user/user";
		return "userTiles";
	}
	
	@RequestMapping("/profileImg")
	public void profileImg(HttpServletRequest req,
							HttpServletResponse resp,
							@RequestParam("userId")String userId) throws IOException{
		
		resp.setHeader("Content-Disposition", "attachment; filename=profile.png");
		resp.setContentType("image.png");
		
//		// 1. 사용자 아이디 파라미터 확인
//		String userId = req.getParameter("userId");
		
//		// 2. 해당 사용자 아이디로 사용자 정보 조회(realFilename)
		UserVO userVO = userService.selectUser(userId);
		
//		// 3-1. realFilename이 존재할 경우
		FileInputStream fis;
		
		if (userVO !=null &&userVO.getRealFilename() != null)
			// 3-1-1. 해당 경로의 파일을 FileInputStream으로 읽는다
			fis = new FileInputStream(new File(userVO.getRealFilename()));
		// 3-2. realFilename이 존재하지 않을 경우
		else {
			// 3-2-1. /upload/noimg.png (application.getRealPath())
			ServletContext application = req.getServletContext();
			String noimgPath = application.getRealPath("/upload/noimg.png");
			fis = new FileInputStream(new File(noimgPath));
		}
		// 4.FileInputStream을 response객체의 outputStream 객체에 write
		ServletOutputStream sos = resp.getOutputStream();
		
		byte[] buff = new byte[512];
		int len = 0;
		while((len=fis.read(buff))!=-1){
			sos.write(buff);
		}
		sos.close();
		fis.close();
	}
	
	/**
	* Method : userForm
	* 작성자 : PC04
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 등록 폼 요청
	*/
	@RequestMapping(path="/userForm",method=RequestMethod.GET)
	public String userForm(){
		return "user/userForm";
	}
	@RequestMapping(path="/userForm",method=RequestMethod.POST)
	public String userForm(UserVO userVo,
							@RequestPart("profile")MultipartFile profile,
							HttpSession session,
							Model model) throws IllegalStateException, IOException{

//		// 1. 사용자 아이디 중복체크
		UserVO duplicateUserVo = userService.selectUser(userVo.getUserId());
		
		// 중복체크 통과(신규등록)
		if (duplicateUserVo == null) {
			
			String realFilename="";
			String filename ="";
			// 사용자 프로필을 업로드 한 경우
			if(profile.getSize()>0){
				realFilename = "d:\\picture\\" +UUID.randomUUID().toString();
				filename = profile.getOriginalFilename();
				profile.transferTo(new File(realFilename));
			}
			userVo.setFilename(filename);
			userVo.setRealFilename(realFilename);
			int insertUser = 0;
			try {
				insertUser = userService.insertUser(userVo);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			// 정상입력(성공)
			if (insertUser == 1) {
				session.setAttribute("msg", "정상 등록 되었습니다.");
				return "redirect:/user/userPagingList";
			}

			// 정상입력(실패)
			else {
				return "/user/userForm"; // 검증 필요
			}
		}
		// 중복 체크 실패
		else {
			model.addAttribute("msg", "중복체크에 실패 했습니다.");
			return "/user/userForm"; // 검증 필요
		}

	}
	
	@RequestMapping(path="/userModifyForm",method=RequestMethod.GET)
	public String userModifyForm(@RequestParam("userId")String userId, Model model){
		UserVO selectUser = userService.selectUser(userId);
		if(selectUser==null){
			return "user/userPagingList";
		}
		model.addAttribute("userVo", selectUser);
		return "user/userModify";
	}
	
	@RequestMapping(path="/userModifyForm",method=RequestMethod.POST)
	public String userModifyForm(UserVO userVo,
									@RequestPart("profile")MultipartFile profile,
									HttpSession session,
									Model model,RedirectAttributes ra) throws IllegalStateException, IOException{
		// 1. 사용자 아이디 중복체크

		// 2-1. 중복체크 통과 : 사용자 정보를 db에 입력
		
		// 2-1-1. 사용자 페이징 리스트 1페이지로 이동
		UserVO fileVO = userService.selectUser(userVo.getUserId());
		if(fileVO==null){
			return "user/userPagingList";
		}
		String filename = fileVO.getFilename();
		String realFilename = fileVO.getRealFilename();
		
		if(profile.getSize()>0){
			
			filename =profile.getOriginalFilename()+"_"+UUID.randomUUID().toString();
			realFilename = "d:\\picture\\" +filename;
			// 디스크에 기록 (d:\picture\ +realFileName)
			File file = new File("d:\\\\picture\\"+filename);
			profile.transferTo(file);
		}
		userVo.setFilename(filename);
		userVo.setRealFilename(realFilename);
		
		
		// 비밀번호 수정 요청여부
		// 사용자가 값을 입력하지 않은 경우 -> 기존 비밀번호 유지
		if(userVo.getPass().equals("")){
			UserVO userVoForPass = userService.selectUser(userVo.getUserId());
			userVo.setPass(userVoForPass.getPass());
		}

		// 사용자가 비밀번호를 신규 등록한 경우
		else{
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
		}
		int updateUser = userService.updateUser(userVo);

		// 정상입력(성공)
		if (updateUser != 0) {
			/*
            
            redirect 파라미터를 보내는 방법
           1.url로 작성
            "redirect:/user/user?userId=" + userVo.getUserId();
           2.model객체의 속성 : 저장된 속성을 자동으로 파라미터 변환
           model.addAttrubute("userId", userVo.getUserId());
            return "redirect:/user/user";
           3. RedirectAtrribute(ra) 객체를 이용
            return "redirect:/user/user";
            */
           ra.addAttribute("userId" , userVo.getUserId());
           ra.addFlashAttribute("msg", "정상 등록 되었습니다");
           //session.setAttribute("msg", "정상 등록 되었습니다");
           //model.addAttribute("userId", uservo.getUserId());
           //return "redirect" + request.getContextPath() + " :/user/user"; // contextPath 작업필요
           return "redirect:/user/user"; // contextPath 작업필요
		}

		// 정상입력(실패)
		else {
			UserVO userVo_dupl = userService.selectUser(userVo.getUserId());
			model.addAttribute("userVo", userVo_dupl);
			model.addAttribute("msg", "수정에 실패하였습니다.");
			return "user/userModify";
		}
	}
	
	
}
