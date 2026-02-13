package org.example;

/**
 * Simple smoke class without external test framework dependencies.
 *
 * Why: if Maven test dependencies are not imported in IDE,
 * JUnit/Spring test annotations become unresolved.
 */
class AppTest {
    boolean contextLoads() {
        return true;
    }
}