package model.asteroid;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.image.ImageView;

public class AsteroidFactory {
	
	public static BasicAsteroid basicAsteroid(final Vec2 pos, final int health, final ImageView image) {
		return new BasicAsteroid(pos, health, image);
	}
	
	public static UnbreakableAsteroid unbreakableAsteroid(final Vec2 pos, final ImageView image) {
		return new UnbreakableAsteroid(pos, image);
	}
}
