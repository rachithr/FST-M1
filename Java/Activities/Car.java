package activities;

public class Car {

	String color;
	String transmission;
	int make;
	int tyres;
	int doors;

	public Car(String color,String transmission,int tyre,int door,int make) {
		
		this.tyres =tyre;
		this.doors =door;	
		this.make=make;
		this.color=color;
		this.transmission=transmission;
		
		//this.input("black","Manual",4,4,4);
		this.displayCharacteristics();
		this.accelarate();
		this.brake();
	}
	
	public Car() {
		this.input("black","Manual",4,4,4);
		
	}
	
	public void input(String color,String transmission,int tyre,int door,int make) {
		this.tyres =tyre;
		this.doors =door;	
		this.make=make;
		this.color=color;
		this.transmission=transmission;
	}

	public void displayCharacteristics() {
		System.out.println(tyres);
		System.out.println(doors);
		System.out.println(make);
		System.out.println(color);
		System.out.println(transmission);
	}
	
	public void accelarate() {
		System.out.println("Car is moving forward.");
	}
	public void brake() {
		System.out.println("Car has stopped.");
	}
}
