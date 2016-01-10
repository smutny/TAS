package tasslegro.model;

import java.io.IOException;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import tasslegro.MyUI;
import tasslegro.base.BaseInformation;
import tasslegro.base.Http_Get;
import tasslegro.base.ImageTasslegro;

public class AllUsers extends CustomComponent implements View {
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
	Button buttonUserAdd = new Button("Add User", new Button.ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
			getUI().getNavigator().navigateTo(MyUI.REGISTER);
		}
	});
	Image imageLogo = new Image();
	Table table = new Table("Użytkownicy:");

	Notification notification = null;
	String responseString = null;
	String httpGetURL = BaseInformation.serverURL + "users";

	public AllUsers() {
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
		this.panel.addComponent(this.buttonUserAdd);
		this.layout.addComponent(this.panel);

		this.imageLogo.setSource(ImageTasslegro.getImageSource());
		this.layout.addComponent(this.imageLogo);

		try {
			Http_Get get = new Http_Get(this.httpGetURL);
			this.responseString = get.getStrinResponse();
			if (get.getStatusCode() == 200) {
				this.notification = new Notification("OK", "Pobrano dane użytkowników",
						Notification.Type.WARNING_MESSAGE);
				this.notification.setDelayMsec(5000);
				this.notification.show(Page.getCurrent());
			} else {
				this.notification = new Notification("Error!", this.responseString, Notification.Type.ERROR_MESSAGE);
				this.notification.setDelayMsec(5000);
				this.notification.show(Page.getCurrent());
				this.responseString = null;
			}
		} catch (IOException e) {
			System.err.println("[ERROR] " + new Date() + ": " + e.getMessage());
			this.notification = new Notification("Error!", "Problem z połączeniem!", Notification.Type.ERROR_MESSAGE);
			this.notification.setDelayMsec(5000);
			this.notification.show(Page.getCurrent());
			this.responseString = null;
		}

		this.table.addContainerProperty("Nazwa", String.class, null);
		this.table.addContainerProperty("Email", String.class, null);
		this.table.addContainerProperty("Imię", String.class, null);
		this.table.addContainerProperty("Nazwisko", String.class, null);
		this.table.addContainerProperty("Adres", String.class, null);
		this.table.addContainerProperty("Kod pocztowy", String.class, null);
		this.table.addContainerProperty("Miasto", String.class, null);
		this.table.addContainerProperty("Telefon", Integer.class, null);
		this.table.addContainerProperty("Numer konta", Integer.class, null);

		if (responseString == null) {
		} else {
			JSONArray jsonArray = new JSONArray(responseString);
			int counter = 1;
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject objects = jsonArray.optJSONObject(i);
				this.table
						.addItem(
								new Object[] { objects.getString("login"), objects.getString("email"),
										objects.getString("name"), objects.getString("surname"),
										objects.getString("address"), objects.getString("zipCode"),
										objects.getString("town"), objects.getInt("phone"), objects.getInt("account") },
								counter++);
			}
		}

		this.table.setHeight("400px");
		this.table.setWidth("95%");
		this.table.setColumnCollapsingAllowed(true);
		this.layout.addComponent(this.table);
	}
}
