
public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myfilename;
	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		this.myXPos = xp;
		this.myYPos = yp;
		this.myXVel = xv;
		this.myYVel = yv;
		this.myMass = mass;
		this.myfilename = filename;
	}
		
	public Body(Body b){
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myfilename = b.myfilename;

	}
		
		
		
	
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
		double diffx = Math.pow(myXPos - b.getX(), 2);
		double diffy = Math.pow(myYPos - b.getY(), 2);
		double alm = diffx + diffy;
		double dis = Math.sqrt(alm);
		return dis;
		}
	public double calcForceExertedBy(Body p) {
		double dosmass = myMass * p.getMass();
		double bigg = 6.67 * Math.pow(10, -11);
		double bot = Math.pow(calcDistance(p), 2);
		double exerby = bigg *(dosmass / bot);
		return exerby;
	}
	
	public double calcForceExertedByX(Body p) {
		double difffx = (p.getX() - myXPos);
		double exxon = (calcForceExertedBy(p)* (difffx/ calcDistance(p)));
		return exxon;
	}
		
	
	public double calcForceExertedByY(Body p) {
		double difffy = (p.getY() - myYPos);
		double exyon = (calcForceExertedBy(p)* (difffy/ calcDistance(p)));
		return exyon;
	}
		
		
	
	public double calcNetForceExertedByX(Body[] bodies){
		double finalforce = 0;
		for (Body b : bodies) {
			if (! this.equals(b)) {
			 finalforce += calcForceExertedByX(b);
			}
		}
		return finalforce;
	}
		
	
	public double calcNetForceExertedByY(Body[] bodies){
		double finalforce2 = 0;
		for (Body b : bodies) {
			if (! this.equals(b)) {
			 finalforce2 += calcForceExertedByY(b);
			}
		}
		return finalforce2;
		
	}
	
	public void update(double deltaT, double xforce, double yforce) {
		double accx = xforce / myMass;
		double accy = yforce / myMass;
		double accvx = myXVel + (deltaT * accx);
		double accvy = myYVel + (deltaT * accy);
		double accxp = myXPos + (deltaT * accvx);
		double accyp = myYPos + (deltaT * accvy);
	myXVel = accvx;
	myYVel = accvy;
	myXPos = accxp;
	myYPos = accyp;
	}
	
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myfilename);
	}
		
	//}


}
