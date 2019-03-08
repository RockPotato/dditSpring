package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDaoImpl;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Resource(name="userDao")
	private IUserDao userDao;

	public UserServiceImpl() {
		
	}

	/**
	* Method : getAllUser
	* 작성자 : PC04
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	*/
	public List<UserVO> getAllUser(){
		List<UserVO> list =userDao.getAllUser();
		return list;
	}

	/**
	* Method : selectUser
	* 작성자 : PC04
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 틎정 사용자 조회
	*/
	@Override
	public UserVO selectUser(String userId) {
		UserVO vo = userDao.selectUser(userId);
		return vo;
	}

	@Override
	public Map<String, Object> selectUserPagingList(PageVO pageVo) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("userList", userDao.selectUserPagingList(pageVo));
		resultMap.put("userCnt", userDao.getUserCnt());
		return resultMap;
	}

	@Override
	public int insertUser(UserVO userVo) {
		return userDao.insertUser(userVo);
	}

	@Override
	public int deleteUser(String userId) {
		int num =userDao.deleteUser(userId);
		return num;
	}

	@Override
	public int updateUser(UserVO userVo) {
		int num =userDao.updateUser(userVo);
		return num;
	}

	@Override
	public void encryptPass(String userId) {
		UserVO selectUser = userDao.selectUser(userId);
		String plainPass = selectUser.getPass();
		String encrypt = KISA_SHA256.encrypt(plainPass);
		selectUser.setPass(encrypt);
		userDao.updateUser( selectUser);
	};
	@Override
	public void encryptPassAll() {
		List<UserVO> allUser = userDao.getAllUser();
		for (UserVO vo :allUser) {
			String plainPass = vo.getPass();
			String encrypt = KISA_SHA256.encrypt(plainPass);
			vo.setPass(encrypt);
			userDao.updateUserPass( vo);
		}
	};
}
