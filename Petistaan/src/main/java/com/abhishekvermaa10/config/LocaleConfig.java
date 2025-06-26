package com.abhishekvermaa10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Configuration
public class LocaleConfig {

    @Bean
    LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		return resolver;
	}
}
