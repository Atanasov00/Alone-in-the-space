package model.explosion;

public class AsteroidExplosion implements Explosion {

	private final String pathImage;
	
	public AsteroidExplosion(final String pathImage) {
		this.pathImage = pathImage;
	}
	
	@Override
	public String getPathImage() {
		return this.pathImage;
	}

}
