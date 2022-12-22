package model.asteroid;

import com.almasb.fxgl.core.math.Vec2;

import model.explosion.Explosion;

public interface Asteroid {
	
	Vec2 getPos();
	
	void setPos(Vec2 pos);
	
	String getPathImage();
	
	Explosion getExplosion();
	
	void destroy();
	
	boolean isAlive();
	
	int getDamageCollision();
}
