package tasslegro;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import tasslegro.model.AddAuction;
import tasslegro.model.AllAuctions;
import tasslegro.model.AllUsers;
import tasslegro.model.MainSite;
import tasslegro.model.Register;

@Theme("mytheme")
@Widgetset("tasslegro.MyAppWidgetset")
public class MyUI extends UI {
	public Navigator navigator;

	public static final String MAIN = "main";
	public static final String USER = "users";
	public static final String REGISTER = "register";
	public static final String AUCTION_ADD = "auction_add";
	public static final String AUCTION = "auctions";

	public MyUI() {
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		getPage().setTitle("Tasslegro");
		this.navigator = new Navigator(this, this);
		this.navigator.addView(MyUI.MAIN, new MainSite());
		this.navigator.addView(MyUI.REGISTER, new Register());
		this.navigator.addView(MyUI.USER, new AllUsers());
		this.navigator.addView(MyUI.AUCTION_ADD, new AddAuction());
		this.navigator.addView(MyUI.AUCTION, new AllAuctions());
		this.navigator.navigateTo(MyUI.MAIN);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = true)
	public static class MyUIServlet extends VaadinServlet {
	}
}
