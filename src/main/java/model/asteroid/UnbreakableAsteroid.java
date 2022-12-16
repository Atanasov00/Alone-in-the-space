package model.asteroid;

import com.almasb.fxgl.core.math.Vec2;

public class UnbreakableAsteroid implements Asteroid {

	private Vec2 pos;
	private final String pathImage;
	
	public UnbreakableAsteroid(final Vec2 pos, final String pathImage) {
		this.pos = pos;
		this.pathImage = pathImage;
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

}
