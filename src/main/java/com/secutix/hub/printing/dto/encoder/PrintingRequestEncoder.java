package com.secutix.hub.printing.dto.encoder;

import java.io.IOException;

import javax.inject.Inject;

import org.atmosphere.config.managed.Encoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.secutix.hub.printing.dto.PrintingRequest;

public class PrintingRequestEncoder implements Encoder<PrintingRequest, String> {

	@Inject
	private ObjectMapper mapper;

	@Override
	public String encode(final PrintingRequest r) {
		try {
			return mapper.writeValueAsString(r);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

}
