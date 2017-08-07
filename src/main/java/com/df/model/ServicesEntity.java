package com.df.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017-08-06.
 */
@Entity
@Table(name = "services", schema = "apisale_service", catalog = "")
public class ServicesEntity {
    private int sid;
    private String sname;
    private Byte status;
    private Byte ptype;
    private Long coincost;
    private Long times;

    @Id
    @Column(name = "sid")
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "sname")
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Basic
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "ptype")
    public Byte getPtype() {
        return ptype;
    }

    public void setPtype(Byte ptype) {
        this.ptype = ptype;
    }

    @Basic
    @Column(name = "coincost")
    public Long getCoincost() {
        return coincost;
    }

    public void setCoincost(Long coincost) {
        this.coincost = coincost;
    }

    @Basic
    @Column(name = "times")
    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServicesEntity that = (ServicesEntity) o;

        if (sid != that.sid) return false;
        if (sname != null ? !sname.equals(that.sname) : that.sname != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (ptype != null ? !ptype.equals(that.ptype) : that.ptype != null) return false;
        if (coincost != null ? !coincost.equals(that.coincost) : that.coincost != null) return false;
        if (times != null ? !times.equals(that.times) : that.times != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sid;
        result = 31 * result + (sname != null ? sname.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (ptype != null ? ptype.hashCode() : 0);
        result = 31 * result + (coincost != null ? coincost.hashCode() : 0);
        result = 31 * result + (times != null ? times.hashCode() : 0);
        return result;
    }
}
