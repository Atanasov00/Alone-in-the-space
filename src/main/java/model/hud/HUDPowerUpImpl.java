package model.hud;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.PowerUpEnum;
import view.GameMap;


public class HUDPowerUpImpl implements HUDPowerUp {

    private static final int X_LAYOUT = 430;
    private static final int Y_LAYOUT = 20;

    private ImageView powerUp = new ImageView();
    private boolean statusMonitor;
    private final GameMap gameMap;

    public HUDPowerUpImpl(final GameMap gameMap) {
        this.gameMap = gameMap;
        this.addPowerUp();
    }

    @Override
    public ImageView getPowerUp() {
        return this.powerUp;
    }

    private void addPowerUp() {
            this.powerUp = new ImageView(new Image("/images/" + "powerUp" + ".png"));
            this.powerUp.setLayoutX(X_LAYOUT);
            this.powerUp.setLayoutY(Y_LAYOUT);
    }

    @Override
    public void showPowerUp(final PowerUpEnum powerUp) {
        try {
            if (powerUp == PowerUpEnum.WeaponDamage) {
                this.gameMap.getGameContainer().getChildren().add(this.powerUp);
                this.statusMonitor = true;
            }
        } catch (Exception e) {
            this.hidePowerUp(powerUp);
        }
    }

    @Override
    public void hidePowerUp(final PowerUpEnum powerUp) {
        try {
            this.gameMap.getGameContainer().getChildren().remove(this.powerUp);
            this.statusMonitor = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final boolean getStatus(int index) {
        return this.statusMonitor;
    }
}
