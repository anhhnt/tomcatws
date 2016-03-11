package com.secutix.hub.printing.dto.decoder;

import java.io.IOException;

import javax.inject.Inject;

import org.atmosphere.config.managed.Decoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.secutix.hub.printing.dto.PrintingRequest;

public class PrintingRequestDecoder implements Decoder<String, PrintingRequest> {

	@Inject
	private ObjectMapper objectMapper;

	@Override
	public PrintingRequest decode(final String s) {
		try {
			return objectMapper.readValue(s, PrintingRequest.class);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

}
