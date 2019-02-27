package kr.or.ddit.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.dao.IUserDao;

@Service
public class UserServiceImpl implements IUserService{

	@Resource(name="userDaoImpl")
	private IUserDao userDao;
	
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<String> getAllUsers() {
		return userDao.getAllUsers();
	}
	
}
