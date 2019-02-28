package kr.or.ddit.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

public interface IUserDao {
	/**
	* Method : getAllUser
	* 작성자 : PC04
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	*/
	public List<UserVO> getAllUser(SqlSession openSession);
	/**
	* Method : selectUser
	* 작성자 : PC04
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 조회
	*/
	public UserVO selectUser(SqlSession openSession,String userId);
	
	/**
	* Method : selectUserPagingList
	* 작성자 : PC04
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : 사용자 페이지 리스트 조회
	*/
	List<UserVO> selectUserPagingList(SqlSession openSession,PageVO pageVo);
	
	int getUserCnt(SqlSession openSession);
	
	/**
	 * Method : getUserCnt
	 * 작성자 : PC04
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 등록
	 */
	int insertUser(SqlSession openSession,UserVO userVo);
	
	/**
	* Method : deleteUser
	* 작성자 : PC04
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	int deleteUser(SqlSession openSession,String userId);
	
	/**
	* Method : updateUser
	* 작성자 : PC04
	* 변경이력 :
	* @param openSession
	* @param userVo
	* @return
	* Method 설명 : 사용자 수정
	*/
	int updateUser(SqlSession openSession,UserVO userVo);
	
	int updateUserPass(SqlSession openSession,UserVO userVo);
}
