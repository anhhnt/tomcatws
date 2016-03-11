package com.secutix.hub.printing;

import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.interceptor.CorsInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.secutix.hub.printing.dto.PrintingRequest;
import com.secutix.hub.printing.dto.decoder.PrintingRequestDecoder;
import com.secutix.hub.printing.dto.encoder.PrintingRequestEncoder;

@ManagedService(path = "/printing/hello", interceptors = { CorsInterceptor.class })
public class PrintingRequestHandler {
	private final Logger LOGGER = LoggerFactory.getLogger(PrintingRequestHandler.class);

	@Ready
	public void onReady(final AtmosphereResource r) {
		LOGGER.info("Browser {} connected.", r.uuid());
	}

	@Disconnect
	public void onDisconnect(final AtmosphereResourceEvent event) {
		if (event.isCancelled()) {
			LOGGER.info("Browser {} unexpectedly disconnected", event.getResource().uuid());
		} else if (event.isClosedByClient()) {
			LOGGER.info("Browser {} closed the connection", event.getResource().uuid());
		}
	}

	@org.atmosphere.config.service.Message(encoders = { PrintingRequestEncoder.class },
			decoders = { PrintingRequestDecoder.class })
	public PrintingRequest onMessage(final PrintingRequest request) {
		LOGGER.info(request.toString());
		return request;
	}

}
