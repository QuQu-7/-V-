package qu.change.service;

import qu.change.dao.VideoDao;
import qu.change.dao.VideoDaoImpl;
import qu.change.domian.Video;

public class VidoeServiceImpl {
	VideoDao dao = new VideoDaoImpl();
	public String setvideourl(Video video) {//��ȡ��Ƶurl ����ɾ����֮ǰ�ı��� ȱ��ǰ�˵�ȡ��ʽ
		return video.getVideourl();
	}
}
