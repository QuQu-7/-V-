package qu.change.dao;

import java.util.List;

import org.json.JSONObject;

import qu.change.domian.User;

public interface UserDao {//�û����ݷ��ʲ㺯��
	public  List<User> findAll();//�����û�����
	public void insertElement(User people);//ע���ò����û�����
	public void logOn(User people);//��¼��
	public String searchCode(User people);//����ǳ�
	public String searchIntro(User people);//��ü��
	public String searchSex(User people);//����Ա�
}
