package SCENERE;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.awt.Point;

import javax.imageio.ImageIO;
import javax.swing.*;

import SDM.backgrounddata;
import SDM.characterdata;

import java.io.IOException;

public class drawbackground extends Canvas implements KeyListener{
	//set variables
			private int location_X,location_Y,movement;
			private int mapsize_x,mapsize_y;
			private int[] view;
			private BufferStrategy strategy;
			private Graphics g;
			private backgrounddata bgdata;
			private characterdata player;
			private boolean test;
			public drawbackground() throws IOException{
				view = new int[24];
				bgdata = new backgrounddata();
				bgdata.loadMap("mapinfo.txt");
				player = new characterdata();
				mapsize_x=bgdata.getmapsize_x();
				mapsize_y=bgdata.getmapsize_y();
				addKeyListener(this);
				this.setIgnoreRepaint(true);
				test=false;
			}
			
		
			public void repaint(){
				strategy = this.getBufferStrategy();
				g = strategy.getDrawGraphics();
				g.setClip(0, 0, 500, 300);//set clip
				g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
				g.setColor(Color.RED);
				
				renderScene();
			}
			//paint frame
			public void renderScene(){
				/*BufferStrategy strategy = this.getBufferStrategy();
				Graphics g = strategy.getDrawGraphics();
				g.setClip(0, 0, windowsize_x, windowsize_y);//set clip
				g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
				g.setColor(Color.RED);*/
				Point location=player.getVirtualCharacterXY();
				location_X=location.x;
				location_Y=location.y;
				checkmovement(location_X,location_Y);
				checkplayerinborder(location_X,location_Y);
				
				
				
				int paint_X=0,paint_Y=0,current_Y,dest_Y,current_X,dest_X,cnt=0;
				
				//renderScene();
				
				
				if(location_Y<=100){
					current_Y=0;
				}
				else if(location_Y>=1800){
					current_Y=1700;
				}else{
					current_Y = location_Y-100;
				}
				if(location_Y>=mapsize_y-200){
					dest_Y = mapsize_y-100;
				}else{
					dest_Y = location_Y+200;
				}
				while(current_Y<=dest_Y){
					paint_X=0;
					
					if(location_X<=200){
						current_X=0;
					}else if(location_X>=4700){
						current_X=4500;
					}else{
						current_X = location_X-200;
					}
					
					if(location_X>=4700){
						dest_X = 4900;
					}else if(location_X<100){
						dest_X = 500;
					}
					else{
						dest_X = location_X+300;
					}
					while(current_X<=dest_X){
						if(current_Y<=-100)current_Y=0;
						view[cnt++]=bgdata.getScenetype(current_Y/100, current_X/100);
						if(!test){
							g.drawImage(bgdata.getImg(bgdata.getScenetype(current_Y/100, current_X/100)), paint_X-current_X%100, paint_Y-current_Y%100, null);
						}	
						paint_X+=100;
						current_X+=100;
						
					}paint_Y+=100;
					current_Y+=100;
				}
				if(!test){
					player.setmovement(-1);
					g.drawImage(bgdata.getImg(5), player.getdraw_player_X(), player.getdraw_player_Y(), null);
					g.drawString(location_X+","+location_Y, player.getdraw_player_X(), player.getdraw_player_Y());
					//System.out.println(player.getdraw_player_X()+","+player.getdraw_player_Y());
					strategy.show();
					Toolkit.getDefaultToolkit().sync();
				}
				
			}
			
