package Aogu.Genes.utils;

import Aogu.Genes.Domain.TblOrderDetailsEntity;
import Aogu.Genes.Domain.TblOrderStatusEntity;
import Aogu.Genes.Domain.TblOrderTypeEntity;
import jxl.CellType;
import jxl.Workbook;
import jxl.write.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;

/**
 * Created by wuxm on 2016/8/16.
 */
public class UtilClass {

    public static String getSequence(){
        Date date = new Date();
        //取当前日期格式化
        String datestr= String.format("%tY%<tm%<td", date);
        //生成四位随机数
        int random =  (int)(Math.random()*9000+1000);
        return datestr + random;
    }

    public static void excelExport(TblOrderDetailsEntity orderDetails,String inputpath,String outputpath) {
        try {
            //第一步：选择模板文件：
            Workbook sourceFile = Workbook.getWorkbook(new File(inputpath));
            File targetFile = new File(outputpath);
            //第二步：通过模板得到一个可写的Workbook,第一个参数是一个输出流对象,第二个参数代表了要读取的模板.
            WritableWorkbook workbook = Workbook.createWorkbook(targetFile, sourceFile);
            //第三步：修改模板中的数据
            updateSheet(workbook,0,2,2,orderDetails.getProjectname());
            updateSheet(workbook,0,3,2,orderDetails.getProjecttype());
            updateSheet(workbook,0,4,2,orderDetails.getLoadstepnum());
            updateSheet(workbook,0,6,2,orderDetails.getTemperature());
            //关闭WritableWorkbook和Workbook以释放资源：
            workbook.write();
            workbook.close();
            sourceFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateSheet(WritableWorkbook workbook,int sheet, int row,int col, String content) throws WriteException {
        WritableSheet excelsheet = workbook.getSheet(sheet);
        WritableCell cell = excelsheet.getWritableCell(col-1, row-1);
        if(cell.getType() == CellType.LABEL)
            ((Label) cell).setString(content);
        else{
            Label l=new Label(col-1, row-1 ,content);
            excelsheet.addCell(l);
        }
    }

}
