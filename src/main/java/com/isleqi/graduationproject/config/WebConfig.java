package com.isleqi.graduationproject.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.endpoint.MetricReaderPublicMetrics;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.aggregate.AggregateMetricReader;
import org.springframework.boot.actuate.metrics.export.MetricExportProperties;
import org.springframework.boot.actuate.metrics.reader.MetricReader;
import org.springframework.boot.actuate.metrics.repository.redis.RedisMetricRepository;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.collect.Maps;

@Configuration
public class WebConfig {

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Bean
	@ExportMetricWriter
	MetricWriter metricWriter(MetricExportProperties export) {
		return new RedisMetricRepository(redisConnectionFactory, export
				.getRedis().getPrefix(), export.getRedis().getKey());
	}

	@Autowired
	private MetricExportProperties export;

	@Bean
	public PublicMetrics metricsAggregate() {
		return new MetricReaderPublicMetrics(aggregatesMetricReader());
	}

	private MetricReader globalMetricsForAggregation() {
		return new RedisMetricRepository(this.redisConnectionFactory,
				this.export.getRedis().getAggregatePrefix(), this.export
						.getRedis().getKey());
	}

	private MetricReader aggregatesMetricReader() {
		AggregateMetricReader repository = new AggregateMetricReader(
				globalMetricsForAggregation());
		return repository;
	}
	
	/**
	 * 参数绑定时将String时间转换为Date
	 * @author yangwk
	 * @time 2017年7月26日 下午4:53:19
	 * @return
	 */
	@Bean
    public Converter<String, Date> stringDateConvert() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = sdf.parse((String) source);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }

	/**
	 * 解决跨域调用问题
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*")
						.allowedMethods("*").allowedHeaders("*")
						.allowCredentials(true)
						.exposedHeaders(HttpHeaders.SET_COOKIE).maxAge(3600L);
			}
		};
	}


}
