package pongGameISU.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import pongGameISU.PongMain;

/**
 * A utility class that holds the methods involving image handling for easier
 * image loading and an organized approach to the loading
 * 
 * @author Mandeep Sran, Navaneeth Mulolli Panikan
 * @version June 17, 2015
 *
 */
public class ImageLoader {

	/**
	 * Returns the BufferedImage array which contains all the loaded images from
	 * passed name
	 * 
	 * @param imageName
	 *            Base name of the image
	 * @param size
	 *            Size of the array and images to load
	 * @return The BufferedImage array with all images
	 */
	public static BufferedImage[] loadImages(String imageName,
			String extension, int size) {
		BufferedImage[] images = new BufferedImage[size];
		for (int i = 0; i < size; i++)
			try {
				images[i] = ImageIO.read(PongMain.class
						.getResource("/pongGameISU/resources/" + imageName
								+ (i + 1) + "." + extension));
			} catch (IOException e) {
				e.printStackTrace();
			}
		return images;
	}

}
