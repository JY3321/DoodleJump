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
    Board(int type,int mode){//��ʼ��ľ��
    	TYPE=type;
    	MODE=mode;
    	switch(mode) {//��Ϸģʽ��ѡ��
    	case 1:
    		filepath="image/Classic Mode/";//Classic Mode ����ģʽ
    		break;
    	case 2:
    		filepath="image/Winter Mode/";//Winter Mode ʥ��ģʽ
    		break;
    	case 3:
    		filepath="image/Jungle Mode/";//Jungle Mode ����ģʽ
    		break;
    	case 4:
    		filepath="image/Underwater Mode/";//Underwater Mode ˮ��ģʽ
    		break;
    	}
    	BoardLoading(TYPE);
    }
    Board(int mode,int prop,int zz){//��ʼ������
    	MODE=mode;
    	PROP=prop;
    	switch(mode) {//��Ϸģʽ��ѡ��
    	case 1:
    		filepath="image/Classic Mode/";//Classic Mode ����ģʽ
    		break;
    	case 2:
    		filepath="image/Winter Mode/";//Winter Mode ʥ��ģʽ
    		break;
    	case 3:
    		filepath="image/Jungle Mode/";//Jungle Mode ����ģʽ
    		break;
    	case 4:
    		filepath="image/Underwater Mode/";//Underwater Mode ˮ��ģʽ
    		break;
    	}
    	PropLoading(PROP);
    }
    void BoardLoading(int type){//����ͼƬ
    	switch(type){//ľ��ѡ��
    	case 1://��ͨľ��
    		this.setIcon(new ImageIcon(filepath+"Plattform Green.png"));
    		break;
    	case 2://ֻ�ܲ�һ�ε�ľ��
    		this.setIcon(new ImageIcon(filepath+"Plattform White.png"));
    		break;
    	case 3://��ľ��(����ľ��)
    		this.setIcon(new ImageIcon(filepath+"Plattform Brown.png"));
    		break;
    	case 4://�����ƶ�ľ��
    		this.setIcon(new ImageIcon(filepath+"Plattform Blue.png"));
    		break;
    	case 5://�����ƶ�ľ��
    		this.setIcon(new ImageIcon(filepath+"Plattform Blue.png"));
    		break;
    	}
    }
    void PropLoading(int prop){//����ͼƬ
    	switch(prop){//ľ��ѡ��	
    	case 1://����
    		this.setIcon(new ImageIcon(filepath+"Spring.png"));
    		this.setSize(17,9);
    		break;
    	case 2://��ñ
    		this.setIcon(new ImageIcon(filepath+"Fly.png"));
    		this.setSize(28,23);
  		break;
    	case 3://���
    		this.setIcon(new ImageIcon(filepath+"Rocket.png"));
    		this.setSize(26,37);
    		break;
    	case 4://С��
    		this.setIcon(new ImageIcon(filepath+"fly1.png"));
    		this.setSize(79,45);
    		break;
    	case 5://�ƶ���
    		this.setIcon(new ImageIcon(filepath+"Move2.png"));
    		this.setSize(39,50);
    		break;
    	case 6://���
    		Random Ran=new Random();
    		int ran=Ran.nextInt(2)+1;
    		System.out.println(ran);
    		this.setIcon(new ImageIcon(filepath+"Monster"+ran+".png"));
    		this.setSize(83,53);
    		break;
    	case 7://�ڶ�
    		this.setIcon(new ImageIcon(filepath+"BlackHole.png"));
    		this.setSize(64,62);
    		break;
    	}
    }
    void Change(){//��ײ����
    	this.setIcon(new ImageIcon(filepath+"Spring Up.png"));
    	this.setSize(19,29);
    }
}