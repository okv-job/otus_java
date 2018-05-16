package ru.korytnikov.oleg.webserver.model;

public class CacheConfig {
    private boolean result = true;
    private int maxElements;
    private long lifeTimeMs;
    private long idleTimeMs;
    private boolean isIternal;

    public CacheConfig() {
    }

    public CacheConfig(int maxElements, long lifeTimeMs, long idleTimeMs) {
        this.maxElements = maxElements;
        this.lifeTimeMs = lifeTimeMs;
        this.idleTimeMs = idleTimeMs;
    }

    public CacheConfig(int maxElements, boolean isIternal) {
        this.maxElements = maxElements;
        this.lifeTimeMs = 0;
        this.idleTimeMs = 0;
        this.isIternal = isIternal;
    }

    @Override
    public String toString() {
        return "CacheConfig{" +
                "maxElements=" + maxElements +
                ", lifeTimeMs=" + lifeTimeMs +
                ", idleTimeMs=" + idleTimeMs +
                ", isIternal=" + isIternal +
                '}';
    }

    public int getMaxElements() {
        return maxElements;
    }

    public void setMaxElements(int maxElements) {
        this.maxElements = maxElements;
    }

    public long getLifeTimeMs() {
        return lifeTimeMs;
    }

    public void setLifeTimeMs(long lifeTimeMs) {
        this.lifeTimeMs = lifeTimeMs;
    }

    public long getIdleTimeMs() {
        return idleTimeMs;
    }

    public void setIdleTimeMs(long idleTimeMs) {
        this.idleTimeMs = idleTimeMs;
    }

    public boolean isIternal() {
        return isIternal;
    }

    public void setIternal(boolean iternal) {
        isIternal = iternal;
    }

}
