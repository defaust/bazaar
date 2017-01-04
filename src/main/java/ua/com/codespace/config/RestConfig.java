package ua.com.codespace.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"ua.com.codespace.rest"})
public class RestConfig {
}
