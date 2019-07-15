package DoodleJump;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
class SmallBoy extends JLabel{//SmallBoy人物类    SmallBoy的名字叫Stan
    int MODE;
    int TYPE;
    int TURN;
    int FLASH;
    SmallBoy(int mode){//初始化函数
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
                modepath="image/Classic Mode/Doodle ";//Classic Mode 经典模式
                break;
            case 2:
                modepath="image/Winter Mode/Doodle ";//Winter Mode 圣诞模式
                break;
            case 3:
                modepath="image/Jungle Mode/Doodle ";//Jungle Mode 丛林模式
            break;
        case 4:
            modepath="image/Underwater Mode/Doodle ";//Underwater Mode 水下模式
            break;
        }
        switch (TYPE){//火箭、飞帽、正常
        	case 1:
        		typepath="Normal";//正常样子
        		break;
        	case 2:
        		typepath="Fly";//飞帽
        		this.setSize(62, 63);
        		break;
        	case 3:
        		typepath="Rocket";//火箭
        		this.setSize(62, 66);
        		break;  		
        }
        switch(TURN){//左右
        	case 1:
        		turnpath="Left";//鼻子朝左
        		break;
        	case 2:
        		turnpath="Right";//鼻子朝右
            	break;

        }
        switch(FLASH){//左右
        case 0:
        	flashpath=".png";
        	break;
        case 1:
        	flashpath="1.png";//鼻子朝左
    		break;
    	case 2:
    		flashpath="2.png";//鼻子朝右
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
