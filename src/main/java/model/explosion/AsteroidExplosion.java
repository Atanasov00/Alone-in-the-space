package model.explosion;

/**
 * 
 * Class that represent explosion animation for asteroids.
 *
 */
public class AsteroidExplosion implements Explosion {

	private final String pathImage;
	
	/**
	 * Constructor
	 * @param pathImage
	 */
	public AsteroidExplosion(final String pathImage) {
		this.pathImage = pathImage;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getPathImage() {
		return this.pathImage;
	}

}
