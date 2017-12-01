package com.chao.springboot;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

@SpringBootApplication
public class ChaoSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChaoSpringbootApplication.class, args);
	}

	/**
	 * 支持FastJson方法
	 * 覆盖方法configureMessageConverters
	 * @return
	 */
	@Bean  //使用@Bean注入fastJsonHttpMessageConvert
	public HttpMessageConverters fastJsonHttpMessageConverters(){
		//1.需要定义一个Convert转换消息的对象
		FastJsonHttpMessageConverter  fastJsonConverter = new FastJsonHttpMessageConverter();
		//2.添加fastjson的配置信息，比如是否要格式化返回的json数据
		FastJsonConfig fastJsonConfig = new FastJsonConfig();

		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		//3.在convert中添加配置信息
		fastJsonConverter.setFastJsonConfig(fastJsonConfig);

		HttpMessageConverter<?> converter = fastJsonConverter;

		return new HttpMessageConverters(converter);

	}

}
