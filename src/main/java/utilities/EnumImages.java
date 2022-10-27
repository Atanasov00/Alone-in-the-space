package utilities;

import javafx.scene.image.Image;

public enum EnumImages {

    /**
     *
     */
    BULLET("images/bullet.png"),

    /**
     *
     */
    BACKGROUND("images/skybox13.jpg"),

    /**
     *
     */
    PLAYER("images/playerShip.png"),

    /**
     *
     */
    ENEMY("images/enemy.png");

    private Image value;

    /**
     * Constructor.
     *
     * @param path
     */
    private EnumImages(final String path) {
        this.value = new Image(getClass().getResourceAsStream(path));
    }

    /**
     * @return value.
     */
    public Image getImage() {
        return this.value;
    }

}
