package DoodleJump;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class StartFrame extends JFrame implements Runnable{//开始界面
	//属性
	int MODE,TIME=8;//模式,修改数值变小可以减少卡顿(StartFrame,MainFrame)
	boolean MUSIC=false,LANGUAGE=true,SOUND=false;//音乐开关、语言切换、音效开关
	//控件
	MusicPlayer bing,jump,ufo,bgm;//声明音乐
	JButton Start,Scores,Rule,Option,Next,Music,Language,Sound,Left,Right;//声明按钮
	JLabel NextLabel,UfoLabel,modeLabel1,modeLabel2,nextScoreCrown1,nextScoreCrown2,nextScoreCrown3;//二级界面框、UFO动画、模式界面、皇冠图
	JTextArea nextScoreRecord=new JTextArea();//排行榜
	SmallBoy Stan;//Stan动画
	//参数
	int t,t2;//动画参数
	boolean isStart=false,isRule=false,isScores=false,isNext=false,isOption=false,isLeft=false,isRight=false;
	//路径
	String languagepath,musicpath="sounds/";
	
	@SuppressWarnings("deprecation")
	public StartFrame(int mode,boolean language,boolean music,boolean sound){//初始化开始界面
		//界面属性设置
		MODE=mode;
		LANGUAGE=language;
		MUSIC=music;
		SOUND=sound;
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setSize(450,720);
		if(LANGUAGE){
			languagepath="image/English/";
			this.setTitle("DoodleJump");	
		}
		else{
			languagepath="image/Chinese/";
			this.setTitle("涂鸦跳跃");
		}
		//背景，模式背景
		JLabel bgLabel=new JLabel(new ImageIcon("image/System/menu.png"));
		modeLabel1=new JLabel(new ImageIcon("image/System/basic.png"));
		modeLabel2=new JLabel(new ImageIcon("image/System/basic.png"));
		switch(MODE){
		case 1:modeLabel1.setIcon(new ImageIcon("image/System/basic.png"));
		break;
		case 2:modeLabel1.setIcon(new ImageIcon("image/System/winter.png"));
		break;
		case 3:modeLabel1.setIcon(new ImageIcon("image/System/jungle.png"));
		break;
		case 4:modeLabel1.setIcon(new ImageIcon("image/System/underwater.png"));
		break;
		}
		bgLabel.setBounds(-5,-50,450,730);
		modeLabel1.setBounds(0,620,450,60);
		this.getLayeredPane().add(bgLabel,new Integer(Integer.MIN_VALUE+1));
		this.getLayeredPane().add(modeLabel1,new Integer(Integer.MIN_VALUE));
		this.getLayeredPane().add(modeLabel2,new Integer(Integer.MIN_VALUE));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		JPanel jp=(JPanel)this.getContentPane();
		jp.setOpaque(false);
		//模式按钮
		Left=new JButton();
		Left.setBounds(0, 620, 225, 60);
		Left.setBorder(null);
		Left.setContentAreaFilled(false);
		Left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MODE--;
				if(SOUND)bing.play();
				switch(MODE) {
				case 1:modeLabel2.setIcon(new ImageIcon("image/System/basic.png"));
				break;
				case 2:modeLabel2.setIcon(new ImageIcon("image/System/winter.png"));
				break;
				case 3:modeLabel2.setIcon(new ImageIcon("image/System/jungle.png"));
				break;
				}
				t2=0;
				modeLabel2.setBounds(-450,620,450,60);
				isLeft=true;
				}	
			}
				);
		Right=new JButton();
		Right.setBounds(225, 620, 225, 60);
		Right.setBorder(null);
		Right.setContentAreaFilled(false);
		Right.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				MODE++;
				if(SOUND)bing.play();
				switch(MODE) {
				case 2:modeLabel2.setIcon(new ImageIcon("image/System/winter.png"));
				break;
				case 3:modeLabel2.setIcon(new ImageIcon("image/System/jungle.png"));
				break;
				case 4:modeLabel2.setIcon(new ImageIcon("image/System/underwater.png"));
				break;
				}
				t2=0;
				modeLabel2.setBounds(450,620,450,60);
				isRight=true;
				}
			}
				);
		add(Left);
		add(Right);
		//开始按钮
		Start=new JButton(new ImageIcon(languagepath+"Button/start.png"));
		Start.setContentAreaFilled(false);
		Start.setBorder(null); 
		Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				isStart=true;
				if(SOUND)bing.play();
			}});
		Start.setBounds(90,250,105,40);
		add(Start);
		//规则按钮
		Rule=new JButton(new ImageIcon(languagepath+"Button/rule.png"));
		Rule.setContentAreaFilled(false);
		Rule.setBorder(null); 
		Rule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				NextLabel.setIcon(new ImageIcon(languagepath+"System/rule1.png"));
				NextLabel.setBounds(20, 250, 403, 255);
				Next.setIcon(new ImageIcon(languagepath+"Button/next.png"));
				Next.setBounds(250,200,83,40);
				isRule=true;
				if(SOUND)bing.play();
			}});
		Rule.setBounds(300,400,105,40);
		add(Rule);
		//设置按钮
		Option=new JButton(new ImageIcon(languagepath+"Button/option.png"));
		Option.setContentAreaFilled(false);
		Option.setBorder(null); 
		Option.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				NextLabel.setIcon(new ImageIcon(languagepath+"System/option.png"));
				NextLabel.setBounds(70, 150, 307, 403);
				Next.setIcon(new ImageIcon(languagepath+"Button/return.png"));
				Next.setBounds(180,330,83,40);
				isOption=true;
				if(SOUND)bing.play();
			}});
		Option.setBounds(150,470,105,40);
		add(Option);
		//排行榜按钮
		Scores=new JButton(new ImageIcon(languagepath+"Button/scores.png"));
		Scores.setContentAreaFilled(false);
		Scores.setBorder(null); 
		Scores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				isScores=true;
				if(SOUND)bing.play();
			}});
		Scores.setBounds(220,320,105,40);
		add(Scores);
		//小人动画
		Board myboard=new Board(1,1);
		myboard.setBounds(40,527,63,15);
		add(myboard);
		Stan=new SmallBoy(1);
		Stan.setBounds(35,280,62,47);
		add(Stan);
		//UFO动画
		UfoLabel=new JLabel(new ImageIcon("image/System/UFO1.png"));
		UfoLabel.setBounds(291, 29, 101, 146);
		add(UfoLabel,-1);
		//音乐注册
		try{
			bing=new MusicPlayer(new File("sounds/bing.wav"));//按键音
			jump=new MusicPlayer(new File("sounds/jump.wav"));//跳跃
			ufo=new MusicPlayer(new File("sounds/ufo.wav"));//UFO音效
			bgm=new MusicPlayer(new File("sounds/bgm.wav"));//BGM
		}catch(Exception e){
		}
		//二级界面框
		NextLabel=new JLabel();
		NextLabel.setOpaque(true);
		NextLabel.setVisible(false);
		Next=new JButton(new ImageIcon(languagepath+"Button/next.png"));
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				isNext=true;
				if(SOUND)bing.play();
			}});
		NextLabel.setIcon(new ImageIcon(languagepath+"System/rule1.png"));
		NextLabel.add(Next);
		add(NextLabel);			
		//排行榜界面
		nextScoreRecord =new JTextArea();
		nextScoreCrown1 =new JLabel();
		nextScoreCrown2=new JLabel();
		nextScoreCrown3 =new JLabel();
		Scores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NextLabel.setIcon(new ImageIcon(languagepath+"System/rank.png"));
				NextLabel.setBounds(13,240,411,368);
				nextScoreRecord.setBounds(160,90,200,200);
				nextScoreRecord.setFont(new java.awt.Font("Dialog",1,25));
				nextScoreRecord.setOpaque(false);
				nextScoreRecord.setVisible(false);
				NextLabel.add(nextScoreRecord);
				nextScoreCrown1.setIcon(new ImageIcon("image/System/crown1.png"));
				nextScoreCrown1.setBounds(100,130,30,30);
				nextScoreCrown1.setVisible(false);
				NextLabel.add(nextScoreCrown1);
				nextScoreCrown2.setIcon(new ImageIcon("image/System/crown2.png"));
				nextScoreCrown2.setBounds(100,165,30,30);
				nextScoreCrown2.setVisible(false);
				NextLabel.add(nextScoreCrown2);
				nextScoreCrown3.setIcon(new ImageIcon("image/System/crown3.png"));
				nextScoreCrown3.setBounds(100,195,30,30);
				nextScoreCrown3.setVisible(false);
				NextLabel.add(nextScoreCrown3);
				Next.setIcon(new ImageIcon(languagepath+"Button/back.png"));
				Next.setBounds(180,290,83,40);
				FileReadWrite a=new FileReadWrite();
				a.FileRW();
				if(LANGUAGE)
				nextScoreRecord.setText("ID          SCORE");
				else
				nextScoreRecord.setText("姓名        分数");
				a.FileRead(nextScoreRecord);
				nextScoreRecord.setEditable(false);
				isScores=true;
				if(SOUND) bing.play();
				}});
		//选项界面背景音乐按钮
		Music=new JButton();
		Music.setVisible(false);
		NextLabel.add(Music);
		Music.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				if(SOUND)bing.play();
				if(MUSIC==true) {
					MUSIC=false;
					bgm.stop();
					}
				else {
					MUSIC=true;
					bgm.play(0);
				}
			}});
		//选项界面语言按钮		
		Language=new JButton();
		Language.setVisible(false);
		NextLabel.add(Language);
		Language.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				if(SOUND)bing.play();
				if(LANGUAGE==true)
					LANGUAGE=false;
				else
					LANGUAGE=true;
			}});
		//选项界面音效按钮	
		Sound=new JButton();
		Sound.setVisible(false);
		NextLabel.add(Sound);
		Sound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				if(SOUND)bing.play();
				if(SOUND==true)
					SOUND=false;
				else
					SOUND=true;
			}});
		ufo.play();
		repaint();
	}
	
	public static void main(String[] args) {
		StartFrame start=new StartFrame(1,true,false,true);
		Thread main=new Thread(start);
		main.start();
	}
	
	public void run(){
		int Step=1;
		int x=300,y=25;
		while(true) {	
			//动画
			Stan.setLocation(35,480-4*t+t*t/50);
			t++;
			if(t<66) 
			UfoLabel.setIcon(new ImageIcon("image/System/UFO1.png"));
			else if(t<132) 
			UfoLabel.setIcon(new ImageIcon("image/System/UFO2.png"));
			else
			UfoLabel.setIcon(new ImageIcon("image/System/UFO3.png"));
			UfoLabel.setLocation(x+(int)(25*Math.sin(6.28*t/200-3.14)),y+(int)(25*Math.cos(6.28*t/200-3.14)));
			if(t==200){
				t=0;
				if(SOUND)jump.play();
			}
			//开始游戏
			if(isStart==true){
				bgm.stop();
				MainFrame game=new MainFrame(MODE,LANGUAGE,MUSIC,SOUND);
				game.setLocation(this.getLocation());
				Thread Game=new Thread(game);	
				Game.start();
				this.setVisible(false);
				Thread.interrupted();
				break;
			}
			//规则二级界面
			if(isRule==true) {
				nextlabelVisible();
				if(isNext==true) {
					Step++;
					Next.setIcon(new ImageIcon(languagepath+"Button/next.png"));
						NextLabel.setIcon(new ImageIcon(languagepath+"System/rule"+Step+".png"));
					if(Step==3) {
						Next.setIcon(new ImageIcon(languagepath+"Button/back.png"));
					}
					if(Step==4) {
						nextlabelHidden();	
						Step=1;
						isRule=false;			
					}
					isNext=false;	
				}
			}
			//排行榜二级界面
			if(isScores==true){
				nextlabelVisible();
				nextScoreRecord.setVisible(true);
				nextScoreCrown1.setVisible(true);
				nextScoreCrown2.setVisible(true);
				nextScoreCrown3.setVisible(true);
				if(isNext==true){
					nextlabelHidden();
					isScores=false;
				}
				isNext=false;
			}
			//选项二级界面
			if(isOption==true) {
				nextlabelVisible();
				Music.setVisible(true);
				Language.setVisible(true);
				Sound.setVisible(true);
				if(isNext==true) {
					nextlabelHidden();
					isOption=false;	
				}
				isNext=false;
				if(LANGUAGE==true) {
					languagepath="image/English/";
					changeLanugage();
					this.setTitle("DoodleJump");
					Language.setBounds(160,80,110,41);
					Language.setIcon(new ImageIcon("image/English/Button/English.png"));	
				}
				else {
					languagepath="image/Chinese/";
					Language.setBounds(120,80,83,40);
					changeLanugage();
					this.setTitle("涂鸦跳跃");
					Language.setIcon(new ImageIcon("image/Chinese/Button/chinese.png"));
				}
				repaint(0);
				if(MUSIC==true) {
					Music.setBounds(120,130,83,40);
					Music.setIcon(new ImageIcon(languagepath+"Button/musicon.png"));
				}
				else {
					Music.setBounds(120,130,90,38);
					Music.setIcon(new ImageIcon(languagepath+"Button/musicoff.png"));
				}
				if(SOUND==true) {
					Sound.setBounds(120,180,83,40);
					Sound.setIcon(new ImageIcon(languagepath+"Button/musicon.png"));
				}
				else {
					Sound.setBounds(120,180,90,38);
					Sound.setIcon(new ImageIcon(languagepath+"Button/musicoff.png"));
				}
			}
			if(isLeft==true) {
				t2++;
				Left.setEnabled(false);
				Right.setEnabled(false);
				modeLabel1.setLocation(0+450*t2/200,620);
				modeLabel2.setLocation(-450+450*t2/200,620);
				repaint(0);
				if(modeLabel2.getLocation().x==0) {
					isLeft=false;
					Left.setEnabled(true);
					Right.setEnabled(true);
					switch (MODE) {
					case 1:modeLabel1.setIcon(new ImageIcon("image/System/basic.png"));
					break;
					case 2:modeLabel1.setIcon(new ImageIcon("image/System/winter.png"));
					break;
					case 3:modeLabel1.setIcon(new ImageIcon("image/System/jungle.png"));
					break;
					case 4:modeLabel1.setIcon(new ImageIcon("image/System/underwater.png"));
					break;
					}
				}
			}
			//模式切换
			if(isRight==true) {
				t2++;
				Right.setEnabled(false);
				Left.setEnabled(false);
				modeLabel1.setLocation(0-450*t2/200,620);
				modeLabel2.setLocation(450-450*t2/200,620);
				repaint(0);
				if(modeLabel2.getLocation().x==0) {
					isRight=false;
					Left.setEnabled(true);
					Right.setEnabled(true);
					switch (MODE) {
					case 1:modeLabel1.setIcon(new ImageIcon("image/System/basic.png"));
					break;
					case 2:modeLabel1.setIcon(new ImageIcon("image/System/winter.png"));
					break;
					case 3:modeLabel1.setIcon(new ImageIcon("image/System/jungle.png"));
					break;
					case 4:modeLabel1.setIcon(new ImageIcon("image/System/underwater.png"));
					break;
					}
				}
			}
			if(MODE==1) Left.setEnabled(false);
			if(MODE==4) Right.setEnabled(false);
			//保证线程常驻
			try {
				Thread.sleep(TIME);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	//主页面二级提示框显示与隐藏
	public void nextlabelHidden(){
		NextLabel.setVisible(false);
		Start.setVisible(true);
		Scores.setVisible(true);
		Rule.setVisible(true);
		Option.setVisible(true);
		if(isOption==true) {
			Music.setVisible(true);
			Language.setVisible(true);
			Sound.setVisible(true);
		}
		if(isScores==true){
			nextScoreRecord.setVisible(true);
			nextScoreCrown1.setVisible(true);
			nextScoreCrown2.setVisible(true);
			nextScoreCrown3.setVisible(true);
		}
	}
	public void nextlabelVisible(){
		NextLabel.setVisible(true);
		Start.setVisible(false);
		Scores.setVisible(false);
		Rule.setVisible(false);
		Option.setVisible(false);
		if(isOption==false) {
			Music.setVisible(false);
			Language.setVisible(false);
			Sound.setVisible(false);
		}
		if(isScores==false){
			nextScoreRecord.setVisible(false);
			nextScoreCrown1.setVisible(false);
			nextScoreCrown2.setVisible(false);
			nextScoreCrown3.setVisible(false);
		}
	}
	//更改语言
	public void changeLanugage() {
		Start.setIcon((new ImageIcon(languagepath+"Button/start.png")));
		Rule.setIcon((new ImageIcon(languagepath+"Button/rule.png")));
		Option.setIcon((new ImageIcon(languagepath+"Button/option.png")));
		Scores.setIcon((new ImageIcon(languagepath+"Button/scores.png")));
		Next.setIcon((new ImageIcon(languagepath+"Button/back.png")));
		NextLabel.setIcon(new ImageIcon(languagepath+"System/option.png"));	
	}
}
