import processing.core.PApplet;

public class Rain {

	int x;
	int y;
	PApplet app;
	int x2;
	int y2;
	int vel;
	int s;

	public Rain(PApplet app, int x, int y, int sp) {

		this.x = x;
		this.y = y;
		this.s = sp;

		this.app = app;
	}

	public void draw(int excludeRangeX1,int excludeRangeX2, int excludeY) {

		int mx = 0;
		if (mx <= 0) {
			mx = 2;
		}

		y = y + s * mx;
		x2 = x;
		y2 = y + 50;

		app.stroke(200);
		drawRainDrop(excludeRangeX1,excludeRangeX2, excludeY);
		
		if (y >= app.height - 100) {
			app.noFill();
			app.stroke(200);
			app.ellipse(x, app.height - app.random(50, 70), app.random(25, 100), 10);
			y = -120;
		}

	}

	public void drawRainDrop(int x1, int x3, int posY) {
		if (x > x1 && x < x3) {
			if (y <= posY) {
				app.line(x, y, x2, y2);
			} 

		}
		else {
			app.line(x, y, x2, y2);
		}
	}

	public int getX() {
		return x2;
	}

	public int getY() {
		return y2;
	}

}
