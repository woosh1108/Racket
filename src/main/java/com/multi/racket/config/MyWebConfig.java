package com.multi.racket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//자동으로 구성된 스프링MVC구성을 변경없이 추가작업을 하기 위해 사용
//@Configuration어노테이션을 선언하고 WebMvcConfigurer상속해서 작업
//WebMvcConfigurer에 작업할 수 있는 메소드를 제공
//=> 인터셉터,뷰리졸버, 메시지컨버터, CORS....
@Configuration
public class MyWebConfig implements WebMvcConfigurer{
	//정적리소스의 경로를 설정하는 경우 사용
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//특정 path로 요청하는 경우 실제 파일이 저장된 위치를 연결해서 리소스를 가져올 수 있도록 처리
		registry.addResourceHandler("/download/**")
				.addResourceLocations("file:///c:/javaweb/upload/");
	}
	
}
