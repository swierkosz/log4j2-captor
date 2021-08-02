import com.github.swierkosz.log4j2.captor.LogCaptor;
import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

import static com.github.swierkosz.log4j2.captor.Log.log;
import static org.assertj.core.api.Assertions.assertThat;

class Usage {

    private static final Logger LOG = LoggerFactory.getLogger(Usage.class);
    private final LogCaptor logCaptor = LogCaptor.forClass(Usage.class);

    @AfterEach
    void tearDown() {
        MDC.clear();
    }

    @Test
    void shouldCaptureLogWithoutPlaceholders() {
        // When
        LOG.info("Hello world!");

        // Then
        assertThat(logCaptor).contains(log(Level.INFO, "Hello world!"));
    }

    @Test
    void shouldCaptureLogWithPlaceholder() {
        // When
        LOG.debug("Hello {}!", "world");

        // Then
        assertThat(logCaptor).contains(log(Level.DEBUG, "Hello world!"));
    }

    @Test
    void shouldCaptureLogWithThrowable() {
        // Given
        Throwable throwable = new Throwable();

        // When
        LOG.error("Hello world!", throwable);

        // Then
        assertThat(logCaptor).contains(log(Level.ERROR, "Hello world!", throwable));
    }

    @Test
    void shouldCaptureLogWithMdc() {
        // Given
        MDC.put("key", "value");

        // When
        LOG.error("Hello world!");

        // Then
        assertThat(logCaptor).contains(log(Level.ERROR, "Hello world!", mapWith("key", "value")));
    }

    @Test
    void shouldCaptureLogsFromNamedLogger() {
        // Given
        Logger logger = LoggerFactory.getLogger("name");
        LogCaptor logCaptor = LogCaptor.forName("name");

        // When
        logger.info("Test message");

        // Then
        assertThat(logCaptor).containsExactly(log(Level.INFO, "Test message"));
    }

    private static Map<String, String> mapWith(String key, String value) {
        Map<String, String> result = new HashMap<>();
        result.put(key, value);
        return result;
    }
}
