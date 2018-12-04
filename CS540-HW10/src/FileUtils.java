import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtils {
	
	public enum FILE_TYPE
	{TRAINING_SET, EVALUATION_SET, TEST_SET};
	
	public static ArrayList<DataItem> getDataItems(FILE_TYPE type) {
		String fileName = null;
		if(type == FILE_TYPE.TRAINING_SET) {
			fileName = "training.txt";
		} else if(type == FILE_TYPE.EVALUATION_SET) {
			fileName = "evaluation.txt";
		} else if(type == FILE_TYPE.TEST_SET){
			fileName = "test.txt";
		}
		return getDataItems(fileName);
	}
	
	private static ArrayList<DataItem> getDataItems(String fileName) {
		ArrayList<DataItem> dataItems = new ArrayList<DataItem>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		String line;
		try {
			while ((line = br.readLine()) != null) {
				String[] split_line = line.split(" ");
				dataItems.add(new DataItem(Double.parseDouble(split_line[0]),
						Double.parseDouble(split_line[1]), Double.parseDouble(split_line[2])));
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
		return dataItems;
	}
}