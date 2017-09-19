package Aogu.Genes.Domain;

import javax.persistence.*;

/**
 * Created by wuxm on 2017/3/15.
 */
@Entity
@Table(name = "tbl_order_sample", schema = "jwdb", catalog = "")
public class TblOrderSampleEntity {
    private Integer resultid;
    private String resultfilename;
    private String resultpath;
    private String resulttype;
    private TblOrderDetailsEntity order;

    @Id
    @Column(name = "resultid", nullable = false)
    public Integer getResultid() {
        return resultid;
    }

    public void setResultid(Integer resultid) {
        this.resultid = resultid;
    }

    @Basic
    @Column(name = "resultfilename", nullable = true, length = 256)
    public String getResultfilename() {
        return resultfilename;
    }

    public void setResultfilename(String resultfilename) {
        this.resultfilename = resultfilename;
    }

    @Basic
    @Column(name = "resultpath", nullable = true, length = 1024)
    public String getResultpath() {
        return resultpath;
    }

    public void setResultpath(String resultpath) {
        this.resultpath = resultpath;
    }

    @Basic
    @Column(name = "resulttype", nullable = true, length = 1024)
    public String getResulttype() {
        return resulttype;
    }

    public void setResulttype(String resulttype) {
        this.resulttype = resulttype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblOrderSampleEntity that = (TblOrderSampleEntity) o;

        if (resultid != null ? !resultid.equals(that.resultid) : that.resultid != null) return false;
        if (resultfilename != null ? !resultfilename.equals(that.resultfilename) : that.resultfilename != null)
            return false;
        if (resultpath != null ? !resultpath.equals(that.resultpath) : that.resultpath != null) return false;
        if (resulttype != null ? !resulttype.equals(that.resulttype) : that.resulttype != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = resultid != null ? resultid.hashCode() : 0;
        result = 31 * result + (resultfilename != null ? resultfilename.hashCode() : 0);
        result = 31 * result + (resultpath != null ? resultpath.hashCode() : 0);
        result = 31 * result + (resulttype != null ? resulttype.hashCode() : 0);
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
}
