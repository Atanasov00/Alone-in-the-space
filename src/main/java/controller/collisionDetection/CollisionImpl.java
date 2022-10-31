package controller.collisionDetection;

import java.util.Collection;

import com.almasb.fxgl.core.math.Vec2;

import model.bullet.Bullet;
import model.ship.Ship;
import utilities.EnumInt;
import view.GameMap;
import view.hud.HUDImpl;

/**
 * 
 * Class that implements Collision interface.
 *
 */
public class CollisionImpl implements Collision {

	private final GameMap gameMap;
	private final HUDImpl hudImpl;

	/**
	 * Constructor.
	 * 
	 * @param gameMap
	 * @param hudImpl
	 */
	public CollisionImpl(final GameMap gameMap, final HUDImpl hudImpl) {
		this.gameMap = gameMap;
		this.hudImpl = hudImpl;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean checkEnemyCollision(final Ship ship, final Ship enemy) {
		return ship.getNode().getBoundsInParent().intersects(enemy.getNode().getBoundsInParent());
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean checkBulletCollision(final Ship ship, final Bullet bullet) {
		return ship.getNode().getBoundsInParent().intersects(bullet.getNode().getBoundsInParent());
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkBorderCollision(final Ship ship) {
		if (ship.getPosition().y >= EnumInt.HEIGHT.getValue() + EnumInt.SLACK.getValue()) {
			ship.setPosition(new Vec2(ship.getPosition().x, 0));
		} else if (ship.getPosition().y <= -EnumInt.SLACK.getValue()) {
			ship.setPosition(new Vec2(ship.getPosition().x, EnumInt.HEIGHT.getValue()));
		} else if (ship.getPosition().x >= EnumInt.WIDTH.getValue() + EnumInt.SLACK.getValue()) {
			ship.setPosition(new Vec2(0, ship.getPosition().y));
		} else if (ship.getPosition().x <= -EnumInt.SLACK.getValue()) {
			ship.setPosition(new Vec2(EnumInt.WIDTH.getValue(), ship.getPosition().y));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkAllCollision(final Ship player, final Collection<Ship> enemies,
			final Collection<Bullet> playerBullets, final Collection<Bullet> enemiesBullets) {

		checkBorderCollision(player);
		checkBulletsBorderCollision(playerBullets, enemiesBullets);

		enemies.forEach((Ship enemy) -> {
			if (enemy.isAlive() && checkEnemyCollision(player, enemy)) {
				player.strike(EnumInt.DAMAGE_COLLISION.getValue());
				this.gameMap.getStatus()
						.setLifePoints(this.gameMap.getStatus().getLifePoints() - EnumInt.ONE.getValue());
			}
		});

		playerBullets.forEach((Bullet bullet) -> enemies.forEach((Ship enemy) -> {
			if (bullet.isAlive() && checkBulletCollision(enemy, bullet)) {
				enemy.strike(bullet.getDamage());
				bullet.destroy();
				if (!enemy.isAlive()) {
					this.gameMap.getStatus().addPoints(EnumInt.ONE.getValue());
					this.hudImpl.getPointsImpl().setPoints(this.gameMap.getStatus().getPoints());
				}
			}
		}));

		enemiesBullets.forEach((Bullet bullet) -> {
			if (bullet.isAlive() && checkBulletCollision(player, bullet)) {
				player.strike(bullet.getDamage());
				bullet.destroy();
				this.gameMap.getStatus().setLifePoints(this.gameMap.getStatus().getLifePoints() - bullet.getDamage());
			}
		});

	}

	/**
	 * {@inheritDoc}
	 */
	public void checkBulletsBorderCollision(final Collection<Bullet> playerBullets,
			final Collection<Bullet> enemiesBullets) {
		playerBullets.forEach((Bullet bullet) -> {
			if (bullet.getPosition().x <= 0 || bullet.getPosition().x >= EnumInt.WIDTH.getValue()
					|| bullet.getPosition().y <= 0 || bullet.getPosition().y >= EnumInt.HEIGHT.getValue()) {
				bullet.destroy();
			}
		});

		enemiesBullets.forEach((Bullet bullet) -> {
			if (bullet.getPosition().x <= 0 || bullet.getPosition().x >= EnumInt.WIDTH.getValue()
					|| bullet.getPosition().y <= 0 || bullet.getPosition().y >= EnumInt.HEIGHT.getValue()) {
				bullet.destroy();
			}
		});
	}

}
