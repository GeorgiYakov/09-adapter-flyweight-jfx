package ohm.softa.a09.model;

import ohm.softa.a09.model.empire.TieBomber;
import ohm.softa.a09.model.empire.TieFighter;
import ohm.softa.a09.model.empire.TieInterceptor;
import ohm.softa.a09.model.rebellion.AWing;
import ohm.softa.a09.model.rebellion.XWing;
import ohm.softa.a09.model.rebellion.YWing;
import ohm.softa.a09.resource.FxImageLoaderAdapter;
import ohm.softa.a09.resource.ResourceLoader;
import ohm.softa.a09.util.NameGenerator;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Random;

/**
 * Factory to create new fighters
 * Creates random fighters
 *
 * @author Peter Kurfer
 */
public final class FighterFactory {

	private static final int NumberOfKnownFighterTypes = 6;
	private final Random random;
	private final NameGenerator nameGenerator;
	private final ResourceLoader<Image> imageResourceLoader;
	private final FxImageLoaderAdapter imageLoader = new FxImageLoaderAdapter();

	private HashMap<String, Image> cache = new HashMap<>();

	private Image loadImage(ClassLoader cl, String path) {
		if (!cache.containsKey(path)) {
			// cache.put(path, imageResourceLoader.loadResource(cl, path));
			cache.put(path, imageLoader.loadImage(path));
		}

		return cache.get(path);
	}

	public FighterFactory() {
		nameGenerator = new NameGenerator();
		random = new Random();
		imageResourceLoader = new ResourceLoader<>(Image::new);
	}

	/**
	 * Create a random Fighter
	 *
	 * @implNote the method has an implementation flaw because it always loads the fighters image
	 * @return a new Fighter instance
	 */
	public Fighter createFighter() {
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		String name = nameGenerator.generateName();


		switch (random.nextInt(NumberOfKnownFighterTypes)) {
			case 0:
				return new AWing(name, imageLoader.loadImage("fighter/awing.jpg"));
			case 1:
				return new XWing(name, imageLoader.loadImage("fighter/xwing.jpg"));
			case 2:
				return new YWing(name, imageLoader.loadImage("fighter/ywing.jpg"));
			case 3:
				return new TieBomber(name, imageLoader.loadImage("fighter/tiebomber.jpg"));
			case 4:
				return new TieFighter(name, imageLoader.loadImage("fighter/tiefighter.jpg"));
			default:
				return new TieInterceptor(name, imageLoader.loadImage("fighter/tieinterceptor.jpg"));
		}
	}
}
