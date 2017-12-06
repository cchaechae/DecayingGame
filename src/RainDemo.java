import processing.core.PApplet;
import processing.core.PVector;

public class RainDemo extends PApplet{

	Rain[] rain;
	
	public void settings() {
		size(600,800);
	}
		
	public void setup(){
		background(0);
		rain = new Rain[50];
		
		for (int i = 0; i < 50; i++) {
			rain[i] = new Rain(this, (int)random(0, this.width), (int)random(0, this.height), (int)random(2, 4));
		}

	}
			
	public void draw(){
		background(0);
		for (int i = 0; i < 50; i++) {
			rain[i].draw();
		}
	}
		
	public static void main(String[] args) {
		PApplet.main(RainDemo.class.getName());
	}
}
