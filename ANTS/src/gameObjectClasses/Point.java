package gameObjectClasses;

public class Point {
	double x, y;
	
	public Point(double rpx, double rpy) {
		this.x = rpx;
		this.y = rpy;
		
	}
	
	public void rotateAroundPoint(Point p, double radianAngle) {
	
	}
	
	public void move(double velX, double velY) {
		x += velX;
		y += velY;
	}
	
	public double getAngleFrom(Point other) {
		
		if(other.getX() > this.getX()) {
			return 2*Math.PI - Math.acos(( other.getY() - y )/getDistanceFromPoint(other));
		}
		
		return Math.acos(( other.getY() - y )/getDistanceFromPoint(other));
	}
	
	public double getDistanceFromPoint(Point other) {
		return Math.sqrt(Math.pow(x - other.getX(), 2) + Math.pow(y - other.getY(), 2));
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
}
