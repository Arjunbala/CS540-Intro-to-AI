public class Sigmoid extends Perceptron {

	public Sigmoid(double[] weights, double[] inputs) {
		super(weights, inputs);
		computeOutput();
	}
	
	protected void computeOutput() {
		mOutput = 1/(1 + Math.exp(-1*mCumulativeInput));
	}
}