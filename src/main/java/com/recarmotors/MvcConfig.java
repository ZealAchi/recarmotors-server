package com.recarmotors;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		MediaType media = new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8"));
		supportedMediaTypes.add(media);
		jsonConverter.setSupportedMediaTypes(supportedMediaTypes);
		return jsonConverter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(customJackson2HttpMessageConverter());
	}
}
