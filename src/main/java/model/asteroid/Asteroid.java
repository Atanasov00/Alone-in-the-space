package model.asteroid;

import com.almasb.fxgl.core.math.Vec2;

import model.explosion.Explosion;

/**
 * 
 * This interface models an Asteroid.
 *
 */
public interface Asteroid {
	
	/**
	 * 
	 * @return Vec2 that represents Asteroid position.
	 */
	Vec2 getPos();
	
	/**
	 * 
	 * @return String that contains the Image path.
	 */
	String getPathImage();
	
	/**
	 * 
	 * @return Explosion animation of the Asteroid. 
	 */
	Explosion getExplosion();
	
	/**
	 * Set the param alive to false when the asteroid is destroyed.  
	 */
	void destroy();
	
	/**
	 * 
	 * @return boolean that represent the status of the asteroid.
	 */
	boolean isAlive();
	
	/**
	 * 
	 * @return int that represent the damage collision for the ship.
	 */
	int getDamageCollision();
}
