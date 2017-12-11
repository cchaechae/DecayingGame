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
	
	boolean umbrella = false;
	

	public Rain(PApplet app, float x, float y, float sp) {

		this.x = x;
		this.y = y;
		this.s = sp;

		this.app = app;
	}

	public void draw( ) {

		float mx = 0;
		if (mx <= 0) {
			mx = 0.2f;
		}

		y = y - s*mx ;
		
		y2 = y + 0.05f;

		app.stroke(200);
		app.strokeWeight(0.005f);
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