/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.model;

/**
 * @author Guimu
 * @date 2020/01/09
 */
public class AccessModel extends BaseObject {

    private String cacheKey;
    private String accessKeyId;
    private String accessKeySecret;

    /**
     * @return java.lang.String
     * @author Guimu
     * @date 2020/1/9
     */
    public String getCacheKey() {
        return cacheKey;
    }

    /**
     * @param cacheKey key
     * @author Guimu
     * @date 2020/1/9
     */
    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    /**
     * 获取accessKeyId
     *
     * @return java.lang.String
     * @author Guimu
     * @date 2020/1/9
     */
    public String getAccessKeyId() {
        return accessKeyId;
    }

    /**
     * 设置accessKeyId
     *
     * @param accessKeyId accessKeyId
     * @author Guimu
     * @date 2020/1/9
     */
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    /**
     * 获取accessKeySecret
     *
     * @return java.lang.String
     * @author Guimu
     * @date 2020/1/9
     */
    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    /**
     * 设置accessKeySecret
     *
     * @param accessKeySecret secret
     * @author Guimu
     * @date 2020/1/9
     */
    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}