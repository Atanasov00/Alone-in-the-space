package controller.gameController;

import com.almasb.fxgl.core.math.Vec2;
import controller.eventController.EventController;
import controller.eventController.EventControllerImpl;
import controller.inputController.InputController;
import controller.playerController.PlayerShipController;
import controller.playerController.PlayerShipControllerImpl;
import controller.sceneManager.SceneManager;
import javafx.scene.image.Image;
import model.bullet.Bullet;
import model.ship.Ship;
import model.status.StatusImpl;
import utilities.EnumString;
import utilities.InputCommands;
import utilities.PlayerValues;
import utilities.PowerUpEnum;
import view.GameMap;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

/**
 * 
 */
public class GameControllerImpl implements GameController {

    private GameMap gameMap;
    private SceneManager sceneManager;
    private EventController eventController;
    private PlayerShipController playerShipController;
    private InputController inputController;
    private Collection<Ship> enemies;

    /**
     * Constructor.
     * 
     * @param gameMap
     */
    public GameControllerImpl(final GameMap gameMap) {
        this.gameMap = gameMap;
        this.gameMap.setBackgroundImage(EnumString.IMAGE_FOLDER.getValue() + "skybox13.jpg");
        this.playerShipController = new PlayerShipControllerImpl(
                new Vec2(this.gameMap.getWidth().doubleValue() / 2, this.getGameMap().getHeight().doubleValue() / 2),
                new Image("images/shipPlayer.png"));
        this.gameMap.setPlayer(this.playerShipController.getPlayerShip());
        this.gameMap.generateAsteroids();
        this.gameMap.setStatus(new StatusImpl(0, PlayerValues.MAIN_SHIP.getValueFromKey("MAXHEALTH")));
        this.playerShipController.setStatus(this.gameMap.getStatus());
        this.gameMap.getStatus().setPlayerController(this.playerShipController);
        this.sceneManager = new SceneManager(this.gameMap);
        this.eventController = new EventControllerImpl(this.gameMap);
        this.eventController.getHudBuilder().setStatus(this.gameMap.getStatus());
        this.enemies = this.gameMap.getActiveEnemyShips();
    }

    @Override
    public void update(final long deltaTime) {
        this.inputController.updatePlayerTasks();
        // Various player movement checks
        if (this.inputController.isTaskActive(InputCommands.UP)) {
            this.playerShipController.thrust(InputCommands.UP);
        }
        if (this.inputController.isTaskActive(InputCommands.DOWN)) {
            this.playerShipController.thrust(InputCommands.DOWN);
        }
        if (this.inputController.isTaskActive(InputCommands.LEFT)) {
            this.playerShipController.rotate(InputCommands.LEFT);
        }
        if (this.inputController.isTaskActive(InputCommands.RIGHT)) {
            this.playerShipController.rotate(InputCommands.RIGHT);
        }
        // Fire Rate check in player controller
        if (this.inputController.isTaskActive(InputCommands.ATTACK)) {
            final Optional<Bullet> playerBulletShot = Optional.ofNullable(this.playerShipController.shot());
            playerBulletShot.ifPresent(bullet -> this.gameMap.addPlayerBullet(bullet));

        }
        // PowerUp check in playercontroller
        if (this.inputController.isTaskActive(InputCommands.POWER_UP)) {
            this.playerShipController.activatePowerUp();
        }
        // For decaying speed
        if (!this.inputController.isTaskActive(InputCommands.UP)
                && !this.inputController.isTaskActive(InputCommands.DOWN)) {
            this.playerShipController.thrustReleased();
        }

        // Collision check
        this.eventController.getCollision().checkAllCollision(this.playerShipController.getPlayerShip(), this.enemies,
                this.gameMap.getBulletsShotByPlayer(), this.gameMap.getBulletsShotByEnemies());
        // Updates the player
        this.playerShipController.update(deltaTime);

        this.gameMap.removeDeadEntity();

        this.enemies.forEach((Ship enemy) -> {
            if (enemy.isInRangeOfAttack(deltaTime)) {
                this.gameMap.addEnemyBullet(enemy.shot());
            }
        });

        this.sceneManager.update(deltaTime);
        // Update status stats (life points, player score)
        this.gameMap.getStatus().update();
        this.eventController.getHudBuilder().update();

        if (!this.eventController.checkGameStatus()) {
            this.gameMap.getGameEngine().stop();
            try {
                this.eventController.endGame(this.gameMap.getGameEngine().getSceneController());
                this.gameMap.getGameEngine().getSceneController().getRanking()
                        .addPlayer(this.gameMap.getGameEngine().getPlayerName(), this.eventController.checkPoints());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return GameMap reference.
     */
    public GameMap getGameMap() {
        return this.gameMap;
    }

    /**
     * Sets the InputController.
     * 
     * @param inputController
     */
    public final void setInputController(final InputController inputController) {
        this.inputController = inputController;
        this.inputController.changeScene(this.playerShipController.display().getScene());
    }

}
