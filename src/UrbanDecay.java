import java.io.IOException;
import java.util.HashMap;

import processing.core.PApplet;

public class UrbanDecay extends PApplet {

	float radx; // Radius
	float rady;
	float angle1; // angle
	float x; // result
	float y;
	float minus = 0.05f;
	final static int NUM_PARTICLE = 1000;
	final static int NUM_BUILDING = 10;

	final static int NUM_WHITE_BUILDING = 14;
	final static int NUM_GREY_BUILDING = 13;
	final static int NUM_DARK_BUILDING = 6;

	final static float ZOOM = 0.5f;

	final static int NUM_RAIN = 75;

	float bottom = -PROJECTOR_RATIO / ZOOM;
	float ground = -2*bottom/15;
	
	PersonTracker tracker=new PersonTracker();
	HashMap<Long, Umbrella> umbrellas = new HashMap<Long, Umbrella>();

	Building[] building = new Building[NUM_BUILDING];
	Particle[] field = new Particle[NUM_PARTICLE];

	Building[] whiteBuilding = new Building[NUM_WHITE_BUILDING];
	Building[] greyBuilding = new Building[NUM_GREY_BUILDING];
	Building[] darkBuilding = new Building[NUM_DARK_BUILDING];
	boolean found = false;

	KinectBodyDataProvider kinectReader;
	Rain[] rain;

	public static float PROJECTOR_RATIO = 1080f / 1920.0f;

	public void createWindow(boolean useP2D, boolean isFullscreen, float windowsScale) {
		if (useP2D) {
			if (isFullscreen) {
				fullScreen(P2D);
			} else {
				size((int) (1920 * windowsScale), (int) (1080 * windowsScale), P2D);
			}
		} else {
			if (isFullscreen) {
				fullScreen();
			} else {
				size((int) (1920 * windowsScale), (int) (1080 * windowsScale));
			}
		}
	}

	// use lower numbers to zoom out (show more of the world)
	// zoom of 1 means that the window is 2 meters wide and appox 1 meter tall.
	public void setScale(float zoom) {
		scale(zoom * width / 2.0f, zoom * -width / 2.0f);
		translate(1f / zoom, -PROJECTOR_RATIO / zoom);
	}

	public void settings() {
		createWindow(false, true, .5f);
	}

