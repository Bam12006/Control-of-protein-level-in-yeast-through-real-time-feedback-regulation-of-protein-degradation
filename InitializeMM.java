/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmicromanager;

import mmcorej.CMMCore;
import org.micromanager.api.MultiStagePosition;
import org.micromanager.api.PositionList;
import ij.IJ;
/**
 *
 * @author Bahar
 */
public class InitializeMM {
    
    public CMMCore mmc() throws Exception
    {
        CMMCore core = new CMMCore();  
        core.loadSystemConfiguration("C:/Program Files/Micro-Manager-1.4/MMConfig_150701-PE-SerialPortAdded.cfg");        
        return core;
    }
    
    public PositionList Position(){
        
        PositionList positionList = new PositionList();
        MultiStagePosition msp = new MultiStagePosition("XYStage",34898  , 25171, "TIZDrive", 6006.6001); //update 1
        msp.setLabel("0th Position");
        msp.setProperty("Slide","Number 0");
        positionList.addPosition(msp);
        
        msp = new MultiStagePosition("XYStage",15738 , 6639, "TIZDrive",  6333.2751); //update 1
        msp.setLabel("1st Position");
        msp.setProperty("Slide", "Number 1");
        positionList.addPosition(msp);
        
        msp = new MultiStagePosition("XYStage",34344 , 6346, "TIZDrive", 6379.9751); //update 1
        msp.setLabel("2nd Position");
        msp.setProperty("Slide", "Number 2");
        positionList.addPosition(msp);
        
//        msp = new MultiStagePosition("XYStage",367989 , 16201, "TIZDrive", 5644.9251); //update 1
//        msp.setLabel("3rd Position");
//        msp.setProperty("Slide", "Number 3");
//        positionList.addPosition(msp);
        
        return positionList;
    }
    
    public double[] offset(){
        
        double offSet0 = 485.825;
        double offSet1 = 484.85;
        double offSet2 = 487.55;                    
//        double offSet3 = 448.1;

        
        double[] offSet = {offSet0, offSet1, offSet2 /*,offSet3 , offSet4, offSet5, offSet6, offSet7,offSet8, offSet9, offSet10, offSet11, offSet12, offSet13, offSet14, offSet15,offSet16 */};
        
        return offSet;
    }
    
    public int[][][] roi(){
        
        int[] rOI1 = {708,360,119,93};
        int[] rOI1Background1 = {306,355,17,22};
        int[] rOI1Background2 = {512,191,16,14};
        int[] rOI1Background3 = {372,551,14,17};
        int[][] rOICell1 = {rOI1, rOI1Background1, rOI1Background2, rOI1Background3};
        
        int[] rOI2 = {644,294,115,91};
//        int[] rOI2Background1 = {168, 263, 105, 152};
//        int[] rOI2Background2 = {792, 935, 101, 113};
//        int[] rOI2Background3 = {141, 641, 130, 82};
        int[][] rOICell2 = {rOI2, rOI1Background1, rOI1Background2, rOI1Background3};
        
        int[] rOI3 = {426,321,103,97};
//        int[] rOI3Background1 = {168, 263, 105, 152};
//        int[] rOI3Background2 = {792, 935, 101, 113};
//        int[] rOI3Background3 = {141, 641, 130, 82};
        int[][] rOICell3 = {rOI3, rOI1Background1, rOI1Background2, rOI1Background3};
        
        int[] rOI4 = {630,599,102,89};
//        int[] rOI4Background1 = {168, 263, 105, 152};
//        int[] rOI4Background2 = {792, 935, 101, 113};
//        int[] rOI4Background3 = {141, 641, 130, 82};
        int[][] rOICell4 = {rOI4, rOI1Background1, rOI1Background2, rOI1Background3};
        
        int[] rOI5 = {216,194,132,135};
//        int[] rOI5Background1 = {168, 263, 105, 152};
//        int[] rOI5Background2 = {792, 935, 101, 113};
//        int[] rOI5Background3 = {141, 641, 130, 82};
        int[][] rOICell5 = {rOI5, rOI1Background1, rOI1Background2, rOI1Background3};
        
        int[] rOI6 = {71,465,79,78};
//        int[] rOI6Background1 = {168, 263, 105, 152};
//        int[] rOI6Background2 = {792, 935, 101, 113};
//        int[] rOI6Background3 = {141, 641, 130, 82};
        int[][] rOICell6 = {rOI6, rOI1Background1, rOI1Background2, rOI1Background3};
//        
        int[] rOI7 = {201,371,119,95};
//        int[] rOI7Background1 = {168, 263, 105, 152};
//        int[] rOI7Background2 = {792, 935, 101, 113};
//        int[] rOI7Background3 = {141, 641, 130, 82};
        int[][] rOICell7 = {rOI7, rOI1Background1, rOI1Background2, rOI1Background3};
//        
        int[] rOI8 = {257,514,130,105};
//        int[] rOI8Background1 = {168, 263, 105, 152};
//        int[] rOI8Background2 = {792, 935, 101, 113};
//        int[] rOI8Background3 = {141, 641, 130, 82};
        int[][] rOICell8 = {rOI8, rOI1Background1, rOI1Background2, rOI1Background3};
//        
        int[] rOI9 = {445,447,100,101};
//        int[] rOI9Background1 = {168, 263, 105, 152};
//        int[] rOI9Background2 = {792, 935, 101, 113};
//        int[] rOI9Background3 = {141, 641, 130, 82};
        int[][] rOICell9 = {rOI9, rOI1Background1, rOI1Background2, rOI1Background3};
//        
        int[] rOI10 = {610,535,126,126};
//        int[] rOI10Background1 = {168, 263, 105, 152};
//        int[] rOI10Background2 = {792, 935, 101, 113};
//        int[] rOI10Background3 = {141, 641, 130, 82};
        int[][] rOICell10 = {rOI10, rOI1Background1, rOI1Background2, rOI1Background3};
//        
//        int[] rOI11 = {641, 608, 121, 110};
////        int[] rOI11Background1 = {168, 263, 105, 152};
////        int[] rOI11Background2 = {792, 935, 101, 113};
////        int[] rOI11Background3 = {141, 641, 130, 82};
//        int[][] rOICell11 = {rOI11, rOI1Background1, rOI1Background2, rOI1Background3};
        
//        int[] rOI12 = {212, 360, 114, 119};
////        int[] rOI12Background1 = {168, 263, 105, 152};
////        int[] rOI12Background2 = {792, 935, 101, 113};
////        int[] rOI12Background3 = {141, 641, 130, 82};
//        int[][] rOICell12 = {rOI12, rOI1Background1, rOI1Background2, rOI1Background3};
//        
//        int[] rOI13 = {475, 399, 148, 208};
////        int[] rOI13Background1 = {168, 263, 105, 152};
////        int[] rOI13Background2 = {792, 935, 101, 113};
////        int[] rOI13Background3 = {141, 641, 130, 82};
//        int[][] rOICell13 = {rOI13, rOI1Background1, rOI1Background2, rOI1Background3};
//        
//        int[] rOI14 = {388, 624, 99, 198};
////        int[] rOI14Background1 = {168, 263, 105, 152};
////        int[] rOI14Background2 = {792, 935, 101, 113};
////        int[] rOI14Background3 = {141, 641, 130, 82};
//        int[][] rOICell14 = {rOI14, rOI1Background1, rOI1Background2, rOI1Background3};
        
        
        
        int[][][] roi = {rOICell1, rOICell2, rOICell3, rOICell4,rOICell5, rOICell6, rOICell7, rOICell8, rOICell9,rOICell10};
        
        return roi;
    }
}
