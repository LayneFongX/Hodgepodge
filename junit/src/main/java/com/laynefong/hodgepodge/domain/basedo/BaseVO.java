package com.laynefong.hodgepodge.domain.basedo;

public class BaseVO<P> extends BaseDO {
    private P id;

    public P getId() {
        return id;
    }

    public void setId(P id) {
        this.id = id;
    }
}
