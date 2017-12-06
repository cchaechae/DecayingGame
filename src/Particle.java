

import processing.core.PApplet;
import processing.core.PVector;


public class Particle {
	PVector location;
	PVector velocity;
	PVector referenceV;
	PVector referenceL;
	
	//boolean falling = false;
	int impatience = 0;
	int clr;
	int velocityY = 1;
	PApplet app;
	boolean clicked;
	
	public Particle(PApplet app, float x, float y, int clr){
		this.app = app;
		location = new PVector(x,y);
		velocity = new PVector();
		referenceV = new PVector();
		referenceL = new PVector(x,y);
	    this.clr = clr;
	    clicked = false;
	}
	
	public void mouseClicked(){
		clicked = true;
		falling();
	}
	
	public void update(float x, float y){
		location.x = x;
		location.y = y;
	}

	public void falling(){
		float x = location.x;
		float y = location.y;
		PVector newLoc = PVector.add(location,velocity);
		float newX = newLoc.x;
		float newY = newLoc.y;
		// it is moving
		if(clicked){
		if((x == newX && y == newY) == false){
			if(newX < 0 || newX >= app.width){
				velocity.x *= -0.5f;
			}else if(newY < 0 || newY >= app.height){
		        velocity.y *= -0.3f;
		      }else{
		          PVector delta = PVector.sub(referenceV,velocity);
		          delta.mult((float)0.8);
		          float heat = impatience/3f;
		          delta.add(new PVector(app.random(-heat,heat), app.random(-heat,heat)));
		          velocity.add(delta);
		          referenceV.sub(delta);
		          impatience++;
		          if(impatience > 4){
		        	  impatience = 4;
		          }
		          location = newLoc;
		      	}
			}
		velocity.y += 0.1f;	
		}
	}	
	
	public void draw(){
		app.strokeWeight(0.005f);
		app.stroke(clr);
		app.point(location.x, location.y);
	}
}

