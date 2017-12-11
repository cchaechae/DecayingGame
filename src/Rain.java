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
	
		System.out.println(y);
		
		x2 = x;
		y2 = y + 0.15f;

		app.stroke(200);
		app.strokeWeight(0.005f);
		if(umbrella){
			
			drawRain();
		}
		else{
			app.line(x, y, x2, y2);
		}
		
		
		if (y <= -1.5f) {
			app.noFill();
			//app.stroke(200);
			///app.ellipse(x, app.height - app.random(50, 70), app.random(25, 100), 10);
			y = 1.5f;
		}

	}

	
	public void drawRain(){
		if(Math.abs(calculateDist(centerX,x2,centerY,y2)) >= radius/2  ){
			if (x > centerX - radius/2 && x < centerX + radius/2) {
				if (y2  >= centerY + 0.32f ) {
					app.line(x, y, x2, y2);
				} 
				

			}
			else {
				app.line(x, y, x2, y2);
			}
		}
	}
	
	public float calculateDist(float x1, float x2, float y1, float y2){
		float diffX =(x2 - x1)*(x2 - x1);
		float diffY = (y2 - y1)*(y2 - y1);
		float distSquare = diffX + diffY;
		
		return (float) Math.sqrt(distSquare);
	}
	
	
	public void isUmbrella(boolean found){
		umbrella = found;
	}
	
	public void setUmbrellaDimensions(float x3,float y3, float f){
		this.centerX = x3;
		this.centerY =  y3;
		this.radius = f;
	}

	public float getX() {
		return x2;
	}

	public float getY() {
		return y2;
	}

}
