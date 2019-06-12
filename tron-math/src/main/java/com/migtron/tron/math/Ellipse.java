/*
 *  Copyright (C) 2019 by Migtron Robotics   
 *  albarral@migtron.com
 */
package com.migtron.tron.math;

import java.awt.geom.Point2D.Float;
import java.awt.Point;

/**
* This class represents a 2D ellipse.
* An ellipse has a centroid and a covariance vector.
* Main axes and orientation can be computed from the ellipse covariance.
* @author albarral
 */

public class Ellipse implements Cloneable
{
    private Float pos;               // centroid (x,y)
    private Vec3f covs;             // cxx, cyy, cxy	
    // main axes (automatically computed)
    private float width;            // main axis radial size
    private float height;           // secondary axis radial size
    private float angle;            // angle of main axis (degrees, counter clockwise direction, [-90, 90])
    
    public Ellipse(Float pos, Vec3f covs)
    {
        this.pos = new Float(pos.x, pos.y);
        this.covs = (Vec3f)covs.clone();
        // on every covariance change, the main axes are recomputed
        updateMainAxes();
    }    

    public Ellipse(Point pos, Vec3f covs)
    {
        this(new Float(pos.x, pos.y), covs);
    }    

    public Ellipse()
    {
        this(new Float(0, 0), new Vec3f(0, 0, 0));
    }
    
    @Override
    public Object clone()
    {
        try {
            // all members automatically copied
            // then class members cloned for deep copy
            Ellipse cloned = (Ellipse)super.clone();
            cloned.pos = (Float)pos.clone();
            cloned.covs = (Vec3f)covs.clone();
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
        
        Ellipse ellipse = (Ellipse)o;
        return (pos.equals(ellipse.pos) &&
                covs.equals(ellipse.covs) && 
                width == ellipse.width && 
                height == ellipse.height && 
                angle == ellipse.angle);
    }
        
    public Float getPosition() {return pos;};
    public Point getPositionPoint() {return Coordinates.float2Point(pos);};
    public Vec3f getCovariances() {return covs;};
    public float getWidth() {return width;};
    public float getHeight() {return height;};
    public float getAngle() {return angle;};
    
    public void setPosition(Float pos)
    {
        this.pos.setLocation(pos);
    }

    public void setPosition(Point pos)
    {
        this.pos.setLocation(pos);
    }
    
    public void setCovariances(Vec3f covs) 
    {
        this.covs.set(covs);
        // on every covariance change, the main axes are recomputed
        updateMainAxes();
    }

    // automatic computation of main axes from the ellipse covariances
    // The resulting angle (in counter clockwise direction) is always inside [-90, 90]
    private void updateMainAxes()
    {
        float cxx = covs.getX();
        float cyy = covs.getY();
        float cxy = covs.getZ();
        float a = cxx - cyy;
        float b = 2*cxy;
        float h = (float)Math.sqrt(a*a + b*b);

        width = (float)Math.sqrt((cxx + cyy + h)/2);
        
        float aux = (cxx + cyy - h);
        if (aux > 0.0f)
            height = (float)Math.sqrt((cxx + cyy - h)/2);
        else
            height = 0.0f;

        float radians = (float)Math.atan2(-b, a)/2;   // sign of y changed because mask Y axis faces down
        angle = (float)Math.toDegrees(radians);       
    }
        
    public float computeShapeFactor()
    {
        if (height != 0.0f)
            return width / height;
        // safety value when zero height
        else
            return 1000f;
    }

    // merge given ellipse into this ellipse (assuming both have the same weight)
    public void merge(Ellipse ellipse)
    {
        merge(ellipse, 0.5f, 0.5f);
    }

    // merge given ellipse into this ellipse combining their covariances and centroids in a ponderated way (using weights) 
    // w1 + w2 must be 1
    public void merge(Ellipse ellipse, float w1, float w2)
    {
        Float pos2 = ellipse.getPosition();
        Vec3f covs2 = ellipse.getCovariances();

        float cx1 = covs.getX();
        float cy1 = covs.getY();
        float cxy1 = covs.getZ();

        float cx2 = covs2.getX();
        float cy2 = covs2.getY();
        float cxy2 = covs2.getZ();

        float w12 = w1*w2;        
        float dx = pos.x - pos2.x;
        float dy = pos.y - pos2.y;

        // new position
        pos.x = w1*pos.x + w2*pos2.x;
        pos.y = w1*pos.y + w2*pos2.y;

        // new covariances 
        Vec3f covs = new Vec3f(w1*cx1 + w2*cx2 + w12*dx*dx,
                w1*cy1 + w2*cy2 + w12*dy*dy,
                w1*cxy1 + w2*cxy2 + w12*dx*dy);
        // main axes are internally recomputed
        setCovariances(covs);
    }
    
    public void clear()
    {
        pos.x = pos.y = 0f;
        covs.data[0] = covs.data[1] = covs.data[2] = 0f;
        width = height = angle = 0f;
    }
    
    @Override
    public String toString()
    {
        String desc = "Ellipse [pos = (" + String.valueOf(pos.x) + ", " + String.valueOf(pos.y) + ")" +         
                " (w, h, ang) = (" + String.valueOf(width) + ", " + String.valueOf(height) + ", " + String.valueOf(angle) + ")" +
                " covs = " + covs.toString() + "]";
        return desc;
    }    
}
