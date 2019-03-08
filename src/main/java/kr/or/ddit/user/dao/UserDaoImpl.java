package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;


@Repository("userDao")
public class UserDaoImpl implements IUserDao {

	@Resource(name="sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;
	/**
	* Method : getAllUser
	* 작성자 : PC04
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	*/
	@Override
	public List<UserVO> getAllUser(){
		List<UserVO> list =sqlSessionTemplate.selectList("user.getAllUser");
		return list;
	}

	/**
	* Method : selectUser
	* 작성자 : PC04
	* 변경이력 :
	* @return
	* Method 설명 : 틎정 사용자 조회
	*/
	@Override
	public UserVO selectUser(String userId) {
		UserVO userVo = sqlSessionTemplate.selectOne("user.selectUser",userId);
		return userVo;
	}

	@Override
	public List<UserVO> selectUserPagingList(PageVO pageVo) {
		List<UserVO> userPageList =sqlSessionTemplate.selectList("user.selectUserPagingList" , pageVo);
		return userPageList;
	}

	@Override
	public int getUserCnt() {
		int userCnt =sqlSessionTemplate.selectOne("user.getUserCnt");
		return userCnt;
	}

	/**
	* Method : insertUser
	* 작성자 : PC04
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 등록
	*/
	@Override
	public int insertUser(UserVO userVo) {
		int insert =sqlSessionTemplate.insert("user.insertUser",userVo);
		return insert;
	}

	/**
	* Method : deleteUser
	* 작성자 : PC04
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	@Override
	public int deleteUser(String userId) {
		int deleteUser =sqlSessionTemplate.delete("user.deleteUser",userId);
		return deleteUser;
	}

	@Override
	public int updateUser( UserVO userVo) {
		int updateUser = sqlSessionTemplate.update("user.updateUser",userVo);
		return updateUser;
	}

	@Override
	public int updateUserPass( UserVO userVo) {
		int updateUser = sqlSessionTemplate.update("user.updateUserPass",userVo);
		return updateUser;
	}
}
