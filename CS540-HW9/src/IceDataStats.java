import java.util.ArrayList;

public class IceDataStats {
	private ArrayList<IceData> mIceDataList;
	private int mNumberPoints;
	private double mMean;
	private double mStandardDeviation;
	private double mYearMean;
	
	private boolean mMeanComputed;
	private boolean mStandardDevitionComputed;
	private boolean mYearMeanComputed;
	
	public IceDataStats(ArrayList<IceData> data) {
		mIceDataList = data;
	}
	
	public int getNumberOfPoints() {
		if(mMeanComputed) {
			return mNumberPoints;
		}
		computeMean();
		return mNumberPoints;
	}
	
	public double getMean() {
		if(mMeanComputed) {
			return mMean;
		}
		computeMean();
		return mMean;
	}
	
	public double getStandardDeviation() {
		if(mStandardDevitionComputed) {
			return mStandardDeviation;
		}
		if(!mMeanComputed) {
			computeMean();
		}
		computeStandardDeviation();
		return mStandardDeviation;
	}
	
	public double getYearMean() {
		if(mYearMeanComputed) {
			return mYearMean;
		}
		double sum = 0.0;
		int n = mIceDataList.size();
		for(int i=0;i<n;i++) {
			sum += mIceDataList.get(i).getYear();
		}
		mYearMean = sum/n;
		return mYearMean;
	}
	
	private void computeMean() {
		int number_points = 0;
		double sum = 0.0;
		for(int i=0;i<mIceDataList.size();i++) {
			sum += mIceDataList.get(i).getNumberOfDaysIce();
			number_points++;
		}
		mNumberPoints = number_points;
		mMean = sum/number_points;
		mMeanComputed = true;
	}
	
	private void computeStandardDeviation() {
		double sum = 0.0;
		for(int i=0;i<mIceDataList.size();i++) {
			sum += Math.pow(mIceDataList.get(i).getNumberOfDaysIce() - mMean, 2);
		}
		mStandardDeviation = Math.sqrt(sum/(mNumberPoints-1));
		mStandardDevitionComputed = true;
	}
}