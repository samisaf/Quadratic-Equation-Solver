package equationprogram;

/**
 * This class represents a quadric equation, it also provides methods for solving it.
 */
public class QuadricEq{
    // Class fields
    private final double a, b, c; // Equation variables
    private int numberOfRoots; // returns -1 if  a linear equation 2 if 2 reals, 1 if 1 repeated, 0 if 2 complex
    private String root1, root2, type; // Equation roots & type
    private double x1, x2, det; // Equation solution  & determinant
    private double realpart,imgpart; //in case equation has complex roots
    
    // Class Constructor
    /**
     * Constructs an equation based on its variables, and then solve it.
     * @param a variable a.
     * @param b variable b.
     * @param c variable c.
     */
    public QuadricEq(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
        SolveEq();
    }
    
    // Class methods
    private void SolveEq(){
        det = (b*b) - (4*a*c);
        if (a==0) { // a linear equation
            type = "This is a linear equation with one real root";
            numberOfRoots = -1;
            x1 = -c/b;
            root1 = String.valueOf(x1);
            root2 = "";
        }
        else { // a quadric equation
            if (det>0){
                type = "Equation has two real roots";
                numberOfRoots = 2;
                x1 = (-b + Math.sqrt(det)) / (2*a);
                x2 = (-b - Math.sqrt(det)) / (2*a);
                root1 = String.valueOf(x1);
                root2 = String.valueOf(x2);
            }
            else if (det==0){
                type = "Equation has one repeated root";
                numberOfRoots = 1;
                x1 = (-b) / (2*a);
                x2 = (-b) / (2*a);
                root1 = String.valueOf(x1);
                root2 = String.valueOf(x2);
            }
            else {
                type = "Equation has two complex roots";
                numberOfRoots = 0;
                realpart = (-b) / (2*a);
                imgpart = Math.sqrt(Math.abs(det)) / (2*a);
                root1 = String.valueOf(realpart) + " + i " + String.valueOf(imgpart);
                root2 = String.valueOf(realpart) + " - i " + String.valueOf(imgpart);            
            }
        }
    }
    
    /**
     * The first root of the equation.
     * @return The first root of the equation.
     */
    public String getRoot1(){
        return root1;
    }
    
    /**
     * The second root of the equation.
     * @return The second root of the equation.
     */
    public String getRoot2(){
        return root2;
    }
    
    /**
     * The type of the equation.
     * @return a String containing a description of the equation, and the number of its roots.
     */
    public String getType(){
        return type;
    }
    
    /**
     * The number of roots of the equation.
     * @return The number of roots of the equation.
     */
    public int getNumberOfRoots(){
        return numberOfRoots;
    }
    
    /**
     * This method is useful if plooting the equation is needed, 
     * it calculates the value of the corresponding y.
     * @param x Variable x.
     * @return Variable y = f(x).
     */
    public double getYfromX(double x){
        double y;
        y = a*x*x + b*x + c;
        return y;
    }
    
    /**
     * ToString Method.
     * @return A String representing the equation "a*X2 + b*X + c =0"
     */
    @Override
    public String toString(){
        String s;
        s = (a + "*x^2 + " + b + "*x + " + c + " = 0");
        return s;
    }
    /**
     * A test method.
     * @param args Command line arguments.
     */
    public static void main(String[] args){ //testdrive method
        QuadricEq e1 = new QuadricEq(1,5,6);
        System.out.println(e1.getType() +" "+ e1.getRoot1() +" "+ e1.getRoot2());
        
        QuadricEq e2 = new QuadricEq(1,4,4);
        System.out.println(e2.getType() +" "+ e2.getRoot1() +" "+ e2.getRoot2());
        
        QuadricEq e3 = new QuadricEq(1,2,5);
        System.out.println(e3.getType() +" "+ e3.getRoot1() +" "+ e3.getRoot2());
        
        System.out.println("Testdrive");
    }
}

