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
	// size of particle
	float size;
	
	Particle[] particles;
	
	// number of particles in a row
	int num;
	
	// number of rows of particles
	int row;
	
	public Building(PApplet app, float xp, float yp, float w, float h){
		this.app = app;
		this.x = xp;
		this.y = yp;
		this.width = w;
		this.height = h;
		roof = new PVector[2];

		if((int)app.random(2) == 0)
			size = 0.01f;
		else
			size = 0.005f;
		
		particles = new Particle[(int)((width/0.02f)*(height/size))];
		num = (int)(width/0.02f);
		row = 0;
	}
	
	public void draw(){
<<<<<<< HEAD
		app.rect(x, y, width, height);
		
	}
	
	public void initParticle(){
		float x = 0f;
		for(int j = 0; j < num; j++){
			if(particles[j + (row*num)] == null){
				particles[j + (row*num)] = new Particle(app, getRoof()[0].x + x, getRoof()[0].y, app.color(app.random(0,255),app.random(0,255),app.random(0,255)));
				x += 0.02f;
			}
		}
		row++;
	}
	
	public void drawParticle(){
=======
>>>>>>> f6c81b44d258e4295e73de0dafbc40b805857f80
		for (int i=0; i < particles.length; i++) {
			if(particles[i] == null)
				break;
			else
				particles[i].update();
		}
	}
	
	public void initParticle(){
		for(int i = 0; i < (int)(height/size); i++){
			float x = 0f;
			int rand = (int) app.random(0,255);
			for(int j = 0; j < num; j++){
				if(particles[j + (i*num)] == null){
					//colorful
//					particles[j + (i*num)] = new Particle(app, getRoof()[0].x + x, getRoof()[0].y-(i*size), app.color(app.random(0,255),app.random(0,255),app.random(0,255)), y, size);
					particles[j + (i*num)] = new Particle(app, getRoof()[0].x + x, getRoof()[0].y-(i*size), app.color(rand, rand, rand), y, size);
					// yellow
//					particles[j + (i*num)] = new Particle(app, getRoof()[0].x + x, getRoof()[0].y-(i*size), app.color(239, 236, 67), y, size);
					x += 0.02f;
				}
			}
		}
	}
	
	public void decay(){
		if(row < (int)(height/size)){
			for(int j = 0; j < num; j++){
				particles[j + ((row)*num)].fall();
			}
			row++;
		}
	}
	
	public PVector[] getRoof(){
		roof[0] = new PVector(x, y + height);
		roof[1] = new PVector(x+width, y + height);
		return roof;
	}
	
}
