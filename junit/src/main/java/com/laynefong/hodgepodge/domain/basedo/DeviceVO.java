package com.laynefong.hodgepodge.domain.basedo;

public class DeviceVO extends BaseVO<String> {

    private String uid;

    private String ownerId;

    private String name;

    private String customName;

    private Integer ability;

    private String secKey;

    private String localKey;

    private String timeZone;

    /**
     * 待删除
     */
    @Deprecated
    private String verProtocol;

    private String productId;

    private String uuid;

    private String lang;

    private String lat;

    private String lon;

    private String ip;

    private Integer port;

    private String countryCode;

    private Boolean sub;

    /**
     * 待删除
     */
    @Deprecated
    private String cadVersion;

    /**
     * 待删除
     */
    @Deprecated
    private String cdVersion;

    private Integer accessType;

    private String runtimeEnv;

    private String icon;

    private Boolean status;

    private String timeZoneId;

    private String devEtag;

    private String etag;

    private Long activeTime;

    private String skill;

    /**
     * 设备属性，总共32位，免配网设备标识为1
     */
    private Integer devAttribute;

    private String mac;

    private String devKey;

    private String sourceCode;

    private String productKey;

    private Boolean restoreFactory;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getSecKey() {
        return secKey;
    }

    public void setSecKey(String secKey) {
        this.secKey = secKey;
    }

    public String getLocalKey() {
        return localKey;
    }

    public void setLocalKey(String localKey) {
        this.localKey = localKey;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Deprecated
    public String getVerProtocol() {
        return verProtocol;
    }

    @Deprecated
    public void setVerProtocol(String verProtocol) {
        this.verProtocol = verProtocol;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Boolean getSub() {
        return sub;
    }

    public void setSub(Boolean sub) {
        this.sub = sub;
    }

    @Deprecated
    public String getCadVersion() {
        return cadVersion;
    }

    @Deprecated
    public void setCadVersion(String cadVersion) {
        this.cadVersion = cadVersion;
    }

    @Deprecated
    public String getCdVersion() {
        return cdVersion;
    }

    @Deprecated
    public void setCdVersion(String cdVersion) {
        this.cdVersion = cdVersion;
    }

    public Integer getAccessType() {
        return accessType;
    }

    public void setAccessType(Integer accessType) {
        this.accessType = accessType;
    }

    public String getRuntimeEnv() {
        return runtimeEnv;
    }

    public void setRuntimeEnv(String runtimeEnv) {
        this.runtimeEnv = runtimeEnv;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public Integer getAbility() {
        return ability;
    }

    public void setAbility(Integer ability) {
        this.ability = ability;
    }

    public String getDevEtag() {
        return devEtag;
    }

    public void setDevEtag(String devEtag) {
        this.devEtag = devEtag;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public Long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Long activeTime) {
        this.activeTime = activeTime;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Integer getDevAttribute() {
        return devAttribute;
    }

    public void setDevAttribute(Integer devAttribute) {
        this.devAttribute = devAttribute;
    }

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getDevKey() {
		return devKey;
	}

	public void setDevKey(String devKey) {
		this.devKey = devKey;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public Boolean getRestoreFactory() {
        return restoreFactory;
    }

    public void setRestoreFactory(Boolean restoreFactory) {
        this.restoreFactory = restoreFactory;
    }
}