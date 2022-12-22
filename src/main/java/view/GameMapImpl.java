package view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.almasb.fxgl.core.math.Vec2;

import controller.gameEngine.GameAnimation;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Entity;
import model.asteroid.Asteroid;
import model.asteroid.AsteroidFactory;
import model.asteroid.BasicAsteroid;
import model.bullet.Bullet;
import model.explosion.AsteroidExplosion;
import model.explosion.Explosion;
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
    private Map<Ship, ProgressBar> enemyLifeBars;
    private Map<Ship, Double> lifeBarFactor;
    private Set<Asteroid> asteroids;
    private Map<Asteroid, ImageView> asteroidsMap;
    
    private Ship player;
    private ProgressBar playerLifeBar;
    private ImageView backGroundImage;
    private Scene scene;
    private GameAnimation gameEngine;
    private AnchorPane gameContainer;
    private Stage stage;
    
    private StatusImpl status;
    
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

    
        this.entities = new HashSet<>();
        this.playerBullets = new HashSet<>();
        this.enemyBullets = new HashSet<>();
        this.enemyShips = new HashSet<>();
        this.enemyLifeBars = new HashMap<>();
        this.lifeBarFactor = new HashMap<>();
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
        this.setUpPlayerLifeBar();
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
        this.setUpEnemyLifeBar(enemy);
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
                if(e instanceof Ship) {
                	this.gameContainer.getChildren().remove(this.enemyLifeBars.get(e));
                	this.enemyLifeBars.remove(e);
                	this.lifeBarFactor.remove(e);
                }
            }
            return !e.isAlive();
        });
        
        this.asteroidsMap.forEach((k, v) -> {
        	if(k instanceof BasicAsteroid) {
        		if(((BasicAsteroid) k).checkHealth()) {
        			((BasicAsteroid) k).destroy();
        			this.gameContainer.getChildren().remove(v);
        			startExplosion((BasicAsteroid) k);
        		}
        	}
        });
        
        Set<Asteroid> destroyed = new HashSet<>();
        this.asteroidsMap.forEach((k, v) -> {
        	if(!k.isAlive()) {
        		destroyed.add(k);
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
	}
	
	public void startExplosion(Asteroid asteroid) {
		ImageView expImg = new ImageView(asteroid.getExplosion().getPathImage());
		expImg.relocate(asteroid.getPos().x, asteroid.getPos().y);
		this.gameContainer.getChildren().add(expImg);
		FadeTransition ft = new FadeTransition(Duration.millis(1500), expImg);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.play();
	}
	
	public void updateLifeBar() {
		this.playerLifeBar.setProgress(Double.valueOf(player.getHealth()) / 100);
		//System.out.println(Double.valueOf(player.getHealth()) / 100);
		this.playerLifeBar.relocate(player.getPosition().x - 35, player.getPosition().y - 50);
		this.enemyLifeBars.forEach((k, v) -> {
			v.setProgress(Double.valueOf(k.getHealth()) / this.lifeBarFactor.get(k) / 100);
			v.relocate(k.getPosition().x - 35, k.getPosition().y - 75);
		});
	}
	
	protected void setUpPlayerLifeBar() {
		this.playerLifeBar = new ProgressBar();
		this.playerLifeBar.setPrefWidth(75);
        this.playerLifeBar.setPrefHeight(15);
        this.playerLifeBar.setStyle("-fx-accent: green;");
        this.playerLifeBar.setProgress(player.getHealth() / 100);
        this.playerLifeBar.relocate(player.getPosition().x - 35, player.getPosition().y - 50);
        this.gameContainer.getChildren().add(playerLifeBar);
	}
	
	protected void setUpEnemyLifeBar(Ship enemy) {
		ProgressBar enemyLifeBar = new ProgressBar();
		enemyLifeBar.setPrefWidth(75);
		enemyLifeBar.setPrefHeight(15);
		enemyLifeBar.setStyle("-fx-accent: green;");
		enemyLifeBar.setProgress(Double.valueOf(enemy.getHealth()) / 100);
		enemyLifeBar.relocate(enemy.getPosition().x - 35, enemy.getPosition().y - 100);
		this.gameContainer.getChildren().add(enemyLifeBar);
		this.enemyLifeBars.put(enemy, enemyLifeBar);
		this.lifeBarFactor.put(enemy, Double.valueOf(enemy.getHealth()) / 100);
	}

}
