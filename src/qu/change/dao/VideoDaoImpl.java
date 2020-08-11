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
	public List<Video> findAllVideo() {//������Ƶ����
		// TODO Auto-generated method stub
		List<Video> list=null;
		try {
			//ͨ��������������
			connection = JDBCUtils.getConnetion();
			System.out.println(connection);
			//ͨ�����Ӷ����ȡ�������ݿ�Ķ���
			String sql="SELECT * FROM videoinfo";//��ѯsql���
			ps=connection.prepareStatement(sql);
			System.out.println(ps);
			//���ز�ѯ�����
			rs=ps.executeQuery();
			System.out.println(rs);
			//����rs������װ����
			list= new ArrayList<Video>();
			while(rs.next()) {
				Video video = new Video();
				video.setTag(rs.getString(2));//������1��ʼ��id��������ȡ
				video.setAuthor(rs.getString(3));
				video.setVideourl(rs.getString(4));
				video.setLike(rs.getInt(5));
				list.add(video);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			JDBCUtils.close(connection, ps, rs);//�ر�����
			
		}
		System.out.println(list);
		return list;
	}
	@Override
	public void insertVideoinfo(Video video) {//������Ƶ������Ϣ �ϴ���Ϣ��
		try {
			connection=JDBCUtils.getConnetion();
			String sql="INSERT INTO videoinfo(tag,author,address,url) VALUES(?,?,?,?)";//�������
			ps=connection.prepareStatement(sql);
			ps.setString(1,video.getTag());
			ps.setString(2,video.getAuthor());//ʹ��prepareStatement���Է�ֹsqlע��
			ps.setString(3,video.getAddress());
			ps.setString(4,video.getVideourl());
			//ִ�и������
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
	public void changeLikes(Video video) {//��������
		try {
			connection=JDBCUtils.getConnetion();
			String sql="UPDATE vidoeinfo SET like =? WHERE url =?";//�������
			ps=connection.prepareStatement(sql);
			ps.setInt(1,video.getLike());
			ps.setString(2,video.getVideourl());//ʹ��prepareStatement���Է�ֹsqlע��
			//ִ�и������
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
