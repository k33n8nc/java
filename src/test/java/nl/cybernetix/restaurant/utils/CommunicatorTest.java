package nl.cybernetix.restaurant.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommunicatorTest {

    @Mock
    Communicator communicator;

    @Test
    void testYesAnswer() {
        when(communicator.askYesNoQuestion("Answer no")).thenReturn(false);
        assertThat(communicator.askYesNoQuestion("Answer no")).isFalse();
    }

    @Test
    void TestNoAnswer(){
        when(communicator.askYesNoQuestion("Answer yes")).thenReturn(true);
        assertThat(communicator.askYesNoQuestion("Answer yes")).isTrue();
    }
}
