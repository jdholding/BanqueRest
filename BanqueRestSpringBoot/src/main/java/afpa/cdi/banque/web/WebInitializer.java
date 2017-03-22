package afpa.cdi.banque.web;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import afpa.cdi.banque.BanqueApplication;


public class WebInitializer  extends SpringBootServletInitializer{ 
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		return builder.sources(BanqueApplication.class);
	}

}
