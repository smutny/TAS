package tasslegro.model;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;

import tasslegro.MyUI;
import tasslegro.base.BaseInformation;
import tasslegro.base.Http_Post;
import tasslegro.base.ImageTasslegro;
import tasslegro.base.ImageUploader;

public class AddAuction extends CustomComponent implements View, Button.ClickListener {
	VerticalLayout layout = new VerticalLayout();
	HorizontalLayout panel = new HorizontalLayout();
	Button buttonAuction = new Button("Auction All", new Button.ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
			getUI().getNavigator().navigateTo(MyUI.AUCTION);
		}
	});
	Button buttonUser = new Button("User All", new Button.ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
			getUI().getNavigator().navigateTo(MyUI.USER);
		}
	});
	Button buttonUserAdd = new Button("Add User", new Button.ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
			getUI().getNavigator().navigateTo(MyUI.REGISTER);
		}
	});
	Image imageLogo = new Image();
	TextField auctionTitle = new TextField();
	TextArea auctionDescription = new TextArea();
	TextField auctionPrice = new TextField();
	ImageUploader receiverImage = new ImageUploader();
	Upload uploadImage = new Upload(null, this.receiverImage);
	Button auctionButtonAdd = new Button("Wyślij");

	Label labelTitle = new Label("Tytuł:");
	Label labelDescription = new Label("Opis:");
	Label labelPrice = new Label("Cena:");
	Label labelImage = new Label("Zdjęcie:");

	int userId = 1;

	Notification notification = null;
	String responseString = null;
	String httpPostURL = BaseInformation.serverURL + "auctions";

	public AddAuction() {
	}

	@Override
	public void enter(ViewChangeEvent event) {
		this.layout = new VerticalLayout();
		setCompositionRoot(this.layout);
		this.layout.setSizeFull();
		this.layout.setMargin(true);
		this.layout.setSpacing(true);

		this.panel.setSpacing(true);
		this.panel.addComponent(this.buttonAuction);
		this.panel.addComponent(this.buttonUser);
		this.panel.addComponent(this.buttonUserAdd);
		this.layout.addComponent(this.panel);

		this.imageLogo.setSource(ImageTasslegro.getImageSource());
		this.layout.addComponent(this.imageLogo);
		this.layout.addComponent(this.labelTitle);
		this.layout.addComponent(this.auctionTitle);
		this.layout.addComponent(this.labelDescription);
		this.layout.addComponent(this.auctionDescription);
		this.layout.addComponent(this.labelPrice);
		this.layout.addComponent(this.auctionPrice);
		this.layout.addComponent(this.labelImage);
		this.uploadImage.setButtonCaption("Dodaj");
		this.uploadImage.setImmediate(true);
		this.layout.addComponent(this.uploadImage);
		this.auctionButtonAdd.setIcon(FontAwesome.HAND_O_RIGHT);
		this.auctionButtonAdd.addClickListener(this);
		this.layout.addComponent(this.auctionButtonAdd);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		int imageId = 0;
		if (this.auctionTitle.getValue().equals("")) {
			this.notification = new Notification("Error!", "Tytuł jest wymagany!", Notification.Type.ERROR_MESSAGE);
			this.notification.setDelayMsec(5000);
			this.notification.show(Page.getCurrent());
			return;
		} else if (this.auctionDescription.getValue().equals("")) {
			this.notification = new Notification("Error!", "Opis jest wymagany!", Notification.Type.ERROR_MESSAGE);
			this.notification.setDelayMsec(5000);
			this.notification.show(Page.getCurrent());
			return;
		} else if (this.auctionPrice.getValue().equals("")) {
			this.notification = new Notification("Error!", "Cena jest wymagany!", Notification.Type.ERROR_MESSAGE);
			this.notification.setDelayMsec(5000);
			this.notification.show(Page.getCurrent());
			return;
		} else if (!NumberUtils.isNumber(this.auctionPrice.getValue())) {
			this.auctionPrice.setValue(this.auctionPrice.getValue().replace(",", "."));
			if (!NumberUtils.isNumber(this.auctionPrice.getValue())) {
				this.notification = new Notification("Error!", "Cena jest liczbą!", Notification.Type.ERROR_MESSAGE);
				this.notification.setDelayMsec(5000);
				this.notification.show(Page.getCurrent());
				return;
			}
		} else if (NumberUtils.toFloat(this.auctionPrice.getValue()) <= 0.0) {
			this.notification = new Notification("Error!", "Cena musi być większa niż 0!",
					Notification.Type.ERROR_MESSAGE);
			this.notification.setDelayMsec(5000);
			this.notification.show(Page.getCurrent());
			return;
		} else if (this.receiverImage.returnInputStream() != null) {
			HttpResponse response = this.receiverImage.uploadImage();
			if (response == null) {
				this.notification = new Notification("Error!", "Problem z połączeniem!",
						Notification.Type.ERROR_MESSAGE);
				this.notification.setDelayMsec(5000);
				this.notification.show(Page.getCurrent());
				return;
			} else {
				HttpEntity entity = response.getEntity();
				String responseString = null;
				try {
					responseString = EntityUtils.toString(entity, "UTF-8");
				} catch (ParseException e) {
					this.notification = new Notification("Error!", "Problem z połączeniem!",
							Notification.Type.ERROR_MESSAGE);
					this.notification.setDelayMsec(5000);
					this.notification.show(Page.getCurrent());
					System.err.println("[ERROR] " + new Date() + ": " + e.getMessage());
					return;
				} catch (IOException e) {
					this.notification = new Notification("Error!", "Problem z połączeniem!",
							Notification.Type.ERROR_MESSAGE);
					this.notification.setDelayMsec(5000);
					this.notification.show(Page.getCurrent());
					System.err.println("[ERROR] " + new Date() + ": " + e.getMessage());
					return;
				}
				if (response.getStatusLine().getStatusCode() == 201) {
					JSONObject msg = new JSONObject(responseString);
					imageId = msg.getInt("id");
				} else {
					this.notification = new Notification("Error!", responseString, Notification.Type.ERROR_MESSAGE);
					this.notification.setDelayMsec(5000);
					this.notification.show(Page.getCurrent());
					return;
				}
			}
		}
		JSONObject msg = new JSONObject();
		msg.put("title", auctionTitle.getValue());
		msg.put("description", auctionDescription.getValue());
		msg.put("price", auctionPrice.getValue());
		msg.put("user_ID", userId);
		msg.put("image_ID", imageId);
		try {
			Http_Post post = new Http_Post(this.httpPostURL, msg.toString());
			if (post.getStatusCode() == 201) {
				this.notification = new Notification("OK", "Pomyślnie dodano aukcjię!",
						Notification.Type.WARNING_MESSAGE);
				this.notification.setDelayMsec(5000);
				this.notification.show(Page.getCurrent());
			} else {
				this.notification = new Notification("Error!", responseString, Notification.Type.ERROR_MESSAGE);
				this.notification.setDelayMsec(5000);
				this.notification.show(Page.getCurrent());
			}
		} catch (IOException e) {
			System.err.println("[ERROR] " + new Date() + ": " + e.getMessage());
			this.notification = new Notification("Error!", "Problem z połączeniem!", Notification.Type.ERROR_MESSAGE);
			this.notification.setDelayMsec(5000);
			this.notification.show(Page.getCurrent());
		}
	}

}
