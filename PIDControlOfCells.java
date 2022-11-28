/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmicromanager;

import ij.process.ImageProcessor;
import mmcorej.CMMCore;
import org.micromanager.api.MultiStagePosition;
import org.micromanager.api.PositionList;

/**
 *
 * @author Bahareh Mahrou
 */
public class PIDControlOfCells {
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // define a desired path in the computer here to save images
        String pathToSave = "D:/data/Bahareh/red induction test/PID Control/2020 experiments/02-29-2020-2/";
        
        int targetPos = 2;
        int redControlPos = 1;
        int darkControlPos = 0;
//        int farRedControlPos = 0;
        int numOfROI = 5;
        System.out.println("numOfROI = " + numOfROI);
        
        
        double kPropotional = 6;
        double kDerivative = 0.05;
        double kIntegral = 0.05;
        double signalAmplitude = 0.25;
        double period = 200; //min
        int experimentTime = 7*60-20; //min
        int samplingTime = 2; //min

        
        
        int iteration = experimentTime/samplingTime;
        double[] intensity = new double[iteration];
        double[] nIntensity = new double[iteration];
        int[] arduinoValue = new int[iteration];
        double newError;
        double oldError = 0;
        double integralError = 0;
        
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//            Creating Objects
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------   
        WriteToExcel write = new WriteToExcel();
        CMMCore core = new InitializeMM().mmc();
        PositionList stagePosition = new InitializeMM().Position();
        double[] offSet = new InitializeMM().offset();
        int[][][] targetROI = new InitializeMM().roi();
        PID pid = new PID();
        Arduino arduino = new Arduino();
        ReferenceSignal refSignal = new ReferenceSignal();
//        double[] reference = refSignal.cosFunction(signalAmplitude, period, experimentTime, samplingTime);
        double[] reference = refSignal.sinFunction(signalAmplitude, period, experimentTime, samplingTime);
        
//        for (int i = 0; i<iteration; i++)
//        {
//            double ref = reference[i];
//            System.out.println(ref);
//        }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//            Apply PID and Calculate Input & Output Intensities
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------           
        
        for (int i = 0; i<iteration; i++)
        {
            double mIntensity = 0;
            System.out.println("t =  " + i + "  min");
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //            Calculate the Intensity of Proteins (Outputs)
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------                             
//            MultiStagePosition.goToPosition(stagePosition.getPosition(farRedControlPos), core);
//            core.setAutoFocusOffset(offSet[farRedControlPos]);
//            ImageProcessor farRedControlImage = new SnapAndSave().SnapSave(pathToSave, i, farRedControlPos, core);
            
            MultiStagePosition.goToPosition(stagePosition.getPosition(redControlPos), core);
            core.setAutoFocusOffset(offSet[redControlPos]);
            ImageProcessor redControlImage = new SnapAndSave().SnapSave(pathToSave, i, redControlPos, core);
            
            MultiStagePosition.goToPosition(stagePosition.getPosition(darkControlPos), core);
            core.setAutoFocusOffset(offSet[darkControlPos]);
            ImageProcessor controlImage = new SnapAndSave().SnapSave(pathToSave, i, darkControlPos, core);
            
            MultiStagePosition.goToPosition(stagePosition.getPosition(targetPos), core);
            core.setAutoFocusOffset(offSet[targetPos]);
            ImageProcessor targetImage = new SnapAndSave().SnapSave(pathToSave, i, targetPos, core);
            
            for (int n = 0; n < numOfROI; n++)
            {
                mIntensity = new IntensityMeasurement().calculate(core, targetImage, targetROI[n]);
                intensity[i] = intensity[i] + mIntensity ;
            }
            intensity[i] = intensity[i]/numOfROI;
            nIntensity[i] = intensity[i]/intensity[0];
            System.out.println("intensity = " + intensity[i]);
            System.out.println("nIntensity = " + nIntensity[i]);
            System.out.println("reference = " + reference[i]);
            
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //            Calculate the Intensity of Blue LED Light (Input)
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------             
            newError = reference[i] - nIntensity[i];
            double[] blueInputIntensity = pid.lightIntensity(oldError, newError, integralError, kPropotional, kIntegral, kDerivative);
            
            arduinoValue[i] = arduino.fadeLED(reference[i] , blueInputIntensity[0], core);
            
            if (arduinoValue[i]==0 & i<50)
            {
                core.setConfig("Control", "40X Red");
                core.setSerialPortCommand("COM5", "9999,0 ,1010, 255, 1111, 0", "\n");
                Thread.sleep(10000);
                MultiStagePosition.goToPosition(stagePosition.getPosition(redControlPos), core);
                core.setAutoFocusOffset(offSet[redControlPos]);
                Thread.sleep(10000);
                core.setSerialPortCommand("COM5", "9999, 0, 1010, 0, 1111, 0", "\n");
                Thread.sleep(samplingTime*60*1000-32000);
            }
            else
            {
                Thread.sleep(samplingTime*60*1000-15000);
                core.setSerialPortCommand("COM5", "9999, 0, 1010, 0, 1111, 0", "\n");
                Thread.sleep(5000);
            }
            
            

            oldError = newError;
            integralError = blueInputIntensity[1];            
        }
        
        write.write(pathToSave, iteration, arduinoValue, intensity, nIntensity, reference); 
    }
}


