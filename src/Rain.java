import java.util.Random;

import processing.core.PApplet;

public class Rain {

	float x;
	float y;
	PApplet app;
	float y2;
	float vel;
	float s;
	float centerX;
	float centerY;
	float radius;
	float x1;
	float x2; 
	float x3;
	float x4;
	
	
	boolean umbrella = false;
	

	public Rain(PApplet app,float x, float y, float sp) {
		
		this.x = x;
		this.y = y;
		this.s = sp;

		this.app = app;
	}

	public void draw( ) {
//		Random rand = new Random();
//
//		x1 = rand.nextFloat() * (-1f - (-2f))  - 2f;
//		x2 = rand.nextFloat() * (0f - (-1f))  - 1f;
//		x3 = rand.nextFloat() * (1f - (0f)) + 0f;
//		x4 = rand.nextFloat() * (2f - (1f)) + 1f;
//
//		int randomNum = (int) (Math.random() * (4 - 1) + 1);
//		if(randomNum == 1){
//			x = x1;
//		}
//		else if(randomNum == 2){
//			x = x2;
//		}
//		else if(randomNum == 3){
//			x = x3;
//		}
//		else if(randomNum == 4){
//			x = x4;
//		}
//		
//		
		
		float mx = 0;
		if (mx <= 0) {
			mx = 0.2f;
		}

		y = y - s*mx ;
		
		y2 = y + 0.05f;

		app.stroke(200);
		app.strokeWeight(0.002f);
		app.line(x, y, x, y2);
	}


	public float getX() {
		return x;
	}

	public float getY() {
		return y2;
	}

	public void setY(float y) {
		this.y = y;
		y2 = y + 0.15f;
	}
	
	public void setX(float x) {
		this.x = x;
	}

}