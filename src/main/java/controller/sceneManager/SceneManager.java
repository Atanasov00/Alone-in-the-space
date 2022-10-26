package controller.sceneManager;

import javafx.scene.Node;
import model.Entity;
import utilities.EnumInt;
import view.GameMap;

import java.util.Set;

public class SceneManager {

    private GameMap gameMap;
    private Set<Entity> entities;

    /**
     * Constructor.
     *
     * @param gameMap
     */
    public SceneManager(final GameMap gameMap) {
        this.gameMap = gameMap;
        this.entities = this.gameMap.getActiveEntities();
    }

    /**
     * Game update.
     */
    public void update(float deltaTime) {
        this.updateBackground();
        //TODO soundmanager
        this.entities.forEach(entity -> updateEntityPosition(entity,deltaTime));
    }

    /**
     * Entity position update.
     * @param entity
     * @param deltaTime
     */
    private void updateEntityPosition(final Entity entity, float deltaTime) {
        entity.move(deltaTime);
    }

    /**
     * Background update.
     */
    private void updateBackground() {
        for (Node image : this.gameMap.getBackground()) {
            image.setLayoutY(image.getLayoutY() + EnumInt.FOUR.getValue());
            if (image.getLayoutY() >= this.gameMap.getHeight().intValue()) {
                image.setLayoutY(-this.gameMap.getHeight().intValue());
            }
        }
    }


}
