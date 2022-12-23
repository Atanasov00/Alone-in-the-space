package model.asteroid;

import com.almasb.fxgl.core.math.Vec2;

import model.explosion.Explosion;

/**
 * 
 * Class that represent a stronger Asteroid.
 *
 */
public class StrongerAsteroid implements Asteroid {

	private Vec2 pos;
	private final String pathImage;
	private final Explosion explosion;
	private boolean alive;
	private final int damageCollision;
	
	/**
	 * Constructor
	 * @param pos
	 * @param pathImage
	 * @param explosion
	 * @param damageCollision
	 */
	public StrongerAsteroid(final Vec2 pos, final String pathImage, final Explosion explosion, final int damageCollision) {
		this.pos = pos;
		this.pathImage = pathImage;
		this.explosion = explosion;
		this.alive = true;
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
	 * {@inheritDoc}
	 */
	public Explosion getExplosion() {
		return this.explosion;
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
	public int getDamageCollision() {
		return this.damageCollision;
	}

}
