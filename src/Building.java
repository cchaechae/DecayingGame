import processing.core.PApplet;
import processing.core.PVector;

public class Building {
	
	PApplet app;
	PVector[] roof;
	float x;
	float y;
	// width of this building
	float width;
	// height of this building
	float height;
	
	Particle[] particles;
	// number of particles in a row
	int num;
	// number of rows of particles
	int row;
	
	public Building(PApplet app, float xp, float yp, float w, float h){
		this.app = app;
		this.x = xp;
		this.y = yp+0.1f;
		this.width = w;
		this.height = h-0.1f;
		roof = new PVector[2];
		particles = new Particle[(int)((width/0.005f)*(height/0.005f))];
		num = (int)(width/0.005f);
		row = 0;
	}
	
	public void draw(){
		app.rect(x, y, width, height);
	}
	
	public void initParticle(){
		if(row > (int)(height/0.005f))
			return;
	
		float x = 0f;
		for(int j = 0; j < num; j++){
			particles[j + (row*num)] = new Particle(app, getRoof()[0].x + x, getRoof()[0].y, app.color(app.random(0,255),app.random(0,255),app.random(0,255)));
			x += 0.005f;
		}
		row++;
	}
	
	public void drawParticle(){
		for (int i=0; i < particles.length; i++) {
			if(particles[i] == null)
				break;
			else
				particles[i].update();
		}
	}
	
	public void decay(){
		height -= 0.005f; 
	}
	
	public float getWidth(){
		return width;
	}
	
	public void mouseClicked(){
		for(int j = 0; j < num; j++){
			particles[j + ((row-1)*num)].mouseClicked();
		}
	}
	
	public PVector[] getRoof(){
		roof[0] = new PVector(x, y + height);
		roof[1] = new PVector(x+width, y + height);
		return roof;
	}
	
}