public class NBody{
	public static double readRadius(String fname){
		In in = new In(fname);
		in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String fname){
		In in = new In(fname);
		int N = in.readInt();
		in.readDouble();
		Planet[] p = new Planet[N];
		for (int i=0;i<N;i++){
			double xPos = in.readDouble();
			double yPos = in.readDouble();
			double xVel = in.readDouble();
			double yVel = in.readDouble();
			double mass = in.readDouble();
			String imag = in.readString();
			p[i] = new Planet(xPos,yPos,xVel,yVel,mass,imag);
		}
		return p;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] p = readPlanets(filename);
		double r = readRadius(filename);
		//StdDraw.setScale(-r, r);
		//StdDraw.picture(0,0,"starfield.jpg");
		int N = p.length;
		//for (int i = 0; i < N; i++){
		//	p[i].draw();
		//}
		//StdAudio.play("2001.mid");
		double ecllipseT = 0;
		while (ecllipseT < T){
			double[] xForces = new double[N];
			double[] yForces = new double[N];
			for (int j=0; j<N;j++){
				xForces[j] = p[j].calcNetForceExertedByX(p);
				yForces[j] = p[j].calcNetForceExertedByY(p);
			}
			for (int k=0;k<N;k++){
				p[k].update(dt,xForces[k],yForces[k]);
			}
			// Set universal scale with radius r
			StdDraw.setScale(-r, r);
			// Draw background picture
			StdDraw.picture(0,0,"starfield.jpg");
			// Draw all the planets
			for (int i = 0; i < N; i++){
				p[i].draw();
			}
			// Pause the animation for 7 mlliseconds
			StdDraw.show(7);
			ecllipseT = ecllipseT + dt;
		}
		// Print out the final status information
		StdOut.printf("%d\n", p.length);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < p.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					p[i].xxPos, p[i].yyPos, p[i].xxVel, p[i].yyVel, p[i].mass, p[i].imgFileName);
		}

	}

}