package com.df.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017-08-06.
 */
@Entity
@Table(name = "instance", schema = "apisale_service", catalog = "")
public class InstanceEntity {
    private int iid;
    private long sid;
    private long times;
    private long count;
    private String itoken;

    @Id
    @Column(name = "iid")
    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    @Basic
    @Column(name = "sid")
    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "times")
    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    @Basic
    @Column(name = "count")
    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Basic
    @Column(name = "itoken")
    public String getItoken() {
        return itoken;
    }

    public void setItoken(String itoken) {
        this.itoken = itoken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstanceEntity that = (InstanceEntity) o;

        if (iid != that.iid) return false;
        if (sid != that.sid) return false;
        if (times != that.times) return false;
        if (count != that.count) return false;
        if (itoken != null ? !itoken.equals(that.itoken) : that.itoken != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iid;
        result = 31 * result + (int) (sid ^ (sid >>> 32));
        result = 31 * result + (int) (times ^ (times >>> 32));
        result = 31 * result + (int) (count ^ (count >>> 32));
        result = 31 * result + (itoken != null ? itoken.hashCode() : 0);
        return result;
    }
}
