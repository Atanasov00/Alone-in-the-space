package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.almasb.fxgl.core.math.Vec2;

import controller.collisionDetection.Collision;
import controller.collisionDetection.CollisionImpl;
import controller.gameEngine.GameAnimation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Entity;
import model.asteroid.Asteroid;
import model.asteroid.AsteroidFactory;
import model.asteroid.BasicAsteroid;
import model.bullet.Bullet;
import model.ship.EnemyFactory;
import model.ship.Ship;
import model.status.StatusImpl;
import utilities.EnumInt;

/**
 * 
 */
public class GameMapImpl implements GameMap {

    private Set<Entity> entities;
    private Set<Bullet> playerBullets;
    private Set<Bullet> enemyBullets;
    private Set<Ship> enemyShips;
    private Set<Asteroid> asteroids;
    private Map<Asteroid, ImageView> asteroidsMap;
    
    private Ship player;
    private ImageView backGroundImage;
    private Scene scene;
    private GameAnimation gameEngine;
    private AnchorPane gameContainer;
    private Stage stage;
    
    private StatusImpl status;
    private final Ship enemy;
    
    private final int width;
    private final int height;
    private int shipCounter = 1;

    /**
     *
     * @param width
     * @param height
     * @param engine
     */
    public GameMapImpl(final int width, final int height, final GameAnimation engine) {
        this(width, height);
        this.setGameEngine(engine);
    }

    /**
     * 
     * @param width2
     * @param height2
     */
    public GameMapImpl(final int width2, final int height2) {
        this.gameContainer = new AnchorPane();
        this.gameContainer.prefWidth(width2);
        this.gameContainer.prefHeight(height2);

        enemy = EnemyFactory.basicEnemy(new Vec2());
        
        this.entities = new HashSet<>();
        this.playerBullets = new HashSet<>();
        this.enemyBullets = new HashSet<>();
        this.enemyShips = new HashSet<>();
        this.asteroids = new HashSet<>();
        this.asteroidsMap = new HashMap<>();
        
        this.backGroundImage = new ImageView();
        
        this.width = width2;
        this.height = height2;

    }

    @Override
    public AnchorPane getGameContainer() {
        return this.gameContainer;
    }

    @Override
    public final Set<Entity> getActiveEntities() {
        return this.entities;
    }

    @Override
    public Number getWidth() {
        return this.width;
    }

    @Override
    public Number getHeight() {
        return this.height;
    }

    @Override
    public void setPlayer(final Ship player) {
        this.player = player;
        this.player.getNode().setId(String.valueOf(this.shipCounter++));
        this.gameContainer.getChildren().add(this.player.getNode());
        //enemy.setPosition(new Vec2(200,300));
        //System.out.println(enemy.getPosition());
        //this.gameContainer.getChildren().add(enemy.getNode());    
        //enemy.getNode().relocate(enemy.getPosition().x, enemy.getPosition().y);
        this.entities.add(player);
    }

    @Override
    public Ship getPlayer() {
        return this.player;
    }

    @Override
    public Set<Ship> getActiveEnemyShips() {
        return this.enemyShips;
    }

    @Override
    public Set<Bullet> getBulletsShotByPlayer() {
        return this.playerBullets;
    }

    @Override
    public void addPlayerBullet(final Bullet bullet) {
        bullet.getNode().setId(String.valueOf(this.shipCounter++));
        this.playerBullets.add(bullet);
        this.entities.add(bullet);
        // this.gameContainer.getChildren().add(this.player.getNode());
        this.gameContainer.getChildren().add(bullet.getNode());
    }

    @Override
    public Set<Bullet> getBulletsShotByEnemies() {
        return this.enemyBullets;
    }

    @Override
    public void addEnemyBullet(final Bullet bullet) {
        bullet.getNode().setId(String.valueOf(this.shipCounter++));
        this.entities.add(bullet);
        this.enemyBullets.add(bullet);
        this.gameContainer.getChildren().add(bullet.getNode());
    }

    @Override
    public void removeEntity(final Entity entity) {
        this.entities.remove(entity);
        this.gameContainer.getChildren().remove(entity.getNode());
    }

    @Override
    public Scene getScene() {
        return this.scene;
    }

