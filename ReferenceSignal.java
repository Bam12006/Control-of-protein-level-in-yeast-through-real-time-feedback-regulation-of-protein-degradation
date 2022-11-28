/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmicromanager;

/**
 *
 * @author Bahar
 */
public class ReferenceSignal {
    public double[] cosFunction(double amplitude, double period, int wholeTime, int samplingTime){
        int num = wholeTime/samplingTime;
        double[] signal = new double[num];
        double frequency = 1/period;
        for (int n = 0; n < num; n++) 
        {
            double pi = Math.PI;
            double x = 2*pi*frequency*samplingTime*n;  
            double cos = Math.cos(x);
            signal[n] = amplitude * cos + 0.75;
        }
        
        
        return signal;
    }
    
    public double[] sinFunction(double amplitude, double period, int wholeTime, int samplingTime){
        int num = wholeTime/samplingTime;
        double[] signal = new double[num];
        double frequency = 1/period;
        for (int n = 0; n < num; n++) 
        {
            double pi = Math.PI;
            double x = 2*pi*frequency*samplingTime*n- (pi/2);  
            double sin = Math.sin(x);
            signal[n] = amplitude * sin + 1.25;
        }       
        return signal;
    }
    
    public double[] stepFunction(double amplitude, double period, int wholeTime, int samplingTime){
        int num = wholeTime/samplingTime;
        double[] signal = new double[num];
        int step = num/3;
        for (int n = 0; n < 1*step; n++) 
        {
            signal[n] = 1;
        }
        
        for (int n = step; n < 2*step; n++) 
        {
            signal[n] = 1.5;
        }
        
        for (int n = 2*step; n < 3*step; n++) 
        {
            signal[n] = 1;
        }   
        return signal;
    }
    
    public double[] sinStepFunction(double amplitude, double period, int wholeTime, int samplingTime){
        int num = wholeTime/samplingTime;
        double[] signal = new double[num];
        int step = num/4;
        double frequency = 1/period;

        for (int n = 0; n < num/2; n++) 
        {
            double pi = Math.PI;
            double x = 2*pi*frequency*samplingTime*n- (pi/2);  
            double sin = Math.sin(x);
            signal[n] = amplitude * sin + 1.25;
        }

        for (int n = 2*step; n < 3*step; n++) 
        {
            signal[n] = 1.5;
        }
        
        for (int n = 3*step; n < 4*step; n++) 
        {
            signal[n] = 1;
        }       
        return signal;
    }
    
    
}
