package qu.change.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qu.change.domian.User;
import qu.change.service.UserService;
import qu.change.service.UserServiceImpl;

/**
 * Servlet implementation class PersonalCenter
 */
@WebServlet("/PersonalCenter")
public class PersonalCenter extends HttpServlet {//������������չʾ
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalCenter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				request.setCharacterEncoding("UTF-8");//�����������(post)
				response.setCharacterEncoding("UTF-8");//�����Ӧ����,����Ҫ���ַ�����������ֽ��������Ҫ�ٴα��룩
				String username=request.getParameter("username");
				String sign=request.getParameter("sign");
				PrintWriter out=response.getWriter();
				//�Ѵ��������ݷ�װ��javabean��
				User user=new User();
				user.setUsername(username);
				UserService service=new UserServiceImpl();
				if("5".equals(sign)) {//�����ǳ�
					String setCodeInfo=service.setCode(user);
					out.print(setCodeInfo);
				}
				else if("6".equals(sign)) {//���¸��˼��
					String setInfoInfo=service.setInfo(user);
					out.print(setInfoInfo);
				}else if("7".equals(sign)) {//�����Ա�
					String setSexInfo = service.setSex(user);
					out.print(setSexInfo);
				}
				
				System.out.println(username);
				System.out.println(sign);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
