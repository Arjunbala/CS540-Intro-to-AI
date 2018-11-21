import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {
	public static ArrayList<IceData> getIceDataFromFile() {
		return getIceDataFromFile(false);
	}
	
	public static ArrayList<IceData> getIceDataFromFile(boolean normalize) {
		double mean = computeMean();
		double standardDeviation = computeStandardDeviation(mean);
		BufferedReader br = null;
		ArrayList<IceData> iceDataList = new ArrayList<IceData>();
		int year = 1855;
		try {
			br = new BufferedReader(new FileReader("ice_data.txt"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		String line;
		try {
			while ((line = br.readLine()) != null) {
				try {
					int ice = Integer.parseInt(line);
					iceDataList.add(new IceData(normalize(year, mean, standardDeviation, normalize),ice));
					year++;
				} catch (NumberFormatException e) {
					// do nothing. Just continue.
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return iceDataList;
	}
	
	private static double normalize(int year, double mean, double std, boolean normalize) {
		if(normalize) {
			return (year-mean)/std;
		}
		return year;
	}
	
	private static double computeMean() {
		double sum = 0.0;
		int n = 0;
		for(int i=1855;i<=2017;i++) {
			sum = sum + i;
			n++;
		}
		return sum/n;
	}
	
	private static double computeStandardDeviation(double mean) {
		double sum = 0.0;
		int n = 0;
		for(int i=1855;i<=2017;i++) {
			sum = sum + Math.pow((i-mean), 2);
			n++;
		}
		return Math.sqrt(sum/(n-1));
	}
}