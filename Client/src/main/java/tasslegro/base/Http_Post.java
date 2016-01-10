package tasslegro.base;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Http_Post {
	CloseableHttpClient httpClient = new DefaultHttpClient();
	HttpPost post = null;
	HttpResponse response = null;

	public Http_Post(String url, String entity) throws ClientProtocolException, IOException {
		this.post = new HttpPost(url);
		this.post.setHeader("Content-type", "application/json");
		StringEntity stringEntity = new StringEntity(entity.toString(), "UTF-8");
		this.post.setEntity(stringEntity);
		this.response = this.httpClient.execute(this.post);
	}

	public Http_Post() {
	}

	public void setURL(String url, String entity) throws ClientProtocolException, IOException {
		this.post = new HttpPost(url);
		this.post.setHeader("Content-type", "application/json");
		StringEntity stringEntity = new StringEntity(entity.toString(), "UTF-8");
		this.post.setEntity(stringEntity);
		this.response = this.httpClient.execute(this.post);
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
