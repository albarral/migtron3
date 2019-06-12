/*
 *  Copyright (C) 2019 by Migtron Robotics   
 *  albarral@migtron.com
 */
package com.migtron.tron.math;

import java.util.Arrays;

/**
 * Class to represent a 3D vector with float precision.
 * Direct access granted to vector components.
 * @author albarral
 */
public class Vec3f implements Cloneable
{
    public static final int SIZE = 3;
    public float[] data;
    
    public Vec3f(float x, float y, float z)
    {
        data = new float[SIZE];
        data[0] = x;
        data[1] = y;
        data[2] = z;
    }

    public Vec3f()
    {
        data = new float[SIZE];
    }    

    // create from integer vector
    public Vec3f(Vec3i vector2)
    {
        this(vector2.getX(), 
                vector2.getY(), 
                vector2.getZ());
    }

    @Override
    public Object clone() // throws CloneNotSupportedException 
    {
        try {
            Vec3f cloned = (Vec3f)super.clone();
            cloned.data = this.data.clone();
            return cloned;
        }
        catch (CloneNotSupportedException e) {
         throw new AssertionError();
      }                
    }

    @Override
    public boolean equals(Object o) 
    {
        if (this == o) 
            return true;
        
        if (o == null || getClass() != o.getClass()) 
            return false;
        
        Vec3f vec3f = (Vec3f)o;
        return Arrays.equals(data, vec3f.data);
    }

    public float getX() {return data[0];};
    public float getY() {return data[1];};
    public float getZ() {return data[2];};
    public void setX(float value) {data[0] = value;};
    public void setY(float value) {data[1] = value;};
    public void setZ(float value) {data[2] = value;};
    
    public void set(Vec3f vector2)
    {
        data[0] = vector2.getX();
        data[1] = vector2.getY();
        data[2] = vector2.getZ();        
    }
    
    // get euclidean square distance to a float vector
    public float getEuclideanSquareDistance(Vec3f vector2)
    {
        float x = data[0] - vector2.data[0];
        float y = data[1] - vector2.data[1];
        float z = data[2] - vector2.data[2];

        return (x*x + y*y + z*z);        
    }

    // get euclidean square distance to an integer vector
    public float getEuclideanSquareDistance(Vec3i vector2)
    {
        float x = data[0] - vector2.data[0];
        float y = data[1] - vector2.data[1];
        float z = data[2] - vector2.data[2];

        return (x*x + y*y + z*z);        
    }

    // get euclidean square distance to a short vector
    public float getEuclideanSquareDistance(Vec3s vector2)
    {
        float x = data[0] - vector2.data[0];
        float y = data[1] - vector2.data[1];
        float z = data[2] - vector2.data[2];

        return (x*x + y*y + z*z);        
    }

    // get euclidean distance to a float vector
    public float getEuclideanDistance(Vec3f vector2)
    {
        return (float)Math.sqrt(getEuclideanSquareDistance(vector2));
    }

    // get euclidean distance to an integer vector
    public float getEuclideanDistance(Vec3i vector2)
    {
        return (float)Math.sqrt(getEuclideanSquareDistance(vector2));
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
