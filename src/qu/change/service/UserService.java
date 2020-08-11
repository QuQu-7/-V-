package qu.change.service;

import qu.change.domian.User;

public interface UserService {
	public String checkLogin(User user);//登录
	public String register(User user);//注册
	public String getchange(User user);//更新个人资料
	public String setInfo(User user);//获取简介
	public String setCode(User user);//获取昵称
	public String setSex(User user);//获取性别
//	public String saveIcon(User user);
}
