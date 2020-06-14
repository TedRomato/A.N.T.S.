package gameObjectClasses;

public class DependentPoint extends Point{

	double currentAngle;
	double distanceFromMainPoint;
	
	public DependentPoint(double rpx, double rpy, Point rp) {
		super(rpx, rpy);
		
		distanceFromMainPoint = getDistanceFromPoint(rp);
		currentAngle = getAngleFrom(rp);
		System.out.println("starting angle : " + currentAngle);
		// TODO Auto-generated constructor stub
	}
	
	public void rotateAroundPoint(Point p, double radianAngle) {
		currentAngle += radianAngle;
		double newY = Math.cos(-currentAngle)*distanceFromMainPoint;
		double newX = Math.sin(-currentAngle)*distanceFromMainPoint;
		y = newY + p.getY();;
		x = newX + p.getX();
	}

}
