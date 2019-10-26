package com.blue.backend.model;

public class Health {
    private final Boolean isHealthy;

    public Health(Boolean isHealthy) {
        this.isHealthy = isHealthy;
    }

    public Boolean getHealthy() {
        return isHealthy;
    }
}
