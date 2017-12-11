import processing.core.PApplet;

public class Rain {

	float x;
	float y;
	PApplet app;
	float x2;
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
		
		x2 = x;
		y2 = y + 0.08f;

		app.stroke(200);
		app.strokeWeight(0.005f);
		app.line(x, y, x2, y2);
		
		if (y <= -1.5f) {
			app.noFill();
			//app.stroke(200);
			///app.ellipse(x, app.height - app.random(50, 70), app.random(25, 100), 10);
			y = 1.5f;
		}

	}


	public float getX() {
		return x2;
	}

	public float getY() {
		return y2;
	}

	public void setY(float y) {
		// TODO Auto-generated method stub
		this.y = y;
		y2 = y + 0.15f;
	}

}
