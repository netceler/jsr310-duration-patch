package com.netceler.jsr310.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.DurationDeserializer;
import com.netceler.jsr310.NegativeDurationParser;
import java.io.IOException;
import java.time.Duration;

public class NegativeDurationDeserializer extends StdDeserializer<Duration> {
    private static final long serialVersionUID = 1L;

    public static final NegativeDurationDeserializer INSTANCE = new NegativeDurationDeserializer();

    private NegativeDurationDeserializer() {
        super(Duration.class);
    }

    @Override
    public Duration deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        switch (parser.getCurrentToken()) {
            case VALUE_STRING:
                String string = parser.getText().trim();
                if (string.length() == 0) {
                    return null;
                }
                return NegativeDurationParser.parse(string);
            default:
                return DurationDeserializer.INSTANCE.deserialize(parser, context);
        }
    }
}