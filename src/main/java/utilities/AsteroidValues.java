package utilities;

import model.explosion.AsteroidExplosion;
import model.explosion.Explosion;

public enum AsteroidValues {

	BASIC_ASTEROID("images/asteroid_01.png", 10, 25, "images/explosion_01.png"),
	
	UNBREAKABLE_ASTEROID("images/asteroid_02.png", 40, 50, "images/explosion_02.png");
	
	private final String imagePath;
	private final int damageCollision;
	private final int health;
	private final Explosion explosion;
	
	AsteroidValues(final String imagePath, final int damageCollision, final int health, final String expPath){
		this.imagePath = imagePath;
		this.damageCollision = damageCollision;
		this.health = health;
		this.explosion = new AsteroidExplosion(expPath);
	}
	
	public String getImagePath() {
		return this.imagePath;
	}
	
	public int getDamageCollison() {
		return this.damageCollision;
	}
	
	public int getInitialHealth() {
		return this.health;
	}
	
	public Explosion getExplosion() {
		return this.explosion;
	}
	
}
