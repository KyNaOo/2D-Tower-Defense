package src.managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import src.Entities.APlant;
import src.help.LoadSave;
import src.objects.Projectile;
import src.objects.Zombie;
import src.Scenes.Playing;
import static src.help.Constants.Towers.*;
import static src.help.Constants.Projectiles.*;

public class ProjectileManager {

	private Playing playing;
	private ArrayList<Projectile> projectiles = new ArrayList<>();
	private BufferedImage[] proj_imgs;
	private int proj_id = 0;

	public ProjectileManager(Playing playing) {
		this.playing = playing;
		importImgs();
	}

	private void importImgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		proj_imgs = new BufferedImage[4];

		proj_imgs[0] = atlas.getSubimage(9 * 32, 32, 32, 32);
        proj_imgs[1] = atlas.getSubimage(1* 32, 64, 32, 32);
        proj_imgs[2] = atlas.getSubimage(0* 32, 64, 32, 32);
        proj_imgs[3] = atlas.getSubimage(8 * 32, 32, 32, 32);
    }

	
    public void newProjectile(Zombie t, APlant e) {
		int type = getProjType(t);

		int xDist = (int) (t.getX() - e.getX());
		int yDist = (int) (t.getY() - e.getY());
		int totDist = Math.abs(xDist) + Math.abs(yDist);

		float xPer = (float) Math.abs(xDist) / totDist;

		float xSpeed = xPer * src.help.Constants.Projectiles.GetSpeed(type);
		float ySpeed = src.help.Constants.Projectiles.GetSpeed(type) - xSpeed;

		if (t.getX() > e.getX())
			xSpeed *= -1;
		if (t.getY() > e.getY())
			ySpeed *= -1;

		float rotate = 0;

		if (type == ARROW) {
			float arcValue = (float) Math.atan(yDist / (float) xDist);
			rotate = (float) Math.toDegrees(arcValue);

			if (xDist < 0)
				rotate += 180;
		}

		projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDmg(), rotate, proj_id++, type));

	}
	public void update() {
		for (Projectile p : projectiles)
			if (p.isActive()) {
				p.move();

				if (isProjHittingEnemy(p)) {
					p.setActive(false);
					if (p.getProjectileType() == BOMB) {
						explodeOnEnemies(p);
					}
				}else if (isProjOutsideBounds(p)){
					p.setActive(false);
				}
			}

	}

	private boolean isProjOutsideBounds(Projectile p) {
		if (p.getPos().getX()>=0){
			if (p.getPos().getX()<=640){
				if (p.getPos().getY()>=0){
					if (p.getPos().getY()<= 800){
						return false;
					}
				}
			}
		}
		return true;
	}

	private boolean isProjHittingEnemy(Projectile p) {
		for (APlant e : playing.getEnemyManager().getEnemies()) {

			if (e.isAlive()){
                
            
				if (e.getBounds().contains(p.getPos())) {
					e.hurt(p.getDmg());
					if(p.getProjectileType() == CHAINS)
						e.slow();

					return true;
				}
		}
		
	}
	return false;
}

    private void explodeOnEnemies(Projectile p) {
		for (APlant e : playing.getEnemyManager().getEnemies()) {
			if (e.isAlive()) {
				float radius = 40.0f;

				float xDist = Math.abs(p.getPos().x - e.getX());
				float yDist = Math.abs(p.getPos().y - e.getY());

				float realDist = (float) Math.hypot(xDist, yDist);

				if (realDist <= radius)
					e.hurt(p.getDmg());
			}

		}

	}

    public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		for (Projectile p : projectiles)
			if (p.isActive()) {
				g2d.translate(p.getPos().x, p.getPos().y);
				g2d.rotate(Math.toRadians(p.getRotation()));
				g2d.drawImage(proj_imgs[p.getProjectileType()], -16, -16, null);
				g2d.rotate(-Math.toRadians(p.getRotation()));
				g2d.translate(-p.getPos().x, -p.getPos().y);
			}

	}

	private int getProjType(Zombie t) {
		switch (t.getTowerType()) {
		case ARCHER:
			return ARROW;
		case CANNON:
			return BOMB;
		case WIZARD:
			return CHAINS;
        case LASER:
            return LAZER;
		}
		return 0;
	}
	public void reset(){
		projectiles.clear();
		proj_id=0;
	}
}
