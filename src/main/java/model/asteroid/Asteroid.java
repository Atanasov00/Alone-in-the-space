package model.asteroid;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.image.ImageView;

public interface Asteroid {
	
	Vec2 getPos();
	
	void setPos(Vec2 pos);
	
	ImageView getImage();
}
