package tasslegro.model;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

import tasslegro.MyUI;
import tasslegro.base.ImageTasslegro;

public class MainSite extends CustomComponent implements View {
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
	Button buttonUserAdd = new Button("Add User", new Button.ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
			getUI().getNavigator().navigateTo(MyUI.REGISTER);
		}
	});
	Button buttonAuctionInfo = new Button("Auction Info", new Button.ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
			getUI().getNavigator().navigateTo(MyUI.AUCTION_INFO);
		}
	});
	Image imageLogo = new Image();

	public MainSite() {
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
		this.panel.addComponent(this.buttonAuctionAdd);
		this.panel.addComponent(this.buttonUser);
		this.panel.addComponent(this.buttonUserAdd);
		this.panel.addComponent(this.buttonAuctionInfo);
		this.layout.addComponent(this.panel);

		this.imageLogo.setSource(ImageTasslegro.getImageSource());
		this.layout.addComponent(this.imageLogo);
	}
}
