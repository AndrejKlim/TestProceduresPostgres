package com.demo.testprocedurespostgres.service.notifier.telegram;

import com.demo.testprocedurespostgres.service.notifier.Notifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Service
@Slf4j
public class TelegramNotifierService implements Notifier {

	@Value("${app.telegram.notice.url}")
	private String urlTemplate;
	@Value("${app.telegram.notice.token}")
	private String apiToken;
	@Value("${app.telegram.notice.chatId}")
	private String chatId;

	@Override
	public void sendNotice(String notice) {
		log.info("Creating notice to Telegram");
		String urlString = String.format(urlTemplate,apiToken, chatId, notice );
		URL url = null;
		try {
			log.info("Creating URL");
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			log.error("Error during creating URL",e);
		}
		URLConnection connection = null;
		try {
			log.info("Opening URL connection");
			if (url != null) {
				connection = url.openConnection();
			}
		} catch (IOException e) {
			log.error("Error during opening URL connection", e);
		}
		StringBuilder sb = new StringBuilder();
		InputStream is = null;
		if (connection != null) {
			try {
				is = new BufferedInputStream(connection.getInputStream());
			} catch (IOException e) {
				log.error("Error during creating BufferedInputStream", e);
			}
		}
		BufferedReader br = null;
		if (is != null) {
			br = new BufferedReader(new InputStreamReader(is));
		}
		String inputLine = "";
		while (true) {
			try {
				if (br != null && (inputLine = br.readLine()) == null) break;
			} catch (IOException e) {
				log.error("Error during reading line",e);
			}
			sb.append(inputLine);
		}

		log.info("Telegram Response : {}", sb.toString());
	}
}
