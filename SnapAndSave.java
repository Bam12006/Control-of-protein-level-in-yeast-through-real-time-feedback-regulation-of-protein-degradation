/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmicromanager;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ImageProcessor;
import mmcorej.CMMCore;
import org.micromanager.utils.ImageUtils;

/**
 *
 * @author Bahar
 */
public class SnapAndSave {
    public ImageProcessor SnapSave(String path, int numerator, int folderNumber, CMMCore core){
        
        String imgNumber = Integer.toString(numerator);
        String iValue = Integer.toString(folderNumber);
        String prePathFP = path + iValue +"/" + imgNumber;
        String prePathBF = path + iValue +"/BF/" + imgNumber;
        
        ImageProcessor cellFP = ImageUtils.makeProcessor(core);;
        
        try{
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//             Snap and Save images
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            //capture an image from fluorescence protein using the camera
            core.setConfig("Control", "40X Green TxRed");
            core.snapImage();
            //make an array of image pixel values 
            short[] specimen = (short[])core.getImage();
            cellFP = ImageUtils.makeProcessor(core, specimen); //make image processor for fluorescence protein
            // save pre-frame to TIF
            ImagePlus impFP = new ImagePlus(prePathFP, cellFP);
            FileSaver fsFP = new FileSaver(impFP);
            fsFP.saveAsTiff(prePathFP + ".tif");
            
            //capture an image from the whole cell using the camera
            core.setConfig("Control", "40X BF");
            core.snapImage();           
            //make an array of image pixel values 
            short[] specimen2 = (short[])core.getImage();
            ImageProcessor cellBF = ImageUtils.makeProcessor(core, specimen2); //make image processor for bright feild (whole cell)
            // save pre-frame to TIF
            ImagePlus impBF = new ImagePlus(prePathBF, cellBF);
            FileSaver fsBF = new FileSaver(impBF);
            fsBF.saveAsTiff(prePathBF + ".tif");
            
            
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        
        return cellFP;
    }   
}
