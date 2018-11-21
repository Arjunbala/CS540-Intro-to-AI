public class Gradients {
	private double mGradient1;
	private double mGradient2;
	
	public Gradients(double g1, double g2) {
		mGradient1 = g1;
		mGradient2 = g2;
	}
	
	public double getFirstGradient() {
		return mGradient1;
	}
	
	public double getSecondGradient() {
		return mGradient2;
	}
}