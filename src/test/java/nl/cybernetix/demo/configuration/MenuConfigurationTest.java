package nl.cybernetix.demo.configuration;

import nl.cybernetix.demo.items.MenuFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuConfigurationTest {

    @Mock
    private MenuFactory menuFactory;

    @InjectMocks
    private MenuConfiguration menuConfiguration;

    @Test
    void menu_ThrowsException_WhenFactoryReturnsNull() {
        when(menuFactory.createMenu()).thenReturn(null);

        assertThrows(IllegalStateException.class, () -> menuConfiguration.menu());
    }
}