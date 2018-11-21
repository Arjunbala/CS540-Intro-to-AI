import java.util.ArrayList;

public class ParameterizedIceStats {
	
	private ArrayList<IceData> mIceData;
	private double mB0;
	private double mB1;
	
	public ParameterizedIceStats(ArrayList<IceData> data, double b0, double b1) {
		mIceData = data;
		mB0 = b0;
		mB1 = b1;
	}
	
	public double getMSE() {
		double sum = 0.0;
		for(int i=0;i<mIceData.size();i++) {
			IceData item = mIceData.get(i);
			sum += Math.pow((mB0 + mB1*item.getYear() - item.getNumberOfDaysIce()), 2);
		}
		return sum/mIceData.size();
	}
	
	public Gradients getGradients() {
		double gradient1_sum = 0.0;
		double gradient2_sum = 0.0;
		double n = mIceData.size();
		for(int i=0;i<n;i++) {
			IceData item = mIceData.get(i);
			gradient1_sum += mB0 + mB1*item.getYear() - item.getNumberOfDaysIce();
			gradient2_sum += (mB0 + mB1*item.getYear() - item.getNumberOfDaysIce())*item.getYear();
		}
		return new Gradients(2*gradient1_sum/n, 2*gradient2_sum/n);
	}
	
	public Parameters getClosedFormSolution() {
		IceDataStats stats = new IceDataStats(mIceData);
		double year_mean = stats.getYearMean();
		double ice_mean = stats.getMean();
		int n = mIceData.size();
		
		double b1_numerator = 0.0;
		double b1_denominator = 0.0;
		for(int i=0;i<n;i++) {
			IceData item = mIceData.get(i);
			b1_numerator += (item.getYear() - year_mean)*(item.getNumberOfDaysIce() - ice_mean);
			b1_denominator += Math.pow(item.getYear() - year_mean, 2);
		}
		double b1 = b1_numerator/b1_denominator;
		double b0 = ice_mean - (b1 * year_mean);
		return new Parameters(b0, b1);
	}
}