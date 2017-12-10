import java.util.Random;

import processing.core.PApplet;
import processing.core.PVector;

public class RainDemo extends PApplet{

	Rain[] rain;
	Rain[] rainOne;
	Rain[] rainTwo;
	Rain[] rainThree;
	
	
	int secWidth = floor(this.width/4);
	
	
	public void settings() {
		size(600,800);
	}
		
	public void setup(){
		background(0);
		
		
		rain = new Rain[25];
		rainOne = new Rain[100];
		rainTwo = new Rain[10];
		rainThree = new Rain[50];
		
	
		int[] arr = {0,150,300,450};
		
		
		//int rand = getRandom(arr);
		for (int i = 0; i < 25; i++) {
			
		
			rain[i] = new Rain(this, (int)random(0 ,150), (int)random(0, this.height), (int)random(2, 4));
		}
		
		for (int i = 0; i < 100; i++) {
			
			rainOne[i] = new Rain(this, (int)random( 150, 300), (int)random(0, this.height), (int)random(2, 4));
		}
	
		for (int i = 0; i < 10; i++) {
			
			rainTwo[i] = new Rain(this, (int)random(300, 450), (int)random(0, this.height), (int)random(2, 4));
		}
		
		for (int i = 0; i < 50; i++) {
			
			rainThree[i] = new Rain(this, (int)random(450, 600), (int)random(0, this.height), (int)random(2, 4));
		}

	}
	public static int getRandom(int[] array) {
	    int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}

			
	public void draw(){
		
		
		
		background(0);
		
		
		fill(204, 102, 0);
		rect(300, 400, 100, 60);
		
		
		
		
		
		for (int i = 0; i < 25 ; i++) {
			rain[i].draw(300,400,400);
		}
		for (int i = 0; i < 100 ; i++) {
			rainOne[i].draw(300,400,400);
		}
		for (int i = 0; i < 10 ; i++) {
			rainTwo[i].draw(300,400,400);
		}
		for (int i = 0; i < 50 ; i++) {
			rainThree[i].draw(300,400,400);
		}
		
		
	}
		
	public static void main(String[] args) {
		PApplet.main(RainDemo.class.getName());
	}
}
