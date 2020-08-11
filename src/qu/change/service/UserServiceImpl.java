package qu.change.service;

import java.util.List;

import qu.change.dao.UserDao;
import qu.change.dao.UserDaoImpl;
import qu.change.domian.User;


public class UserServiceImpl implements UserService {//��service���ע��
	UserDao dao = new UserDaoImpl();

	@Override
	public String checkLogin(User user) {
		List<User> list = dao.findAll();
		for(int i=0;i<list.size();i++) {
			User user2=list.get(i);
			if(user2.getUsername().equals(user.getUsername()) && user2.getPassword().equals(user.getPassword())) {
				return "��¼�ɹ�";
			}
		}
		return "��¼ʧ��,�����������";
	}

	@Override
	public String register(User user) {
		List<User> list = dao.findAll();
		for(int i=0;i<list.size();i++) {
			User user2=list.get(i);
			if(user2.getUsername().equals(user.getUsername())) {
				return "ע��ʧ�ܣ����û����Ѵ��ڣ�";
			}
		}
		dao.insertElement(user);
		return "ע��ɹ�";
	}
	@Override
	public String getchange(User user) {
			dao.logOn(user);
			return "���ϱ���ɹ���";
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
