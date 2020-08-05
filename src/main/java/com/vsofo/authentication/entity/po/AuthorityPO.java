package com.vsofo.authentication.entity.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/5/15 18:04
 * @description 权限表
 */
public class AuthorityPO implements Serializable {

    private long  authorityId;   //权限id
    private String  authorityName;  //权限名称
    private String  authorityAlias; //权限别名
    private String  authorityInfo;  //权限信息
    private Date createTime;    //创建时间
    private Date  updateTime;   //修改时间
    private String  url;        //url地址

    public AuthorityPO() {
    }

    public AuthorityPO(long authorityId, String authorityName, String authorityAlias, String authorityInfo, Date createTime, Date updateTime, String url) {
        this.authorityId = authorityId;
        this.authorityName = authorityName;
        this.authorityAlias = authorityAlias;
        this.authorityInfo = authorityInfo;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.url = url;
    }

    @Override
    public String toString() {
        return "AuthorityPO{" +
                "authorityId=" + authorityId +
                ", authorityName='" + authorityName + '\'' +
                ", authorityAlias='" + authorityAlias + '\'' +
                ", authorityInfo='" + authorityInfo + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", url='" + url + '\'' +
                '}';
    }

    public long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(long authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityAlias() {
        return authorityAlias;
    }

    public void setAuthorityAlias(String authorityAlias) {
        this.authorityAlias = authorityAlias;
    }

    public String getAuthorityInfo() {
        return authorityInfo;
    }

    public void setAuthorityInfo(String authorityInfo) {
        this.authorityInfo = authorityInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
