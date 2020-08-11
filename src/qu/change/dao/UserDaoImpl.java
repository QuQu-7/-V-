package qu.change.dao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import qu.change.domian.User;
import qu.change.util.JDBCUtils;

public class UserDaoImpl implements UserDao {
	Connection connection=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		List<User> list=null;
		try {
			//通过工具类获得连接
			connection = JDBCUtils.getConnetion();
			System.out.println(connection);
			//通过连接对象获取操作数据库的对象
			String sql="SELECT * FROM userinfo";//查询sql语句
			ps=connection.prepareStatement(sql);
			System.out.println(ps);
			//返回查询结果集
			rs=ps.executeQuery();
			System.out.println(rs);
			//遍历rs，并封装数据
			list= new ArrayList<User>();
			while(rs.next()) {
				User user=new User();
				user.setUsername(rs.getString(2));//索引从1开始，id参数不用取
				user.setPassword(rs.getString(3));
				list.add(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			JDBCUtils.close(connection, ps, rs);//关闭连接
			
		}
		System.out.println(list);
		return list;
	}
	

	@Override
	public void insertElement(User people) {
		// TODO Auto-generated method stub
		try {
			connection=JDBCUtils.getConnetion();
			String sql="INSERT INTO userinfo(username,password) VALUES(?,?)";//插入语句
			ps=connection.prepareStatement(sql);
			ps.setString(1,people.getUsername());//使用prepareStatement可以防止sql注入
			ps.setString(2,people.getPassword());
			//执行更新语句
			ps.addBatch();
			ps.executeBatch();
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtils.close(connection, ps, rs);
		}
	}
	

	@Override
	public void logOn(User people) {
		// TODO Auto-generated method stub
		try {
			connection = JDBCUtils.getConnetion();
			String sql="UPDATE userinfo SET sex =?,introduction =?,codename =? WHERE username =?";
			System.out.println(sql);
			ps=connection.prepareStatement(sql);
			ps.setString(1,people.getSex());//使用prepareStatement可以防止sql注入
			ps.setString(2,people.getTitlechange());
			ps.setString(3,people.getNamechange());//使用prepareStatement可以防止sql注入
			ps.setString(4,people.getUsername());
			//执行更新语句
			ps.addBatch();
			ps.executeBatch();
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			JDBCUtils.close(connection, ps, rs);//关闭连接
		}

	}
	
	@Override
	public String searchCode(User people) {
		List<User> list=null;
		String codename="Test";
		try {
			connection = JDBCUtils.getConnetion();
			String sql="SELECT codename FROM userinfo WHERE username =?";
			System.out.println(sql);
			ps=connection.prepareStatement(sql);
			ps.setString(1,people.getUsername());
			rs=ps.executeQuery();
			//System.out.println(rs);
			//遍历rs，并封装数据
			while(rs.next()) {
				codename = rs.getString("codename");
				System.out.println(codename);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			JDBCUtils.close(connection, ps, rs);//关闭连接
		}
		return codename;
	}
	
	public String searchIntro(User people) {
		List<User> list=null;
		String introduction = "TEST";
		try {
			connection = JDBCUtils.getConnetion();
			String sql="SELECT introduction FROM userinfo WHERE username =?";
			System.out.println(sql);
			ps=connection.prepareStatement(sql);
			ps.setString(1,people.getUsername());
			rs=ps.executeQuery();
			//System.out.println(rs);
			//遍历rs，并封装数据
			while(rs.next()) {
				introduction = rs.getString("introduction");
				System.out.println(introduction);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			JDBCUtils.close(connection, ps, rs);//关闭连接
		}
		return introduction;
	}
	
	public String searchSex(User people) {
		List<User> list=null;
		String sex = "男";
		try {
			connection = JDBCUtils.getConnetion();
			String sql="SELECT sex FROM userinfo WHERE username =?";
			System.out.println(sql);
			ps=connection.prepareStatement(sql);
			ps.setString(1,people.getUsername());
			rs=ps.executeQuery();
			//System.out.println(rs);
			//遍历rs，并封装数据
			while(rs.next()) {
				sex = rs.getString("sex");
				System.out.println(sex);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			JDBCUtils.close(connection, ps, rs);//关闭连接
		}
		return sex;
	}
	
	
	/*@Override
	public void getIcon(User people) {
		List<User> list=null;
		try {
			//通过工具类获得连接
			connection = JDBCUtils.getConnetion();
			System.out.println(connection);
			//通过连接对象获取操作数据库的对象
			String sql="SELECT * FROM user WHERE username = '"+people.getUsername()+"' AND password = '"+people.getPassword()+"'";//查询sql语句
			ps=connection.prepareStatement(sql);
			rs=ps.executeQuery();
			list= new ArrayList<User>();
			while(rs.next()) {
				people.setIcon(rs.getString(6));//索引从1开始，id参数不用取
			}
			
		          InputStream inputStream = getStringStream(people.getIcon());
		          String dirstr = "Icon"+people.getUsername();
		          File dir = new File(dirstr);
		          if (!dir.exists()){
		              dir.mkdirs();
		          }
		          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		          String filestr="IMA"+people.getUsername()+(String)df.format(new Date());
		          people.setIconaddress(dirstr+"/"+filestr);
		          // new Date()为获取当前系统时间 设置图片存储路径
		          File file = new File(dirstr, filestr);//根据目录和文件名得到file对象
		          FileOutputStream fos = new FileOutputStream(file);
		          byte[] buf = new byte[1024*8];
		          int len = -1;
		          while ((len = inputStream.read(buf)) != -1){
		              fos.write(buf, 0, len);
		          }
		          fos.flush();
		          String sql2="INSERT INTO user(iconaddress) VALUES(?)";
		  		ps=connection.prepareStatement(sql2);
		  		ps.setString(1,people.getIconaddress());//使用prepareStatement可以防止sql注入
		  } catch (Exception e) {
		      e.printStackTrace();
		  }finally{
			JDBCUtils.close(connection, ps, rs);//关闭连接
			}
		

	}
	
	public static InputStream getStringStream(String sinputString) {
		if(sinputString != null && !sinputString.trim().equals("")) {
			try {
				ByteArrayInputStream tinputStringStream = new ByteArrayInputStream(sinputString.getBytes());
				return tinputStringStream;
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}*/

}
