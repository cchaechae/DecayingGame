import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class UrbanDecay extends PApplet {

	// float radx; // Radius
	// float rady;
	// float angle1; // angle
	// float x; // result
	// float y;
	// float minus = 0.05f;
	final static int NUM_BUILDING = 17;

	final static float ZOOM = 0.5f;

	final static int NUM_RAIN = 75;

	float bottom = -PROJECTOR_RATIO / ZOOM;
	float ground = -2 * bottom / 15;

	PersonTracker tracker = new PersonTracker();
	HashMap<Long, Umbrella> umbrellas = new HashMap<Long, Umbrella>();

	Building[] building = new Building[NUM_BUILDING];

	// boolean found = false;

	PImage imgBackground;

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

		imgBackground = loadImage("backgroun1.jpg");
		imgBackground.resize(displayWidth, displayHeight);

	}

	public void setup() {

		try {
			kinectReader = new KinectBodyDataProvider("exitTest.kinect", 10);
		} catch (IOException e) {
			System.out.println("Unable to creat e kinect producer");
		}
		// kinectReader = new KinectBodyDataProvider(8008);
		kinectReader.start();

		// rain
		rain = new Rain[NUM_RAIN];

		for (int i = 0; i < NUM_RAIN; i++) {

			rain[i] = new Rain(this, random(-2f, 2f), random(0, 1.5f), random(0.2f, 0.4f));

		}

		building[0] = new Building(this, -1.55f, bottom + ground, 0.1f, 0.12f);
		building[1] = new Building(this, -1.3f, bottom + ground, 0.13f, 0.37f);
		building[2] = new Building(this, -1.1f, bottom + ground, 0.12f, 0.28f);
		building[3] = new Building(this, -0.9f, bottom + ground, 0.2f, 0.62f);
		building[4] = new Building(this, -0.6f, bottom + ground, 0.11f, 0.58f);
		building[5] = new Building(this, -0.49f, bottom + ground, 0.09f, 0.38f);
		building[6] = new Building(this, -0.4f, bottom + ground, 0.14f, 0.79f);
		building[7] = new Building(this, -0.161f, bottom + ground, 0.16f, 0.65f);
		building[8] = new Building(this, 0f, bottom + ground, 0.1f, 1.25f);
		building[9] = new Building(this, 0.13f, bottom + ground, 0.13f, 0.99f);
		building[10] = new Building(this, 0.25f, bottom + ground, 0.13f, 0.3f);
		building[11] = new Building(this, 0.38f, bottom + ground, 0.26f, 0.82f);
		building[12] = new Building(this, 0.65f, bottom + ground, 0.2f, 0.5f);
		building[13] = new Building(this, 0.9f, bottom + ground, 0.13f, 0.3f);
		building[14] = new Building(this, 1.02f, bottom + ground, 0.23f, 0.12f);
		building[15] = new Building(this, 1.25f, bottom + ground, 0.12f, 0.45f);
		building[16] = new Building(this, 1.43f, bottom + ground, 0.1f, 0.13f);

		for (int i = 0; i < NUM_BUILDING; i++) {
			building[i].initParticle();
		}

	}

	public void touchBuilding(Rain r) {

		for (int i = 0; i < 5; i++) {
			if (building[i].getRoof()[0].x <= r.getX() && r.getX() <= building[i].getRoof()[1].x
					&& r.getY() <= building[i].getRoof()[0].y) {
				building[i].decay();
				r.setY(1.5f);
				
			}
		}

		for (int i = 5; i < 10; i++) {
			if (building[i].getRoof()[0].x <= r.getX() && r.getX() <= building[i].getRoof()[1].x
					&& r.getY() <= building[i].getRoof()[0].y) {
				building[i].decay();
				r.setY(1.5f);
				
			}
		}

		for (int i = 10; i < NUM_BUILDING; i++) {
			if (building[i].getRoof()[0].x <= r.getX() && r.getX() <= building[i].getRoof()[1].x
					&& r.getY() <= building[i].getRoof()[0].y) {
				building[i].decay();
				r.setY(1.5f);
				float x2 = random(1f, 2f);
				float x3 = random(-2f, -1f);
				float x1 = random(-1f, 0f);
				float x4 = random(0f, 1f);
				

				
				Random random = new Random();
				
				int e = random.nextInt(3 - 0 + 1) + 0;

				if (e == 0) {
					rain[i].setX(x3);
				

				} 
				else if(e == 1){
					rain[i].setX(x4);
					
				}
				else if(e == 2){
					rain[i].setX(x1);
				
				}	
				
				else {
					
					rain[i].setX(x2);
				

				}
			}
		}
	}
	
	public void draw() {

		setScale(ZOOM);

		// black background
		// background(0);
		noStroke();
		background(imgBackground);
		noStroke();

		stroke(255);

		strokeWeight(0.002f);
		// ground
		line(-2f, bottom + ground, 2f, bottom + ground);

		fill(255, 255, 255);

		strokeWeight(1);
		for (int i = 0; i < NUM_RAIN; i++) {
			rain[i].draw();
		}

		int numPpl = 0;

		fill(255, 255, 255);
		// white buildings
		for (int i = 0; i < NUM_BUILDING; i++) {
			noStroke();
			building[i].draw();
		}

		for (int i = 0; i < NUM_RAIN; i++) {
			touchBuilding(rain[i]);
			// if raindrop touches ground
			if (rain[i].getY() <= bottom + ground) {
				rain[i].setY(1.5f);

				float x1 = random(-2f, 0f);
				float x2 = random(0.5f, 2f);

				Random random = new Random();
				float d = random.nextBoolean() ? x1 : x2;

				if (d == x1) {
					rain[i].setX(x1);
					

				} else {

					rain[i].setX(x2);
					

				}

			}

		}

		// KinectBodyData bodyData = kinectReader.getMostRecentData();
		KinectBodyData bodyData = kinectReader.getData();
		tracker.update(bodyData);

		// detecting multiple users
		for (Long id : tracker.getEnters()) {
			
			//UMBRELLA FIX COLOR CHANGES
			if (umbrellas.size() < 1) {
				
				Umbrella umbrella = new Umbrella(this);

				umbrellas.put(id, umbrella);
			}
		}

		for (Long id : tracker.getExits()) {
			umbrellas.remove(id);
		}

		for (Body b : tracker.getPeople().values()) {
			Umbrella u = umbrellas.get(b.getId());
			numPpl++;

			if (u != null && b != null) {

				u.update(b);

				for (int i = 0; i < NUM_RAIN; i++) {

					if (u.detectRain(rain[i].getX(), rain[i].getY())) {
						rain[i].setY(1.5f);

						float x1 = random(0f, 1f);
						float x2 = random(-2f, -1f);
						Random randomno = new Random();
						float c = randomno.nextBoolean() ? x1 : x2;
						if (c == x2) {
							
							rain[i].setX(x2);
						} else {
						
							rain[i].setX(x1);
						}
					}
				}

				u.drawUmbrella(numPpl);
			}

		}

	}

	public static void main(String[] args) {
		PApplet.main(UrbanDecay.class.getName());
	}

}