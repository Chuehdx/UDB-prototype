package SDM;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.imageio.ImageIO;

public class backgrounddata {
	private BasicBlock[][] scene;
	private Image[] img;
	private boolean loadsuccess;
	private int mapsize_x,mapsize_y;
	public backgrounddata(){
		
		scene = new BasicBlock[20][50];
		img=new Image[6];
		mapsize_x=5000;
		mapsize_y=2000;
		loadsuccess=false;
	}

	public void loadMap(String mapfile){
		//read map
		try{
			img[0] = ImageIO.read(new File(("blue.png")));	//obstacle
			img[1] = ImageIO.read(new File(("red.png")));
			img[2] = ImageIO.read(new File(("black.png")));
			img[3] = ImageIO.read(new File(("green.png")));
			img[4] = ImageIO.read(new File(("yellow.png")));
			img[5] = ImageIO.read(new File(("player.png")));
		}catch(IOException e){
			e.printStackTrace();
		}
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(mapfile);
			bufferedReader = new BufferedReader(fileReader);
			loadsuccess=true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			loadsuccess=false;
			defaultmapsetup();
			//e.printStackTrace();
		}
        
        
        //read map from file
		if(loadsuccess){
			for(int y=0;y<20;y++){
	  			for(int x=0;x<50;x++){
	  				scene[y][x] = new BasicBlock();
	  				do{
	  					try {
							scene[y][x].color = Integer.parseInt(bufferedReader.readLine());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
	  				}while(x==25&&y==10&&scene[y][x].color==0);
	  			}
	  		}  
		}
        
		/*
		//randomize map
		for(int y=0;y<20;y++){
			for(int x=0;x<50;x++){
				scene[y][x] = new BasicBlock();
				do{
					scene[y][x].color = (int) (Math.random()*5); 
				}while(x==25&&y==10&&scene[y][x].color==0);
			}
		}
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("mapinfo.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int y=0;y<20;y++){
			for(int x=0;x<50;x++){
					writer.println(scene[y][x].color); 			
			}
		}
		writer.close();*/
	}
	
	public void defaultmapsetup(){
		//randomize map
		for(int y=0;y<20;y++){
			for(int x=0;x<50;x++){
				scene[y][x] = new BasicBlock();
				do{
					scene[y][x].color = (int) (Math.random()*5); 
				}while(x==25&&y==10&&scene[y][x].color==0);
			}
		}
	}
	
	public Image getImg(int i){
		assert i>=0 && i<=5 : "Image not found.";
		return img[i];
	}
	
	public int getScenetype(int x,int y){
		return scene[x][y].color;
	}
	
	
	public int getmapsize_x(){
		return mapsize_x;
	}
	
	public int getmapsize_y(){
		return mapsize_y;
	}
	
	public boolean getloadsuccess(){
		return loadsuccess;
	}
	
}


//basic map block
class BasicBlock{
	int color;
}
