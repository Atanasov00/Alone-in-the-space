package view;

import java.util.Map;
import java.util.Set;

import controller.gameEngine.GameAnimation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Entity;
import model.asteroid.Asteroid;
import model.asteroid.BasicAsteroid;
import model.bullet.Bullet;
import model.ship.Ship;
import model.status.StatusImpl;
import javafx.scene.image.ImageView;

/**
 * 
 */
public interface GameMap {

    /**
     * @return the panel where the content is drawn.
     */
    AnchorPane getGameContainer();

    /**
     * @return the active entities on the GameMap.
     */
    Set<Entity> getActiveEntities();

    /**
     * @return Width.
     */
    Number getWidth();

    /**
     * @return Height.
     */
    Number getHeight();

    /**
     * @param player add the player Entity to the game.
     */
    void setPlayer(Ship player);

    /**
     * @return the player Entity.
     */
    Ship getPlayer();

    /**
     * @return a set with all the current enemy ships on the map.
     */
    Set<Ship> getActiveEnemyShips();

    /**
     * @return all active bullets shot by the player.
     */
    Set<Bullet> getBulletsShotByPlayer();

    /**
     * Add player bullet.
     *
     * @param bullet bullet entity from player
     */
    void addPlayerBullet(Bullet bullet);

    /**
     * @return all active bullets shot by the enemies.
     */
    Set<Bullet> getBulletsShotByEnemies();

    /**
     * Adds enemy bullets.
     *
     * @param bullet bullet entity from enemy
     */
    void addEnemyBullet(Bullet bullet);

    /**
     * Remove entities from the game.
     *
     * @param entity to be removed.
     */
    void removeEntity(Entity entity);

    /**
     * @return the scene.
     */
    Scene getScene();

    /**
     * Set the scene.
     *
     * @param scene to be set.
     */
    void setScene(Scene scene);

    /**
     * This method help for end game: set a stage reference.
     *
     * @param stage current stage
     */
    void setStageReference(Stage stage);

    /**
     * Set the background image.
     *
     * @param path the path of the file.
     */
    void setBackgroundImage(String path);

    /**
     *
     * @return the background.
     */
    Node getBackground();

    /**
     * @return Stage reference.
     */
    Stage getStage();

    // background image

    /**
     * Add an enemy ship to the game.
     *
     * @param enemy
     */
    void addEnemyShip(Ship enemy);

    /**
     * Set a GameLoop reference
     *
     * @param gameEngine
     */
    void setGameEngine(GameAnimation gameEngine);

    /**
     * @return GameLoop reference.
     */
    GameAnimation getGameEngine();

    /**
     * Set a Status reference.
     * 
     * @param status the status to reference to
     */
    void setStatus(StatusImpl status);

    /**
     * @return Status reference.
     */
    StatusImpl getStatus();

    /**
     * checks all the dead entities and remove them and the nodes
     */
    void removeDeadEntity();
    
    Set<Asteroid> getAsteroids();
    
    Map<Asteroid, ImageView> getMapAsteroids();
    
    void generateAsteroids();
    
    void startExplosion(BasicAsteroid asteroid);
}
