package qu.change.dao;

import java.util.List;

import org.json.JSONObject;

import qu.change.domian.User;

public interface UserDao {//用户数据访问层函数
	public  List<User> findAll();//遍历用户数据
	public void insertElement(User people);//注册用插入用户数据
	public void logOn(User people);//登录用
	public String searchCode(User people);//获得昵称
	public String searchIntro(User people);//获得简介
	public String searchSex(User people);//获得性别
}
