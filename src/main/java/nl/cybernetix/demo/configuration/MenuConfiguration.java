package nl.cybernetix.demo.configuration;

import nl.cybernetix.demo.items.Menu;
import nl.cybernetix.demo.items.MenuFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class MenuConfiguration {

    private final MenuFactory menuFactory;

    @Bean
    public Menu menu(){
        return menuFactory.createMenu();
    }
}
