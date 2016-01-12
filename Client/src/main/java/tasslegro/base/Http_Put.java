package tasslegro.base;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Http_Put {
	CloseableHttpClient httpClient = new DefaultHttpClient();
	HttpPut put = null;
	HttpResponse response = null;

	public Http_Put(String url, String entity) throws ClientProtocolException, IOException {
		this.put = new HttpPut(url);
		this.put.setHeader("Content-type", "application/json");
		StringEntity stringEntity = new StringEntity(entity.toString(), "UTF-8");
		this.put.setEntity(stringEntity);
		this.response = this.httpClient.execute(this.put);
	}

	public Http_Put() {
	}

	public void setURL(String url, String entity) throws ClientProtocolException, IOException {
		this.put = new HttpPut(url);
		this.put.setHeader("Content-type", "application/json");
		StringEntity stringEntity = new StringEntity(entity.toString(), "UTF-8");
		this.put.setEntity(stringEntity);
		this.response = this.httpClient.execute(this.put);
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
