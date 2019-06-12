/*
 *  Copyright (C) 2019 by Migtron Robotics   
 *  albarral@migtron.com
 */
package com.migtron.tron.util;

/**
 * Utility class to manage operating system calls
 * @author albarral
 */
public class Environment 
{
    // obtains user's home path
    public static String getHomePath()
    {
        return System.getenv("HOME");
    }    
}
