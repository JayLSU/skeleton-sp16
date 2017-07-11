public class Planet{
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	/* Constructor 1*/
	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	/* Constructor 2*/
	public  Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/*Calculate the distance*/
	public double calcDistance(Planet p){
		double dist2 = (this.xxPos - p.xxPos) * (this.xxPos - p.xxPos)+  (this.yyPos - p.yyPos)*(this.yyPos - p.yyPos);
		double dist = Math.sqrt(dist2);
		return dist;
	}

	/*Calculate force*/
	public double calcForceExertedBy(Planet p){
		double r = this.calcDistance(p);
		double G = 6.67e-11;
		double F = G * this.mass * p.mass / (r*r);
		return F;
	}

	/*Calculate the x-axis force part*/
	public double calcForceExertedByX(Planet p){
		double dx = p.xxPos - this.xxPos;
		double r = this.calcDistance(p);
		double F = this.calcForceExertedBy(p);
		double Fx = F * dx / r;
		return Fx;
	}
	/*Calculate the y-axis force part*/
	public double calcForceExertedByY(Planet p) {
		double dy = p.yyPos - this.yyPos;
		double r = this.calcDistance(p);
		double F = this.calcForceExertedBy(p);
		double Fy = F * dy / r;
		return Fy;
	}

	/*Calculate the net x-axis forces*/
	public double calcNetForceExertedByX(Planet[] p){
		int len = p.length;
		double Fxnet = 0;
		for(int i = 0; i < len; i++){
			if(this.equals(p[i])){
				Fxnet = Fxnet;
			}else{
				double Fx = this.calcForceExertedByX(p[i]);
				Fxnet = Fxnet + Fx;
			}
		}
		return Fxnet;
	}

	/*Calculate the net y-axis forces*/
	public double calcNetForceExertedByY(Planet[] p){
		int len = p.length;
		double Fynet = 0;
		for(int i = 0; i < len; i++){
			if(this.equals(p[i])){
				Fynet = Fynet;
			}else{
				double Fy = this.calcForceExertedByY(p[i]);
				Fynet = Fynet + Fy;
			}
		}
		return Fynet;
	}

	/*Update of the planet status in terms of positions and velocities*/
	public void update(double dt, double fX, double fY){
		double ax = fX / this.mass;
		double ay = fY / this.mass;
		this.xxVel = this.xxVel + dt * ax;
		this.yyVel = this.yyVel + dt * ay;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}

}