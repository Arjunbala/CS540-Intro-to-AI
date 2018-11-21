public class IceData {
	private double mYear;
	private int mNoDaysIce;
	
	public IceData() {
		// default constructor
		mYear = -1;
		mNoDaysIce = -1;
	}
	
	public IceData(double year, int no_days_ice) {
		// Parameterized constructor
		mYear = year;
		mNoDaysIce = no_days_ice;
	}
	
	public double getYear() {
		return mYear;
	}
	
	public int getNumberOfDaysIce() {
		return mNoDaysIce;
	}
}