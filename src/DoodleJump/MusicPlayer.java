package DoodleJump;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;

class MusicPlayer {
	Player audioPlayer = null;//����һ�����Žӿ�
	URL url;
	@SuppressWarnings("deprecation")
	public MusicPlayer(File file) throws Exception{//����һ��׼��Player,׼���ò���
		url=file.toURL();
		audioPlayer = Manager.createRealizedPlayer(url);
	}
	public void play(){//ֱ�ӵ��ò��ŷ����Ϳ���
		audioPlayer.start();	
		try {
		this.audioPlayer=Manager.createRealizedPlayer(url);
		} catch (NoPlayerException e) {
			e.printStackTrace();
		} catch (CannotRealizeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void play(int x){//ֱ�ӵ��ò��ŷ����Ϳ���
		audioPlayer.start();	
	}
	public void stop(){//ֹͣ��ʱ��һ��Ҫ�ͷ���Դ
		audioPlayer.stop();
		audioPlayer.close();
		try {
			this.audioPlayer=Manager.createRealizedPlayer(url);
		} catch (NoPlayerException e) {
		} catch (CannotRealizeException e) {
		} catch (IOException e) {
		}
	}
}