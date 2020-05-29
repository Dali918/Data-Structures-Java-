package InterfacePractice;

public class Mercedes implements Vehicle {

	@Override
	public void brake() {
		// TODO Auto-generated method stub
		System.out.print("Mercedes is braking");
	}

	@Override
	public void accelerate() {
		// TODO Auto-generated method stub
		System.out.print("Mercedes is accelerating");
	}

	@Override
	public void cruise() {
		// TODO Auto-generated method stub
		System.out.print("Mercedes is cruising");
	}

}
