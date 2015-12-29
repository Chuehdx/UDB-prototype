import static org.junit.Assert.*;

import java.awt.Point;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import SCENERE.drawbackground;


public class SCENEREtest {
	drawbackground draw;
	SDMstub SDM;
	@Before
	public void setUp() throws Exception {
		draw = new drawbackground();
		SDM = new SDMstub();
		draw.setSDM(SDM);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void renderscenetest(){
		draw.settest();
		draw.renderScene();
		int[] view=new int[24];
		view=draw.getview();
		int[] correct = new int[24];
		correct[0]=0;correct[1]=0;correct[2]=2;correct[3]=3;
		correct[4]=1;correct[5]=2;correct[6]=4;correct[7]=2;
		correct[8]=1;correct[9]=1;correct[10]=1;correct[11]=2;
		correct[12]=4;correct[13]=1;correct[14]=4;correct[15]=4;
		correct[16]=4;correct[17]=3;correct[18]=0;correct[19]=3;
		correct[20]=2;correct[21]=0;correct[22]=2;correct[23]=0;
		Assert.assertArrayEquals(view,correct);
		
		SDM.setlocation_X(0);
		SDM.setlocation_Y(0);
		draw.renderScene();
		view=draw.getview();
		correct[0]=1;correct[1]=1;correct[2]=0;correct[3]=1;
		correct[4]=3;correct[5]=1;correct[6]=0;correct[7]=2;
		correct[8]=0;correct[9]=2;correct[10]=1;correct[11]=2;
		correct[12]=4;correct[13]=3;correct[14]=3;correct[15]=3;
		correct[16]=4;correct[17]=2;correct[18]=0;correct[19]=3;
		correct[20]=2;correct[21]=0;correct[22]=2;correct[23]=0;
		Assert.assertArrayEquals(view,correct);
		
		SDM.setlocation_X(4900);
		SDM.setlocation_Y(1900);
		draw.renderScene();
		view=draw.getview();
		correct[0]=1;correct[1]=2;correct[2]=4;correct[3]=4;
		correct[4]=2;correct[5]=3;correct[6]=0;correct[7]=3;
		correct[8]=1;correct[9]=1;correct[10]=3;correct[11]=1;
		correct[12]=1;correct[13]=2;correct[14]=0;correct[15]=3;
		correct[16]=4;correct[17]=2;correct[18]=0;correct[19]=3;
		correct[20]=2;correct[21]=0;correct[22]=2;correct[23]=0;
		Assert.assertArrayEquals(view,correct);
	}
	
	
	@Test
	public void checkmovementtest(){
		SDM.setmovement(0);
		draw.checkmovement(SDM.getVirtualCharacterXY().x,SDM.getVirtualCharacterXY().y);
		Point p0 = new Point(2475,1000);
		Assert.assertEquals(SDM.getVirtualCharacterXY(),p0);
		
		SDM.setmovement(1);
		draw.checkmovement(SDM.getVirtualCharacterXY().x,SDM.getVirtualCharacterXY().y);
		Point p1 = new Point(2500,1000);
		Assert.assertEquals(SDM.getVirtualCharacterXY(),p1);
		
		SDM.setmovement(2);
		draw.checkmovement(SDM.getVirtualCharacterXY().x,SDM.getVirtualCharacterXY().y);
		Point p2 = new Point(2500,975);
		Assert.assertEquals(SDM.getVirtualCharacterXY(),p2);
		
		SDM.setmovement(3);
		draw.checkmovement(SDM.getVirtualCharacterXY().x,SDM.getVirtualCharacterXY().y);
		Point p3 = new Point(2500,1000);
		Assert.assertEquals(SDM.getVirtualCharacterXY(),p3);
	}
	
	@Test
	public void checkplayerinbordertest() throws IOException {
		SDM.setmovement(2);
		SDM.setlocation_X(200);
		SDM.setlocation_Y(100);
		draw.checkplayerinborder(SDM.getVirtualCharacterXY().x,SDM.getVirtualCharacterXY().y);
		Assert.assertEquals(SDM.getdraw_player_X(),200);
		Assert.assertEquals(SDM.getdraw_player_Y(),75);
		
		SDM.setmovement(3);
		SDM.setlocation_X(200);
		SDM.setlocation_Y(75);
		draw.checkplayerinborder(SDM.getVirtualCharacterXY().x,SDM.getVirtualCharacterXY().y);
		Assert.assertEquals(SDM.getdraw_player_X(),200);
		Assert.assertEquals(SDM.getdraw_player_Y(),100);
		
		SDM.setmovement(0);
		SDM.setlocation_X(200);
		SDM.setlocation_Y(100);
		draw.checkplayerinborder(SDM.getVirtualCharacterXY().x,SDM.getVirtualCharacterXY().y);
		Assert.assertEquals(SDM.getdraw_player_X(),175);
		Assert.assertEquals(SDM.getdraw_player_Y(),100);

		SDM.setmovement(1);
		SDM.setlocation_X(175);
		SDM.setlocation_Y(100);
		draw.checkplayerinborder(SDM.getVirtualCharacterXY().x,SDM.getVirtualCharacterXY().y);
		Assert.assertEquals(SDM.getdraw_player_X(),200);
		Assert.assertEquals(SDM.getdraw_player_Y(),100);
	}
}
