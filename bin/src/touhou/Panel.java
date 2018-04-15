package touhou;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Computer
 */
public class Panel extends JPanel {
    
	int xOffset = 200;
	int yOffset = 200;
	int columnDist = 200;
	
	int strikeYPos = 850;
	int strikeHeight = 100;
	int buttonHeight = 100;
	int deleteHeight = 1200;
	
	String typeData = "";
	int typeDataYOffset = 60;
	int lastLineBreak = 0;
	
	int rate = 1000;
    
	int v = 5;
	
	
    boolean wPressed=false;
    boolean qPressed=false;
    boolean rPressed=false;
    boolean ePressed=false;
    boolean tPressed=false;
    boolean yPressed=false;
    boolean uPressed=false;
    
    Map<String , BufferedImage> bufferedImages;
    
    char[] row1Letters = {'e','n','e','i','l','e','n','f','e','i'};
    char[] row2Letters = {'t','d','c','t','s','g','t','d','t','s'};
    char[] row3Letters = {'a','h','w','a','u','y','a','h','u','a'};
    char[] row4Letters = {'o','m','p','o','r','o','m','b','o','r'};
    char[] row5Letters = {'v','q','k','j','v','x','k','j','v','z'};
    
    Column[] columns = { 
    					 new Column(1, 0 , row1Letters , rate ),
    					 new Column(2, 125 , row2Letters , rate ),
    					 new Column(3, 0 , row3Letters , rate ),
    					 new Column(4, 125 , row4Letters , rate ),
    					 new Column(5, 0 , row5Letters , rate ),
    					};
 

    BufferedImage pImg;
    BufferedImage img= getImage("LETER/A_small.png");
    BufferedImage hoop = getImage("LETER/hoop.png");
    
