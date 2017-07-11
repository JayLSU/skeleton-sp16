public class NBody{
	public static double readRadius(String fname){
		In in = new In(fname);
		double temp = in.readInt();
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

}