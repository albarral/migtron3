/*
 *  Copyright (C) 2019 by Migtron Robotics   
 *  albarral@migtron.com
 */
package com.migtron.tron.math.color;

import com.migtron.tron.math.Vec3i;

/**
 * Utility class for color definitions
 * @author albarral
 */
public class Colors
{
    public enum eColor{
        eCOLOR_BLACK, 
        eCOLOR_WHITE,
        eCOLOR_GREY,
        eCOLOR_RED,
        eCOLOR_YELLOW,
        eCOLOR_ORANGE,
        eCOLOR_GREEN,
        eCOLOR_CYAN,
        eCOLOR_BLUE,
        eCOLOR_PURPLE
    }
    
    // get RGB components (in [0, 255] range) of given eColor
    public static Vec3i getRGB(eColor ecolor)
    {
        Vec3i color; 
        switch (ecolor)
        {
            case eCOLOR_BLACK:
                color = new Vec3i(0, 0, 0);
                break;
            case eCOLOR_WHITE:
                color = new Vec3i(255, 255, 255);
                break;                
            case eCOLOR_GREY:
                color = new Vec3i(128, 128, 128);
                break;                
            case eCOLOR_RED:
                color = new Vec3i(255, 0, 0);
                break;
            case eCOLOR_YELLOW:
                color = new Vec3i(255, 255, 0);
                break;
            case eCOLOR_ORANGE:
                color = new Vec3i(255, 128, 0);
                break;
            case eCOLOR_GREEN:
                color = new Vec3i(0, 255, 0);
                break;
            case eCOLOR_CYAN:
                color = new Vec3i(0, 255, 255);
                break;
            case eCOLOR_BLUE:
                color = new Vec3i(0, 0, 255);
                break;
            case eCOLOR_PURPLE:
                color = new Vec3i(255, 0, 255);
                break;
            default: 
                // default is black
                color = new Vec3i(0, 0, 0);
        }
        return color;        
    }
}
