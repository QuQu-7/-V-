package qu.change.service;

import qu.change.dao.VideoDao;
import qu.change.dao.VideoDaoImpl;
import qu.change.domian.Video;

public class VidoeServiceImpl {
	VideoDao dao = new VideoDaoImpl();
	public String setvideourl(Video video) {//获取视频url 这里删掉了之前的表述 缺个前端调取方式
		return video.getVideourl();
	}
}
