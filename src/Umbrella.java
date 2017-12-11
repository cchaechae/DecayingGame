import processing.core.PApplet;
import processing.core.PVector;

public class Umbrella {
	
	Body body;
	float radius;
	float size = 0.75f;
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
		colorPalette[0] = app.color(63, 31, 142);
	}
	
	public void update(Body body){
		
		this.body = body;
		head = body.getJoint(Body.HEAD);
		neck = body.getJoint(Body.NECK);
	}
	

	public float getX(){
		
		return head.x;
	}
	public float getY(){
		return head.y;
	}

	
	public void drawUmbrella(int pplNum){
		
		if (head!= null && neck!= null){
		
		determineSize();
		
		app.strokeWeight(0.015f);
		
		//draw Umbrella body
		app.stroke(colorPalette[pplNum]);
		app.fill(colorPalette[pplNum]);
		app.arc(head.x, head.y, size, size, 0, app.HALF_PI+app.HALF_PI);
		
		//draw Umbrella tip
		app.line(head.x, head.y + radius , head.x, head.y + radius + 0.02f);
		

		//draw Umbrella handle
		app.stroke(colorPalette[pplNum]);
		app.strokeWeight(0.015f);
		app.line(head.x, head.y, head.x , neck.y);

		//draw Umbrella lines
		app.stroke(colorPalette[0]);
		app.strokeWeight(0.01f);
		float umpTop = head.y + radius;
		float divide = size/4;
		app.line(head.x, umpTop, head.x + divide, head.y);
		app.line(head.x, umpTop, head.x - divide, head.y);
//		app.beginShape();
//		head.x = head.x - 0.5f;
//		head.y = head.y - 0.5f;
//		app.vertex(head.x, head.y);
//		app.bezierVertex(head.x, head.y, head.x + 50, head.y+50, head.x + 50, head.y+50);
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
	public boolean detectRain(float x, float y){
		
		//get the rain coordinates (location)
		//determines if the rain is in the range of radius from head center
		if (head!=null){
			float d = PApplet.dist(x, y, head.x, head.y);
		
			if (d <= radius) return true;
		}
		
		return false;
	}

	
}