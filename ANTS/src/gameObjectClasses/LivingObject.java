package gameObjectClasses;

import java.awt.Color;
import java.awt.Graphics;

import handlers.Camera;

public class LivingObject extends GameObject{
	int[] Xcords, Ycords, distancesX,distancesY;
	int rpX, rpY, radius;
	int angle;
	int degrees = 1;
	boolean distanceDone = false;
	public LivingObject(int[] Xcords, int[] Ycords, int rpX, int rpY,int angle) {
		if(Xcords.length != Ycords.length) {
			System.out.println("Cords missing");
		}
		else {
			this.Xcords = Xcords;
			this.Ycords = Ycords;
			this.rpX = rpX;
			this.rpY = rpY;
			this.degrees = angle;
			this.angle = 360/Xcords.length/2;
			distancesX = new int[Xcords.length];
			distancesY = new int[Xcords.length];
		}
	}
	public void update(int VelX, int VelY) {
		move(VelX,VelY);
		rotate();
	}
	public void rotate() {
		int currentAngle = angle;
		for(int i = 0; i < Xcords.length;i++) {
			if(!distanceDone) {
				distancesX[i] = Math.abs(rpX - Xcords[i]);
				distancesY[i] = Math.abs(rpY - Ycords[i]);
			}
			Xcords[i] = (int) (rpX + Math.cos(Math.toRadians(angle))*distancesX[i]);
			Ycords[i] = (int) (rpY + Math.sin(Math.toRadians(angle))*distancesY[i]);
			angle += 360/Xcords.length;
		}
		distanceDone = true;
		angle = currentAngle;
		angle+=degrees;
	}
	public void move(int VelX, int VelY) {
		rpX+=VelX;
		rpY+=VelY;
	}
	public void render(Graphics g, Camera c) {
		g.setColor(Color.RED);
		g.fillPolygon(Xcords, Ycords, Xcords.length);
	}
	public boolean checkIfInViewport(Camera c) {
		return false;
	}
	public int[] getXcords() {
		return Xcords;
	}
	public void setXcords(int[] xcords) {
		Xcords = xcords;
	}
	public int[] getYcords() {
		return Ycords;
	}
	public void setYcords(int[] ycords) {
		Ycords = ycords;
	}
	public int getRpX() {
		return rpX;
	}
	public void setRpX(int rpX) {
		this.rpX = rpX;
	}
	public int getRpY() {
		return rpY;
	}
	public void setRpY(int rpY) {
		this.rpY = rpY;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}
	
}
