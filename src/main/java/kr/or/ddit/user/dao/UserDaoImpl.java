package kr.or.ddit.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements IUserDao{

	@Override
	public List<String> getAllUsers() {
		List<String> userList = new ArrayList<String>();
		userList.add("user1");
		userList.add("user2");
		userList.add("user3");
		return userList;
	}

}
