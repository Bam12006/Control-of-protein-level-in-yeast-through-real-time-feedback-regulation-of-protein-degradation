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

import mmcorej.CMMCore;

public class Arduino {
    public int fadeLED(double refValue ,double fadingValue, CMMCore setValue)
    {
        //double ref = (refValue - 1)*1.5; //kProportional = 1.5 which is multiplied here.               
        //fadingValue = fadingValue - 0.15;
        if (fadingValue >= -0.15 && fadingValue <= 0.15)
        {
            fadingValue = ((fadingValue-0.15) * 10)/0.3;
        } else if (fadingValue < -0.15){
            fadingValue = 10;
        }
        
        else if (fadingValue > 0.15){
            fadingValue = 0;
        }
        int value = (int) fadingValue;
        value = Math.abs(value);
        String fadeValue = Integer.toString(value);
        System.out.println("Arduino  =  " + fadeValue);
        try{
            setValue.setSerialPortCommand("COM5", "9999," + fadeValue + ",1010, 0, 1111, 0", "\n");
            
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(1);   
        }
        return value;
    }
}
