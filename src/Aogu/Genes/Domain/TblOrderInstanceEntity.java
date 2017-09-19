package Aogu.Genes.Domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by wuxm on 2016/8/31.
 */
@Entity
@Table(name = "tbl_order_instance", schema = "jwdb", catalog = "")
public class TblOrderInstanceEntity {
    private int instanceid;
    private Integer optid;
    private String optname;
    private String optrole;
    private Timestamp opttime;
    private String stand;
    private TblOrderDetailsEntity order;
    private TblOrderStatusEntity orderstatus;

    @Id
    @Column(name = "instanceid", nullable = false)
    public int getInstanceid() {
        return instanceid;
    }

    public void setInstanceid(int instanceid) {
        this.instanceid = instanceid;
    }

    @Basic
    @Column(name = "optid", nullable = true)
    public Integer getOptid() {
        return optid;
    }

    public void setOptid(Integer optid) {
        this.optid = optid;
    }

    @Basic
    @Column(name = "optname", nullable = true, length = 256)
    public String getOptname() {
        return optname;
    }

    public void setOptname(String optname) {
        this.optname = optname;
    }

    @Basic
    @Column(name = "optrole", nullable = true, length = 32)
    public String getOptrole() {
        return optrole;
    }

    public void setOptrole(String optrole) {
        this.optrole = optrole;
    }

    @Basic
    @Column(name = "opttime", nullable = true)
    public Timestamp getOpttime() {
        return opttime;
    }

    public void setOpttime(Timestamp opttime) {
        this.opttime = opttime;
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

        TblOrderInstanceEntity that = (TblOrderInstanceEntity) o;

        if (instanceid != that.instanceid) return false;
        if (optid != null ? !optid.equals(that.optid) : that.optid != null) return false;
        if (optname != null ? !optname.equals(that.optname) : that.optname != null) return false;
        if (optrole != null ? !optrole.equals(that.optrole) : that.optrole != null) return false;
        if (opttime != null ? !opttime.equals(that.opttime) : that.opttime != null) return false;
        if (stand != null ? !stand.equals(that.stand) : that.stand != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceid;
        result = 31 * result + (optid != null ? optid.hashCode() : 0);
        result = 31 * result + (optname != null ? optname.hashCode() : 0);
        result = 31 * result + (optrole != null ? optrole.hashCode() : 0);
        result = 31 * result + (opttime != null ? opttime.hashCode() : 0);
        result = 31 * result + (stand != null ? stand.hashCode() : 0);
        return result;
    }

    @ManyToOne(cascade={CascadeType.REFRESH, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name = "orderid", referencedColumnName = "orderid")
    public TblOrderDetailsEntity getOrder() {
        return order;
    }

    public void setOrder(TblOrderDetailsEntity order) {
        this.order = order;
    }

    @ManyToOne
    @JoinColumn(name = "orderstatus", referencedColumnName = "orderstatus")
    public TblOrderStatusEntity getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(TblOrderStatusEntity orderstatus) {
        this.orderstatus = orderstatus;
    }
}
