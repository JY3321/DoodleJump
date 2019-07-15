package DoodleJump;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;

class MusicPlayer {
	Player audioPlayer = null;//建立一个播放接口
	URL url;
	@SuppressWarnings("deprecation")
	public MusicPlayer(File file) throws Exception{//创建一个准备Player,准备好播放
		url=file.toURL();
		audioPlayer = Manager.createRealizedPlayer(url);
	}
	public void play(){//直接调用播放方法就可以
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
	public void play(int x){//直接调用播放方法就可以
		audioPlayer.start();	
	}
	public void stop(){//停止的时候一定要释放资源
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