package tasslegro.base;

import java.io.File;
import java.util.Date;

import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Image;

public final class ImageTasslegro {
	static private Image image = null;

	public ImageTasslegro() {
	}

	static public Image getImage() {
		if (ImageTasslegro.image == null) {
			ImageTasslegro.LoadImage();
			return ImageTasslegro.image;
		} else {
			return ImageTasslegro.image;
		}
	}

	static public Resource getImageSource() {
		if (ImageTasslegro.image == null) {
			ImageTasslegro.LoadImage();
			return ImageTasslegro.image.getSource();
		} else {
			return ImageTasslegro.image.getSource();
		}
	}

	static public float getHeight() {
		return ImageTasslegro.image.getHeight();
	}

	static public float getWidth() {
		return ImageTasslegro.image.getWidth();
	}

	static private void LoadImage() {
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		System.out.println(
				"[LOG] " + new Date() + ": Load image form: " + basepath + "/VAADIN/themes/mytheme/img/tasllegro.png");
		FileResource resource = new FileResource(new File(basepath + "/VAADIN/themes/mytheme/img/tasllegro.png"));
		ImageTasslegro.image = new Image("", resource);
	}
}
