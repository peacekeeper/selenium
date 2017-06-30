package org.openqa.grid.selenium;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/*
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
*/
public class GridStatusUpdate {
/*
	private static Logger logger = Logger.getLogger(GridStatusUpdate.class);

	public static String urlBase = "https://scserviceapi-dev.setitcredit.com/SetitCreditApplicationService.svc/grid/";

	private static final Gson gson = new Gson();

	public static final int STATUS_UNKNOWN = 0;
	public static final int STATUS_READY = 1;
	public static final int STATUS_RUNNING = 2;
	public static final int STATUS_MAINTENANCE = 3;
	public static final int STATUS_INITIALIZING = 4;
	public static final int STATUS_NOTREADY = 5;

	public static Properties properties;

	static {

		try {

			properties = new Properties();
			properties.load(new FileReader(new File("statusupdate.properties")));
		} catch (Exception ex) {

			ex.printStackTrace(System.err);
		}
	}

	static {

		Runtime.getRuntime().addShutdownHook(new Thread() {

			public void run() {

				logger.info("Shutdown hook");

				try {

					GridStatusUpdate.update(STATUS_MAINTENANCE, "unknown");
				} catch (IOException ex) {

					ex.printStackTrace(System.err);
				}
			}
		});
	}

	public static String envGridUuid() {

		String gridUuid = System.getProperty("grid.uuid");
		if (gridUuid == null) gridUuid = System.getenv("grid.uuid");
		if (gridUuid == null) gridUuid = properties.getProperty("grid.uuid");
		return gridUuid;
	}

	public static String envGridName() {

		String gridName = System.getProperty("grid.name");
		if (gridName == null) gridName = System.getenv("grid.name");
		if (gridName == null) gridName = properties.getProperty("grid.name");
		return gridName;
	}

	public static String envGridLocation() {

		String gridLocation = System.getProperty("grid.location");
		if (gridLocation == null) gridLocation = System.getenv("grid.location");
		if (gridLocation == null) gridLocation = properties.getProperty("grid.location");
		return gridLocation;
	}

	public static String envAuthId() {

		String gridAuthId = System.getProperty("grid.authid");
		if (gridAuthId == null) gridAuthId = System.getenv("grid.authid");
		if (gridAuthId == null) gridAuthId = properties.getProperty("grid.authid");
		return gridAuthId;
	}

	public static String envAuthKey() {

		String gridAuthKey = System.getProperty("grid.authkey");
		if (gridAuthKey == null) gridAuthKey = System.getenv("grid.authkey");
		if (gridAuthKey == null) gridAuthKey = properties.getProperty("grid.authkey");
		return gridAuthKey;
	}

	public static final IvParameterSpec iv = new IvParameterSpec(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});

	public static String calculateAuthorization(String authId, String authKey) {

		try {

			SecretKeySpec skeySpec;
			skeySpec = new SecretKeySpec(authKey.getBytes("ASCII"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			String value = new Date().toString() + " " + new Date().toString() + "|" + authId;
			byte[] encrypted = cipher.doFinal(value.getBytes());

			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception ex) {

			throw new IllegalArgumentException(ex.getMessage(), ex);
		}
	}

	public static void update(
			int status, 
			String capabilities) throws IOException {

		if (envGridUuid() == null) return;

		logger.info("Status update " + status + ": " + capabilities);

		String url = urlBase + envGridUuid();
		String name = envGridName();
		String location = envGridLocation();
		String authId = envAuthId();
		String authKey = envAuthKey();

		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.setHostnameVerifier(new AllowAllHostnameVerifier());
		HttpClient httpClient = builder.build();
		HttpPut httpPut = new HttpPut(url);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("Status", status);
		jsonObject.addProperty("Name", name);
		jsonObject.addProperty("Location", location);
		jsonObject.addProperty("Capabilities", "no nodes registered yet");
		jsonObject.addProperty("GridURL", "http://myurl.com/#me");	// TODO
		// TODO GridGroup
		// TODO GridPriority
		String body = jsonObject.toString();

		String authorization = calculateAuthorization(authId, authKey);
		httpPut.setHeader("Content-Type", "application/json");
		httpPut.setHeader("Authorization", authorization);
		httpPut.setEntity(new StringEntity(body));

		HttpResponse response = httpClient.execute(httpPut);
		if (response.getStatusLine().getStatusCode() != 200) throw new IOException("Update unsuccessful: " + url + " --> " + response.getStatusLine().getStatusCode() + " (" + response.getStatusLine().getReasonPhrase() + ")");

		String responseline = new BufferedReader(new InputStreamReader(response.getEntity().getContent())).readLine();
		JsonObject j = gson.fromJson(responseline, JsonObject.class);
		int HttpCode = j.get("HttpCode").getAsInt();
		if (HttpCode != 200) throw new IOException("Update unsuccessful: " + responseline);
	}
*/}
