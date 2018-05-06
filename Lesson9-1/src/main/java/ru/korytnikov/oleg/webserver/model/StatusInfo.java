package ru.korytnikov.oleg.webserver.model;

public class StatusInfo {
    private boolean result = true;
    private int maxElements;
    private long lifeTimeMs;
    private long idleTimeMs;
    private boolean isIternal;
    private int hitCount;
    private int missCount;

    public StatusInfo() {
    }

    public StatusInfo(int maxElements, long lifeTimeMs, long idleTimeMs) {
        this.maxElements = maxElements;
        this.lifeTimeMs = lifeTimeMs;
        this.idleTimeMs = idleTimeMs;
    }

    public StatusInfo(int maxElements, boolean isIternal) {
        this.maxElements = maxElements;
        this.lifeTimeMs = 0;
        this.idleTimeMs = 0;
        this.isIternal = isIternal;
    }

    @Override
    public String toString() {
        return "StatusInfo{" +
                "maxElements=" + maxElements +
                ", lifeTimeMs=" + lifeTimeMs +
                ", idleTimeMs=" + idleTimeMs +
                ", isIternal=" + isIternal +
                ", hitCount=" + hitCount +
                ", missCount=" + missCount +
                '}';
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
