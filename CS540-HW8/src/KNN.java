import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A kNN classification algorithm implementation.
 * 
 */

public class KNN {

	/**
	 * In this method, you should implement the kNN algorithm. You can add other
	 * methods in this class, or create a new class to facilitate your work. If
	 * you create other classes, DO NOT FORGET to include those java files when
	 * preparing your code for hand in.
	 *
	 * Also, Please DO NOT MODIFY the parameters or return values of this
	 * method, or any other provided code. Again, create your own methods or
	 * classes as you need them.
	 * 
	 * @param trainingData An Item array of training data
	 * @param testData     An Item array of test data
	 * @param k            The number of neighbors to use for classification
	 * @return The object KNNResult contains classification accuracy, category
	 *         assignment, etc.
	 */
	public KNNResult classify(Item[] trainingData, Item[] testData, int k) {

		/* ... YOUR CODE GOES HERE ... */
		// for each test item in testData

		// find kNN in trainingData

		// get predicted category, save in KNNResult.categoryAssignment

		// save kNN in KNNResult.nearestNeighbors

		// calculate accuracy

		double correctly_predicted_values = 0;
		String[] categoryAssignment = new String[testData.length];
		String[][] nearestNeighbors = new String[testData.length][k];

		for (int i = 0; i < testData.length; i++) {
			// Data structure to store k nearest neighbors for testItem
			ArrayList<KNNArrayItem> k_nearest_neighbors = new ArrayList<KNNArrayItem>();
			// Now, by default keep the k_nearest_neighbors as the first k
			// values in the test data. If the training set size is less than k,
			// add everything
			int j = 0;
			for (j = 0; j < k && j < trainingData.length; j++) {
				KNNArrayItem item = new KNNArrayItem(j,
						computeDistance(testData[i].features,
								trainingData[j].features),
						trainingData[j].category, trainingData[j].name);
				k_nearest_neighbors.add(item);
			}
			Collections.sort(k_nearest_neighbors,
					new NearestNeighborComparator());
			while (j < trainingData.length) {
				KNNArrayItem newItem = new KNNArrayItem(j,
						computeDistance(testData[i].features,
								trainingData[j].features),
						trainingData[j].category, trainingData[j].name);
				if ((newItem.getDistance() < k_nearest_neighbors
						.get(k_nearest_neighbors.size() - 1).getDistance())
						|| (newItem.getDistance() == k_nearest_neighbors
								.get(k_nearest_neighbors.size() - 1)
								.getDistance()
								&& new CategoryComparator(newItem.getCategory(),
										k_nearest_neighbors
												.get(k_nearest_neighbors.size()
														- 1)
												.getCategory()).getComparision() > 0)) {
					k_nearest_neighbors.remove(k_nearest_neighbors.size() - 1);
					k_nearest_neighbors.add(newItem);
					// Sort now to get resorted k_nearest_neighbors
					Collections.sort(k_nearest_neighbors,
							new NearestNeighborComparator());
				}
				j++;
			}
			int[] count_categories = new int[ItemCategory.numberCategories()];
			for(int m=0;m<ItemCategory.numberCategories();m++) {
				count_categories[m] = 0;
			}
			for(int m=0;m<k_nearest_neighbors.size();m++) {
				count_categories[ItemCategory.getIntegerForCategory(k_nearest_neighbors.get(m).getCategory())-1]++;
			}
			int max_count = count_categories[0];
			int max_index = 0;
			for(int m=1;m<ItemCategory.numberCategories();m++) {
				if(count_categories[m] >= max_count) {
					max_count = count_categories[m];
					max_index = m;
				}
			}
			
			// Now populate row i of the result
			categoryAssignment[i] = ItemCategory.getCategoryFromInteger(max_index+1);
			for(int m=0;m<k_nearest_neighbors.size();m++) {
				nearestNeighbors[i][m] = k_nearest_neighbors.get(m).getName();
			}
			if (categoryAssignment[i].equals(testData[i].category)) {
				correctly_predicted_values++;
			}
		}
		KNNResult result = new KNNResult();
		result.accuracy = (double) correctly_predicted_values/testData.length;
		result.categoryAssignment = categoryAssignment;
		result.nearestNeighbors = nearestNeighbors;
		return result;
	}

	private double computeDistance(double[] testFeatures,
			double[] trainingFeatures) {
		double square_distance = 0;
		for (int i = 0; i < testFeatures.length; i++) {
			square_distance = square_distance
					+ Math.pow((testFeatures[i] - trainingFeatures[i]), 2);
		}
		return Math.sqrt(square_distance);
	}
}

class ItemCategory {
	public final static String NATION = "nation";
	public final static String MACHINE = "machine";
	public final static String FRUIT = "fruit";

	public final static int CATEGORY_NATION = 3;
	public final static int CATEGORY_MACHINE = 2;
	public final static int CATEGORY_FRUIT = 1;
	
	public static int getIntegerForCategory(String c) {
		if (c.equals(ItemCategory.NATION)) {
			return ItemCategory.CATEGORY_NATION;
		} else if (c.equals(ItemCategory.MACHINE)) {
			return ItemCategory.CATEGORY_MACHINE;
		} else {
			return ItemCategory.CATEGORY_FRUIT;
		}
	}
	
	public static String getCategoryFromInteger(int category) {
		if(category == CATEGORY_FRUIT) {
			return FRUIT;
		} else if (category == CATEGORY_NATION) {
			return NATION;
		} else {
			return MACHINE;
		}
	}
	
	public static int numberCategories() {
		return 3;
	}
}

class CategoryComparator {
	private int first_category;
	private int second_category;

	public CategoryComparator(String c1, String c2) {
		first_category = ItemCategory.getIntegerForCategory(c1);
		second_category = ItemCategory.getIntegerForCategory(c2);
	}

	public int getComparision() {
		return first_category - second_category;
	}
}

class NearestNeighborComparator implements Comparator<KNNArrayItem> {

	@Override
	public int compare(KNNArrayItem item1, KNNArrayItem item2) {
		if (item1.getDistance() != item2.getDistance()) {
			return (item1.getDistance() - item2.getDistance() > 0)?1:-1;
		}
		return new CategoryComparator(item1.getCategory(), item2.getCategory())
				.getComparision();
	}
}

class KNNArrayItem {
	private int index; // index within the training set
	private double distance; // distance of the test item from the training set
								// item at index
	private String category; // category of this item
	private String name;

	public KNNArrayItem(int index, double distance, String category, String name) {
		this.index = index;
		this.distance = distance;
		this.category = category;
		this.name = name;
	}

	public int getIndex() {
		return this.index;
	}

	public double getDistance() {
		return this.distance;
	}

	public String getCategory() {
		return this.category;
	}
	
	public String getName() {
		return this.name;
	}
}