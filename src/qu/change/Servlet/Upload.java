package qu.change.Servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
public class Upload extends HttpServlet {//用户头像文件上传接口
	private static final long serialVersionUID = 1L;
	private final String savaPath="C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\icon\\";     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items;
			try {
				items = upload.parseRequest(request);
				Iterator iter = items.iterator();
				// 可以接收多个文件
				while(iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (item.isFormField()) {
						// 普通文本信息处理
						String paramName = item.getFieldName();
						String paramValue = item.getString();
						System.out.println(paramName + ":" + paramValue);
					} else {
						// 上传文件信息处理
						String fileName = item.getName();
						byte[] data = item.get();
						String filePath = savaPath + fileName;
						File file = new File(savaPath);
						if (!file.exists()) {
							file.mkdirs();
						}
						System.out.println("filePath：" + filePath);
						File file2 = new File(filePath);
						if (file2.exists())
							file2.delete();
						FileOutputStream fos = new FileOutputStream(file2);
						fos.write(data);
						fos.close();
					}
				}
				response.getWriter().write("UPLOAD_SUCCESS");
			} catch (FileUploadException e) {
				e.printStackTrace();
				response.getWriter().write("UPLOAD_FAILED");
			}
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
