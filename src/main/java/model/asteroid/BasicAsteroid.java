package model.asteroid;

import com.almasb.fxgl.core.math.Vec2;

import model.explosion.Explosion;

/**
 * 
 * Class that represent a BasicAsteroid
 *
 */
public class BasicAsteroid implements Asteroid {

	private Vec2 pos;
	private int health;
	private final String pathImage;
	private boolean alive;
	private final Explosion explosion;
	private final int damageCollision;
	
	/**
	 * Constructor
	 * @param pos
	 * @param health
	 * @param pathImage
	 * @param explosion
	 * @param damageCollision
	 */
	public BasicAsteroid(final Vec2 pos, final int health, final String pathImage, final Explosion explosion, final int damageCollision) {
		this.pos = pos;
		this.health = health;
		this.pathImage = pathImage;
		this.alive = true;
		this.explosion = explosion;
		this.damageCollision = damageCollision;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Vec2 getPos() {
		return this.pos;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getPathImage() {
		return this.pathImage;
	}
	
	/**
	 * Method that decrement asteroid's health.
	 * @param damage
	 */
	public void strike(final int damage) {
		this.health -= damage;
	}
	
	/**
	 * 
	 * @return boolean that indicate if asteroid was destroyed or not.
	 */
	public boolean checkHealth() {
		return this.health <= 0;
	}
	/**
	 * {@inheritDoc}
	 */
	public void destroy() {
		this.alive = false;
	}
	/**
	 * {@inheritDoc}
	 */
	public boolean isAlive() {
		return this.alive;
	}
	/**
	 * {@inheritDoc}
	 */
	public Explosion getExplosion() {
		return this.explosion;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getDamageCollision() {
		return this.damageCollision;
	}

}
