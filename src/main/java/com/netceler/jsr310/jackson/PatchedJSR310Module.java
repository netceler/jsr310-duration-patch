package com.netceler.jsr310.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.time.Duration;

/**
 * @see NegativeDurationDeserializer
 */
public class PatchedJSR310Module extends SimpleModule {
    private static final long serialVersionUID = 1L;

    public PatchedJSR310Module() {
        super(Version.unknownVersion());

        addDeserializer(Duration.class, NegativeDurationDeserializer.INSTANCE);
    }
}
