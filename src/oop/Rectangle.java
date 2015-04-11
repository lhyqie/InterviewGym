package oop;

public class Rectangle extends Shape {
	
	private int width;
	private int height;
	
	
	public Rectangle(int w, int h){
		this.width = w;
		this.height = h;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getArea() {
		return height * width;
	}

}
