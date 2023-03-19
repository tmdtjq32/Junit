package me.tmdtjq32.myproject.src.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class webFilterConfiguration {

    @Bean
    @Order()
    public FilterRegistrationBean<studyFilter> studyFilter() {
        FilterRegistrationBean<studyFilter> studyFilterBean = new FilterRegistrationBean<>();
        studyFilterBean.setFilter(new studyFilter());
        studyFilterBean.addUrlPatterns("/study/*");

        return studyFilterBean;
    }

}
