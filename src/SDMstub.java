import java.awt.Point;

import SDM.*;


public class SDMstub extends characterdata{
	private int draw_player_X=200;
	private int draw_player_Y=100;
	private int movement=-1;
	private backgrounddata bg;
	private Point location;
	
	public SDMstub(){
		location=new Point(2500,1000);
	}
	@Override
	public Point getVirtualCharacterXY(){
		return location;
	}
	@Override
	public int getdraw_player_X(){
		return draw_player_X;
	}
	@Override
	public int getdraw_player_Y(){
		return draw_player_Y;
	}
	@Override
	public int getmovement(){
		return movement;
	}
	@Override
	public void setlocation_X(int i){
		assert i<=bg.getmapsize_x()-100;
		location.x=i;
	}
	@Override
	public void setlocation_Y(int i){
		assert i<=bg.getmapsize_y()-100;
		location.y=i;
	}
	@Override
	public void setdraw_player_X(int i){
		assert i>=0 && i<=500;
		draw_player_X=i;
	}
	@Override
	public void setdraw_player_Y(int i){
		assert i>=0 && i<=300;
		draw_player_Y=i;
	}
	@Override
	public void setmovement(int i){
		assert i>=-1 && i<=3;
		movement=i;
	}
}
