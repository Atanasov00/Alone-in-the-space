package model.asteroid;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.image.ImageView;
import model.explosion.AsteroidExplosion;
import model.explosion.Explosion;
import utilities.EnumInt;

public class AsteroidFactory {
	
	public static BasicAsteroid basicAsteroid(final Vec2 pos, final int health, final String pathImage, final Explosion exp) {
		return new BasicAsteroid(pos, health, pathImage, exp);
	}
	
	public static UnbreakableAsteroid unbreakableAsteroid(final Vec2 pos, final String pathImage) {
		return new UnbreakableAsteroid(pos, pathImage);
	}
	
	public static Set<Asteroid> spawnAsteroids(){
		Set<BasicAsteroid> basic = new HashSet<>();
		Set<UnbreakableAsteroid> unbreakable = new HashSet<>();
		Random rnd = new Random();
		
		int factorX = EnumInt.WIDTH.getValue() / 13;
		int minX = 0;
		int maxX = factorX;
	
		int randAsteroid;
		
		while(basic.size() + unbreakable.size() < 13) {
			if(unbreakable.size() < 3) {
				randAsteroid = rnd.nextInt(2);
				if(randAsteroid == 0) {
					Explosion exp = new AsteroidExplosion("images/explosion_01.png");
					basic.add(basicAsteroid(new Vec2(rnd.nextInt(maxX - minX) + minX, rnd.nextInt(EnumInt.HEIGHT.getValue()) - 50), 
					100, "images/asteroid_01.png", exp));
				} else if(randAsteroid == 1) {
					unbreakable.add(unbreakableAsteroid(new Vec2(rnd.nextInt(maxX - minX) + minX, 
							rnd.nextInt(EnumInt.HEIGHT.getValue()) - 112), "images/asteroid_02.png"));
				}
			} else {
				Explosion exp = new AsteroidExplosion("images/explosion_01.png");
				basic.add(basicAsteroid(new Vec2(rnd.nextInt(maxX - minX)+ minX, rnd.nextInt(EnumInt.HEIGHT.getValue()) - 50), 
						100, "images/asteroid_01.png", exp));
			}
			minX += factorX;
			maxX += factorX;
		}
		
		Set<Asteroid> asteroid = new HashSet<>();
		asteroid.addAll(basic);
		asteroid.addAll(unbreakable);
		return asteroid;
	}
}
