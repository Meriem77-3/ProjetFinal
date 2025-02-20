package com.fst.ArtSphere;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {ArtSphereApplicationTests.TestConfig.class})
class ArtSphereApplicationTests {

    @Test
    void contextLoads() {
    }

    @org.springframework.context.annotation.Configuration
    static class TestConfig {
        // Configuration vide pour le test
    }
}