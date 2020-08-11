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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {//登陆注册保存资料
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");//解决请求乱码(post)
		response.setCharacterEncoding("UTF-8");//解决响应乱码,下面要以字符流输出（若字节流输出则要再次编码）
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String sex = request.getParameter("sex");
		String namechange=request.getParameter("codename");
		String titlechange=request.getParameter("introduction");
		
		//String icon=request.getParameter("icon");
		String sign=request.getParameter("sign");
		//String iconaddress = request.getParameter("iconaddress");
		PrintWriter out=response.getWriter();
		//把传来的数据封装进javabean中
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setSex(sex);
		user.setNamechange(namechange);
		user.setTitlechange(titlechange);
		//user.setIcon(icon);
		UserService service=new UserServiceImpl();
		if("1".equals(sign)) {//登录操作(设置了一个标记)
			String loginInfo=service.checkLogin(user);
			out.print(loginInfo);
		}
		else if("2".equals(sign)) {//注册操作
			String registerInfo=service.register(user);
			out.print(registerInfo);
		}else if("3".equals(sign)) {//保存编辑的资料
			String savechangeInfo = service.getchange(user);
			//String saveiconInfo = service.saveIcon(user);
			out.print(savechangeInfo);
			//out.print(saveiconInfo);
		}
		System.out.println(username);//在控制台输出
		System.out.println(password);
		System.out.println(namechange);
		System.out.println(titlechange);
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
