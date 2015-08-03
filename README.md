# jsr310-duration-patch
Patch parse of specific negative in java 8 Durations (JDK8). 

See OpenJDK bug [JDK-8054978](https://bugs.openjdk.java.net/browse/JDK-8054978)

With jdk8, this test fails in line 3:

    final Duration millis100 = Duration.ofMillis(-100);
    assertThat(millis100.toString()).isEqualTo("PT-0.1S");
    assertThat(Duration.parse("PT-0.1S")).isEqualTo(millis100);
    
# Content

This repository provide:
* a patched Duration parser : *com.netceler.jsr310.NegativeDurationParser*
* a dedicated Jackson deserializer
* a dedicated Jackson Module, to register **after** the *com.fasterxml.jackson.datatype.jsr310.JSR310Module* : *com.netceler.jsr310.jackson.PatchedJSR310Module*
