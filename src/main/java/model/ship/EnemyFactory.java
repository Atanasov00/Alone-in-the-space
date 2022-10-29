package model.ship;

import com.almasb.fxgl.core.math.Vec2;
import javafx.scene.image.Image;
import model.gun.GunFactory;

public class EnemyFactory {
    public static Ship BasicEnemy(Vec2 newPosition) throws IllegalArgumentException {
	class BasicEnemy extends AbstractShip {

	    public BasicEnemy(int health, float maxSpeed, float acceleration, float rotationSpeed, long attackCD,
		    Vec2 position) {
		super(health, maxSpeed, acceleration, rotationSpeed, attackCD, position);
		// TODO Auto-generated constructor stub
	    }

	}
	;
	Ship en = new BasicEnemy(10, 50, 20f, 180, 1000, newPosition);
	en.setGun(GunFactory.shootgun(en));
	en.setSprite(new Image("images/ship_0.png"));
	return en;

    }

    public static Ship MissileEnemy(Vec2 newPosition) throws IllegalArgumentException {
	class MissileEnemy extends AbstractShip {

	    public MissileEnemy(int health, float maxSpeed, float acceleration, float rotationSpeed, long attackCD,
		    Vec2 position) {
		super(health, maxSpeed, acceleration, rotationSpeed, attackCD, position);
		// TODO Auto-generated constructor stub
	    }

	}
	;
	Ship en = new MissileEnemy(30, 35, 5f, 120, 3000, newPosition);
	en.setGun(GunFactory.missile(en));
	en.setSprite(new Image("images/ship_22.png"));
	return en;

    }

    public static Ship RifleEnemy(Vec2 newPosition) throws IllegalArgumentException {
	class RifleEnemy extends AbstractShip {

	    public RifleEnemy(int health, float maxSpeed, float acceleration, float rotationSpeed, long attackCD,
		    Vec2 position) {
		super(health, maxSpeed, acceleration, rotationSpeed, attackCD, position);
		// TODO Auto-generated constructor stub
	    }

	};
	
	Ship en = new RifleEnemy(2, 80, 10f, 360, 500, newPosition);
	en.setGun(GunFactory.rifle(en));
	en.setSprite(new Image("images/ship_30.png"));
	return en;
    }

}