package BesiegeYourFreinds;

import object.Background;
import object.EnglishCatapult;
import object.PlayerSprite;
import util.Matrix3x3f;
import util.SimpleFramework;
import util.Vector2f;
import gfx.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BYFApp extends SimpleFramework {

    public boolean show;
    public Background bg;
    public EnglishCatapult player;
    private Textures tex;
    private BufferedImage spriteSheet;
    private boolean is_shooting = true;
    private int health = 100;
    
    public BYFApp() {
        appTitle = "Besiege Your Friends";
        appWidth = 1280;
        appHeight = 720;
        appMaintainRatio = true;
        appBorderScale = 1.0f;
        appWorldWidth = 16.0f;
        appWorldHeight = 9.0f;
    }

    @Override
    protected void initialize() {
        super.initialize();

        BufferedImageLoader loader = new BufferedImageLoader();
		try{
			spriteSheet = loader.loadImage("/spritesheet.png");
		}catch(IOException e) {
			e.printStackTrace();
		}
		tex = new Textures(this);
		//bg = new Background(tex);
        player = new EnglishCatapult(new Vector2f(-0.375f, 0.375f), new Vector2f(0.375f, -0.375f), tex, this);
    }

    @Override
    protected void processInput(float delta) {

    }

    @Override
    protected void updateObjects(float delta) {
    	player.update(delta, getViewportTransform());

      //  player.applyGravity(delta);

      //  bg.update(delta, getViewportTransform());
      //  player.update(delta, getViewportTransform());
    }
    
    public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
				player.setVelX(5);
		} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			player.setVelX(-5);
		} else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			player.setVelY(-5);
		} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			player.setVelY(5);
		} else if (key == KeyEvent.VK_SPACE && !is_shooting) {
			setIs_shooting(true);
		//	c.addEntity(new Bullet(player.getX(), player.getY(), tex, this));
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			player.setVelX(0);
		} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			player.setVelX(0);
		} else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			player.setVelY(0);
		} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			player.setVelY(0);
		}else if (key == KeyEvent.VK_SPACE) {
			is_shooting = false;
		}
	}
		
    @Override
    protected void render(Graphics g) {

       // bg.render(g);
    	// Graphics2D g2d = (Graphics2D) g;
         //g2d.drawImage(tex.snow, appWorldWidth, appWorldHeight,  null);
         player.render(g);

        super.render(g);
    }
    
    public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
    
    public boolean getIs_shooting() {
		return is_shooting;
	}

	public void setIs_shooting(boolean is_shooting) {
		this.is_shooting = is_shooting;
	}
	
	public int getHealth() {
			return health;
		}

		public void setHealth(int health) {
			this.health = health;
		}

}