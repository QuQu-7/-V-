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
public class PersonalCenter extends HttpServlet {//个人中心数据展示
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
				request.setCharacterEncoding("UTF-8");//解决请求乱码(post)
				response.setCharacterEncoding("UTF-8");//解决响应乱码,下面要以字符流输出（若字节流输出则要再次编码）
				String username=request.getParameter("username");
				String sign=request.getParameter("sign");
				PrintWriter out=response.getWriter();
				//把传来的数据封装进javabean中
				User user=new User();
				user.setUsername(username);
				UserService service=new UserServiceImpl();
				if("5".equals(sign)) {//更新昵称
					String setCodeInfo=service.setCode(user);
					out.print(setCodeInfo);
				}
				else if("6".equals(sign)) {//更新个人简介
					String setInfoInfo=service.setInfo(user);
					out.print(setInfoInfo);
				}else if("7".equals(sign)) {//更新性别
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
