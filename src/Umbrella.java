import processing.core.PApplet;
import processing.core.PVector;

public class Umbrella {
	
	float radius;
	float size;
	PApplet app;
	PVector head;
	
	public Umbrella(PApplet app, float size, PVector head){
		
		this.app = app;
		this.size = size; //size varies depending on the number of people
		this.head = head;
	}
	
	public void drawUmbrella(){
		
		determineSize();
		app.arc(head.x, head.y, size, size, 0, app.HALF_PI+app.HALF_PI);
		app.line(head.x, head.y, head.x + size*2, 10);
		app.stroke(150);
	}
	
	private void determineSize() {
		
		//detects if there are two people
	}

	/**
	 * returns true if the umbrella detects the rain
	 * @return
	 */
	public boolean detectRain(){
		
		//get the rain coordinates (location)
		//determines if the rain is in the range of radius from head center
		
		return false;
	}
	
}
