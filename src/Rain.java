import processing.core.PApplet;

public class Rain {

	int x;
	int y;
	PApplet app;
	int x2;
	int y2;
	int vel;
	int s;
	float centerX;
	float centerY;
	float radius;
	
	boolean umbrella = false;
	

	public Rain(PApplet app, int x, int y, int sp) {

		this.x = x;
		this.y = y;
		this.s = sp;

		this.app = app;
	}

	public void draw( ) {

		int mx = 0;
		if (mx <= 0) {
			mx = 2;
		}

		y = y + s * mx;
		x2 = x;
		y2 = y + 50;

		app.stroke(200);
		if(umbrella){
			drawRain();
		}
		else{
			app.line(x, y, x2, y2);
		}
		
		
		if (y >= app.height - 100) {
			app.noFill();
			app.stroke(200);
			app.ellipse(x, app.height - app.random(50, 70), app.random(25, 100), 10);
			y = -120;
		}

	}

	
	public void drawRain(){
		if(Math.abs(calculateDist(centerX,x2,centerY,y2)) >= radius/2  ){
			if (x > centerX - radius/2 && x < centerX + radius/2) {
				if (y <= centerY) {
					app.line(x, y, x2, y2);
				} 

			}
			else {
				app.line(x, y, x2, y2);
			}
		}
	}
	
	public double calculateDist(float x1, int x2, float y1, int y2){
		double diffX =(x2 - x1)*(x2 - x1);
		double diffY = (y2 - y1)*(y2 - y1);
		double distSquare = diffX + diffY;
		
		return Math.sqrt(distSquare);
	}
	
	
	public void isUmbrella(boolean found){
		umbrella = found;
	}
	
	public void setUmbrellaDimensions(float x3,float y3, float f){
		this.centerX = x3;
		this.centerY =  y3;
		this.radius = f;
	}

	public int getX() {
		return x2;
	}

	public int getY() {
		return y2;
	}

}
