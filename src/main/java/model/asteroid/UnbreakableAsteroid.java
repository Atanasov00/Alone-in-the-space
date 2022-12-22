package model.asteroid;

import com.almasb.fxgl.core.math.Vec2;

import model.explosion.Explosion;

public class UnbreakableAsteroid implements Asteroid {

	private Vec2 pos;
	private final String pathImage;
	private final Explosion explosion;
	private boolean alive;
	private final int damageCollision;
	
	public UnbreakableAsteroid(final Vec2 pos, final String pathImage, final Explosion explosion, final int damageCollision) {
		this.pos = pos;
		this.pathImage = pathImage;
		this.explosion = explosion;
		this.alive = true;
		this.damageCollision = damageCollision;
	}
	
	@Override
	public Vec2 getPos() {
		return this.pos;
	}

	@Override
	public void setPos(Vec2 pos) {
		this.pos = pos;
	}

	@Override
	public String getPathImage() {
		return this.pathImage;
	}

	@Override
	public Explosion getExplosion() {
		return this.explosion;
	}

	@Override
	public void destroy() {
		this.alive = false;
	}

	@Override
	public boolean isAlive() {
		return this.alive;
	}

	@Override
	public int getDamageCollision() {
		return this.damageCollision;
	}

}
