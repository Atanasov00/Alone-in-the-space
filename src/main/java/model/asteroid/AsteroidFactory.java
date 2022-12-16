package model.asteroid;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.image.ImageView;
import utilities.EnumInt;

public class AsteroidFactory {
	
	public static BasicAsteroid basicAsteroid(final Vec2 pos, final int health, final String pathImage) {
		return new BasicAsteroid(pos, health, pathImage);
	}
	
	public static UnbreakableAsteroid unbreakableAsteroid(final Vec2 pos, final String pathImage) {
		return new UnbreakableAsteroid(pos, pathImage);
	}
	
	public static Set<Asteroid> spawnAsteroids(){
		Set<BasicAsteroid> basic = new HashSet<>();
		Set<UnbreakableAsteroid> unbreakable = new HashSet<>();
		Random rnd = new Random();
		
		while(basic.size() < 10) {
			basic.add(basicAsteroid(new Vec2(rnd.nextInt(EnumInt.WIDTH.getValue() - 44), rnd.nextInt(EnumInt.HEIGHT.getValue() - 39)), 
					100, "images/asteroid_01.png"));
		}
		
		while(unbreakable.size() < 3) {
			unbreakable.add(unbreakableAsteroid(new Vec2(rnd.nextInt(EnumInt.WIDTH.getValue() - 101), 
					rnd.nextInt(EnumInt.HEIGHT.getValue() - 101)), "images/asteroid_02.png"));
		}
		
		Set<Asteroid> asteroid = new HashSet<>();
		asteroid.addAll(basic);
		asteroid.addAll(unbreakable);
		return asteroid;
	}
}
