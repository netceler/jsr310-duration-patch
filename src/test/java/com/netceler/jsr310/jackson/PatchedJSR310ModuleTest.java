package com.netceler.jsr310.jackson;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import java.io.IOException;
import java.time.Duration;
import org.junit.Before;
import org.junit.Test;

public class PatchedJSR310ModuleTest {

    private final ObjectMapper patchedMapper = new ObjectMapper();
    private final ObjectMapper vanillaMapper = new ObjectMapper();

    @Before
    public void register_patch() {
        vanillaMapper.registerModule(new JSR310Module());
        patchedMapper.registerModules(new JSR310Module(), new PatchedJSR310Module());
    }

    @Test(expected = AssertionError.class)
    public void should_hit_parse_bug_with_vanilla_parser() throws IOException {
        final Duration millis100 = Duration.ofMillis(-100);
        assertThat(vanillaMapper.readValue("\"PT-0.1S\"", Duration.class)).isEqualTo(millis100);
    }

    @Test
    public void should_handle_negative_durations() throws IOException {
        final Duration millis100 = Duration.ofMillis(-100);
        assertThat(patchedMapper.readValue("\"PT-0.1S\"", Duration.class)).isEqualTo(millis100);
    }

}