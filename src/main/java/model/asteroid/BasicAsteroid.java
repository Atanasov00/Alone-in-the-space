package model.asteroid;

import com.almasb.fxgl.core.math.Vec2;

public class BasicAsteroid implements Asteroid {

	private Vec2 pos;
	private int health;
	private final String pathImage;
	private boolean alive;
	
	public BasicAsteroid(final Vec2 pos, final int health, final String pathImage) {
		this.pos = pos;
		this.health = health;
		this.pathImage = pathImage;
		this.alive = true;
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

}
