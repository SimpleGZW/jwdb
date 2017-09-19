package Aogu.Genes.Domain;

import javax.persistence.*;

/**
 * Created by wuxm on 2016/8/31.
 */
@Entity
@Table(name = "tbl_order_define", schema = "jwdb", catalog = "")
public class TblOrderDefineEntity {
    private int defineid;
    private String param;
    private String isbegin;
    private String isend;
    private String stand;
    private TblOrderStatusEntity nextstatus;
    private TblOrderStatusEntity currstatus;
    private TblOrderTypeEntity ordertype;

    @Id
    @Column(name = "defineid", nullable = false)
    public int getDefineid() {
        return defineid;
    }

    public void setDefineid(int defineid) {
        this.defineid = defineid;
    }

    @Basic
    @Column(name = "param", nullable = true, length = 32)
    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Basic
    @Column(name = "isbegin", nullable = true, length = 32)
    public String getIsbegin() {
        return isbegin;
    }

    public void setIsbegin(String isbegin) {
        this.isbegin = isbegin;
    }

    @Basic
    @Column(name = "isend", nullable = true, length = 32)
    public String getIsend() {
        return isend;
    }

    public void setIsend(String isend) {
        this.isend = isend;
    }

    @Basic
    @Column(name = "stand", nullable = true, length = 1024)
    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblOrderDefineEntity that = (TblOrderDefineEntity) o;

        if (defineid != that.defineid) return false;
        if (param != null ? !param.equals(that.param) : that.param != null) return false;
        if (isbegin != null ? !isbegin.equals(that.isbegin) : that.isbegin != null) return false;
        if (isend != null ? !isend.equals(that.isend) : that.isend != null) return false;
        if (stand != null ? !stand.equals(that.stand) : that.stand != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = defineid;
        result = 31 * result + (param != null ? param.hashCode() : 0);
        result = 31 * result + (isbegin != null ? isbegin.hashCode() : 0);
        result = 31 * result + (isend != null ? isend.hashCode() : 0);
        result = 31 * result + (stand != null ? stand.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "nextcode", referencedColumnName = "orderstatus")
    public TblOrderStatusEntity getNextstatus() {
        return nextstatus;
    }

    public void setNextstatus(TblOrderStatusEntity nextstatus) {
        this.nextstatus = nextstatus;
    }

    @ManyToOne
    @JoinColumn(name = "currentcode", referencedColumnName = "orderstatus")
    public TblOrderStatusEntity getCurrstatus() {
        return currstatus;
    }

    public void setCurrstatus(TblOrderStatusEntity currstatus) {
        this.currstatus = currstatus;
    }

    @ManyToOne
    @JoinColumn(name = "ordertype", referencedColumnName = "ordertype")
    public TblOrderTypeEntity getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(TblOrderTypeEntity ordertype) {
        this.ordertype = ordertype;
    }
}
