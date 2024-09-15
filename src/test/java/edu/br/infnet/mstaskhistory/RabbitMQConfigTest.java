package edu.br.infnet.mstaskhistory;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.br.infnet.mstaskhistory.configs.RabbitMQConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RabbitMQConfigTest {

    @InjectMocks
    private RabbitMQConfig rabbitMQConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void messageConverter_shouldReturnJackson2JsonMessageConverter() {
        Jackson2JsonMessageConverter converter = rabbitMQConfig.messageConverter();
        assertNotNull(converter);

        ObjectMapper objectMapper = (ObjectMapper) ReflectionTestUtils.getField(converter, "objectMapper");
        assertNotNull(objectMapper);
    }
}
