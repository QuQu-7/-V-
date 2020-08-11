package qu.change.dao;

import java.util.List;


import qu.change.domian.Video;

public interface VideoDao {
	public List<Video> findAllVideo();
	public void insertVideoinfo(Video video);
	public void changeLikes(Video video);
}
