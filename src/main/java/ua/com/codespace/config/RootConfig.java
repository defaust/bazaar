package ua.com.codespace.config;

import ua.com.codespace.config.JpaConfig.Test;
import ua.com.codespace.config.JpaConfig.Dev;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"ua.com.codespace"},
        excludeFilters = {
                @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
        })
@PropertySource("classpath:/application.properties")
@Import({Test.class, Dev.class})
public class RootConfig {
}
