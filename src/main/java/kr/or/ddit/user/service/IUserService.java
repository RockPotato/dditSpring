package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

public interface IUserService {
	public List<UserVO> getAllUser();
	public UserVO selectUser(String userId);
	Map<String,Object> selectUserPagingList(PageVO pageVo);
	int insertUser(UserVO userVo);
	int deleteUser(String userId);
	int updateUser(UserVO userVo);
	void encryptPass(String userId);
	void encryptPassAll();
}
