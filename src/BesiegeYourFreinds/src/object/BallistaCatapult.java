package object;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import BesiegeYourFreinds.BYFApp;
import bounding.BoundingShapes;
import gfx.Animation;
import gfx.Textures;
import util.Matrix3x3f;
import util.Utility;
import util.Vector2f;

public class BallistaCatapult {

	private float velX = 0f;
	private float velY = 0f;
	float x;
	float y;
	private Textures tex;
	private BYFApp game;
	private Animation animFire, animDestroyed,animAim;
	private float gravity = 0f;// = 4.0f;
	
	private Matrix3x3f world;
    private float rotateRadian;
    private Vector2f translate;
    private Vector2f scale;
    private boolean showBounds;
    private BufferedImage scaledImage;
    private Vector2f topLeft;
    private Vector2f bottomRight;
    private BoundingShapes outterBounds;
    protected ArrayList<BoundingShapes> innerBounds;
    	
	public BallistaCatapult(Vector2f topLeft, Vector2f bottomRight, Textures tex, BYFApp game) {
		
		this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.innerBounds = new ArrayList<BoundingShapes>();
		this.game = game;
		this.tex = tex;
		animFire = new Animation(200, tex.ballistaCatapultFire);
		animDestroyed = new Animation(200, tex.ballistaCatapultDestroyed);
		animAim = new Animation(200, tex.ballistaCatapultAim);
		translate = new Vector2f(0, 0);
        scale = new Vector2f(1, 1);
        rotateRadian = 0;
	}
	
	public void update( float deltaTime, Matrix3x3f viewport){

    	animFire.update();
    	animDestroyed.update();
    	animAim.update();
    	world =  viewport;
//        this.scale = scale;
//        translate.translate( tx, ty );
//        this.rotateRadian += rotate;

    }
	
	public void render(Graphics g) {
		
		 scaleImage(world);
         Graphics2D g2d = (Graphics2D) g;
         g2d.drawImage(scaledImage, createTransform(),  null);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(game.getIs_shooting() == true) {
			return animFire.getCurrentFrame();
		}
		else if(game.getHealth() <= 0)
		{
			return animDestroyed.getCurrentFrame();
		}
		else if(false)
		{
			return animAim.getCurrentFrame();
		}
		
		else
			return tex.ballistaCatapultFire[0];
	}
	

	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public void showBounds(Graphics G) {

        for (int i=0; i<innerBounds.size(); i++) {
            innerBounds.get(i).render(G);
        }

    }

    private AffineTransform createTransform() {
        Vector2f screen = world.mul(translate);
        AffineTransform transform = AffineTransform.getTranslateInstance(screen.x, screen.y);
        //transform.scale(scale.x, scale.y);
        transform.rotate(rotateRadian);
        transform.translate(-scaledImage.getWidth() / 2, -scaledImage.getHeight() / 2);
        return transform;
    }

    public void scaleImage(Matrix3x3f view) {
        Vector2f screenTopLeft = view.mul(topLeft);
        Vector2f screenBottomRight = view.mul(bottomRight);
        int scaledWidth = (int) Math.abs(screenBottomRight.x - screenTopLeft.x);
        int scaledHeight = (int) Math.abs(screenBottomRight.y - screenTopLeft.y);
     //   if (scaledImage == null || scaledWidth != scaledImage.getWidth() || scaledHeight != scaledImage.getHeight()) {
            scaledImage = Utility.scaleImage(getCurrentAnimationFrame(), scaledWidth, scaledHeight);
       // }
    }

   public void applyGravity(float delta) {
        translate.y -= gravity * delta;
    }

}
