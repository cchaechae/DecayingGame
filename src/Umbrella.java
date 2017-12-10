import processing.core.PApplet;
import processing.core.PVector;

public class Umbrella {
	
	Body body;
	float radius;
	float size = 0.5f;
	PApplet app;
	PVector head;
	PVector neck;
	int[] colorPalette;
	
	public Umbrella(PApplet app){
		
		this.app = app;
		radius = size/2;
		colorPalette = new int [4];
		
		//umbrella color
		colorPalette[1]= app.color(164, 48, 37);
		colorPalette[2] = app.color(98, 164, 128);
		colorPalette[3] = app.color(98, 164, 216);
		
		//stick color
		colorPalette[0] = app.color(97, 20, 124);
	}
	
	public void update(Body body){
		
		this.body = body;
		head = body.getJoint(Body.HEAD);
		neck = body.getJoint(Body.NECK);
	}
	
	public void drawUmbrella(int pplNum){
		
		if (head!= null && neck!= null){
		
		determineSize();
		
		app.stroke(colorPalette[pplNum]);
		app.fill(colorPalette[pplNum]);
		app.line(head.x, head.y + radius , head.x, head.y + radius + 0.03f);
		app.arc(head.x, head.y, size, size, 0, app.HALF_PI+app.HALF_PI);
		//size: width and height
		
		
		app.stroke(colorPalette[0]);
		app.strokeWeight(0.02f);
		app.line(head.x, head.y, head.x , neck.y);
		
//		app.beginShape();
//		app.vertex(neck.x, neck.y);
//		app.bezierVertex(neck.x - 0.05f, neck.y - 0.05f, neck.x - 0.1f, neck.y - 0.05f, neck.x, neck.y);
//		app.endShape();

		}
		
	}
	
	private void determineSize() {
		
		//detects if there are two people
	}

	/**
	 * returns true if the umbrella detects the rain
	 * @return
	 */
	public boolean detectRain(int x, int y){
		
		//get the rain coordinates (location)
		//determines if the rain is in the range of radius from head center
		float d = PApplet.dist(x, y, head.x, head.y);
		
		if (d <= radius) return true;
		
		return false;
	}
	
}
