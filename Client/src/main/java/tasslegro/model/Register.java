package tasslegro.model;

import java.io.IOException;
import java.util.Date;

import org.json.simple.JSONObject;

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
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import tasslegro.MyUI;
import tasslegro.base.BaseInformation;
import tasslegro.base.Http_Post;
import tasslegro.base.ImageTasslegro;

public class Register extends CustomComponent implements View, Button.ClickListener {
	VerticalLayout layout = new VerticalLayout();
	HorizontalLayout panel = new HorizontalLayout();
	Button buttonAuction = new Button("Auction All", new Button.ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
			getUI().getNavigator().navigateTo(MyUI.AUCTION);
		}
	});
	Button buttonAuctionAdd = new Button("Add Auction", new Button.ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
			getUI().getNavigator().navigateTo(MyUI.AUCTION_ADD);
		}
	});
	Button buttonUser = new Button("User All", new Button.ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
			getUI().getNavigator().navigateTo(MyUI.USER);
		}
	});
	Image imageLogo = new Image();
	TextField name = new TextField();
	TextField surname = new TextField();
	TextField email = new TextField();
	TextField phone = new TextField();
	TextField login = new TextField();
	PasswordField pass = new PasswordField();
	PasswordField passrep = new PasswordField();
	TextField account = new TextField();
	TextField address = new TextField();
	TextField town = new TextField();
	TextField zipCode = new TextField();
	Button register = new Button("ZAREJESTRUJ");

	Label labelName = new Label("Imie:");
	Label labelSurname = new Label("Nazwisko:");
	Label labelEmail = new Label("Email:*");
	Label labelPhone = new Label("Telefon");
	Label labelLogin = new Label("Login:*");
	Label labelPass = new Label("Hasło:*");
	Label labelPassrep = new Label("Powtórz Haslo:*");
	Label labelAccount = new Label("Numer Konta:");
	Label labelAddress = new Label("Adres:");
	Label labelTown = new Label("Miasto:");
	Label labelZipCode = new Label("Kod Pocztowy:");
	Label require = new Label("*wymagane");

	Notification notification = null;
	String responseString = null;
	String httpPostURL = BaseInformation.serverURL + "users";

	public Register() {
	}

	@Override
	public void enter(ViewChangeEvent event) {
		setCompositionRoot(this.layout);
		this.layout.setSizeFull();
		this.layout.setMargin(true);
		this.layout.setSpacing(true);

		this.panel.setSpacing(true);
		this.panel.addComponent(this.buttonAuction);
		this.panel.addComponent(this.buttonAuctionAdd);
		this.panel.addComponent(this.buttonUser);
		this.layout.addComponent(this.panel);

		this.imageLogo.setSource(ImageTasslegro.getImageSource());
		this.layout.addComponent(this.imageLogo);
		this.layout.addComponent(this.labelName);
		this.layout.addComponent(this.name);
		this.layout.addComponent(this.labelSurname);
		this.layout.addComponent(this.surname);
		this.layout.addComponent(this.labelEmail);
		this.layout.addComponent(this.email);
		this.layout.addComponent(this.labelPhone);
		this.layout.addComponent(this.phone);
		this.layout.addComponent(this.labelLogin);
		this.login = new TextField();
		this.login.setValidationVisible(true);
		this.layout.addComponent(this.login);
		this.layout.addComponent(this.labelPass);
		this.pass = new PasswordField();
		this.layout.addComponent(this.pass);
		this.layout.addComponent(this.labelPassrep);
		this.passrep = new PasswordField();
		this.layout.addComponent(this.passrep);
		this.layout.addComponent(this.labelAddress);
		this.layout.addComponent(this.address);
		this.layout.addComponent(this.labelTown);
		this.layout.addComponent(this.town);
		this.layout.addComponent(this.labelZipCode);
		this.layout.addComponent(this.zipCode);
		this.layout.addComponent(require);
		this.register.setIcon(FontAwesome.HAND_O_RIGHT);
		this.register.addClickListener(this);
		this.layout.addComponent(this.register);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (login.getValue().equals("")) {
			this.notification = new Notification("Error!", "Login jest wymagany!", Notification.Type.ERROR_MESSAGE);
			this.notification.setDelayMsec(5000);
			this.notification.show(Page.getCurrent());
			return;
		}
		if (email.getValue().equals("")) {
			this.notification = new Notification("Error!", "Email jest wymagany!", Notification.Type.ERROR_MESSAGE);
			this.notification.setDelayMsec(5000);
			this.notification.show(Page.getCurrent());
			return;
		}
		if (pass.getValue().equals("")) {
			this.notification = new Notification("Error!", "Hasło jest wymagane!", Notification.Type.ERROR_MESSAGE);
			this.notification.setDelayMsec(5000);
			this.notification.show(Page.getCurrent());
			return;
		}
		if (passrep.getValue().equals("")) {
			this.notification = new Notification("Error!", "Powtórz Hasło jest wymagane!",
					Notification.Type.ERROR_MESSAGE);
			this.notification.setDelayMsec(5000);
			this.notification.show(Page.getCurrent());
			return;
		}
		if (!pass.getValue().equals(passrep.getValue())) {
			this.notification = new Notification("Error!", "Hasła nie są takie same!", Notification.Type.ERROR_MESSAGE);
			this.notification.setDelayMsec(5000);
			this.notification.show(Page.getCurrent());
			return;
		}
		JSONObject msg = new JSONObject();
		msg.put("name", name.getValue());
		msg.put("surname", surname.getValue());
		msg.put("login", login.getValue());
		msg.put("pass", pass.getValue());
		msg.put("email", email.getValue());
		msg.put("address", address.getValue());
		msg.put("town", town.getValue());
		msg.put("zipCode", zipCode.getValue());
		msg.put("phone", phone.getValue());
		msg.put("account", account.getValue());
		try {
			Http_Post post = new Http_Post(this.httpPostURL, msg.toString());
			if (post.getStatusCode() == 201) {
				this.notification = new Notification("OK", "Pomyślnie dodano użytkownika!",
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
