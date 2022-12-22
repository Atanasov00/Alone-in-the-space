package controller.eventController;

import java.io.IOException;

import controller.collisionDetection.Collision;
import controller.gameSwitcher.SceneController;
import model.hud.HUDPowerUp;
import view.hud.HUDImpl;

/**
 * This interface contains the methods that are used to handle the HUD and to
 * coordinate the events with the game status.
 */
public interface EventController {

    /**
     * @return collision.
     */
    Collision getCollision();

    /**
     * @return the value of the points.
     */
    int checkPoints();

    /**
     * @return the remaining life points.
     */
    int checkLifePoints();

    HUDImpl getHudBuilder();

    /**
     *
     * @param sceneController
     * @throws IOException
     */
    void endGame(SceneController sceneController) throws IOException;

    /**
     * @return the game status.
     */
    boolean checkGameStatus();

    /**
     * @return powerUp HUD reference.
     */
    HUDPowerUp getPowerUp();
}
