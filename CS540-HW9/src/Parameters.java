public class Parameters {
	private double mB0;
	private double mB1;
	
	public Parameters(double b0, double b1) {
		mB0 = b0;
		mB1 = b1;
	}
	
	public double getFirstParameter() {
		return mB0;
	}
	
	public double getSecondParameter() {
		return mB1;
	}
}