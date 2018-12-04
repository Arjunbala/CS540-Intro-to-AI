public class Perceptron {
	protected double[] mWeights;
	protected double[] mInputs;
	protected double mCumulativeInput;
	protected double mOutput;

	public Perceptron(double[] weights, double[] inputs) {
		mWeights = weights;
		mInputs = inputs;
		computeCumulativeInput();
		computeOutput();
	}

	public double getCumulativeInput() {
		return mCumulativeInput;
	}
	
	public double getOutput() {
		return mOutput;
	}
	
	private void computeCumulativeInput() {
        mCumulativeInput = 0;
        for(int i=0;i<mWeights.length;i++) {
        	mCumulativeInput += mWeights[i]*mInputs[i];
        }
	}
	
	protected void computeOutput() {
		// Different layers will override this method
		mOutput = 0;
	}
}