    @Override
    public void setScene(final Scene scene) {
        this.scene = scene;
    }

    @Override
    public void setStageReference(final Stage stage) {
        this.stage = stage;
    }

    @Override
    public void setBackgroundImage(final String path) {
        this.backGroundImage = new ImageView("images/skybox13.jpg");
        this.backGroundImage.setLayoutX(EnumInt.ZERO.getValue());
        this.backGroundImage.setLayoutY(EnumInt.ZERO.getValue());
        this.backGroundImage.setFitWidth(this.width);
        this.backGroundImage.setFitHeight(this.height);
        this.gameContainer.getChildren().add(this.backGroundImage);
    }

    @Override
    public Node getBackground() {
        return this.backGroundImage;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public void addEnemyShip(final Ship enemy) {
        enemy.getNode().setId(String.valueOf(this.shipCounter++));
        this.enemyShips.add(enemy);
        this.entities.add(enemy);
        this.gameContainer.getChildren().add(enemy.getNode());
    }

    @Override
    public void setGameEngine(final GameAnimation gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public GameAnimation getGameEngine() {
        return this.gameEngine;
    }

    @Override
    public void setStatus(final StatusImpl status) {
        this.status = status;
    }

    @Override
    public StatusImpl getStatus() {
        return this.status;
    }

    @Override
    public void removeDeadEntity() {
        // TODO Auto-generated method stub
        this.enemyBullets.removeIf(e -> !(e.isAlive()));
        this.enemyShips.removeIf(e -> !(e.isAlive()));
        this.playerBullets.removeIf(e -> !(e.isAlive()));
        this.entities.removeIf(e -> {
            if (!e.isAlive()) {
                this.gameContainer.getChildren().remove(e.getNode());
            }
            return !e.isAlive();
        });
        
        this.asteroidsMap.forEach((k, v) -> {
        	if(k instanceof BasicAsteroid) {
        		if(((BasicAsteroid) k).checkHealth()) {
        			((BasicAsteroid) k).destroy();
        			this.gameContainer.getChildren().remove(v);
        		}
        	}
        });
        
        Set<Asteroid> destroyed = new HashSet<>();
        this.asteroidsMap.forEach((k, v) -> {
        	if(k instanceof BasicAsteroid) {
        		if(!((BasicAsteroid) k).isAlive()) {
        			destroyed.add(k);
        		}
        	}
        });
        
        destroyed.forEach((Asteroid ast) -> {
        	this.asteroidsMap.remove(ast);
        });
    }

	@Override
	public Set<Asteroid> getAsteroids() {
		return null;
	}

	@Override
	public Map<Asteroid, ImageView> getMapAsteroids() {
		return this.asteroidsMap;
	}

	@Override
	public void generateAsteroids() {
		this.asteroids = AsteroidFactory.spawnAsteroids();
		asteroids.forEach((Asteroid asteroid) -> {
        	asteroidsMap.put(asteroid, new ImageView(asteroid.getPathImage()));
        });
        asteroidsMap.forEach((k,v) ->{
        	v.relocate(k.getPos().x, k.getPos().y);
        	this.gameContainer.getChildren().add(v);
        });
		/*List<Integer> val = new ArrayList<>();
		do {
			val.clear();
			asteroidsMap.clear();
			this.asteroids = AsteroidFactory.spawnAsteroids();
			asteroids.forEach((Asteroid asteroid) -> {
        		asteroidsMap.put(asteroid, new ImageView(asteroid.getPathImage()));
        	});
        	asteroidsMap.forEach((k,v) ->{
        		v.relocate(k.getPos().x, k.getPos().y);        	
        		//this.gameContainer.getChildren().add(v);
        	});
        
        	Set<Asteroid> ast = asteroidsMap.keySet();
        	asteroidsMap.forEach((k, v) -> {
        		ast.forEach((Asteroid asteroid)->{
        			if(!k.equals(asteroid)) {
        				if(v.intersects(asteroidsMap.get(asteroid).getBoundsInParent())) {
        					val.add(1);
        				}
        			}
        		});
        	});
        	System.out.println(val.size());
		} while(val.size() != 0);
		
		asteroidsMap.forEach((k,v) ->{
    		this.gameContainer.getChildren().add(v);
    	});*/
	}

}
