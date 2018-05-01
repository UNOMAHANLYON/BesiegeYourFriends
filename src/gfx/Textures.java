package gfx;

import java.awt.image.BufferedImage;
import BesiegeYourFreinds.BYFApp;

public class Textures {
	
	public BufferedImage[] ballistaCatapultFire;
	public BufferedImage[] ballistaCatapultAim;
	public BufferedImage[] ballistaCatapultDestroyed;
	public BufferedImage[] vikingCatapultFire;
	public BufferedImage[] vikingCatapultDestroyed;
	public BufferedImage[] dragonCatapultFire;
	public BufferedImage[] englishCatapultFire;
	public BufferedImage[] rock;
	public BufferedImage[] arrow;
	public BufferedImage snow;
	
	private SpriteSheet ss;
	private int width = 64;
	private int height = 64;
	
	public Textures(BYFApp game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	
	private void getTextures() {
		
		ballistaCatapultFire = new BufferedImage[9];
		ballistaCatapultAim = new BufferedImage[10];
		ballistaCatapultDestroyed = new BufferedImage[9];
		vikingCatapultFire = new BufferedImage[13];
		vikingCatapultDestroyed = new BufferedImage[9];
		dragonCatapultFire = new BufferedImage[15];
		englishCatapultFire = new BufferedImage[10];
		
		ballistaCatapultFire[0] = ss.grabImage(1, 1, width, height);
		ballistaCatapultFire[1] = ss.grabImage(2, 1, width, height);
		ballistaCatapultFire[2] = ss.grabImage(3, 1, width, height);
		ballistaCatapultFire[3] = ss.grabImage(4, 1, width, height);
		ballistaCatapultFire[4] = ss.grabImage(5, 1, width, height);
		ballistaCatapultFire[5] = ss.grabImage(6, 1, width, height);
		ballistaCatapultFire[6] = ss.grabImage(7, 1, width, height);
		ballistaCatapultFire[7] = ss.grabImage(8, 1, width, height);
		ballistaCatapultFire[8] = ss.grabImage(9, 1, width, height);
		
		ballistaCatapultAim[0] = ss.grabImage(1, 2, width, height);
		ballistaCatapultAim[1] = ss.grabImage(2, 2, width, height);
		ballistaCatapultAim[2] = ss.grabImage(3, 2, width, height);
		ballistaCatapultAim[3] = ss.grabImage(4, 2, width, height);
		ballistaCatapultAim[4] = ss.grabImage(5, 2, width, height);
		ballistaCatapultAim[5] = ss.grabImage(6, 2, width, height);
		ballistaCatapultAim[6] = ss.grabImage(7, 2, width, height);
		ballistaCatapultAim[7] = ss.grabImage(8, 2, width, height);
		ballistaCatapultAim[8] = ss.grabImage(9, 2, width, height);
		ballistaCatapultAim[9] = ss.grabImage(10, 2, width, height);
		
		ballistaCatapultDestroyed[0] = ss.grabImage(1, 3, width, height);
		ballistaCatapultDestroyed[1] = ss.grabImage(2, 3, width, height);
		ballistaCatapultDestroyed[2] = ss.grabImage(3, 3, width, height);
		ballistaCatapultDestroyed[3] = ss.grabImage(4, 3, width, height);
		ballistaCatapultDestroyed[4] = ss.grabImage(5, 3, width, height);
		ballistaCatapultDestroyed[5] = ss.grabImage(6, 3, width, height);
		ballistaCatapultDestroyed[6] = ss.grabImage(7, 3, width, height);
		ballistaCatapultDestroyed[7] = ss.grabImage(8, 3, width, height);
		ballistaCatapultDestroyed[8] = ss.grabImage(9, 3, width, height);
		
		vikingCatapultFire[0] = ss.grabImage(1, 4, width, height);
		vikingCatapultFire[1] = ss.grabImage(2, 4, width, height);
		vikingCatapultFire[2] = ss.grabImage(3, 4, width, height);
		vikingCatapultFire[3] = ss.grabImage(4, 4, width, height);
		vikingCatapultFire[4] = ss.grabImage(5, 4, width, height);
		vikingCatapultFire[5] = ss.grabImage(6, 4, width, height);
		vikingCatapultFire[6] = ss.grabImage(7, 4, width, height);
		vikingCatapultFire[7] = ss.grabImage(8, 4, width, height);
		vikingCatapultFire[8] = ss.grabImage(9, 4, width, height);
		vikingCatapultFire[9] = ss.grabImage(10, 4, width, height);
		vikingCatapultFire[10] = ss.grabImage(11, 4, width, height);
		vikingCatapultFire[11] = ss.grabImage(12, 4, width, height);
		vikingCatapultFire[12] = ss.grabImage(13, 4, width, height);
		
		vikingCatapultDestroyed[0] = ss.grabImage(1, 5, width, height);
		vikingCatapultDestroyed[1] = ss.grabImage(2, 5, width, height);
		vikingCatapultDestroyed[2] = ss.grabImage(3, 5, width, height);
		vikingCatapultDestroyed[3] = ss.grabImage(4, 5, width, height);
		vikingCatapultDestroyed[4] = ss.grabImage(5, 5, width, height);
		vikingCatapultDestroyed[5] = ss.grabImage(6, 5, width, height);
		vikingCatapultDestroyed[6] = ss.grabImage(7, 5, width, height);
		vikingCatapultDestroyed[7] = ss.grabImage(8, 5, width, height);
		vikingCatapultDestroyed[8] = ss.grabImage(9, 5, width, height);
		
		dragonCatapultFire[0] = ss.grabImage(1, 6, width, height);
		dragonCatapultFire[1] = ss.grabImage(2, 6, width, height);
		dragonCatapultFire[2] = ss.grabImage(3, 6, width, height);
		dragonCatapultFire[3] = ss.grabImage(4, 6, width, height);
		dragonCatapultFire[4] = ss.grabImage(5, 6, width, height);
		dragonCatapultFire[5] = ss.grabImage(6, 6, width, height);
		dragonCatapultFire[6] = ss.grabImage(7, 6, width, height);
		dragonCatapultFire[7] = ss.grabImage(8, 6, width, height);
		dragonCatapultFire[8] = ss.grabImage(9, 6, width, height);
		dragonCatapultFire[9] = ss.grabImage(10, 6, width, height);
		dragonCatapultFire[10] = ss.grabImage(11, 6, width, height);
		dragonCatapultFire[11] = ss.grabImage(12, 6, width, height);
		dragonCatapultFire[12] = ss.grabImage(13, 6, width, height);
		dragonCatapultFire[13] = ss.grabImage(14, 6, width, height);
		dragonCatapultFire[14] = ss.grabImage(15, 6, width, height);
						
		englishCatapultFire[0] = ss.grabImage(1, 7, width, height);
		englishCatapultFire[1] = ss.grabImage(2, 7, width, height);
		englishCatapultFire[2] = ss.grabImage(3, 7, width, height);
		englishCatapultFire[3] = ss.grabImage(4, 7, width, height);
		englishCatapultFire[4] = ss.grabImage(5, 7, width, height);
		englishCatapultFire[5] = ss.grabImage(6, 7, width, height);
		englishCatapultFire[6] = ss.grabImage(7, 7, width, height);
		englishCatapultFire[7] = ss.grabImage(8, 7, width, height);
		englishCatapultFire[8] = ss.grabImage(9, 7, width, height);
		englishCatapultFire[9] = ss.grabImage(10, 7, width, height);
		
		rock = new BufferedImage[8];
		arrow = new BufferedImage[8];
		
		rock[0] = ss.grabImage(1, 8, width, height);
		rock[1] = ss.grabImage(2, 8, width, height);
		rock[2] = ss.grabImage(3, 8, width, height);
		rock[3] = ss.grabImage(4, 8, width, height);
		rock[4] = ss.grabImage(5, 8, width, height);
		rock[5] = ss.grabImage(6, 8, width, height);
		rock[6] = ss.grabImage(7, 8, width, height);
		rock[7] = ss.grabImage(8, 8, width, height);
		
		arrow[0] = ss.grabImage(1, 9, width, height);
		arrow[1] = ss.grabImage(2, 9, width, height);
		arrow[2] = ss.grabImage(3, 9, width, height);
		arrow[3] = ss.grabImage(4, 9, width, height);
		arrow[4] = ss.grabImage(5, 9, width, height);
		arrow[5] = ss.grabImage(6, 9, width, height);
		arrow[6] = ss.grabImage(7, 9, width, height);
		arrow[7] = ss.grabImage(8, 9, width, height);
		
		snow = ss.grabImage(1, 10, 320, 192);
		
	}
}
