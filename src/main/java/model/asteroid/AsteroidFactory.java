package model.asteroid;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.image.ImageView;
import utilities.EnumInt;

public class AsteroidFactory {
	
	public static BasicAsteroid basicAsteroid(final Vec2 pos, final int health, final ImageView image) {
		return new BasicAsteroid(pos, health, image);
	}
	
	public static UnbreakableAsteroid unbreakableAsteroid(final Vec2 pos, final ImageView image) {
		return new UnbreakableAsteroid(pos, image);
	}
	
	public static Set<Asteroid> spawnAsteroids(){
		Set<BasicAsteroid> basic = new HashSet<>();
		Set<UnbreakableAsteroid> unbreakable = new HashSet<>();
		Random rnd = new Random();
		
		while(basic.size() == 0) {
			if(basic.size() == 0) {
				basic.add(basicAsteroid(new Vec2(rnd.nextInt(EnumInt.WIDTH.getValue()), rnd.nextInt(EnumInt.HEIGHT.getValue())), 
						100, new ImageView("images/asteroid_02.png")));
			}
		}
		
		Set<Asteroid> asteroid = new HashSet<>();
		asteroid.addAll(basic);
		
		return asteroid;
	}
}
