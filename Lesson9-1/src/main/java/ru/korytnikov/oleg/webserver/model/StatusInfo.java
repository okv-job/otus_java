package ru.korytnikov.oleg.webserver.model;

public class StatusInfo {

    private int hitCount;
    private int missCount;
    private CacheConfig cacheConfig;
    private boolean result = true;

    public StatusInfo() {
    }

    public StatusInfo(int hitCount, int missCount, CacheConfig cacheConfig) {
        this.hitCount = hitCount;
        this.missCount = missCount;
        this.cacheConfig = cacheConfig;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public int getMissCount() {
        return missCount;
    }

    public void setMissCount(int missCount) {
        this.missCount = missCount;
    }

    public CacheConfig getCacheConfig() {
        return cacheConfig;
    }

    public void setCacheConfig(CacheConfig cacheConfig) {
        this.cacheConfig = cacheConfig;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "StatusInfo{" +
                "hitCount=" + hitCount +
                ", missCount=" + missCount +
                ", cacheConfig=" + cacheConfig +
                ", result=" + result +
                '}';
    }
}
