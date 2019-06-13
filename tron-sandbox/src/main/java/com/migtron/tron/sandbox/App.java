package com.migtron.tron.sandbox;

import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.ColourSpace;
import org.openimaj.image.colour.RGBColour;
//import org.openimaj.image.processing.convolution.FGaussianConvolve;
import org.openimaj.image.typography.hershey.HersheyFont;
import org.openimaj.image.pixel.Pixel;
import org.openimaj.math.geometry.shape.Rectangle;

/**
 * OpenIMAJ Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) 
    {    
        int w = 600;
        int h = 100;
        
        MBFImage image = new MBFImage(w, h, ColourSpace.RGB);
        
        image.fill(RGBColour.WHITE);        		        
        image.drawText("Hello World", 10, 60, HersheyFont.CURSIVE, 50, RGBColour.BLACK);
        //Apply a Gaussian blur
        //image.processInplace(new FGaussianConvolve(2f));
        
        Pixel p1 = new Pixel(10, 10);
        Pixel p2 = new Pixel(50, 50);        
        Rectangle window = new Rectangle(p1, p2);
        
        MBFImage image2 = image.extractROI(window);

        DisplayUtilities.display(image);
        DisplayUtilities.display(image2);
    }
}
