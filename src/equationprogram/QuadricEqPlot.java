package equationprogram;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * The class provides a convenient way to plot the function that corresponds to the equation object.
 * @author Sami Safadi
 */
public final class QuadricEqPlot extends JComponent{
    // Defining the class fiels
    private final int No_Of_Points = 500; //number of points used to draw the plot
    private final Point2D.Double p[] = new Point2D.Double[No_Of_Points+1];
    private final double x1, x2; //interval on the x axis
    private final double y1, y2; //interval on the y axis
    private final QuadricEq function; //the function being drawn
    
    /**
     * Creates a new instance(constructor) of QuadricEqPlot.
     * @param function The function that the plot will represent.
     * @param x1 The start of the plot on the X axis.
     * @param x2 The end of the plot on the X axis.
     * @param y1 The start of the plot on the Y axis.
     * @param y2 The end of the plot on the Y axis.
     */
    public QuadricEqPlot(QuadricEq function,double x1,double x2,double y1,double y2) {
        super();
        this.setSize(No_Of_Points,No_Of_Points);
        this.x1 = x1; 
        this.x2 = x2; 
        this.y1 = y1;
        this.y2 = y2;
        this.function = function;
        setPoints();
    }
    
    // Creates class methodes
    /**
     * This methods assigns the points corresponding to the function in the plot.
     */
    public void setPoints(){    //This methodes put the points in space
        double spacing = (Math.abs(x2-x1) / No_Of_Points);
        double x,y;
        for(int i=0;i<=No_Of_Points; i++){
            x = x1 + i*spacing;
            y = function.getYfromX(x);
            p[i] = new Point2D.Double(x,y);
        }
    }
    
    /**
     * This methods draws the plot on a specific container.
     * @param g a graphics object.
     */
    @Override
    public void paintComponent(Graphics g) {    //This methodes draw the equation on the plot
        Graphics2D g2 = (Graphics2D) g;

        //fill the graphic with a white area
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,this.getWidth(),this.getHeight());
        
        //set stroke & antialiasing properties
        BasicStroke bs = new BasicStroke(1);
        g2.setStroke(bs);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        
        //set the axis transformation
        double ScaleX = 1*(this.getWidth() / (x2-x1));
        double ScaleY = 1*(this.getHeight() / (y2-y1));
        
        //draw the X & Y axis
        g2.setColor(Color.BLACK);
        Line2D.Double axis = new Line2D.Double();
        
        axis.setLine(0, y2*ScaleY, (x2-x1)*ScaleX, y2*ScaleY);
        g2.draw(axis);
        axis.setLine(-x1*ScaleX, 0, -x1*ScaleX, (y2-y1)*ScaleY);
        g2.draw(axis);
        
        //draw the plot by joining the points p[] by lines
        g2.setColor(Color.RED);
        Line2D.Double l = new Line2D.Double();
        for(int i=0;i<No_Of_Points;i++){
            l.setLine((p[i].getX()-x1)*ScaleX,(y2-p[i].getY())*ScaleY,
                    (p[i+1].getX()-x1)*ScaleX,(y2-p[i+1].getY())*ScaleY);
            g2.draw(l);
        }
            
        //finally I need to tell the user about the interval of the current plot
        g2.setColor(Color.BLUE);
        String info1 = "Equation " + function.toString();
        String info2 = "Roots x1 = " + function.getRoot1() + " & x2 = " + function.getRoot2();
        g2.drawString(info1,10,20);
        g2.drawString(info2,10,40);
        g2.drawString("Plotting from " + x1 + " to " + x2 + " on the X axis",10,60);
        g2.drawString("Plotting from " + y1 + " to " + y2 + " on the Y axis",10,80);

    }
    
    /**
     * A test method.
     * @param args Command line arguments.
     */
    public static void main(String[] args){ //Testdrive method
        QuadricEq e1 = new QuadricEq(-1,5,7);
        QuadricEqPlot p1 = new QuadricEqPlot(e1,-10,10,-20,20);
        JFrame f = new JFrame();
        f.setSize(500,500);
        f.add(p1);
        f.setVisible(true);
    }
}
