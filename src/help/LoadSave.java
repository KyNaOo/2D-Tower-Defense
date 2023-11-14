package src.help;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {

	public static BufferedImage getSpriteAtlas() {

		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("src/help/spriteV2.png");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return img;
	}

}