			//check border x and y
			public void checkplayerinborder(int location_X,int location_Y){
				//check map_x border
				int	draw_player_X=player.getdraw_player_X(),draw_player_Y=player.getdraw_player_Y();//,
				movement=player.getmovement();
				if(location_X<200){
					if(draw_player_X>=25&&movement==0)player.setdraw_player_X(draw_player_X-25);
					else if(movement==1)player.setdraw_player_X(draw_player_X+25);
				}else if(location_X==200){
					if(movement==0)player.setdraw_player_X(draw_player_X-25);
				}else if(location_X>4700){
					if(draw_player_X<=375&&movement==1)player.setdraw_player_X(draw_player_X+25);
					else if(movement==0)player.setdraw_player_X(draw_player_X-25);
				}else if(location_X==4700){
					if(movement==1)player.setdraw_player_X(draw_player_X+25);
				}
				
				//check map_y border
				if(location_Y<100){
					if(draw_player_Y>=25&&movement==2)player.setdraw_player_Y(draw_player_Y-25);
					else if(movement==3)player.setdraw_player_Y(draw_player_Y+25);
				}else if(location_Y==100){
					if(movement==2)player.setdraw_player_Y(draw_player_Y-25);
					//if(movement==3)player.setdraw_player_Y(draw_player_Y+25);
				}else if(location_Y>mapsize_y-(((300/2)/100)*100+100)){
					if(draw_player_Y<=175&&movement==3)player.setdraw_player_Y(draw_player_Y+25);
					else if(movement==2)player.setdraw_player_Y(draw_player_Y-25);
				}else if(location_Y==mapsize_y-(((300/2)/100)*100+100)){
					if(movement==3)player.setdraw_player_Y(draw_player_Y+25);
				}
			}
			
			//check collision with obstacle
			public void checkmovement(int location_X,int location_Y){
				movement=player.getmovement();
				//System.out.print(movement);
				if(movement==0){
					if(location_X>=25){//&&(bgdata.getScenetype(location_Y/100, (location_X-25)/100)!=0&&bgdata.getScenetype((location_Y+75)/100, (location_X-25)/100)!=0)){
			    		player.setlocation_X(location_X-25);
			    	}else player.setmovement(-1);
				}else if(movement==1){
					if(location_X<=4875){//&&(bgdata.getScenetype(location_Y/100, (location_X+100)/100)!=0&&bgdata.getScenetype((location_Y+75)/100, (location_X+100)/100)!=0)){
						player.setlocation_X(location_X+25);
			    	}else player.setmovement(-1);
				}else if(movement==2){
					if(location_Y>=25){//&&(bgdata.getScenetype((location_Y-25)/100, (location_X)/100)!=0&&bgdata.getScenetype((location_Y-25)/100, (location_X+75)/100)!=0)){
			    		player.setlocation_Y(location_Y-25);
			    	}else player.setmovement(-1);
				}else if(movement==3){
					if(location_Y<=(mapsize_y-100-25)){//&&(bgdata.getScenetype((location_Y+100)/100, (location_X)/100)!=0&&bgdata.getScenetype((location_Y+100)/100, (location_X+75)/100)!=0)){
						player.setlocation_Y(location_Y+25);
			    	}else player.setmovement(-1);
				}
				
			}
			
			public int[] getview(){
				return view;
			}
			
			public void settest(){
				test=true;
			}
			
			public void setSDM(characterdata playerstub){
				player=playerstub;
			}
			/*public void setupmovementtest(int i){
				movement=i;
				Point location=player.getVirtualCharacterXY();
				location_X=location.x;
				location_Y=location.y;
			}
			
			public void setupbordertest(int i){
				movement=i;
				location_X=200;
				location_Y=100;
			}
			
			/*public Point getlocation(){
				Point location=player.getVirtualCharacterXY();
				return location;
			}
			
			
			
			public Point getdrawlocation(){
				Point drawlocation=new Point(player.getdraw_player_X(),player.getdraw_player_Y());
				return drawlocation;
			}*/
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int key = e.getKeyCode();
				//left
			    if (key == KeyEvent.VK_LEFT) {
			    	player.setmovement(0);
			    }
			    //right
			    else if (key == KeyEvent.VK_RIGHT) {
			    	player.setmovement(1);
			    }
			    //up
			    else  if (key == KeyEvent.VK_UP) {
			    	player.setmovement(2);
			    }
			    //down
			    else  if (key == KeyEvent.VK_DOWN) {
			    	player.setmovement(3);
			    }

			}
}
