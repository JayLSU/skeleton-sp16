public class TestPlanet {
	public static void main(String[] args) {
		Planet p1 = new Planet(1e12,2e11,0,0,2e30,"sun.gif");
		Planet p2 = new Planet(2.3e12,9.5e11,0,0,6e26,"saturn.gif");
		System.out.println("The force on p1 exerted by p2 is " + p1.calcForceExertedBy(p2));
		System.out.println("The force on p2 exerted by p1 is " + p2.calcForceExertedBy(p1));
		System.out.println("The force on p1 exerted by p2 on x-axis is " + p1.calcForceExertedByX(p2));
		System.out.println("The force on p2 exerted by p1 on x-axis is " + p2.calcForceExertedByX(p1));
	}
}