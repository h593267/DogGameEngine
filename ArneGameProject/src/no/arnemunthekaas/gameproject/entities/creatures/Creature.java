package no.arnemunthekaas.gameproject.entities.creatures;

import no.arnemunthekaas.gameproject.Game;
import no.arnemunthekaas.gameproject.entities.Entity;
import no.arnemunthekaas.gameproject.levels.Level;
import no.arnemunthekaas.gameproject.tiles.Tile;

public abstract class Creature extends Entity {
	
	//Set default size for creatures
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64;
	public static final int DEFAULT_CREATURE_HEIGHT = 64;

	protected float speed;
	protected float xMove, yMove;

	public Creature(float x, float y, int width, int height) {
		super(x, y, width, height);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move() {
		if(!checkEntityCollisions(xMove, 0f) && (x < Tile.TILEWIDTH * Level.instance.width) && (x > 0)) ;
			moveX();
		if(!checkEntityCollisions(0f, yMove)  && (y < Tile.TILEHEIGHT * Level.instance.height) && (y >= 0)) 
			moveY();	
		
		if(x > Tile.TILEWIDTH * Level.instance.width - Level.instance.width)
			x = Tile.TILEWIDTH * Level.instance.width - Level.instance.width;
		
		if(y > Tile.TILEHEIGHT * Level.instance.height - Level.instance.width)
			y = Tile.TILEHEIGHT * Level.instance.height - Level.instance.height;
		
		if(x < 0)
			x = 0;
		
		if(y < 0)
			y = 0;
	}
	
	public void moveX() {
		if (xMove > 0) { //Moving right
			
			int tx = (int) ((x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH);
			
			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
			}
			
		} else if (xMove < 0) { //Moving left
			int tx = (int) ((x + xMove + bounds.x) / Tile.TILEWIDTH);
			
			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {	
				x += xMove;
			} else {
				 x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}
		}
	}
	
	public void moveY() { //Moving up
		if (yMove < 0) {
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
			
			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && 
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;	
			} else {
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			}
			
		} else if (yMove > 0) { //Moving down
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			
			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && 
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;	
			} else {
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
			}
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return Game.instance.level.getTile(x, y).isSolid();
	}
	
	//GETTERS SETTERS
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	
	

}
