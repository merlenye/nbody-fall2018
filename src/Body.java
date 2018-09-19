
public class Body {
	//initializes private instance variables. private for good practice
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myfilename;
	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		this.myXPos = xp; //build a constructor and set instance variables to parameters
		this.myYPos = yp;
		this.myXVel = xv;
		this.myYVel = yv;
		this.myMass = mass;
		this.myfilename = filename;
	}
		
	public Body(Body b){
		//create a copy of the body for a baseline in following calculations
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myfilename = b.myfilename;

	}
		
		
		
	//create getter meathods to get into private instance variables and use them in calculations
	public double getX(){
		return myXPos;
	}
	public double getY() {
		return myYPos;
	}
	public double getXVel() {
		return myXVel;
	}
	public double getYVel() {
		return myYVel;
	}
	public double getMass() {
		return myMass;
	}
	public String getName() {
		return myfilename;
	}
	public double calcDistance(Body b) {
		//distance between body b and the original
		double diffx = Math.pow(myXPos - b.getX(), 2); //diff squared of x coordinates
		double diffy = Math.pow(myYPos - b.getY(), 2); //diff of squared y coordinates
		double alm = diffx + diffy;
		double dis = Math.sqrt(alm); //sqaure root makes calculations above simpler
		return dis;
		}
	public double calcForceExertedBy(Body p) {
		//gets total force exerted
		double dosmass = myMass * p.getMass(); 
		double bigg = 6.67 * Math.pow(10, -11); //gravitational constant
		double bot = Math.pow(calcDistance(p), 2);
		double exerby = bigg *(dosmass / bot); //completes equation
		return exerby;
	}
	
	public double calcForceExertedByX(Body p) {
		//force exerted in x direction
		double difffx = (p.getX() - myXPos);
		// diff between initial x and new x pos
		double exxon = (calcForceExertedBy(p)* (difffx/ calcDistance(p)));
		//completes equation
		return exxon;
	}
		
	
	public double calcForceExertedByY(Body p) {
		// same as above but in y direction
		double difffy = (p.getY() - myYPos);
		double exyon = (calcForceExertedBy(p)* (difffy/ calcDistance(p)));
		return exyon;
	}
		
		
	
	public double calcNetForceExertedByX(Body[] bodies){
		// all force exerted on body x
		double finalforce = 0;
		for (Body b : bodies) {
			if (! this.equals(b)) {
				//loop through bodies and add thier force if there not the body which force upon is being measured
			 finalforce += calcForceExertedByX(b);
			}
		}
		return finalforce;
	}
		
	
	public double calcNetForceExertedByY(Body[] bodies){
		// same as above for y direction
		double finalforce2 = 0;
		for (Body b : bodies) {
			if (! this.equals(b)) {
			 finalforce2 += calcForceExertedByY(b);
			}
		}
		return finalforce2;
		
	}
	
	public void update(double deltaT, double xforce, double yforce) {
		double accx = xforce / myMass; //calculates law of motion for both direction
		double accy = yforce / myMass;
		double accvx = myXVel + (deltaT * accx); //calculates acceleration for both
		double accvy = myYVel + (deltaT * accy);
		double accxp = myXPos + (deltaT * accvx); //calculates acceleration and velocity relationship
		double accyp = myYPos + (deltaT * accvy);
	myXVel = accvx; //assigns these calculations to instance variables
	myYVel = accvy;
	myXPos = accxp;
	myYPos = accyp;
	}
	
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myfilename);
	}
		
	//}


}
