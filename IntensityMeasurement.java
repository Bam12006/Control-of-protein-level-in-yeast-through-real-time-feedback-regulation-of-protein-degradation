/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmicromanager;


import ij.process.ImageProcessor;
import mmcorej.CMMCore;

/**
 *
 * @author Bahar
 */
public class IntensityMeasurement {
    public double calculate(CMMCore core, ImageProcessor cell, int[][] ROI){
        
        double targetIntensity = 0;
        
        ImageProcessor target = cell.duplicate();
        ImageProcessor background1 = cell.duplicate();
        ImageProcessor background2 = cell.duplicate();
        ImageProcessor background3 = cell.duplicate();
        
        try{            
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//             Calculate Fluorescence Intensity of a Cell or a Colony of Cells (within a defined region of interest)
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------            
            //set region of interest for region1
            target.setRoi(ROI[0][0], ROI[0][1], ROI[0][2], ROI[0][3]);
            double targetMean = target.getStatistics().mean;
            
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------        
//              calculate Integrated Density of background for target cell area
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            //set region of interest for 1st background
            background1.setRoi(ROI[1][0], ROI[1][1], ROI[1][2], ROI[1][3]);
            double backgroundMean1 = background1.getStatistics().mean;
            
            //set region of interest for 2nd background
            background2.setRoi(ROI[2][0], ROI[2][1], ROI[2][2], ROI[2][3]);
            double backgroundMean2 = background2.getStatistics().mean;
            
            //set region of interest for background
            background3.setRoi(ROI[3][0], ROI[3][1], ROI[3][2], ROI[3][3]);
            double backgroundMean3 = background3.getStatistics().mean;
            
            double backgroundMean = (backgroundMean1 + backgroundMean2 + backgroundMean3)/3;
            
            targetIntensity = targetMean - backgroundMean;
            
            
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        
        return targetIntensity;
    }
    
}
