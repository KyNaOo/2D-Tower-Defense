package src.managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import src.Entities.APlant;
import src.help.LoadSave;
import src.objects.Projectile;
import src.objects.Tower;
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
        
		proj_imgs[0] = atlas.getSubimage(8 * 32, 32, 32, 32);
        proj_imgs[1] = atlas.getSubimage(9 * 32, 32, 32, 32);
        proj_imgs[2] = atlas.getSubimage(0* 32, 64, 32, 32);
        proj_imgs[3] = atlas.getSubimage(1* 32, 64, 32, 32);
    }

	public void newProjectile(Tower t, APlant e) {
		int type = getProjType(t);

		int xDist = (int) Math.abs(t.getX() - e.getX());
		int yDist = (int) Math.abs(t.getY() - e.getY());
		int totDist = xDist + yDist;

		float xPer = (float) xDist / totDist;

		float xSpeed = xPer * src.help.Constants.Projectiles.GetSpeed(type);
		float ySpeed = src.help.Constants.Projectiles.GetSpeed(type) - xSpeed;

		if (t.getX() > e.getX())
			xSpeed *= -1;
		if (t.getY() > e.getY())
			ySpeed *= -1;

		projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDmg(), proj_id++, type));

	}

	public void update() {
		for (Projectile p : projectiles)
			if (p.isActive()) {
				p.move();
				if (isProjHittingEnemy(p)) {
					p.setActive(false);
				} else {
					// we do nothing
				}
			}

	}

	private boolean isProjHittingEnemy(Projectile p) {
		for (APlant e : playing.getEnemyManager().getEnemies()) {
			if (e.getBounds().contains(p.getPos())) {
				e.hurt(p.getDmg());
				return true;
			}
		}
		return false;
	}

	public void draw(Graphics g) {
		for (Projectile p : projectiles)
			if (p.isActive())
				g.drawImage(proj_imgs[p.getProjectileType()], (int) p.getPos().x, (int) p.getPos().y, null);
	}

	private int getProjType(Tower t) {
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

}
