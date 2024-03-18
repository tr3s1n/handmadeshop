package com.tresin.cvproj.handmade_shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.Ssl;

@Configuration
public class SSLConfig {
	@Value("${app.ssl.enabled:true}")
	private boolean sslEnabled;

	@Value("${server.ssl.key-store}")
	private String keyStore;

	@Value("${server.ssl.key-store-password}")
	private String keyStorePassword;

	@Value("${server.ssl.key-store-type}")
	private String keyStoreType;

	@Value("${server.ssl.key-alias}")
	private String keyAlias;


	@Bean
	public TomcatServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		if (sslEnabled) {
			tomcat.setSsl(getSSL());
		}
		return tomcat;
	}


	public Ssl getSSL() {
		Ssl ssl = new Ssl();
		ssl.setKeyStoreType(keyStoreType);
		ssl.setKeyAlias(keyAlias);
		ssl.setKeyStorePassword(keyStorePassword);
		ssl.setKeyStore(keyStore);
		return ssl;
	}
}