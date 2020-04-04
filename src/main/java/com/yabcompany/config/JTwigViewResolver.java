package com.yabcompany.config;

import com.yabcompany.twig_finctions.DatePastFunction;
import org.jtwig.environment.EnvironmentConfiguration;
import org.jtwig.environment.EnvironmentConfigurationBuilder;
import org.jtwig.extension.Extension;
import org.jtwig.spring.JtwigViewResolver;
import org.jtwig.web.servlet.JtwigRenderer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.yabcompany.controllers")

public class JTwigViewResolver {
    private BeanFactory beanFactory;

//    implements BeanFactoryAware
    @Bean
    public ViewResolver viewResolver() {
        JtwigViewResolver viewResolver = new JtwigViewResolver();
        viewResolver.setPrefix("/WEB-INF/twig/");
        viewResolver.setSuffix(".html.twig");
        viewResolver.setRedirectContextRelative(false);
        viewResolver.setCache(false);
        viewResolver.setOrder(1);
//        viewResolver.setRenderer(jTwigRenderer());
        return viewResolver;
    }

    /*@Bean
    public JtwigRenderer jTwigRenderer() {
        return new JtwigRenderer(jTwigEnvironmentConfiguration());
    }

    @Bean
    public EnvironmentConfiguration jTwigEnvironmentConfiguration() {
        return EnvironmentConfigurationBuilder
                .configuration()
                .extensions()
                .add(appJTwigExtension())
                .and()
                .build();
    }

    @Bean
    public Extension appJTwigExtension() {
        return new Extension() {
            @Override
            public void configure(EnvironmentConfigurationBuilder ecb) {
                ecb
                        .functions()
                        .add(beanFactory.getBean(DatePastFunction.class))
//                        .and()
//                        .parameters()
//                        .add(TRANSLATE_EXTENSION_ENVIRONMENT, translateEnvironment)
                ;
            }
        };
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }*/
}
