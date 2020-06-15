package gameObjectClasses;

public class DependentPoint extends Point{

	double currentAngle;
	double distanceFromMainPoint;
	
	public DependentPoint(double rpx, double rpy, Point rp) {
		super(rpx, rpy);
		
		distanceFromMainPoint = getDistanceFromPoint(rp);
		currentAngle = getAngleFrom(rp);
		// TODO Auto-generated constructor stub
	}
	
	public void rotateAroundPoint(Point p, double radianAngle) {
		currentAngle += radianAngle;
		double adjustedAngle = getAdjustedAngle();
		double newY;
		double newX;
		
	
		
		if(adjustedAngle <= Math.PI/2) {
			newY = Math.cos(adjustedAngle)*distanceFromMainPoint;
			newX = Math.sqrt(Math.pow(distanceFromMainPoint, 2) - Math.pow(newY, 2));
			y = p.getY() - newY;
			x = p.getX() + newX;
		}else if(adjustedAngle <= Math.PI) {
			newX = Math.cos(adjustedAngle-Math.PI/2)*distanceFromMainPoint;
			newY = Math.sqrt(Math.pow(distanceFromMainPoint, 2) - Math.pow(newX, 2));
			y = p.getY() + newY;
			x = p.getX() + newX;
		}else if(adjustedAngle <= Math.PI*3/2) {
			newY = Math.cos(adjustedAngle-Math.PI)*distanceFromMainPoint;
			newX = Math.sqrt(Math.pow(distanceFromMainPoint, 2) - Math.pow(newY, 2));
			y = p.getY() + newY;
			x = p.getX() - newX;
		}else {
			newX = Math.cos(adjustedAngle-Math.PI*3/2)*distanceFromMainPoint;
			newY = Math.sqrt(Math.pow(distanceFromMainPoint, 2) - Math.pow(newX, 2));
			y = p.getY() - newY;
			x = p.getX() - newX;

		}

	}
	
	public double getAdjustedAngle() { 
		int times = (int) Math.floor((Math.abs(currentAngle)/(Math.PI*2)));
		if(currentAngle < 0) {
			times = -times;
		}
		if(currentAngle >= 0) {
			return currentAngle - times*Math.PI*2;
		}
		if(currentAngle < 0) {
			return 2*Math.PI + currentAngle - times*Math.PI*2;
		}
		return (Double) null;
	}

}
