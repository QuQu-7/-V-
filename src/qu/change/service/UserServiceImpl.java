package qu.change.service;

import java.util.List;

import qu.change.dao.UserDao;
import qu.change.dao.UserDaoImpl;
import qu.change.domian.User;


public class UserServiceImpl implements UserService {//见service类的注释
	UserDao dao = new UserDaoImpl();

	@Override
	public String checkLogin(User user) {
		List<User> list = dao.findAll();
		for(int i=0;i<list.size();i++) {
			User user2=list.get(i);
			if(user2.getUsername().equals(user.getUsername()) && user2.getPassword().equals(user.getPassword())) {
				return "登录成功";
			}
		}
		return "登录失败,密码输入错误";
	}

	@Override
	public String register(User user) {
		List<User> list = dao.findAll();
		for(int i=0;i<list.size();i++) {
			User user2=list.get(i);
			if(user2.getUsername().equals(user.getUsername())) {
				return "注册失败，该用户名已存在！";
			}
		}
		dao.insertElement(user);
		return "注册成功";
	}
	@Override
	public String getchange(User user) {
			dao.logOn(user);
			return "资料保存成功！";
	}
	@Override
	public String setInfo(User user) {
		String string = dao.searchIntro(user);
		return string;
	}
	@Override
	public String setCode(User user) {
		String string = dao.searchCode(user);
		return string;
	}
	@Override
	public String setSex(User user) {
		String string = dao.searchSex(user);
		return string;
	}
	
	
}
