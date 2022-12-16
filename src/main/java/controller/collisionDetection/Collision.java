package controller.collisionDetection;

import model.asteroid.Asteroid;
import model.bullet.Bullet;
import model.ship.Ship;

import java.util.Collection;
import java.util.Map;

import javafx.scene.image.ImageView;

/**
 * 
 * This interface detects collision between ships and bullets. 
 *
 */
public interface Collision {

    /**
     * Check if there is a collision with enemies.
     * @param playerShip the player ship
     * @param enemy the enemy ship
     * @return True if a collision occurred
     */
    boolean checkEnemyCollision(Ship playerShip, Ship enemy);

    /**
     * Check if there is a collision with bullets.
     * @param playerShip the player ship
     * @param bullet the active bullet
     * @return True if a collision occurred
     */
    boolean checkBulletCollision(Ship playerShip, Bullet bullet);

    /**
     * Check collision with enemies and bullets.
     * @param playerShip the player
     * @param enemies list of enemies to check
     * @param playerBullets all the bullets shot by the player
     * @param enemiesBullets all the bullets shot by each enemy
     */
    void checkAllCollision(Ship playerShip, Collection<Ship> enemies, Collection<Bullet> playerBullets, Collection<Bullet> enemiesBullets, Map<Asteroid, ImageView> asteroids);

    /**
     * Check if there is a collision with borders then send the player on the other side
     * @param playerShip the player ship
     */
    void checkBorderCollision(Ship playerShip);
    /**
     * Check if any bullet collides with borders.
     * @param playerBullets collection of bullets shot by the player
     * @param enemiesBullets collection of all bullets shot by the enemies
     */
    void checkBulletsBorderCollision(Collection<Bullet> playerBullets, Collection<Bullet> enemiesBullets);

    void checkBulletsAsteroidsCollision(Collection<Bullet> playerBullets, Collection<Bullet> enemiesBullets, 
				Map<Asteroid, ImageView> asteroids);
}
