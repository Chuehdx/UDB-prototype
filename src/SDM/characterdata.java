package SDM;
import java.awt.Point;

public class characterdata {
	private int draw_player_X,draw_player_Y,movement;
	private backgrounddata bg;
	private Point location;
	public characterdata(){
		location=new Point(2500,1000);
		draw_player_X=200;
		draw_player_Y=100;
		movement=-1;
		bg=new backgrounddata();
	}
	
	public Point getVirtualCharacterXY(){
		return location;
	}

	
	public int getdraw_player_X(){
		return draw_player_X;
	}
	
	public int getdraw_player_Y(){
		return draw_player_Y;
	}
	
	public int getmovement(){
		return movement;
	}
	
	public void setlocation_X(int i){
		assert i<=bg.getmapsize_x()-100;
		location.x=i;
	}
	
	public void setlocation_Y(int i){
		assert i<=bg.getmapsize_y()-100;
		location.y=i;
	}
	
	public void setdraw_player_X(int i){
		assert i>=0 && i<=500;
		draw_player_X=i;
	}
	
	public void setdraw_player_Y(int i){
		assert i>=0 && i<=300;
		draw_player_Y=i;
	}
	
	public void setmovement(int i){
		assert i>=-1 && i<=3;
		movement=i;
	}
}
