package qu.change.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import qu.change.domian.Video;
import qu.change.util.JDBCUtils;

public class VideoDaoImpl implements VideoDao{
	Connection connection=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	@Override
	public List<Video> findAllVideo() {//遍历视频数据
		// TODO Auto-generated method stub
		List<Video> list=null;
		try {
			//通过工具类获得连接
			connection = JDBCUtils.getConnetion();
			System.out.println(connection);
			//通过连接对象获取操作数据库的对象
			String sql="SELECT * FROM videoinfo";//查询sql语句
			ps=connection.prepareStatement(sql);
			System.out.println(ps);
			//返回查询结果集
			rs=ps.executeQuery();
			System.out.println(rs);
			//遍历rs，并封装数据
			list= new ArrayList<Video>();
			while(rs.next()) {
				Video video = new Video();
				video.setTag(rs.getString(2));//索引从1开始，id参数不用取
				video.setAuthor(rs.getString(3));
				video.setVideourl(rs.getString(4));
				video.setLike(rs.getInt(5));
				list.add(video);
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
	public void insertVideoinfo(Video video) {//插入视频数据信息 上传信息用
		try {
			connection=JDBCUtils.getConnetion();
			String sql="INSERT INTO videoinfo(tag,author,address,url) VALUES(?,?,?,?)";//插入语句
			ps=connection.prepareStatement(sql);
			ps.setString(1,video.getTag());
			ps.setString(2,video.getAuthor());//使用prepareStatement可以防止sql注入
			ps.setString(3,video.getAddress());
			ps.setString(4,video.getVideourl());
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
	public void changeLikes(Video video) {//更改赞数
		try {
			connection=JDBCUtils.getConnetion();
			String sql="UPDATE vidoeinfo SET like =? WHERE url =?";//插入语句
			ps=connection.prepareStatement(sql);
			ps.setInt(1,video.getLike());
			ps.setString(2,video.getVideourl());//使用prepareStatement可以防止sql注入
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

}
