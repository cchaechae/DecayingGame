import processing.core.PApplet;
import processing.core.PVector;

public class Building {
	
	PApplet app;
	PVector[] roof;
	float x;
	float y;
	float width;
	float height;
	
	public Building(PApplet app, float x, float y, float w, float h){
		this.app = app;
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		roof = new PVector[2];
		roof[0] = new PVector(x, y+h);
		roof[1] = new PVector(x+w, y+h);
	}
	
	public void draw(){
		app.rect(x, y, width, height);
	}
	
	public PVector[] getRoof(){
		return roof;
	}
	
}
