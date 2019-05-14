package application;

public class PointList {
	
	double x;
	double y;
	double size;
	double color1;
	double color2;
	double color3;
	
	public PointList(double x, double y, double size, double color1, double color2, double color3) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getSize() {
		return size;
	}
	public double getColor1() { return color1; }
	public double getColor2() { return color2; }
	public double getColor3() { return color3; }
	
}
