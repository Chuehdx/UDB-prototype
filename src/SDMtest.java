import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Test;

import SDM.*;


public class SDMtest {
	backgrounddata bg;
	characterdata cd;
	
	@Before
	public void setUp() throws Exception {
		bg = new backgrounddata();

		cd = new characterdata();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	
	
	
	@Test
	public void loadmaptest() {
		bg.loadMap("map.txt");
		Assert.assertFalse(bg.getloadsuccess());
		for(int y=0;y<20;y++){
  			for(int x=0;x<50;x++){
  				assert bg.getScenetype(y, x)>=0 && bg.getScenetype(y, x)<5;
  			}
  		}
		
		String result="";
		bg.loadMap("mapinfo.txt");
		for(int y=0;y<20;y++){
  			for(int x=0;x<50;x++){
  				result+=bg.getScenetype(y, x);
  			}
  		}
		Assert.assertEquals(result,"110131343314342414231331224104103130032403114"
				+ "020220202122043244422300304024403421122120331024311012243334203"
				+ "0210343303330233211440324203214114222324202341313304300112223002"
				+ "114302411211021410320230024122121131322200103133420301410002030"
				+ "4432334311011244240040212231200241013322330243401423121014002420"
				+ "4402302120340242341240424222424011102403000300411013003303044004"
				+ "1412412204210232430031401022212434011330144102200243424430204420"
				+ "4322242210022044424423201314401031103131232231002312102331424102"
				+ "4231213013103430243321204334230042111242321443212400142422042432"
				+ "3111001043442002244144434104322440143431440000232031424324112401"
				+ "2002032020203221210222133043231330210011433042432430410321121231"
				+ "2313120430403330200240113334121024201131321133233404340234421312"
				+ "4411300440203431110314403323110224424043023244320212021344024434"
				+ "2011043322132330034110211211221300320333303044341200322423012341"
				+ "2024414121131342134212442031304223024403211030344331001103114124"
				+ "0444203031113331331312100321423103232042222200234412310031120");

	}
	
	@Test
	public void getimgtest() throws IOException {
		bg.loadMap("mapinfo.txt");
		Image img[] = new Image[6];
		img[0] = ImageIO.read(new File(("blue.png")));	
		img[1] = ImageIO.read(new File(("red.png")));
		img[2] = ImageIO.read(new File(("black.png")));
		img[3] = ImageIO.read(new File(("green.png")));
		img[4] = ImageIO.read(new File(("yellow.png")));
		img[5] = ImageIO.read(new File(("player.png")));
		Image[] testimg = new Image[6];
		testimg[0] = bg.getImg(0);
		testimg[1] = bg.getImg(1);
		testimg[2] = bg.getImg(2);
		testimg[3] = bg.getImg(3);
		testimg[4] = bg.getImg(4);
		testimg[5] = bg.getImg(5);
		for(int i=0;i<6;i++){
			assertNotNull(testimg[i]);
		}
		int[][] pixels1 = new int[img[0].getHeight(null)][img[0].getWidth(null)];
		int[][] pixels2 = new int[testimg[0].getHeight(null)][testimg[0].getWidth(null)];
		for(int k=0;k<6;k++){
			for( int i = 0; i < img[k].getHeight(null); i++ )
			   for( int j = 0; j < img[k].getWidth(null); j++ )
			        pixels1[i][j] = ((BufferedImage) img[k]).getRGB( i, j );
			
			for( int i = 0; i < testimg[k].getHeight(null); i++ )
			    for( int j = 0; j < testimg[k].getWidth(null); j++ )
			        pixels2[i][j] = ((BufferedImage) testimg[k]).getRGB( i, j );
			Assert.assertArrayEquals(pixels2,pixels1);
		}
		
		
	}
	
	@Test
	public void getscenetypetest() {
		backgrounddata bg = new backgrounddata();
		bg.loadMap("mapinfo.txt");
		Assert.assertEquals(bg.getScenetype(0, 0),1);
		Assert.assertEquals(bg.getScenetype(0, 2),0);
		Assert.assertEquals(bg.getScenetype(12, 20),0);
		Assert.assertEquals(bg.getScenetype(3, 40),3);
		Assert.assertEquals(bg.getScenetype(18, 33),1);
	}

	
	
	
	
	@Test
	public void getVirtualCharacterXYtest() {
		characterdata cd = new characterdata();
		Point tmp = new Point(2500,1000);
		Assert.assertEquals(cd.getVirtualCharacterXY(),tmp);
	}
	
	@Test
	public void getdraw_player_Xtest() {
		characterdata cd = new characterdata();
		Assert.assertEquals(cd.getdraw_player_X(),200);
	}
	
	@Test
	public void getdraw_player_Ytest() {
		characterdata cd = new characterdata();
		Assert.assertEquals(cd.getdraw_player_Y(),100);
	}
	
	@Test
	public void getmovementtest() {
		characterdata cd = new characterdata();
		Assert.assertEquals(cd.getmovement(),-1);
	}
	
	@Test
	public void setlocation_Xtest() {
		characterdata cd = new characterdata();
		cd.setlocation_X(500);
		Assert.assertEquals(cd.getVirtualCharacterXY().x,500);
	}
	
	@Test
	public void setlocation_Ytest() {
		characterdata cd = new characterdata();
		cd.setlocation_Y(500);
		Assert.assertEquals(cd.getVirtualCharacterXY().y,500);
	}
	
	@Test
	public void setdraw_player_Xtest() {
		characterdata cd = new characterdata();
		cd.setdraw_player_X(300);
		Assert.assertEquals(cd.getdraw_player_X(),300);
	}
	
	@Test
	public void setdraw_player_Ytest() {
		characterdata cd = new characterdata();
		cd.setdraw_player_Y(100);
		Assert.assertEquals(cd.getdraw_player_Y(),100);
	}
	
	@Test
	public void setmovementtest() {
		characterdata cd = new characterdata();
		cd.setmovement(0);
		Assert.assertEquals(cd.getmovement(),0);
	}
}
