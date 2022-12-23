package model.asteroid;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.almasb.fxgl.core.math.Vec2;

import model.explosion.Explosion;
import utilities.AsteroidValues;

public class AsteroidFactory {
	
	public static BasicAsteroid basicAsteroid(final Vec2 pos, final int health, final String pathImage, final Explosion exp, final int damage) {
		return new BasicAsteroid(pos, health, pathImage, exp, damage);
	}
	
	public static StrongerAsteroid unbreakableAsteroid(final Vec2 pos, final String pathImage, final Explosion exp, final int damage) {
		return new StrongerAsteroid(pos, pathImage, exp, damage);
	}
	
	public static Set<Asteroid> spawnAsteroids(){
		Set<BasicAsteroid> basic = new HashSet<>();
		Set<StrongerAsteroid> unbreakable = new HashSet<>();
		Random rnd = new Random();
		
		Map<Integer, List<Vec2>> pseudoRandom = new HashMap<>();
		pseudoRandom.put(0, List.of(new Vec2(170,20), new Vec2(210,70), new Vec2(190,390), new Vec2(490,490), new Vec2(670,600), new Vec2(900,360), 
				new Vec2(440,220), new Vec2(850,630), new Vec2(735,66), new Vec2(339, 410), new Vec2(50,20), new Vec2(50,580), new Vec2(1000, 20)));
		pseudoRandom.put(1, List.of(new Vec2(70,20), new Vec2(210,70), new Vec2(190,590), new Vec2(490,490), new Vec2(670,600), new Vec2(900,360), 
				new Vec2(49,525), new Vec2(1110,630), new Vec2(956,66), new Vec2(339, 410), new Vec2(620,20), new Vec2(40,310), new Vec2(900, 490)));
		pseudoRandom.put(2, List.of(new Vec2(340,17), new Vec2(133,56), new Vec2(190,390), new Vec2(563,15), new Vec2(670,600), new Vec2(777,210), 
				new Vec2(440,220), new Vec2(950,480), new Vec2(1135,217), new Vec2(339, 410), new Vec2(1100,43), new Vec2(505,313), new Vec2(109, 574)));
		
		int spawnList = rnd.nextInt(3);
		
		for(int i = 0; basic.size() + unbreakable.size() < 13; i++) {
			if(i < 10) {
				basic.add(basicAsteroid(pseudoRandom.get(spawnList).get(i), AsteroidValues.BASIC_ASTEROID.getInitialHealth(), 
						AsteroidValues.BASIC_ASTEROID.getImagePath(), AsteroidValues.BASIC_ASTEROID.getExplosion(), 
						AsteroidValues.BASIC_ASTEROID.getDamageCollison()));
			} else {
				unbreakable.add(unbreakableAsteroid(pseudoRandom.get(spawnList).get(i), AsteroidValues.UNBREAKABLE_ASTEROID.getImagePath(), 
							AsteroidValues.UNBREAKABLE_ASTEROID.getExplosion(), AsteroidValues.UNBREAKABLE_ASTEROID.getDamageCollison()));
			}
		}
		
		Set<Asteroid> asteroid = new HashSet<>();
		asteroid.addAll(basic);
		asteroid.addAll(unbreakable);
		return asteroid;
	}
}
