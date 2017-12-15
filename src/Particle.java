import processing.core.PApplet;
import processing.core.PVector;

public class Particle {
	PVector location;
	
	int impatience;
	// color of particle
	int clr;
	float velocityY;
	float velocityX = 0.001f;
	PApplet app;
	boolean fall = false;
	boolean direction;
	// size of particle
	float size;
	float bottom;
	
	public Particle(PApplet app, float x, float y, int clr, float bottom, float size){
		this.app = app;
		location = new PVector(x,y);
	    this.clr = clr;
	    this.bottom = bottom;
	    this.size = size;
	    impatience = (int)app.random(0,15);
	    velocityY = app.random(0.002f, 0.01f);
	    if((int)app.random(0,2) == 0)
	    	direction = false;
	    else
	    	direction = true;
	}
	
	public void fall(){
		fall = true;
	}
	
	public void update(){
		if(fall){
			if(location.y > bottom){
				if(impatience == 0)
					location.y -= velocityY;
				else
					impatience--;
				if(direction){
					location.x += velocityX;
				}
				else{
					location.x -= velocityX;
				}
			}
		}
		app.strokeWeight(size);
		app.stroke(clr);
		app.point(location.x, location.y);
		velocityY += 0.00005f;
	}	
}
