package model.asteroid;

import com.almasb.fxgl.core.math.Vec2;

public interface Asteroid {
	
	Vec2 getPos();
	
	void setPos(Vec2 pos);
	
	String getPathImage();
	
}
