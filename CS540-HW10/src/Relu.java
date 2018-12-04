public class Relu extends Perceptron {

	public Relu(double[] weights, double[] inputs) {
		super(weights, inputs);
		computeOutput();
	}
	
	protected void computeOutput() {
		mOutput = Math.max(mCumulativeInput, 0);
	}
}