    public Panel(int width,int height)
    {
        
    	this.bufferedImages = initializeButtonImages();
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pMouseClicked(evt);
            }
        });
        
        
       
    }
    
    public static void main(String args []) throws InterruptedException
    {
        
       //initialize the buttonImages map
    	//initializeButtonImages();
    	
    	
    	
             
        Dimension windowSize=new Dimension(2000,1200);
        Panel p=new Panel(windowSize.width,windowSize.height);
           
        Frame frame=new Frame(p,2000,1200);
        //p.go();
       
        double t=0;
        int count=0;
        long start=System.currentTimeMillis();
        long now;
        
        
        while(true){
               
                p.repaint();
               
                Thread.sleep(25-(System.currentTimeMillis()-start));
                // Thread.sleep(18);
                 start=System.currentTimeMillis();
                
           
        }
       
        
    }
    
    
    
    
   
    @Override
    
    public void paint(Graphics g) throws ConcurrentModificationException
    {
        Graphics2D g2d = (Graphics2D) g;
        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       // g2d.setColor(Color.black);
        g2d.fillRect(0, 0, getSize().width, getSize().height);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN , 90));
        
        int i = 0;
      
        
        for(Column column : columns)
        {
        	 
        	//System.out.println(column.blocks.size());
        	 
        	
        	for(int j = 0 ; j < column.blocks.length ; j++)
        	{ 
        		//Block block = blockIter.next();
        		
        		column.blocks[j].pos += v;
        		
        		
        		
        		
        		if(++column.currentRate >= column.rate)
        		{
        			
        			if(column.lettersIndex == column.letters.length)
        			{
        				column.lettersIndex = 0;
        			}
        			
        			

        		}
        		
        		//if block is within strike zone register the hit
        		if((!column.blocks[j].disabled)&&(column.blocks[j].pos + buttonHeight >= strikeYPos + buttonHeight/2) && !(column.blocks[j].pos > strikeYPos + buttonHeight/2) ){
        			//System.out.println("registered key: "+column.blocks[j].letter);
        			if(column.columnId == 1 && qPressed && yPressed)
        			{
        				System.out.println(column.blocks[j].letter);
        				column.blocks[j].display = false;
        				column.blocks[j].disabled = true;
        				typeData += column.blocks[j].letter;
        			}
        			
        			if(column.columnId == 2 && wPressed && yPressed)
        			{
        				System.out.println(column.blocks[j].letter);
        				column.blocks[j].disabled = true;
        				column.blocks[j].display = false;

        				typeData += column.blocks[j].letter;
        			}
        			
        			if(column.columnId == 3 && ePressed && yPressed)
        			{
        				System.out.println(column.blocks[j].letter);
        				column.blocks[j].disabled = true;
        				column.blocks[j].display = false;

        				typeData += column.blocks[j].letter;
        			}
        			
        			if(column.columnId == 4 && rPressed && yPressed)
        			{
        				System.out.println(column.blocks[j].letter);
        				column.blocks[j].disabled = true;
        				column.blocks[j].display = false;

        				typeData += column.blocks[j].letter;
        			}
        			
        			if(column.columnId == 5 && tPressed && yPressed)
        			{
        				System.out.println(column.blocks[j].letter);
        				column.blocks[j].disabled = true;
        				column.blocks[j].display = false;

        				typeData += column.blocks[j].letter;
        			}
        			
        			
        		}
        		
        		//if block height is at delete zone remove the block
        		if(column.blocks[j].pos > (deleteHeight + buttonHeight/2))
        		{
        			column.blocks[j].pos = -500;
        			column.blocks[j].disabled = false;
        			column.blocks[j].display = true;
        			
        			column.blocks[j].letter = column.letters[column.lettersIndex];
        			
        			if(++column.lettersIndex == 10)
        			{
        				column.lettersIndex = 0;
        			}
        				
        				
        			
        			
        			//else draw the block
        		}else{
        			
        			if(column.blocks[j].display){
        				//g2d.drawRect(xOffset + i*columnDist , block.pos, buttonHeight , buttonHeight);
            			g2d.drawImage(bufferedImages.get(String.valueOf(column.blocks[j].letter)), xOffset + i*columnDist, column.blocks[j].pos, null);
            			//g2d.drawString(String.valueOf(block.letter), xOffset + i*columnDist,  block.pos);
        			}
        			
        			
        		}
        		
        		
        		
        	}
        	
        	
        	
        	
        	i++;
        }
        
    
        
        //draw the hoopy hoops
        for(int j = 0 ; j < 5 ; j++)
        {
        	g2d.drawImage(hoop, xOffset + (j*columnDist) -20, strikeYPos, null);
        }
        
        
        if(typeData.length() > 10)
        {
        	int remaining = typeData.length();
        	int index = 0;
        	int lines = 0;
        	
        	
        	while(remaining >= 10)
        	{
        		remaining -= 10;
        		
        		g2d.drawString(typeData.substring(index, index + 10), 1400, 400+typeDataYOffset *( lines ++));
        		index += 10;
        		
        	}
        	g2d.drawString(typeData.substring(index), 1400, 400+typeDataYOffset *( lines ++));
        	
        	//typeDataYOffset +=10;
        	
        }else{
            g2d.drawString(typeData, 1400, 400+typeDataYOffset);

        }
        
        
       
    }
    

  
   public void loadAnimation(String[] frameFileNames)
   {
	   
   }
    
    public static BufferedImage getImage(String filename)
    {
        BufferedImage img= null;
        
        try {
            img = ImageIO.read(new File(filename));
           } catch (IOException e) {
               System.out.println("couldn't read image");
           }
        return img;
    }
    
    public void animate(BufferedImage img) throws InterruptedException
    {
        int width=img.getWidth();
        int height=img.getHeight();
        int slides=27;
        int hSlides=7;
        int vSlides=4;
        
        int x=0;
        int y=0;
        int hToDraw;
        while(true)
        {
           for(int i=0;i<4;i++)
           {
               if(i==vSlides-1){
                   hToDraw=hSlides-((hSlides*vSlides)-slides);
               }else{
                   hToDraw=hSlides;
               }
                   
               
               for(int j=0;j<hToDraw;j++)
               {
                   drawImage(img.getSubimage(j*(width/hSlides), i*(height/vSlides), (width/hSlides), (height/vSlides)));
                    Thread.sleep(100);
               }
           }
            
        }
       
    }
    
   
    
    public void pMouseClicked(java.awt.event.MouseEvent evt)
    {
         System.out.println("HURR");
     };
    
    public void pKeyPressed(java.awt.event.KeyEvent evt) 
    {                                
         
         //System.out.println(evt.getKeyCode());
         if(evt.getKeyCode()==69&&!ePressed)
            ePressed=true;
         if(evt.getKeyCode()==81&&!qPressed)
            qPressed=true;
         if(evt.getKeyCode()==82&&!rPressed)
            rPressed=true;
         if(evt.getKeyCode()==87&&!wPressed)
            wPressed=true;
         if(evt.getKeyCode()==84&&!tPressed)
             tPressed=true;
         if(evt.getKeyCode()==89&&!yPressed)
             yPressed=true;
         if(evt.getKeyCode()==85&&!uPressed)
             uPressed=true;
         
         
    }    
    
    public void pKeyReleased(java.awt.event.KeyEvent evt) 
    {                                
      
    	 //System.out.println(evt.getKeyCode());
         if(evt.getKeyCode()==69&&ePressed)
             ePressed=false;
         if(evt.getKeyCode()==81&&qPressed)
             qPressed=false;
         if(evt.getKeyCode()==82&&rPressed)
             rPressed=false;
         if(evt.getKeyCode()==87&&wPressed)
             wPressed=false;
         if(evt.getKeyCode()==84&&tPressed)
             tPressed=false;
         if(evt.getKeyCode()==89&&yPressed)
             yPressed=false;
         if(evt.getKeyCode()==85&&uPressed)
             uPressed=false;
    } 

    public void drawImage(BufferedImage img)
    {
        this.img=img;
        repaint();
    }
    
    public static Map<String , BufferedImage> initializeButtonImages(){
    	
    	Map<String , BufferedImage> ret = new HashMap<String, BufferedImage>();
    	
    	ret.put("a", getImage("LETER/A_small.png"));
    	ret.put("b", getImage("LETER/B_small.png"));
    	ret.put("c", getImage("LETER/C_small.png"));
    	ret.put("d", getImage("LETER/D_small.png"));
    	ret.put("e", getImage("LETER/E_small.png"));
    	ret.put("f", getImage("LETER/F_small.png"));
    	ret.put("g", getImage("LETER/G_small.png"));
    	ret.put("h", getImage("LETER/H_small.png"));
    	ret.put("i", getImage("LETER/I_small.png"));
    	ret.put("j", getImage("LETER/J_small.png"));
    	ret.put("k", getImage("LETER/K_small.png"));
    	ret.put("l", getImage("LETER/L_small.png"));
    	ret.put("m", getImage("LETER/M_small.png"));
    	ret.put("n", getImage("LETER/N_small.png"));
    	ret.put("o", getImage("LETER/O_small.png"));
    	ret.put("p", getImage("LETER/P_small.png"));
    	ret.put("q", getImage("LETER/Qu_small.png"));
    	ret.put("r", getImage("LETER/R_small.png"));
    	ret.put("s", getImage("LETER/S_small.png"));
    	ret.put("t", getImage("LETER/T_small.png"));
    	ret.put("u", getImage("LETER/U_small.png"));
    	ret.put("v", getImage("LETER/V_small.png"));
    	ret.put("w", getImage("LETER/W_small.png"));
    	ret.put("x", getImage("LETER/X_small.png"));
    	ret.put("y", getImage("LETER/Y_small.png"));
    	ret.put("z", getImage("LETER/Z_small.png"));
    	
    	return ret;
    }
    
    
}


  