package model.asteroid;

import com.almasb.fxgl.core.math.Vec2;

import model.explosion.Explosion;

public class BasicAsteroid implements Asteroid {

	private Vec2 pos;
	private int health;
	private final String pathImage;
	private boolean alive;
	private final Explosion explosion;
	private final int damageCollision;
	
	public BasicAsteroid(final Vec2 pos, final int health, final String pathImage, final Explosion explosion, final int damageCollision) {
		this.pos = pos;
		this.health = health;
		this.pathImage = pathImage;
		this.alive = true;
		this.explosion = explosion;
		this.damageCollision = damageCollision;
	}
	
	@Override
	public Vec2 getPos() {
		return this.pos;
	}

	@Override
	public void setPos(final Vec2 pos) {
		this.pos = pos;
	}

	@Override
	public String getPathImage() {
		return this.pathImage;
	}
	
	public void strike(final int damage) {
		this.health -= damage;
	}
	
	public boolean checkHealth() {
		return this.health <= 0;
	}
	
	public void destroy() {
		this.alive = false;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public Explosion getExplosion() {
		return this.explosion;
	}

	@Override
	public int getDamageCollision() {
		return this.damageCollision;
	}

}
