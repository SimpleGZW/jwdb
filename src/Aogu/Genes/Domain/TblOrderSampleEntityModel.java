package Aogu.Genes.Domain;

import java.util.Date;
import java.util.List;

/**
 * Created by wuxm on 2016/8/31.
 */

public class TblOrderSampleEntityModel {
    private List<TblOrderSampleEntity> samplelist;

    public List<TblOrderSampleEntity> getSamplelist() {
        return samplelist;
    }

    public void setSamplelist(List<TblOrderSampleEntity> samplelist) {
        this.samplelist = samplelist;
    }

    public TblOrderSampleEntityModel(List<TblOrderSampleEntity> samplelist) {
        super();
        this.samplelist = samplelist;
    }

    public TblOrderSampleEntityModel() {
        super();
    }

}
