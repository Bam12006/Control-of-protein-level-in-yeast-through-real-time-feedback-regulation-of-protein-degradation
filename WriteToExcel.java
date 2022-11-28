/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmicromanager;

import java.io.File;
import java.io.IOException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author cholab01
 */
public class WriteToExcel {
    public void write(String path ,int row, int[] data1, double[] data2, double[] data3, double[] data4) throws IOException, WriteException{
        File f = new File(path + "excel.xls");
        WritableWorkbook myexel = Workbook.createWorkbook(f);
        WritableSheet mysheet = myexel.createSheet("mySheet", 0);
        
       
            for (int j = 0; j < row; j++)
            {
                String stringData1 = Double.toString(data1[j]);
                Label l1 = new Label(1, j, stringData1);
                String stringData2 = Double.toString(data2[j]);
                Label l2 = new Label(2, j, stringData2);
                String stringData3 = Double.toString(data3[j]);
                Label l3 = new Label(3, j, stringData3);
                String stringData4 = Double.toString(data4[j]);
                Label l4 = new Label(4, j, stringData4);
                
                mysheet.addCell(l1);
                mysheet.addCell(l2);
                mysheet.addCell(l3);
                mysheet.addCell(l4);
            }
        
        myexel.write();
        myexel.close();
    }
}
