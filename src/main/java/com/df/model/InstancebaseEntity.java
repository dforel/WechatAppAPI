package com.df.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017-08-06.
 */
@Entity
@Table(name = "instancebase", schema = "apisale_service", catalog = "")
public class InstancebaseEntity {
    private int iid;
    private long userid;
    private Byte renew;

    @Id
    @Column(name = "iid")
    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    @Basic
    @Column(name = "userid")
    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "renew")
    public Byte getRenew() {
        return renew;
    }

    public void setRenew(Byte renew) {
        this.renew = renew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstancebaseEntity that = (InstancebaseEntity) o;

        if (iid != that.iid) return false;
        if (userid != that.userid) return false;
        if (renew != null ? !renew.equals(that.renew) : that.renew != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iid;
        result = 31 * result + (int) (userid ^ (userid >>> 32));
        result = 31 * result + (renew != null ? renew.hashCode() : 0);
        return result;
    }
}
