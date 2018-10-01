/*
 *This file is serializing and deserializing json object to byte stream and vice-versa.
*/

package com.boptima.ratelimiter.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Component
public class RestConfig {
  public RestConfig() {
  }


  private ObjectMapper jacksonObjectMapper() {
    return (new ObjectMapper()).setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    ArrayList<HttpMessageConverter<?>> messageConverters = new ArrayList();
    MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
    jsonMessageConverter.setObjectMapper(this.jacksonObjectMapper());
    messageConverters.add(jsonMessageConverter);
    restTemplate.setMessageConverters(messageConverters);
    return restTemplate;
  }
}