/*
 *  Copyright (C) 2019 by Migtron Robotics   
 *  albarral@migtron.com
 */
package com.migtron.tron.math;

/**
  * Utility class to perform mathematical operations on angles
* @author albarral
 */
public class Angle
{
   // correct the given angle to stay in the [0,360) range
    public static float inLimits(float angle)
    {   
        // if too big angle, reduce it below 360
        if (angle >= 360.0f)
        {
            while (angle >= 360.0f)
                angle -= 360.0f;
        }
        // if negative angle, increase it above 0
        else if (angle < 0.0f)
        {
            while (angle < 0.0f)
                angle += 360.0f;
        }

        return angle;    
    }

    // correct the given angle difference taking into account the cyclic nature of angles
    public static float cyclicDifference(float angleDif)
    {
        if (Math.abs(angleDif)<180.0)
            return angleDif;
        else
        {
            return (angleDif>0.0 ? angleDif-360.0f : angleDif+360.0f);
        }    
    }        
}
