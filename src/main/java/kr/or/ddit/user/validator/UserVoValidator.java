package kr.or.ddit.user.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.or.ddit.prod.model.LprodVO;
import kr.or.ddit.user.model.UserVO;

public class UserVoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserVO.class.isAssignableFrom(clazz); //해당  Vo 객체에 할당하는 것이 가능한 지? 
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserVO UserVO = (UserVO)target;
		
		//비밀번호가 8자리 이상이어야 한다.
		if(UserVO.getPass().length()<8){
			errors.rejectValue("pass","passlen");//에러코드 설정
		}
		//사용자 아이디 검증(빈값이면 안된다)
	    if(UserVO.getUserId().equals("")){
	        errors.rejectValue("userId", "required"); //required:은 error.properties와 이름이 같아야한다.
	    }
		//사용자 아이디는 6자리 이상이어야 합니다.
		if(UserVO.getUserId().length()<6){
			errors.rejectValue("userId", "userIdLen");
		}
	}
}
