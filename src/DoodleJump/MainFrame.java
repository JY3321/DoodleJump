package DoodleJump;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements KeyListener,Runnable {//游戏界面
	//属性
	int MODE,SCORE,TIME=8;//模式，分数,修改数值变小可以减少卡顿(StartFrame,MainFrame)
	boolean BACK=false,PAUSE=false,DEATH=false,LANGUAGE,MUSIC,SOUND;//返回 暂停 死亡 语言 音乐 音效
	//控件
	SmallBoy Stan;//DOODLE
	Layer[] k=new Layer[30];//木板层
	JButton Control,Back;//暂停游戏按钮，返回主页面按钮
	JPanel ActionBar;
	JLabel Score,Highscore,Yourname,Yourscore;
	JLabel PauseLabel,DeathLabel1,DeathLabel2;//二级界面框
	JTextField Name=new JTextField(),HighScore=new JTextField();
	MusicPlayer bing,jump,fly,rocket,spring,boardbreak,falldown,fly1,move,monster,hole;
	FileReadWrite b=new FileReadWrite();
	//参数
	boolean isStart=true,moveBg=false,isKey=false,isJump=true,isEdit=false;//开始的标志 是否移动背景 按下按键移动的标志
	boolean jumpStatus=true;//跳动状态 为false的时候代表下降 为true的时候代表上升
    int X,Y,V0,a;//DOODLE的位置 初速度 加速度倒数
    int StanBasePOS=500;//起跳基准位置
    int BdWidth=57,BdHeight=15;//木板宽高
    int StanWidth=62,StanHeight=47;//Stan宽高
    int t=0,propt=0;
    int step;
    boolean propflag=true;
    //路径
    String modepath,languagepath,musicpath="sounds/";
  	
	
	@SuppressWarnings("deprecation")
	MainFrame(int mode,boolean language,boolean music,boolean sound) {//初始化游戏界面(参数为模式)
		//属性设置
		MODE=mode;
		LANGUAGE=language;
		SCORE=0;
		MUSIC=music;
		SOUND=sound;
		switch(MODE) {//游戏模式的选择
    	case 1:modepath="image/Classic Mode/";//Classic Mode 经典模式
    	break;
    	case 2:modepath="image/Winter Mode/";//Winter Mode 圣诞模式
    	break;
    	case 3:modepath="image/Jungle Mode/";//Jungle Mode 丛林模式
    	break;
    	case 4:modepath="image/Underwater Mode/";//Underwater Mode 水下模式
    	break;
    	}
		if(LANGUAGE){
			languagepath="image/English/";
			this.setTitle("DoodleJump");
			Score=new JLabel("SCORE:"+SCORE);
		}
		else{
			languagepath="image/Chinese/";
			this.setTitle("涂鸦跳跃");
			Score=new JLabel("分数:"+SCORE);
		}
		//界面设置
		this.setSize(450,720);
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		addKeyListener(this);
		addWindowListener(new WindowAdapter() {
		@SuppressWarnings("unused")
		public void WindowClosing(ActionEvent e) {System.exit(0);}
		});
		//背景
		JLabel bgLabel=new JLabel(new ImageIcon(modepath+"BackGround.png"));
		bgLabel.setBounds(0,0,450,690);
		this.getLayeredPane().add(bgLabel,new Integer(Integer.MIN_VALUE));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp=(JPanel)this.getContentPane();
		jp.setOpaque(false);
		//导航栏
		ActionBar=new JPanel();
		add(ActionBar);
		ActionBar.setBounds(0,0,450,50);
		ActionBar.setLayout(null);
		ActionBar.setOpaque(false);
		//按钮
		Control=new JButton(new ImageIcon("image/System/Pause.png"));
		Back=new JButton(new ImageIcon("image/System/Back.png"));
		Back.setVisible(false);
		Control.setBorder(null);
		Back.setBorder(null);
		Control.setContentAreaFilled(false);
		Back.setContentAreaFilled(false);
		Score.setFont(new Font("黑体",30,30));
		Score.setBounds(50,5,450,40);
		Control.setBounds(390,5,40,40);
		Back.setBounds(5,5,40,40);

		ActionBar.add(Score);
		ActionBar.add(Control);
		ActionBar.add(Back);
		
		//死亡界面
		//死亡下滑界面1
		DeathLabel1=new JLabel();
		JLabel GameOverLabe1=new JLabel();
		DeathLabel1.setOpaque(false);
		DeathLabel1.setBounds(0,690,450,690);
		DeathLabel1.add(GameOverLabe1);
		GameOverLabe1.setOpaque(false);
		GameOverLabe1.setVisible(true);
		GameOverLabe1.setIcon(new ImageIcon(languagepath+"System/died.png"));
		GameOverLabe1.setBounds(125,180,200,400);
		DeathLabel1.setIcon(new ImageIcon("image/Classic Mode/basicline.png"));
		add(DeathLabel1);
		
		//死亡下滑界面2
		JLabel GameOverLabe2=new JLabel();
		DeathLabel2=new JLabel();
		JLabel Highscore=new JLabel();
		JLabel Yourname=new JLabel();
		JLabel Yourscore=new JLabel();
		DeathLabel2.add(Highscore);
		DeathLabel2.add(Yourname);
		DeathLabel2.add(Yourscore);
		DeathLabel2.add(GameOverLabe2);
		
		Highscore.setOpaque(false);
		Highscore.setVisible(true);
		Highscore.setIcon(new ImageIcon(languagepath+"System/high score.png"));
		Highscore.setBounds(40,355,300,150);
		
		Yourname.setOpaque(false);
		Yourname.setVisible(true);
		Yourname.setIcon(new ImageIcon(languagepath+"System/your name.png"));
		Yourname.setBounds(40,300,300,150);
		
		Yourscore.setOpaque(false);
		Yourscore.setVisible(true);
		Yourscore.setIcon(new ImageIcon(languagepath+"System/your score.png"));
		Yourscore.setBounds(40,245,300,150);
		
		GameOverLabe2.setOpaque(false);
		GameOverLabe2.setVisible(true);
		GameOverLabe2.setIcon(new ImageIcon(languagepath+"System/gameover.png"));
		GameOverLabe2.setBounds(70,100,300,150);
		
		
		DeathLabel2.setOpaque(false);
		DeathLabel2.setBounds(0,690*2,450,690);
		DeathLabel2.setIcon(new ImageIcon("image/Classic Mode/basicline.png"));
		add(DeathLabel2);
		//暂停界面
		PauseLabel=new JLabel();
		PauseLabel.setOpaque(true);
		PauseLabel.setVisible(false);
		PauseLabel.setIcon(new ImageIcon("image/System/pauselabel.png"));
		PauseLabel.setBounds(15, 100, 415, 503);
		add(PauseLabel);	
		//按钮监听
		Control.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(SOUND) bing.play();
				if(PAUSE==true) {
				ActionBar.add(Score);
				ActionBar.add(Control);
				Score.setBounds(50,5,450,40);
				Control.setBounds(390,5,40,40);
				Control.setIcon(new ImageIcon("image/System/Pause.png"));
				PauseLabel.setVisible(false);
				PAUSE=false;
				}
				else { 
				ActionBar.remove(Score);
				ActionBar.remove(Control);
				Score.setBounds(60,200,450,40);
				Control.setBounds(150, 250, 130,130);
				Back.setBounds(230, 360, 150,150);
				Back.setVisible(true);
				Control.setIcon(new ImageIcon("image/System/Play.png"));
				Back.setIcon(new ImageIcon(languagepath+"Button/menu.png"));
				PauseLabel.add(Control);
				PauseLabel.add(Back);
				PauseLabel.add(Score);
				PauseLabel.setVisible(true);
				PAUSE=true;
				}
				repaint(0);
				requestFocus();
			}});
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				BACK=true;
				Name.setText(Name.getText());
				b.FileWrite(Name.getText()+"        "+SCORE);
				if(SOUND) bing.play();
			}});

		//初始化游戏
		for(int i=0;i<30;i++) {//新建层
			k[i]=new Layer(690-i*23,MODE,0);
		}
		for(int i=0;i<30;i++) {	
			switch(k[i].PROP){
        	case 1:
        		k[i].prop.setLocation(k[i].myPosition[0].x+22,k[i].myPosition[0].y-9);
				add(k[i].prop);
				break;
        	case 2:
        		k[i].prop.setLocation(k[i].myPosition[0].x+22,k[i].myPosition[0].y-23);   				
				add(k[i].prop);
				break;
        	case 3:
        		k[i].prop.setLocation(k[i].myPosition[0].x+22,k[i].myPosition[0].y-37);
				add(k[i].prop);
				break;
        	}
			for(int j=0;j<k[i].NUM;j++) {
				k[i].myBoard[j].setBounds(k[i].myPosition[j].x, k[i].myPosition[j].y,57, 15);
				add(k[i].myBoard[j]);
				}
		}
		Stan= new SmallBoy(MODE);
		V0=4;
		a=25;
		Stan.setBounds(k[10].myPosition[0].x,k[10].myPosition[0].y-StanHeight,StanWidth,StanHeight);
		add(Stan);
		try{
			bing=new MusicPlayer(new File(musicpath+"bing.wav"));//按键声
			jump=new MusicPlayer(new File(musicpath+"jump.wav"));//跳跃
			spring=new MusicPlayer(new File(musicpath+"spring.wav"));//弹簧
			fly=new MusicPlayer(new File(musicpath+"fly.wav"));//飞帽
			rocket=new MusicPlayer(new File(musicpath+"rocket.wav"));//火箭
			boardbreak=new MusicPlayer(new File(musicpath+"break.wav"));//木板破碎
			fly1=new MusicPlayer(new File(musicpath+"Fly1.wav"));//死亡
			move=new MusicPlayer(new File(musicpath+"Move.wav"));//死亡
			monster=new MusicPlayer(new File(musicpath+"Monster.wav"));//死亡
			hole=new MusicPlayer(new File(musicpath+"blackhole.wav"));//死亡
			falldown=new MusicPlayer(new File(musicpath+"falldown.wav"));//死亡
		}catch(Exception e){
		}
		
		Name.setText("panda");
		Name.setOpaque(false);
		Name.setBounds(250,345,100,40);
		Name.setBorder(null);
		Name.setFont(new Font("黑体",38,38));
		Name.setEditable(false);
		Name.setVisible(false);
		HighScore.setText(b.highscore);
		HighScore.setOpaque(false);
		HighScore.setBounds(250,405,100,40);
		HighScore.setBorder(null);
		HighScore.setFont(new Font("黑体",38,38));
		HighScore.setEditable(false);
		HighScore.setVisible(false);
		Name.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				isEdit=true;
				Name.setText("");
				Name.setEditable(true);
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
		});
		Name.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
				if(isEdit==true&&e.getKeyCode()==KeyEvent.VK_ENTER) {
					isEdit = false;
					Name.setEditable(false);
				}
			}
			public void keyReleased(KeyEvent e) {
			}
		});
		DeathLabel2.add(Name);
		DeathLabel2.add(HighScore);
		repaint();
	}
	public void run(){
		requestFocus();//重置焦点
		while(true) {
			//判断是否返回
			if(BACK==true){
				StartFrame start=new StartFrame(MODE,LANGUAGE,MUSIC,SOUND);
				start.setLocation(this.getLocation());
				Thread Start=new Thread(start);
				Start.start();
				dispose();
				Thread.interrupted();
				break;
			}
			//跳跃过程
			if(PAUSE==false) {
				if(isStart==true){  
				move();			  //检测移动
				Stan.Modify();//更改小人样式
				StanWidth=Stan.Modify().x;
				StanHeight=Stan.Modify().y;
				dis();			  //刷新多余木板
				if(moveBg==false){//上升时
					Y = StanBasePOS - jump(t);
					t++;
					if(propflag==true)
						propt++;
					else
						propt--;
				}
				else{			  //移动背景
					for(int i=0;i<30;i++) {
            			k[i].y=k[i].y+V0-t/a;
            			for(int j=0;j<k[i].NUM;j++) {
            				k[i].myPosition[j].y=k[i].y;
            			}	
            		}
					StanBasePOS=270+a*V0*V0/2;
					SCORE+=V0-t/a;
					if(LANGUAGE==true)
						Score.setText("SCORE:"+SCORE);
					else
						Score.setText("分数:"+SCORE);
					t++;
					
					if(propflag==true)
						propt++;
					else
						propt--;
				}
				if(t==V0*a){//下降时
					jumpStatus=false;
					Stan.TYPE=1;
					Stan.FLASH=0;				
				}
				if(Stan.TYPE==2){
	            	if(t%20<10)
	            		Stan.FLASH=1;
	            	else
	            		Stan.FLASH=2;
	            }
				if(Y<270&&jumpStatus==true&&moveBg==false){//判断能否移动背景
					moveBg=true;
				}
				else if(t==V0*a&&moveBg==true){
					moveBg=false;
				}
				if(Y>690){//死亡判断
					isStart = false;
					Y=0;
					t=0;
					DEATH=true;
					falldown.play();
					for(int i=0;i<30;i++){
            		if(k[i].PROP!=0){
            			k[i].PROP=0;
            			remove(k[i].prop);
            		}
        			for(int j=0;j<k[i].NUM;j++){
        				remove(k[i].myBoard[j]);
        			}	
        		}
            }
			if(propt==400) {
				propflag=false;
			}
			if(propt==0) {
				propflag=true;
			}
			if(isJump==true) {
            impact();//碰撞检测
			}
            Stan.setLocation(X,Y);//重新绘制DOODLE
            for(int i=0;i<30;i++) {//重新绘制木板,道具	
            	switch(k[i].PROP){
            	case 1:
            		k[i].prop.setLocation(k[i].myPosition[0].x+22,k[i].myPosition[0].y-9);
            		add(k[i].prop);
    				break;
            	case 2:
            		k[i].prop.setLocation(k[i].myPosition[0].x+22,k[i].myPosition[0].y-23);
            		add(k[i].prop);
    				break;
            	case 3:
            		k[i].prop.setLocation(k[i].myPosition[0].x+22,k[i].myPosition[0].y-37);
            		add(k[i].prop);
    				break;
            	case 4:
    				k[i].prop.setLocation(k[i].myPosition[0].x,k[i].myPosition[0].y);
    				if(propt%30<=10)
    					k[i].prop.setIcon(new ImageIcon(modepath+"Fly1.png"));
    				if(propt%30<=20&&propt%30>10)
    					k[i].prop.setIcon(new ImageIcon(modepath+"Fly2.png"));
    				if(propt%30<=30&&propt%30>20)
    				k[i].prop.setIcon(new ImageIcon(modepath+"Fly3.png"));
    				add(k[i].prop);
    				break;
            	case 5:
    				k[i].prop.setLocation(k[i].myPosition[0].x+(int)24*propt/400,k[i].myPosition[0].y-50);
    				if(propt==0)
    					k[i].prop.setIcon(new ImageIcon(modepath+"Move2.png"));
    				if(propt==400)
    					k[i].prop.setIcon(new ImageIcon(modepath+"Move1.png"));
    				add(k[i].prop);
    				break;
            	case 6:
    				k[i].prop.setLocation(k[i].myPosition[0].x,k[i].myPosition[0].y);
    				add(k[i].prop);
    				remove(k[i].myBoard[0]);
    				break;
            	case 7:
    				k[i].prop.setLocation(k[i].myPosition[0].x,k[i].myPosition[0].y);
    				add(k[i].prop);
    				remove(k[i].myBoard[0]);
    				break;
            	case 8:
    				k[i].prop.setLocation(k[i].myPosition[0].x+22,k[i].myPosition[0].y-29);
    				add(k[i].prop);
    				break;	
            	}
        		for(int j=0;j<k[i].NUM;j++){
        			if(k[i].TYPE[j]==4) 
        				k[i].myPosition[j].y=k[i].y+(int)100*propt/400-50;
        			if(k[i].TYPE[j]==5) 
        				k[i].myPosition[j].x=k[i].x+(int)100*propt/400-50;
        			k[i].myBoard[j].setLocation(k[i].myPosition[j].x,k[i].myPosition[j].y);
		
        		}
        	}
            repaint(0);
        	}
			else{//死亡时
					if(DeathLabel2.getLocation().getY()>0) {
				move();
				Y=(t)*t/30;
				t++;
				Stan.setLocation(X,Y);
				ActionBar.remove(Score);
				ActionBar.remove(Control);
				DeathLabel2.add(Back);
				Score.setBounds(250,300,450,40);
				Score.setFont(new Font("黑体",38, 38));
				Score.setText(""+SCORE);
				Back.setBounds(150, 460, 150,150);
				Back.setIcon(new ImageIcon(languagepath+"Button/backmenu.png"));	
				Back.setVisible(true);
				Name.setVisible(true);		
				HighScore.setVisible(true);
				DeathLabel2.setVisible(true);
				DeathLabel2.add(Score);
				DeathLabel1.setBounds(0, 0-(t)*t/30, 450,690);
				DeathLabel2.setBounds(0, 690-(t)*t/30, 450,690);
				repaint(0);
				}
			}
			}
			//保证线程常驻
			try {
				Thread.sleep(8);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
    }	
	//移动&&版边移屏方法	
	public void move(){
        if(isKey==true){
            X+=step;
        }
        if(X<-20){
            X=470;
        }
        if(X>470){
            X=-20;
        }
    }
	//碰撞检测
	public void impact(){
		for(int i=0;i<30;i++) {		
			for(int j=0;j<k[i].NUM;j++){
				if(jumpStatus==true&&(k[i].PROP==4||k[i].PROP==5||k[i].PROP==6||k[i].PROP==7)&&(Stan.TYPE!=3)) {
					int x=0,y=0;
					switch(k[i].PROP) {
					case 4:
						x=79;
						y=45;
						break;
					case 5:
						x=39;
						y=50;
						break;
					case 6:
						x=83;
						y=53;
						break;
					case 7:
						x=64;
						y=62;
						break;
					}
					if((X>k[i].myPosition[0].x-StanWidth/3*2&&X<k[i].myPosition[0].x+x-StanWidth/3&&Y>k[i].myPosition[0].y&&Y<k[i].myPosition[0].y+y))
					{isJump=false;
					if(k[i].PROP==7)Stan.setVisible(false);
					switch(k[i].PROP) {
					case 4:
						if(SOUND) {
						fly1.play();}
						break;
					case 5:if(SOUND) {
						move.play();}
						break;
					case 6:if(SOUND) {
						monster.play();}
						break;
					case 7:if(SOUND) {
					hole.play();}
						break;
					}
					}
				}
				if(jumpStatus==false&&(X>k[i].myPosition[j].x-StanWidth/3*2&&X<k[i].myPosition[j].x+BdWidth-StanWidth/3&&Y>k[i].myPosition[j].y-StanHeight*2/3-15&&Y<k[i].myPosition[j].y-StanHeight*2/3+15)){
	                jumpStatus=true;
	                t=0;
	                StanBasePOS=k[i].myPosition[j].y-StanHeight;
	                V0=4;
	                a=25;
	                switch(k[i].TYPE[j]){//木板碰撞
	                case 1:
	                	if(k[i].PROP!=4||k[i].PROP!=5||k[i].PROP!=6)
	                		if(SOUND) {jump.play();}
	                	break;
	                case 2:
	                	if(SOUND) {jump.play();}
	                	remove(k[i].myBoard[j]);
	                	k[i].myPosition[j].x=-100;
	                	break;
	                case 3:
	                	jumpStatus=false;
		                t=V0*a*2;
	                	remove(k[i].myBoard[j]);
	                	k[i].myPosition[j].x=-100;
	                	boardbreak.play();
	                	break;
	                case 4:
	                	if(SOUND) {jump.play();}
	                	break;
	                case 5:
	                	if(SOUND) {jump.play();}
	                	break;
	                }
	                if(k[i].PROP!=0){//道具碰撞
	                	Stan.TYPE=k[i].PROP;
	                	if(k[i].PROP==4||k[i].PROP==5||k[i].PROP==6) {
	                		Stan.TYPE=1;
	                	}
	                	switch(k[i].PROP){
	                	case 1:
	                		V0=8;
	                		a=25;
	                		if(SOUND) {spring.play();}	
	                		k[i].Change();	
	                		break;
	                	case 2:
		                	V0=10;
		                	a=30;
		                	if(SOUND) {fly.play();}
		                	k[i].Change();
		                	break;
	                	 case 3:
	 		                V0=20;
	 		                a=20;
	 		               if(SOUND) { rocket.play();}
	 		                k[i].Change();
	 		                break;
	                	 case 4:
	                		k[i].prop.setVisible(false);
	                		k[i].myPosition[0].x=-100;
	                		if(SOUND) {fly1.play();}
	                		SCORE=SCORE+250;
		                	break;
	                	 case 5:
	                		k[i].prop.setVisible(false);
	                		k[i].myPosition[0].x=-100;
	                		if(SOUND) {move.play();}
	                		SCORE=SCORE+500;
		                	break;
	                	 case 6:
	                		 k[i].prop.setVisible(false);
	                		 k[i].myPosition[0].x=-100;
	                		 if(SOUND) {monster.play();}
	                		 SCORE=SCORE+1000;
	                		 break;
	                	 case 7:
	                		 isJump=false;
	                		 Stan.setVisible(false);
	                		 if(SOUND) {hole.play();}
	                		 break;
	                	
	                	}
	                }
	            }
	        }	
		}
	}	
    //最低木板
	public int findMin(){
		int min=k[0].y;
		for(int i=1;i<30;i++)
			min=min<k[i].y?min:k[i].y;
		return min;
	}
	//回收木板
	public void dis(){
		for(int i=0;i<30;i++){
			if(k[i].y>=690){
				if(k[i].PROP!=0)
				remove(k[i].prop);
				for(int j=0;j<k[i].NUM;j++)
    				remove(k[i].myBoard[j]);
				k[i]=new Layer(findMin()-50-(int)SCORE/4000,MODE,SCORE);
				switch(k[i].PROP){
            	case 1:
            		k[i].prop.setLocation(k[i].myPosition[0].x+22,k[i].myPosition[0].y-9);
    				add(k[i].prop);
    				break;
            	case 2:
            		k[i].prop.setLocation(k[i].myPosition[0].x+22,k[i].myPosition[0].y-23);   				
    				add(k[i].prop);
    				break;
            	case 3:
            		k[i].prop.setLocation(k[i].myPosition[0].x+22,k[i].myPosition[0].y-37);
    				add(k[i].prop);
    				break;
            	case 7:
    				k[i].prop.setLocation(k[i].myPosition[0].x+22,k[i].myPosition[0].y-29);
    				add(k[i].prop);
    				break;
            	case 4:
    				k[i].prop.setLocation(k[i].myPosition[0].x,k[i].myPosition[0].y);
    				add(k[i].prop);
    				k[i].myBoard[0].setVisible(false);
    				break;
            	}
				for(int j=0;j<k[i].NUM;j++) {
					k[i].myBoard[j].setBounds(k[i].myPosition[j].x, k[i].myPosition[j].y,57, 15);
    				add(k[i].myBoard[j]);
    			}
			}
		}
	}
	//跳跃方法
	public int jump(int x){
        return V0*x-x*x/2/a;
    }
	//按键监听
	public void keyPressed(KeyEvent e){
		if(isKey==false){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:{
                    step=-2;
                    Stan.TURN=1;
                    isKey=true;
                    break;
                }
                case KeyEvent.VK_RIGHT:{
                    step=2;
                    Stan.TURN=2;
                    isKey=true;
                    break;
                }
            }
        }
	}
	public void keyReleased(KeyEvent e){
		 isKey=false;
	}
	public void keyTyped(KeyEvent e){
	}	
}