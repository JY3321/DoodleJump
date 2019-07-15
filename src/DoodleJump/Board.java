package DoodleJump;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Board extends JLabel{
	int TYPE;
	int MODE;
	int PROP;
	String filepath=null;
    Board(int type,int mode){//初始化木板
    	TYPE=type;
    	MODE=mode;
    	switch(mode) {//游戏模式的选择
    	case 1:
    		filepath="image/Classic Mode/";//Classic Mode 经典模式
    		break;
    	case 2:
    		filepath="image/Winter Mode/";//Winter Mode 圣诞模式
    		break;
    	case 3:
    		filepath="image/Jungle Mode/";//Jungle Mode 丛林模式
    		break;
    	case 4:
    		filepath="image/Underwater Mode/";//Underwater Mode 水下模式
    		break;
    	}
    	BoardLoading(TYPE);
    }
    Board(int mode,int prop,int zz){//初始化道具
    	MODE=mode;
    	PROP=prop;
    	switch(mode) {//游戏模式的选择
    	case 1:
    		filepath="image/Classic Mode/";//Classic Mode 经典模式
    		break;
    	case 2:
    		filepath="image/Winter Mode/";//Winter Mode 圣诞模式
    		break;
    	case 3:
    		filepath="image/Jungle Mode/";//Jungle Mode 丛林模式
    		break;
    	case 4:
    		filepath="image/Underwater Mode/";//Underwater Mode 水下模式
    		break;
    	}
    	PropLoading(PROP);
    }
    void BoardLoading(int type){//加载图片
    	switch(type){//木板选择
    	case 1://普通木板
    		this.setIcon(new ImageIcon(filepath+"Plattform Green.png"));
    		break;
    	case 2://只能踩一次的木板
    		this.setIcon(new ImageIcon(filepath+"Plattform White.png"));
    		break;
    	case 3://碎木板(陷阱木板)
    		this.setIcon(new ImageIcon(filepath+"Plattform Brown.png"));
    		break;
    	case 4://左右移动木板
    		this.setIcon(new ImageIcon(filepath+"Plattform Blue.png"));
    		break;
    	case 5://上下移动木板
    		this.setIcon(new ImageIcon(filepath+"Plattform Blue.png"));
    		break;
    	}
    }
    void PropLoading(int prop){//加载图片
    	switch(prop){//木板选择	
    	case 1://弹簧
    		this.setIcon(new ImageIcon(filepath+"Spring.png"));
    		this.setSize(17,9);
    		break;
    	case 2://飞帽
    		this.setIcon(new ImageIcon(filepath+"Fly.png"));
    		this.setSize(28,23);
  		break;
    	case 3://火箭
    		this.setIcon(new ImageIcon(filepath+"Rocket.png"));
    		this.setSize(26,37);
    		break;
    	case 4://小怪
    		this.setIcon(new ImageIcon(filepath+"fly1.png"));
    		this.setSize(79,45);
    		break;
    	case 5://移动怪
    		this.setIcon(new ImageIcon(filepath+"Move2.png"));
    		this.setSize(39,50);
    		break;
    	case 6://大怪
    		Random Ran=new Random();
    		int ran=Ran.nextInt(2)+1;
    		System.out.println(ran);
    		this.setIcon(new ImageIcon(filepath+"Monster"+ran+".png"));
    		this.setSize(83,53);
    		break;
    	case 7://黑洞
    		this.setIcon(new ImageIcon(filepath+"BlackHole.png"));
    		this.setSize(64,62);
    		break;
    	}
    }
    void Change(){//碰撞更改
    	this.setIcon(new ImageIcon(filepath+"Spring Up.png"));
    	this.setSize(19,29);
    }
}