package model.asteroid;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.image.ImageView;

public class BasicAsteroid implements Asteroid {

	private Vec2 pos;
	private int health;
	private final ImageView image;
	
	public BasicAsteroid(final Vec2 pos, final int health, final ImageView image) {
		this.pos = pos;
		this.health = health;
		this.image = image;
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
	public ImageView getImage() {
		return this.image;
	}
	
	public void strike(final int damage) {
		this.health -= damage;
	}
	
	boolean checkHealth() {
		return this.health <= 0;
	}

}
