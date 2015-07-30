package com.netceler.jsr310;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import org.junit.Test;

public class NegativeDurationParserTest {

    /** it this test fail, it means that bug is corrected on your JVM! great! */
    @Test(expected = AssertionError.class)
    public void should_demonstrate_java_bug_with_negative_duration_with_zero_seconds() {
        final Duration millis100 = Duration.ofMillis(-100);

        assertThat(millis100.toString()).isEqualTo("PT-0.1S");
        assertThat(Duration.parse("PT-0.1S")).isEqualTo(millis100);
    }

    @Test
    public void should_demonstrate_patch() {
        final Duration millis100 = Duration.ofMillis(-100);

        assertThat(millis100.toString()).isEqualTo("PT-0.1S");
        assertThat(NegativeDurationParser.parse("PT-0.1S")).isEqualTo(millis100);
    }

}