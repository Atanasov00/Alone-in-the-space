package model.asteroid;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.image.ImageView;

public class UnbreakableAsteroid implements Asteroid {

	private Vec2 pos;
	private final ImageView image;
	
	public UnbreakableAsteroid(final Vec2 pos, final ImageView image) {
		this.pos = pos;
		this.image = image;
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
	public ImageView getImage() {
		return this.image;
	}

}
