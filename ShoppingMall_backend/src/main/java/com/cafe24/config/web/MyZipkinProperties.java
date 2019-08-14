package com.cafe24.config.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.sleuth.zipkin2.ZipkinProperties;

import zipkin2.codec.SpanBytesEncoder;

/**
 * Zipkin settings
 *
 * @author skok1025
 * @since 1.0.0
 */
@ConfigurationProperties("spring.zipkin")
public class MyZipkinProperties extends ZipkinProperties{
	/** URL of the zipkin query server instance. You can also provide
	 *  the service id of the Zipkin server if Zipkin's registered in
	 *  service discovery (e.g. http://zipkinserver/)
	 */
	private String baseUrl = "http://192.168.1.46:9411/zipkin/";
	/**
	 * Enables sending spans to Zipkin
	 */
	private boolean enabled = true;
	/**
	 * Timeout in seconds before pending spans will be sent in batches to Zipkin
	 */
	private int messageTimeout = 1;
	/**
	 * Encoding type of spans sent to Zipkin. Set to {@link SpanBytesEncoder#JSON_V1} if your server
	 * is not recent.
	 */
	private SpanBytesEncoder encoder = SpanBytesEncoder.JSON_V2;

	/**
	 * Configuration related to compressions of spans sent to Zipkin
	 */
	private Compression compression = new Compression();

	private Service service = new Service();

	public String getBaseUrl() {
		return this.baseUrl;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public int getMessageTimeout() {
		return this.messageTimeout;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setMessageTimeout(int messageTimeout) {
		this.messageTimeout = messageTimeout;
	}

	public void setCompression(Compression compression) {
		this.compression = compression;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public SpanBytesEncoder getEncoder() {
		return this.encoder;
	}

	public void setEncoder(SpanBytesEncoder encoder) {
		this.encoder = encoder;
	}

	/** When enabled, spans are gzipped before sent to the zipkin server */
	public static class Compression {

		private boolean enabled = false;

		public boolean isEnabled() {
			return this.enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
	}

	/** When set will override the default {@code spring.application.name} value of the service id */
	public static class Service {

		/** The name of the service, from which the Span was sent via HTTP, that should appear in Zipkin */
		private String name;

		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	/** Configuration related to locating of the host name from service discovery.
	 *  This property is NOT related to finding Zipkin via Service Disovery.
	 *  To do so use the {@link ZipkinProperties#baseUrl} property with the
	 *  service name set inside the URL.
	 */
	public static class Locator {

		private Discovery discovery;

		public Discovery getDiscovery() {
			return this.discovery;
		}

		public void setDiscovery(Discovery discovery) {
			this.discovery = discovery;
		}

		public static class Discovery {

			/** Enabling of locating the host name via service discovery */
			private boolean enabled;

			public boolean isEnabled() {
				return this.enabled;
			}

			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}
		}
	}
}
