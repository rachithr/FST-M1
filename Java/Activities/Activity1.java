package activities;


public class Activity1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sedan vento= new Sedan("Black", "Manual", 4, 2, 2014);
//		Car SUV=new Car("Gold", "Automatic", 4, 2, 2019);
		SUV Hummer = new SUV();
		
//		Sedan.displayCharacteristics();
//		Sedan.accelarate();
//		Sedan.brake();
//		SUV.displayCharacteristics();
		vento.sedanchar();
		Hummer.SUVchar();
	}

}
