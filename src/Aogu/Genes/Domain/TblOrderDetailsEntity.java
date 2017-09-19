package Aogu.Genes.Domain;

import org.apache.avro.generic.GenericData;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by wuxm on 2016/8/31.
 */
@Entity
@Table(name = "tbl_order_details", schema = "jwdb", catalog = "")
public class TblOrderDetailsEntity {
    private int orderid;
    private String orderno;
    private Date submitdate;
    private String remark;
    private String isfinished;
    private String isdelete;
    private Date opttime;
    private String stand;
    private TblOrgUserEntity optinfo;
    private TblOrderStatusEntity orderstatus;
    private Set<TblOrderInstanceEntity> instancelist = new HashSet<>();
    private Set<TblOrderSampleEntity> samplelist = new HashSet<>();
    private String projectname;
    private String projecttype;
    private String loadstepnum;
    private String temperature;
    private Integer materialnum;
    private Integer nodenum;
    private Integer picturenum;
    private String matdataPath;
    private String temExamplePath;
    private String outputPPath;
    private TblOrderTypeEntity ordertype;

    @Id
    @GeneratedValue
    @Column(name = "orderid", nullable = false)
    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    @Basic
    @Column(name = "orderno", nullable = true, length = 256)
    public String getOrderno() {

        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    @Basic
    @Column(name = "submitdate", nullable = true)
    public Date getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(Date submitdate) {
        this.submitdate = submitdate;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 1024)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "isfinished", nullable = true, length = 32)
    public String getIsfinished() {
        return isfinished;
    }

    public void setIsfinished(String isfinished) {
        this.isfinished = isfinished;
    }

    @Basic
    @Column(name = "isdelete", nullable = true, length = 32)
    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete;
    }

    @Basic
    @Column(name = "opttime", nullable = false)
    public Date getOpttime() {
        return opttime;
    }

    public void setOpttime(Date opttime) {
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

        TblOrderDetailsEntity that = (TblOrderDetailsEntity) o;

        if (orderid != that.orderid) return false;
        if (orderno != null ? !orderno.equals(that.orderno) : that.orderno != null) return false;
        if (submitdate != null ? !submitdate.equals(that.submitdate) : that.submitdate != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (isfinished != null ? !isfinished.equals(that.isfinished) : that.isfinished != null) return false;
        if (isdelete != null ? !isdelete.equals(that.isdelete) : that.isdelete != null) return false;
        if (opttime != null ? !opttime.equals(that.opttime) : that.opttime != null) return false;
        if (stand != null ? !stand.equals(that.stand) : that.stand != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderid;
        result = 31 * result + (orderno != null ? orderno.hashCode() : 0);
        result = 31 * result + (submitdate != null ? submitdate.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (isfinished != null ? isfinished.hashCode() : 0);
        result = 31 * result + (isdelete != null ? isdelete.hashCode() : 0);
        result = 31 * result + (opttime != null ? opttime.hashCode() : 0);
        result = 31 * result + (stand != null ? stand.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "optid", referencedColumnName = "userid")
    public TblOrgUserEntity getOptinfo() {
        return optinfo;
    }

    public void setOptinfo(TblOrgUserEntity optinfo) {
        this.optinfo = optinfo;
    }

    @ManyToOne
    @JoinColumn(name = "orderstatus", referencedColumnName = "orderstatus")
    public TblOrderStatusEntity getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(TblOrderStatusEntity orderstatus) {
        this.orderstatus = orderstatus;
    }

    @OneToMany(mappedBy = "order",fetch=FetchType.EAGER,cascade = { CascadeType.REFRESH, CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE })
    public Set<TblOrderInstanceEntity> getInstancelist() {
        return instancelist;
    }

    public void setInstancelist(Set<TblOrderInstanceEntity> instancelist) {
        this.instancelist = instancelist;
    }

    @OneToMany(mappedBy = "order",fetch=FetchType.EAGER,cascade = { CascadeType.REFRESH, CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE })
    public Set<TblOrderSampleEntity> getSamplelist() {
        return samplelist;
    }

    public void setSamplelist(Set<TblOrderSampleEntity> samplelist) {
        this.samplelist = samplelist;
    }

    public void addSample(TblOrderSampleEntity sample) {
        sample.setOrder(this);
        this.samplelist.add(sample);
    }

    @Basic
    @Column(name = "projectname", nullable = true, length = 256)
    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    @Basic
    @Column(name = "projecttype", nullable = true, length = 256)
    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    @Basic
    @Column(name = "loadstepnum", nullable = true, length = 256)
    public String getLoadstepnum() {
        return loadstepnum;
    }

    public void setLoadstepnum(String loadstepnum) {
        this.loadstepnum = loadstepnum;
    }

    @Basic
    @Column(name = "temperature", nullable = true, length = 256)
    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Basic
    @Column(name = "materialnum", nullable = true)
    public Integer getMaterialnum() {
        return materialnum;
    }

    public void setMaterialnum(Integer materialnum) {
        this.materialnum = materialnum;
    }

    @Basic
    @Column(name = "nodenum", nullable = true)
    public Integer getNodenum() {
        return nodenum;
    }

    public void setNodenum(Integer nodenum) {
        this.nodenum = nodenum;
    }

    @Basic
    @Column(name = "picturenum", nullable = true)
    public Integer getPicturenum() {
        return picturenum;
    }

    public void setPicturenum(Integer picturenum) {
        this.picturenum = picturenum;
    }

    @Basic
    @Column(name = "MATDATA_path", nullable = true, length = 255)
    public String getMatdataPath() {
        return matdataPath;
    }

    public void setMatdataPath(String matdataPath) {
        this.matdataPath = matdataPath;
    }

    @Basic
    @Column(name = "TemExample_path", nullable = true, length = 255)
    public String getTemExamplePath() {
        return temExamplePath;
    }

    public void setTemExamplePath(String temExamplePath) {
        this.temExamplePath = temExamplePath;
    }

    @Basic
    @Column(name = "OutputP_path", nullable = true, length = 255)
    public String getOutputPPath() {
        return outputPPath;
    }

    public void setOutputPPath(String outputPPath) {
        this.outputPPath = outputPPath;
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
