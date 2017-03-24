package br.com.wslt.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

// http://localhost:8080/Wslt/service

@ApplicationPath("service")
public class WsltResourceConfig  extends ResourceConfig {
	public WsltResourceConfig(){
		packages("br.com.wslt.service");
	}
}
