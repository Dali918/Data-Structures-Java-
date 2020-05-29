package InterfacePractice;

public class Honda implements Vehicle {

	@Override
	public void brake() {
		// TODO Auto-generated method stub
		System.out.print("Honda is braking");
	}

	@Override
	public void accelerate() {
		// TODO Auto-generated method stub
		System.out.print("Honda is accelerating");
	}

	@Override
	public void cruise() {
		// TODO Auto-generated method stub
		System.out.print("Honda is cruising");
	}

}
