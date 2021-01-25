package ru.mospolytech.dpo.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/img/**")
//                .addResourceLocations("file:///" + uploadPath + "/");       //WINDOWS
//                .addResourceLocations("file://" + uploadPath + "/"); //LINUX
    }
    
//    @Value("${spring.datasource.url}")
//  private String dbUrl;
//
//  @Bean
//  public DataSource dataSource() {
//      HikariConfig config = new HikariConfig();
//      config.setJdbcUrl(dbUrl);
//      return new HikariDataSource(config);
//  }
    @Bean
    public FilterRegistrationBean encodingFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CharacterEncodingFilter());
        Map<String, String> params = new HashMap<>();
        params.put("encoding", "UTF-8");
        params.put("forceEncoding", "true");
        registrationBean.setInitParameters(params);
        registrationBean.addUrlPatterns("/");
        return registrationBean;
    }
}
