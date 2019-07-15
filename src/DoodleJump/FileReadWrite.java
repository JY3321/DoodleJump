package DoodleJump;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JTextArea;
public class  FileReadWrite{
    String highscore;
    FileReadWrite (){
    	FileRW();
    }
    public void FileRW(){
        FileReader fr=null;
        FileWriter fw=null;
        try{
            fr = new FileReader("ScoreRecord.txt");
            BufferedReader br = new BufferedReader(fr);
            String tmp;
            java.util.List<String> list = new ArrayList<String>();
            while ((tmp = br.readLine()) != null){
                list.add(tmp);
            }
            br.close();
            fr.close();
            fw=new FileWriter("ScoreRecord.txt");
            BufferedWriter bw=new BufferedWriter(fw);
            Collections.sort(list, new Comparator<String>(){
                public int compare(String o1, String o2){
                    return Integer.parseInt(o2.split("        ")[1]) - Integer.parseInt(o1.split("        ")[1]);
                }
            });
            highscore=list.get(0).split("        ")[1];
            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i));
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void FileRead(JTextArea a){
    	FileReader fr = null;
    	try{
    		fr = new FileReader("ScoreRecord.txt");
    		BufferedReader br = new BufferedReader(fr);
    		String tmp2;
    		if(br!=null) {
    			for (int i=0;i<3;i++){
    				tmp2 = br.readLine();
    				if (tmp2 == null) break;
    				a.setText(a.getText() + "\n" + tmp2);
    			}
    		}
    		br.close();
    		fr.close();
    	}catch (Exception e){
    		e.printStackTrace();
    	}
    }
    public void FileWrite(String a){
    	FileWriter fw =null;
    	try{
    		fw = new FileWriter("ScoreRecord.txt",true);
    		BufferedWriter bw=new BufferedWriter(fw);
    		bw.write(a);
    		bw.newLine();
    		bw.close();
    		fw.close();
    	}catch (Exception e){
            e.printStackTrace();
        }
     }
}










