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
		double distance = getDistanceFromPoint(other);
		double xD = x-other.getX();
		double yD = y-other.getY();
		if(xD > 0) {
			if(yD < 0) {
				return Math.acos(-yD/distance);
			}
			if(yD > 0) {
				return Math.PI/2 + Math.acos(xD/distance);
			}else {
				return Math.PI/2;
			}
		}
		if(xD < 0) {
			if(yD > 0) {
				return Math.PI + Math.acos(yD/distance);
			}
			if(yD < 0) {
				return Math.PI*3/2 + Math.acos(-xD/distance);
			}else {
				return Math.PI*3/2;
			}
		}else {
			if(yD > 0) {
				return Math.PI;
			}else {
				return 0;
			}
		}
		
		
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
