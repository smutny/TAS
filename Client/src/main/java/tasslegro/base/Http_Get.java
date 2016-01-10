package tasslegro.base;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Http_Get {
	CloseableHttpClient httpClient = new DefaultHttpClient();
	HttpGet get = null;
	HttpResponse response = null;

	public Http_Get(String url) throws ClientProtocolException, IOException {
		this.get = new HttpGet(url);
		this.get.setHeader("Accept", "application/json");
		this.response = this.httpClient.execute(this.get);
	}

	public Http_Get() {
	}

	public void setURL(String url) throws ClientProtocolException, IOException {
		this.get = new HttpGet(url);
		this.get.setHeader("Accept", "application/json");
		this.response = this.httpClient.execute(this.get);
	}

	public int getStatusCode() {
		if (response == null) {
			return 0;
		}
		return this.response.getStatusLine().getStatusCode();
	}

	public String getStrinResponse() throws ParseException, IOException {
		if (response == null) {
			return null;
		}
		HttpEntity entity = this.response.getEntity();
		return EntityUtils.toString(entity, "UTF-8");
	}
}
