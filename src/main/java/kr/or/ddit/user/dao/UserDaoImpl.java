package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;


@Repository("userDao")
public class UserDaoImpl implements IUserDao {
	/**
	* Method : getAllUser
	* 작성자 : PC04
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	*/
	@Override
	public List<UserVO> getAllUser(SqlSession openSession){
		List<UserVO> list =openSession.selectList("user.getAllUser");
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
	public UserVO selectUser(SqlSession openSession,String userId) {
		UserVO userVo = openSession.selectOne("user.selectUser",userId);
		return userVo;
	}

	@Override
	public List<UserVO> selectUserPagingList(SqlSession openSession,PageVO pageVo) {
		List<UserVO> userPageList =openSession.selectList("user.selectUserPagingList" , pageVo);
		return userPageList;
	}

	@Override
	public int getUserCnt(SqlSession openSession) {
		int userCnt =openSession.selectOne("user.getUserCnt");
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
	public int insertUser(SqlSession openSession,UserVO userVo) {
		int insert =openSession.insert("user.insertUser",userVo);
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
	public int deleteUser(SqlSession openSession,String userId) {
		int deleteUser =openSession.delete("user.deleteUser",userId);
		return deleteUser;
	}

	@Override
	public int updateUser(SqlSession openSession, UserVO userVo) {
		int updateUser = openSession.update("user.updateUser",userVo);
		return updateUser;
	}

	@Override
	public int updateUserPass(SqlSession openSession, UserVO userVo) {
		int updateUser = openSession.update("user.updateUserPass",userVo);
		return updateUser;
	}
}
