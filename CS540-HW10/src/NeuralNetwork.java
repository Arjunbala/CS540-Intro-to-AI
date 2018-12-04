public class NeuralNetwork {
	
	double[] mWeights = new double[9];
	double m_x1;
	double m_x2;
	double m_y;
	Relu m_r1;
	Relu m_r2;
	Sigmoid m_s;
	double[] mVDerivatives = new double[3];
	double[] mUDerivatives = new double[3];
	double[] mWeightDerivatives = new double[9];
	
	public NeuralNetwork(double[] weights, double x1, double x2, double y) {
		mWeights = weights;
		m_x1 = x1;
		m_x2 = x2;
		m_y = y;
		performComputation();
	}
	
	public double[] getResults() {
		double results[] = new double[6];
		results[0] = m_r1.getCumulativeInput();
		results[1] = m_r1.getOutput();
		results[2] = m_r2.getCumulativeInput();
		results[3] = m_r2.getOutput();
		results[4] = m_s.getCumulativeInput();
		results[5] = m_s.getOutput();
		return results;
	}
	
	public double getError() {
		return Math.pow((m_s.getOutput()-m_y),2)/2;
	}
	
    public double getSigmoidOutputDerivative() {
		return mVDerivatives[2];
	}
    
    public double getSigmoidInputDerivative() {
    	return mUDerivatives[2];
    }
    
    public double getRelu1OutputDerivative() {
    	return mVDerivatives[0];
    }
    
    public double getRelu2OutputDerivative() {
    	return mVDerivatives[1];
    }
    
    public double getRelu1InputDerivative() {
    	return mUDerivatives[0];
    }
    
    public double getRelu2InputDerivative() {
    	return mUDerivatives[1];
    }
    
    public double[] getWeightDerivatives() {
    	return mWeightDerivatives;
    }
	
	private void performComputation() {
		double[] relu1_weights = {mWeights[0], mWeights[1], mWeights[2]};
		double[] relu1_inputs = {1.0,m_x1,m_x2};
		m_r1 = new Relu(relu1_weights, relu1_inputs);
		double[] relu2_weights = {mWeights[3], mWeights[4], mWeights[5]};
		double[] relu2_inputs = {1.0,m_x1,m_x2};
		m_r2 = new Relu(relu2_weights, relu2_inputs);
		double[] sigmoid_weights = {mWeights[6], mWeights[7], mWeights[8]};
		double[] sigmoid_inputs = {1,m_r1.getOutput(),m_r2.getOutput()};
		m_s = new Sigmoid(sigmoid_weights, sigmoid_inputs);
		mVDerivatives[2] =  m_s.getOutput()-m_y;
		mUDerivatives[2] = mVDerivatives[2]*m_s.getOutput()*(1 - m_s.getOutput());
		mVDerivatives[1] = mWeights[8]*mUDerivatives[2];
		if(m_r2.getCumulativeInput() >= 0) {
			mUDerivatives[1] = mVDerivatives[1];
		} else {
			mUDerivatives[1] = 0;
		}
		mVDerivatives[0] = mWeights[7]*mUDerivatives[2];
		if(m_r1.getCumulativeInput() >= 0) {
			mUDerivatives[0] = mVDerivatives[0];
		} else {
			mUDerivatives[0] = 0;
		}
		computeWeightDerivatives();
	}
	
	private void computeWeightDerivatives() {
		mWeightDerivatives[0] = 1*mUDerivatives[0];
		mWeightDerivatives[1] = m_x1*mUDerivatives[0];
		mWeightDerivatives[2] = m_x2*mUDerivatives[0];
		
		mWeightDerivatives[3] = 1*mUDerivatives[1];
		mWeightDerivatives[4] = m_x1*mUDerivatives[1];
		mWeightDerivatives[5] = m_x2*mUDerivatives[1];
		
		mWeightDerivatives[6] = 1*mUDerivatives[2];
		mWeightDerivatives[7] = m_r1.getOutput()*mUDerivatives[2];
		mWeightDerivatives[8] = m_r2.getOutput()*mUDerivatives[2];
	}
}