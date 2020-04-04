package com.yabcompany.config;

import com.yabcompany.components.ViewResolverErrorExtended;
import com.yabcompany.twig_finctions.DatePastFunction;
import org.jtwig.environment.EnvironmentConfiguration;
import org.jtwig.environment.EnvironmentConfigurationBuilder;
import org.jtwig.functions.JtwigFunction;
import org.jtwig.spring.JtwigViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.util.Locale;


@Configuration
@ComponentScan("com.yabcompany.controllers")
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setRedirectContextRelative(false);
        resolver.setOrder(100);
        return resolver;
    }

   /* // ViewResolver for twig
    @Bean
    public ViewResolver twigViewResolver() {
        DatePastFunction datePastFunction = new DatePastFunction();
        EnvironmentConfiguration configuration = EnvironmentConfigurationBuilder
                .configuration()
                .functions()
                .add(datePastFunction)
                .and()
                .build();
        JtwigViewResolver resolver = new JtwigViewResolver();
//        System.out.println(configuration.getFunctions());
//        for(JtwigFunction f: configuration.getFunctions()){
//            System.out.println(f.name());
//        }
        resolver.setPrefix("/WEB-INF/twig/");
        resolver.setSuffix(".html.twig");
        resolver.setRedirectContextRelative(false);
        resolver.setOrder(10);

        return resolver;
    }*/

    @Bean
    public ViewResolver errorExtViewResolver() {
        ViewResolverErrorExtended resolver = new ViewResolverErrorExtended();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setErrorPrefix("/WEB-INF/jsp/errors/");
        resolver.setErrorSuffix(".jsp");
        resolver.setRedirectContextRelative(false);
        resolver.setOrder(1);
        return resolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    

}
