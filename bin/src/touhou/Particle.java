package src.touhou;

import java.awt.image.BufferedImage;

/**
 *
 * @author Computer
 */
public class Particle {
    
    public boolean active;
    public double x;
    public double y;
    private double speed;
    public double direction;
    public BufferedImage img;
    
    public Particle(double x, double y, double speed, double direction, BufferedImage img )
    {
        this.x=x;
        this.y=y;
        this.speed=speed;
        this.direction=direction;
        this.img=img;
        this.active=true;
    }
    
    public void update()
    {
        //System.out.println("DIRE"+Math.toDegrees(direction));
        //System.out.println("direction: "+(Math.cos(Math.toRadians(direction))));
        
        x=(x+(speed*(Math.cos(direction))));
        y=(y-(speed*(Math.sin(direction))));
        
        if(x<0||x>500||y<0||y>700)
        {
            active=false;
        }

    }
    
}