	public void setup() {

		try {
			kinectReader = new KinectBodyDataProvider("exitTest.kinect", 10);
		} catch (IOException e) {
			System.out.println("Unable to create kinect producer");
		}
		//kinectReader = new KinectBodyDataProvider(8008);
		kinectReader.start();
		
		// rain
<<<<<<< HEAD
		rain = new Rain[NUM_RAIN];

		for (int i = 0; i < NUM_RAIN; i++) {
=======
		rain = new Rain[25];
		rainOne = new Rain[100];
		rainTwo = new Rain[50];
		rainThree = new Rain[150];

		for (int i = 0; i < 25; i++) {

			rain[i] = new Rain(this,  random(-2f, -1f),random(0, 1.5f), random(0.2f, 0.4f));

		}

		for (int i = 0; i < 100; i++) {

			rainOne[i] = new Rain(this, random(-1f, 0f),  random(0, 1.5f),random(0.2f, 0.4f));

		}

		for (int i = 0; i < 50; i++) {
>>>>>>> origin/master

			rain[i] = new Rain(this,  random(-2f, 2f),random(0, 1.5f), random(0.2f, 0.4f));

		}

<<<<<<< HEAD
=======
		for (int i = 0; i < 150; i++) {

			rainThree[i] = new Rain(this,  random(1f, 2f), random(0, 1.5f),
					random(0.2f, 0.4f));
		}

>>>>>>> origin/master
		// white buildings
		whiteBuilding[0] = new Building(this, -1.55f, bottom+ground, 0.13f, 0.1f);
		whiteBuilding[1] = new Building(this, -1.3f, bottom+ground, 0.21f, 0.37f);
		whiteBuilding[2] = new Building(this, -0.95f, bottom+ground, 0.05f, 0.28f);
		whiteBuilding[3] = new Building(this, -0.9f, bottom+ground, 0.3f, 0.33f);
		whiteBuilding[4] = new Building(this, -0.6f, bottom+ground, 0.05f, 0.28f);
		whiteBuilding[5] = new Building(this, -0.4f, bottom+ground, 0.24f, 0.69f);
		whiteBuilding[6] = new Building(this, -0.161f, bottom+ground, 0.161f, 0.15f);
		whiteBuilding[7] = new Building(this, 0f, bottom+ground, 0.08f, 0.82f);
		whiteBuilding[8] = new Building(this, 0.079f, bottom+ground, 0.102f, 0.95f);
		whiteBuilding[9] = new Building(this, 0.18f, bottom+ground, 0.08f, 0.82f);
		whiteBuilding[10] = new Building(this, 0.35f, bottom+ground, 0.35f, 0.5f);
		whiteBuilding[11] = new Building(this, 0.8f, bottom+ground, 0.21f, 0.3f);
		whiteBuilding[12] = new Building(this, 1.25f, bottom+ground, 0.14f, 0.15f);
		whiteBuilding[13] = new Building(this, 1.43f, bottom+ground, 0.13f, 0.07f);

		// grey buildings
		greyBuilding[0] = new Building(this, -1.8f, bottom+ground, 0.32f, 0.35f);
		greyBuilding[1] = new Building(this, -1.2f, bottom+ground, 0.25f, 0.7f);
		greyBuilding[2] = new Building(this, -0.7f, bottom+ground, 0.25f, 0.48f);
		greyBuilding[3] = new Building(this, -0.3f, bottom+ground, 0.03f, 1f);
		greyBuilding[4] = new Building(this, -0.271f, bottom+ground, 0.08f, 1.1f);
		greyBuilding[5] = new Building(this, -0.192f, bottom+ground, 0.024f, 1.35f);
		greyBuilding[6] = new Building(this, -0.17f, bottom+ground, 0.081f, 1.1f);
		greyBuilding[7] = new Building(this, -0.09f, bottom+ground, 0.03f, 1f);
		greyBuilding[8] = new Building(this, 0.1f, bottom+ground, 0.35f, 1.05f);
		greyBuilding[9] = new Building(this, 0.65f, bottom+ground, 0.3f, 0.75f);
		greyBuilding[10] = new Building(this, 1.3f, bottom+ground, 0.3f, 0.37f);
		greyBuilding[11] = new Building(this, 1.5f, bottom+ground, 0.2f, 0.19f);
		greyBuilding[12] = new Building(this, 1.8f, bottom+ground, 0.15f, 0.1f);

		// dark grey buildings
		darkBuilding[0] = new Building(this, -1.5f, bottom+ground, 0.22f, 0.67f);
		darkBuilding[1] = new Building(this, -1f, bottom+ground, 0.22f, 1.05f);
		darkBuilding[2] = new Building(this, -0.5f, bottom+ground, 0.25f, 1.15f);
		darkBuilding[3] = new Building(this, 0f, bottom+ground, 0.4f, 1.25f);
		darkBuilding[4] = new Building(this, 0.6f, bottom+ground, 0.18f, 0.95f);
		darkBuilding[5] = new Building(this, 1.1f, bottom+ground, 0.53f, .65f);
	}

	/**
	 * rain goes back to the top if rain touches the building
	 * @param r
	 */
	public void touchBuilding(Rain r){
		
<<<<<<< HEAD
		for(int i = 0; i < NUM_WHITE_BUILDING; i++){
			if(whiteBuilding[i].getRoof()[0].x <= r.getX() && r.getX() <= whiteBuilding[i].getRoof()[1].x && r.getY() <= whiteBuilding[i].getRoof()[0].y){
				whiteBuilding[i].decay();
				r.setY(1.5f);
			}
		}
		
		for(int i = 0; i < NUM_GREY_BUILDING; i++){
			if(greyBuilding[i].getRoof()[0].x <= r.getX() && r.getX() <= greyBuilding[i].getRoof()[1].x && r.getY() <= greyBuilding[i].getRoof()[0].y){
				greyBuilding[i].decay();
				r.setY(1.5f);
			}
=======
		if(!found){
		for (int i = 0; i < 25; i++) {
			rain[i].draw();
		}
		for (int i = 0; i < 100; i++) {
			rainOne[i].draw();
			
		}
		for (int i = 0; i < 50; i++) {
			rainTwo[i].draw();
			
		}
		for (int i = 0; i < 150; i++) {
			rainThree[i].draw();
			
>>>>>>> origin/master
		}
		
		for(int i = 0; i < NUM_DARK_BUILDING; i++){
			if(darkBuilding[i].getRoof()[0].x <= r.getX() && r.getX() <= darkBuilding[i].getRoof()[1].x && r.getY() <= darkBuilding[i].getRoof()[0].y){
				darkBuilding[i].decay();
				r.setY(1.5f);
			}
		}
	}
	
	public void draw() {
		background(0);
		
		setScale(ZOOM);
		
		fill(255, 255, 255);
		// ground
		noStroke();
		rect(-2f, bottom, 4f, ground);

		strokeWeight(1);
		//if(!found){
			for (int i = 0; i < NUM_RAIN; i++) {
				rain[i].draw();
			}
		//}

		int numPpl = 0;
		
		fill(50, 50, 50);
		for (int i = 0; i < NUM_DARK_BUILDING; i++) {
			noStroke();
			darkBuilding[i].draw();
			if(mousePressed){
				darkBuilding[i].initParticle();
			}
			darkBuilding[i].drawParticle();
		}

		fill(120, 120, 120);
		for (int i = 0; i < NUM_GREY_BUILDING; i++) {
			noStroke();
			greyBuilding[i].draw();
			if(mousePressed){
				greyBuilding[i].initParticle();
			}
			greyBuilding[i].drawParticle();
		}

		fill(255, 255, 255);
		// white buildings
		for (int i = 0; i < NUM_WHITE_BUILDING; i++) {
			noStroke();
			whiteBuilding[i].draw();
			whiteBuilding[i].drawParticle();
		}
		

		for (int i = 0; i < NUM_RAIN; i++) {
			touchBuilding(rain[i]);
			// if raindrop touches ground
			if (rain[i].getY() <= bottom + ground) {
				rain[i].setY(1.5f); 
			}
		
		}
		
		// KinectBodyData bodyData = kinectReader.getMostRecentData();
		KinectBodyData bodyData = kinectReader.getData();
		tracker.update(bodyData);
		
		//detecting multiple users
		for (Long id: tracker.getEnters()){
			Umbrella umbrella =  new Umbrella(this);

			umbrellas.put(id, umbrella);
			//numPpl++;
		}

		for (Long id : tracker.getExits()) {
			umbrellas.remove(id);
			//numPpl--;
		}

		//the rain goes back to the top (disappears) when it touches the umbrella
		for (Body b : tracker.getPeople().values()) {
			Umbrella u = umbrellas.get(b.getId());
			
			numPpl++;
			u.setColor(numPpl);

			if (u != null && b != null){

				u.update(b);
				found = true;
				
				for (int i = 0; i < NUM_RAIN; i++) {

					rain[i].draw();


					if (u.detectRain(rain[i].getX(), rain[i].getY())){
						rain[i].setY(1.5f);
					}

				}
<<<<<<< HEAD
				
				u.drawUmbrella();
=======
				for (int i = 0; i < 100; i++) {
					rainOne[i].draw();
					if (u.detectRain(rainOne[i].getX(), rainOne[i].getY())){
						rainOne[i].setY(1.5f);
					}
				}
				for (int i = 0; i < 50; i++) {
					rainTwo[i].draw();
					if (u.detectRain(rainTwo[i].getX(), rainTwo[i].getY())){
						rainTwo[i].setY(1.5f);
					}
				}
				for (int i = 0; i < 150; i++) {
					rainThree[i].draw();
					if (u.detectRain(rainThree[i].getX(), rainThree[i].getY())){
						rainThree[i].setY(1.5f);
					}
				}

				u.drawUmbrella(numPpl);
>>>>>>> origin/master
			}
		}
<<<<<<< HEAD

	}	
=======
	}	


	/**
	 * Draws an ellipse in the x,y position of the vector (it ignores z). Will
	 * do nothing is vec is null. This is handy because get joint will return
	 * null if the joint isn't tracked.
	 * 
	 * @param vec
	 */
	public void drawIfValid(PVector vec) {
		if (vec != null) {
			ellipse(vec.x, vec.y, .1f, .1f);
		}
	}
>>>>>>> origin/master

	public static void main(String[] args) {
		PApplet.main(UrbanDecay.class.getName());
	}

}