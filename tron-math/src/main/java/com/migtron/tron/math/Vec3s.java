/*
 *  Copyright (C) 2019 by Migtron Robotics   
 *  albarral@migtron.com
 */
package com.migtron.tron.math;

import java.util.Arrays;


/**
 * Class to represent a 3D vector with short precision.
 * Direct access granted to vector components.
 * @author albarral
 */
public class Vec3s implements Cloneable
{
    public static final int SIZE = 3;
    public short[] data;
    
    public Vec3s(short x, short y, short z)
    {
        data = new short[SIZE];
        data[0] = x;
        data[1] = y;
        data[2] = z;
    }

    public Vec3s()
    {
        data = new short[SIZE];
    }
    
    public Vec3s(Vec3s vector2)
    {
        this(vector2.getX(), 
                vector2.getY(), 
                vector2.getZ());
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException 
    {
        Vec3s cloned = (Vec3s)super.clone();
        cloned.data = this.data.clone();
        return cloned;
    }
    
    @Override
    public boolean equals(Object o) 
    {
        if (this == o) 
            return true;
        
        if (o == null || getClass() != o.getClass()) 
            return false;
        
        Vec3s vec3s = (Vec3s)o;
        return Arrays.equals(data, vec3s.data);
    }
    
    public short getX() {return data[0];};
    public short getY() {return data[1];};
    public short getZ() {return data[2];};
    public void setX(short value) {data[0] = value;};
    public void setY(short value) {data[1] = value;};
    public void setZ(short value) {data[2] = value;};
    
    public void set(Vec3s vector2)
    {
        data[0] = vector2.getX();
        data[1] = vector2.getY();
        data[2] = vector2.getZ();        
    }
    
    // get euclidean square distance to a short vector
    public float getEuclideanSquareDistance(Vec3s vector2)
    {
        float x = data[0] - vector2.data[0];
        float y = data[1] - vector2.data[1];
        float z = data[2] - vector2.data[2];

        return (x*x + y*y + z*z);        
    }
    
    // get euclidean distance to a short vector
    public float getEuclideanDistance(Vec3s vector2)
    {
        return (float)Math.sqrt(getEuclideanSquareDistance(vector2));
    }
    
    @Override
    public String toString()
    {
        String desc = "(" + String.valueOf(data[0]) + ", " + String.valueOf(data[1]) + ", " + String.valueOf(data[2]) + ")";
        return desc;
    }    
}
