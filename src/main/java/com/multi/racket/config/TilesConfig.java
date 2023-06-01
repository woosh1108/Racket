package com.multi.racket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
public class TilesConfig {
	//tiles기반으로 뷰를 만들 수 있는 뷰리졸버를 만들고 우선순위 1위로 정의함
		@Bean
		public UrlBasedViewResolver viewResolver() {
			UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
			tilesViewResolver.setViewClass(TilesView.class);
			tilesViewResolver.setOrder(0);
			return tilesViewResolver;
		}
		//tiles설정파일을 인식해서 등록된 뷰정보를 기반으로 뷰를 만들 수 있는 객체
		@Bean
		public TilesConfigurer tilesConfigurer() {
			TilesConfigurer configurer = new TilesConfigurer();
			configurer.setDefinitions(new String[] {"/WEB-INF/tiles/**-tiles.xml"});
			return configurer;
		}
}
