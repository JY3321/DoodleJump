package DoodleJump;

import java.util.Random;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Layer extends JLabel{
	int x,y,NUM,MODE;//
	int[] TYPE;
	int PROP;
	Board[] myBoard;
	Board prop;
	Position[] myPosition;
	Layer(int y,int mode,int score){
		NewNUM(score);	
		this.y=y;
		NewPosition(NUM);
		NewTYPE(NUM,score);
		MODE=mode;
		NewBoard(NUM,TYPE);
		NewPROP(score);
	}
	void NewNUM(int score){
		if(score<500) {
			long time = System.currentTimeMillis();
			Random Ran=new Random(time);
			int ran=Ran.nextInt(100);
			if(ran<80) NUM=1;
			else NUM=2;
		}
		if(score>=500&&score<20000) {
		long time = System.currentTimeMillis();
		Random Ran=new Random(time);
		int ran=Ran.nextInt(100);
		if(ran<80) NUM=1;
		else if(ran>=80&&ran<=95) NUM=2;
		else NUM=3;
		}
		else NUM=1;
	}
	void NewPROP(int score){
		long time = System.currentTimeMillis();
		Random Ran=new Random(time);
		int ran=Ran.nextInt(100);
		if(score<500)
		PROP=0;//ÆÕÍ¨
		else {
		if(TYPE[0]==1) {//Ö»ÔÚÆÕÍ¨Ä¾°åÉú³É
		if(ran==3||ran==6||ran==9||ran==13||ran==16||ran==19)
		PROP=1;//µ¯»É		
		else if(ran==1||ran==11)
		PROP=2;//·ÉÃ±
		else if(ran==2||ran==12)
		PROP=3;//»ð¼ý
		else if(ran==4)
		PROP=4;//Ð¡¹Ö
		else if(ran==5)
		PROP=5;//ÒÆ¶¯¹Ö
		else if(ran==7)
		PROP=6;//´ó¹Ö
		else if(ran==8)
		PROP=7;//ºÚ¶´
		}
		prop=new Board(MODE,PROP,0);
		}
	}
	void NewPosition(int NUM){
		myPosition=new Position[NUM];
		for(int i=0;i<NUM;i++){
			boolean flag=true;
			do{
				flag=true;
				myPosition[i]=new Position(y);	
				if(i>0){
					for(int j=i-1;j>=0;j--){
						if(Math.abs(myPosition[i].x-myPosition[j].x)<63) 
							flag=false;
					}
				}
			}while(flag==false);
		}
	}
	void NewTYPE(int NUM,int score){
		TYPE=new int[NUM];
		if(score<500) {
			for(int i=NUM-1;i>=0;i--) TYPE[i]=1;		
			}
		else {
			Random Ran=new Random();
			for(int i=NUM-1;i>=0;i--) {
				int ran=Ran.nextInt(100);
				if(ran<70) TYPE[i]=1;
				else if(ran>=70&&ran<85)TYPE[i]=2;
				else if(ran>=85&&ran<90) TYPE[i]=3;
				else if(ran>=90&&ran<96) TYPE[i]=4;
				else if(ran>=96) {TYPE[i]=5; x=myPosition[i].x;}			
			}
		}
	}
	void NewBoard(int NUM,int[] TYPE){
		myBoard=new Board[NUM];
		for(int i=0;i<NUM;i++) {
			myBoard[i]=new Board(TYPE[i],MODE);	
		}
	}
	void Change() {
		switch(PROP){
		case 1:
			prop.Change();
			PROP=8;
			break;
		case 2:
			PROP=0;
			prop.setVisible(false);
			break;
		case 3:
			PROP=0;
			prop.setVisible(false);
			break;
		}	
	}
}
