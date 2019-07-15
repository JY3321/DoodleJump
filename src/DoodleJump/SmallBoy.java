package DoodleJump;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
class SmallBoy extends JLabel{//SmallBoy������    SmallBoy�����ֽ�Stan
    int MODE;
    int TYPE;
    int TURN;
    int FLASH;
    SmallBoy(int mode){//��ʼ������
        MODE=mode;
        TYPE=1;
        TURN=2;
        FLASH=0;
        Modify();
    }
    public Position Modify(){
        String modepath=null,typepath=null,turnpath=null,flashpath=null;
        switch (MODE){
            case 1:
                modepath="image/Classic Mode/Doodle ";//Classic Mode ����ģʽ
                break;
            case 2:
                modepath="image/Winter Mode/Doodle ";//Winter Mode ʥ��ģʽ
                break;
            case 3:
                modepath="image/Jungle Mode/Doodle ";//Jungle Mode ����ģʽ
            break;
        case 4:
            modepath="image/Underwater Mode/Doodle ";//Underwater Mode ˮ��ģʽ
            break;
        }
        switch (TYPE){//�������ñ������
        	case 1:
        		typepath="Normal";//��������
        		break;
        	case 2:
        		typepath="Fly";//��ñ
        		this.setSize(62, 63);
        		break;
        	case 3:
        		typepath="Rocket";//���
        		this.setSize(62, 66);
        		break;  		
        }
        switch(TURN){//����
        	case 1:
        		turnpath="Left";//���ӳ���
        		break;
        	case 2:
        		turnpath="Right";//���ӳ���
            	break;

        }
        switch(FLASH){//����
        case 0:
        	flashpath=".png";
        	break;
        case 1:
        	flashpath="1.png";//���ӳ���
    		break;
    	case 2:
    		flashpath="2.png";//���ӳ���
        	break;

    }
        ImageIcon icon=new ImageIcon(modepath+typepath+turnpath+flashpath);
        this.setIcon(icon);
        this.setSize(icon.getIconWidth(),icon.getIconHeight());
        Position p =new Position(0);
        p.x=icon.getIconWidth();
        p.y=icon.getIconHeight();
        return p;
    }
}
