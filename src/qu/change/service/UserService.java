package qu.change.service;

import qu.change.domian.User;

public interface UserService {
	public String checkLogin(User user);//��¼
	public String register(User user);//ע��
	public String getchange(User user);//���¸�������
	public String setInfo(User user);//��ȡ���
	public String setCode(User user);//��ȡ�ǳ�
	public String setSex(User user);//��ȡ�Ա�
//	public String saveIcon(User user);
}
