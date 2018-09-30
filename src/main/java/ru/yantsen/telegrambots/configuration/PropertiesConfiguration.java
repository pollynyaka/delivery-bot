package ru.yantsen.telegrambots.configuration;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PropertiesConfiguration {

    @Bean
    public PropertyPlaceholderConfigurer properties() {
        final PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setIgnoreResourceNotFound(true);

        final List<Resource> resourceLst = new ArrayList<Resource>();
        resourceLst.add(new ClassPathResource("proxy.properties"));
        resourceLst.add(new FileSystemResource("/home/bo/Develop/spring/BotProject/bot.properties"));

        ppc.setLocations(resourceLst.toArray(new Resource[]{}));

        return ppc;
    }
}


