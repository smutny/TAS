package tasslegro.base;

import java.io.File;
import java.util.Date;

import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Image;

public final class ImageNoImage {
	static private Image image = null;

	public ImageNoImage() {
	}

	static public Image getImage() {
		if (ImageNoImage.image == null) {
			ImageNoImage.LoadImage();
			return ImageNoImage.image;
		} else {
			return ImageNoImage.image;
		}
	}

	static public Resource getImageSource() {
		if (ImageNoImage.image == null) {
			ImageNoImage.LoadImage();
			return ImageNoImage.image.getSource();
		} else {
			return ImageNoImage.image.getSource();
		}
	}

	static public float getHeight() {
		return ImageNoImage.image.getHeight();
	}

	static public float getWidth() {
		return ImageNoImage.image.getWidth();
	}

	static private void LoadImage() {
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		System.out.println(
				"[LOG] " + new Date() + ": Load image form: " + basepath + "/VAADIN/themes/mytheme/img/noimage.png");
		FileResource resource = new FileResource(new File(basepath + "/VAADIN/themes/mytheme/img/noimage.png"));
		ImageNoImage.image = new Image("", resource);
	}
}
