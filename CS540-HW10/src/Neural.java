import java.util.ArrayList;
import java.util.Arrays;

public class Neural {
	public static void main(String args[]) {
		if (args.length == 0) {
			System.out.println("java FLAG [args]");
			System.exit(0);
		}
		int flag = Integer.parseInt(args[0]);
		switch(flag) {
		case 100:
			if (args.length < 12 || args.length > 12) {
				System.out.println(
						"Flag 100 - invalid number of arguments");
				System.exit(0);
			}
			double[] weights_vector_100 = {Double.parseDouble(args[1]), Double.parseDouble(args[2]),
					Double.parseDouble(args[3]), Double.parseDouble(args[4]),
					Double.parseDouble(args[5]), Double.parseDouble(args[6]),
					Double.parseDouble(args[7]), Double.parseDouble(args[8]),
					Double.parseDouble(args[9])};
			handleFlag100(weights_vector_100, Double.parseDouble(args[10]),
					Double.parseDouble(args[11]));
			break;
		case 200:
			if (args.length < 13 || args.length > 13) {
				System.out.println(
						"Flag 200 - invalid number of arguments");
				System.exit(0);
			}
			double[] weights_vector_200 = {Double.parseDouble(args[1]), Double.parseDouble(args[2]),
					Double.parseDouble(args[3]), Double.parseDouble(args[4]),
					Double.parseDouble(args[5]), Double.parseDouble(args[6]),
					Double.parseDouble(args[7]), Double.parseDouble(args[8]),
					Double.parseDouble(args[9])};
			handleFlag200(weights_vector_200, Double.parseDouble(args[10]),
					Double.parseDouble(args[11]),Double.parseDouble(args[12]));
			break;
		case 300:
			if (args.length < 13 || args.length > 13) {
				System.out.println(
						"Flag 300 - invalid number of arguments");
				System.exit(0);
			}
			double[] weights_vector_300 = {Double.parseDouble(args[1]), Double.parseDouble(args[2]),
					Double.parseDouble(args[3]), Double.parseDouble(args[4]),
					Double.parseDouble(args[5]), Double.parseDouble(args[6]),
					Double.parseDouble(args[7]), Double.parseDouble(args[8]),
					Double.parseDouble(args[9])};
			handleFlag300(weights_vector_300, Double.parseDouble(args[10]),
					Double.parseDouble(args[11]),Double.parseDouble(args[12]));
			break;
		case 400:
			if (args.length < 13 || args.length > 13) {
				System.out.println(
						"Flag 400 - invalid number of arguments");
				System.exit(0);
			}
			double[] weights_vector_400 = {Double.parseDouble(args[1]), Double.parseDouble(args[2]),
					Double.parseDouble(args[3]), Double.parseDouble(args[4]),
					Double.parseDouble(args[5]), Double.parseDouble(args[6]),
					Double.parseDouble(args[7]), Double.parseDouble(args[8]),
					Double.parseDouble(args[9])};
			handleFlag400(weights_vector_400, Double.parseDouble(args[10]),
					Double.parseDouble(args[11]),Double.parseDouble(args[12]));
			break;
		case 500:
			if (args.length < 14 || args.length > 14) {
				System.out.println(
						"Flag 500 - invalid number of arguments");
				System.exit(0);
			}
			double[] weights_vector_500 = {Double.parseDouble(args[1]), Double.parseDouble(args[2]),
					Double.parseDouble(args[3]), Double.parseDouble(args[4]),
					Double.parseDouble(args[5]), Double.parseDouble(args[6]),
					Double.parseDouble(args[7]), Double.parseDouble(args[8]),
					Double.parseDouble(args[9])};
			handleFlag500(weights_vector_500, Double.parseDouble(args[10]),
					Double.parseDouble(args[11]),Double.parseDouble(args[12]),
					Double.parseDouble(args[13]));
			break;
		case 600:
			if (args.length < 11 || args.length > 11) {
				System.out.println(
						"Flag 600 - invalid number of arguments");
				System.exit(0);
			}
			double[] weights_vector_600 = {Double.parseDouble(args[1]), Double.parseDouble(args[2]),
					Double.parseDouble(args[3]), Double.parseDouble(args[4]),
					Double.parseDouble(args[5]), Double.parseDouble(args[6]),
					Double.parseDouble(args[7]), Double.parseDouble(args[8]),
					Double.parseDouble(args[9])};
			handleFlag600(weights_vector_600, Double.parseDouble(args[10]));
			break;
		case 700:
			if (args.length < 12 || args.length > 12) {
				System.out.println(
						"Flag 700 - invalid number of arguments");
				System.exit(0);
			}
			double[] weights_vector_700 = {Double.parseDouble(args[1]), Double.parseDouble(args[2]),
					Double.parseDouble(args[3]), Double.parseDouble(args[4]),
					Double.parseDouble(args[5]), Double.parseDouble(args[6]),
					Double.parseDouble(args[7]), Double.parseDouble(args[8]),
					Double.parseDouble(args[9])};
			handleFlag700(weights_vector_700, Double.parseDouble(args[10]), Integer.parseInt(args[11]));
			break;
		case 800:
			if (args.length < 12 || args.length > 12) {
				System.out.println(
						"Flag 800 - invalid number of arguments");
				System.exit(0);
			}
			double[] weights_vector_800 = {Double.parseDouble(args[1]), Double.parseDouble(args[2]),
					Double.parseDouble(args[3]), Double.parseDouble(args[4]),
					Double.parseDouble(args[5]), Double.parseDouble(args[6]),
					Double.parseDouble(args[7]), Double.parseDouble(args[8]),
					Double.parseDouble(args[9])};
			handleFlag800(weights_vector_800, Double.parseDouble(args[10]), Integer.parseInt(args[11]));
			break;
		}
	}
	
	public static void handleFlag100(double[] weights_vector, double x1, double x2) {
		NeuralNetwork n = new NeuralNetwork(weights_vector, x1, x2, 0);
		double[] results = n.getResults();
		for(int i=0;i<results.length;i++) {
			System.out.print(String.format("%.5f", results[i]));
			System.out.print(" ");
		}
		System.out.print("\n");
	}
	
	public static void handleFlag200(double[] weights_vector, double x1, double x2, double y) {
		NeuralNetwork n = new NeuralNetwork(weights_vector, x1, x2, y);
		System.out.print(String.format("%.5f", n.getError()) + " ");
		System.out.print(String.format("%.5f", n.getSigmoidOutputDerivative()) + " ");
		System.out.print(String.format("%.5f", n.getSigmoidInputDerivative()));
		System.out.print("\n");
	}
	
	public static void handleFlag300(double[] weights_vector, double x1, double x2, double y) {
		NeuralNetwork n = new NeuralNetwork(weights_vector, x1, x2, y);
		System.out.print(String.format("%.5f", n.getRelu1OutputDerivative()) + " ");
		System.out.print(String.format("%.5f", n.getRelu1InputDerivative()) + " ");
		System.out.print(String.format("%.5f", n.getRelu2OutputDerivative()) + " ");
		System.out.print(String.format("%.5f", n.getRelu2InputDerivative()));
		System.out.print("\n");
	}
	
	public static void handleFlag400(double[] weights_vector, double x1, double x2, double y) {
		NeuralNetwork n = new NeuralNetwork(weights_vector, x1, x2, y);
		double[] weight_derivatives = n.getWeightDerivatives();
		for(int i=0;i<weight_derivatives.length;i++) {
			System.out.print(String.format("%.5f", weight_derivatives[i]) + " ");
		}
		System.out.print("\n");
	}
	
	public static void handleFlag500(double[] weights_vector, double x1, double x2, double y, double learning_rate) {
		NeuralNetwork n = new NeuralNetwork(weights_vector, x1, x2, y);
		for(int i=0;i<weights_vector.length;i++) {
			System.out.print(String.format("%.5f", weights_vector[i]) + " ");
		}
		System.out.print("\n");
		System.out.println(String.format("%.5f", n.getError()));
		double[] new_weights = new double[weights_vector.length];
		double[] weight_gradients = n.getWeightDerivatives();
		for(int i=0;i<new_weights.length;i++) {
			new_weights[i] = weights_vector[i] - (learning_rate*weight_gradients[i]);
		}
		NeuralNetwork new_nn = new NeuralNetwork(new_weights, x1, x2, y);
		for(int i=0;i<new_weights.length;i++) {
			System.out.print(String.format("%.5f", new_weights[i]) + " ");
		}
		System.out.print("\n");
		System.out.println(String.format("%.5f", new_nn.getError()));
	}
	
	public static void handleFlag600(double[] weights_vector, double learning_rate) {
		ArrayList<DataItem> training_items = FileUtils.getDataItems(FileUtils.FILE_TYPE.TRAINING_SET);
		ArrayList<DataItem> evaluation_items = FileUtils.getDataItems(FileUtils.FILE_TYPE.EVALUATION_SET);
		for(int i=0;i<training_items.size();i++) {
			System.out.println(String.format("%.5f", training_items.get(i).get_x1()) + " "
					+ String.format("%.5f", training_items.get(i).get_x2()) + " "
					+ String.format("%.5f", training_items.get(i).get_y()));
			NeuralNetwork n = new NeuralNetwork(weights_vector, training_items.get(i).get_x1(), 
					training_items.get(i).get_x2(), training_items.get(i).get_y());
			double[] weight_gradients = n.getWeightDerivatives();
			for(int j=0;j<weights_vector.length;j++) {
				weights_vector[j] = weights_vector[j] - (learning_rate*weight_gradients[j]);
			}
			for(int j=0;j<weights_vector.length;j++) {
				System.out.print(String.format("%.5f", weights_vector[j]) + " ");
			}
			System.out.print("\n");
			System.out.println(String.format("%.5f", computeEvaluationSetError(evaluation_items, weights_vector)));
		}
	}
	
	public static void handleFlag700(double[] weights_vector, double learning_rate, int epochs) {
		ArrayList<DataItem> training_items = FileUtils.getDataItems(FileUtils.FILE_TYPE.TRAINING_SET);
		ArrayList<DataItem> evaluation_items = FileUtils.getDataItems(FileUtils.FILE_TYPE.EVALUATION_SET);
		for(int k=0;k<epochs;k++) {
			for(int i=0;i<training_items.size();i++) {
				NeuralNetwork n = new NeuralNetwork(weights_vector, training_items.get(i).get_x1(), 
						training_items.get(i).get_x2(), training_items.get(i).get_y());
				double[] weight_gradients = n.getWeightDerivatives();
				for(int j=0;j<weights_vector.length;j++) {
					weights_vector[j] = weights_vector[j] - (learning_rate*weight_gradients[j]);
				}
			}
			for(int j=0;j<weights_vector.length;j++) {
				System.out.print(String.format("%.5f", weights_vector[j]) + " ");
			}
			System.out.print("\n");
			System.out.println(String.format("%.5f", computeEvaluationSetError(evaluation_items, weights_vector)));
		}
	}
	
	public static void handleFlag800(double[] weights_vector, double learning_rate, int max_epochs) {
		ArrayList<DataItem> training_items = FileUtils.getDataItems(FileUtils.FILE_TYPE.TRAINING_SET);
		ArrayList<DataItem> evaluation_items = FileUtils.getDataItems(FileUtils.FILE_TYPE.EVALUATION_SET);
		ArrayList<DataItem> test_items = FileUtils.getDataItems(FileUtils.FILE_TYPE.TEST_SET);
		
		int epochs = 0;
		boolean error_increased = false;
		double current_error = Double.MAX_VALUE;
		double[] old_weights = new double[weights_vector.length];
		while(epochs < max_epochs && !error_increased) {
			old_weights = Arrays.copyOf(weights_vector,weights_vector.length);
			for(int i=0;i<training_items.size();i++) {
				NeuralNetwork n = new NeuralNetwork(weights_vector, training_items.get(i).get_x1(), 
						training_items.get(i).get_x2(), training_items.get(i).get_y());
				double[] weight_gradients = n.getWeightDerivatives();
				for(int j=0;j<weights_vector.length;j++) {
					weights_vector[j] = weights_vector[j] - (learning_rate*weight_gradients[j]);
				}
			}
			double new_error = computeEvaluationSetError(evaluation_items, weights_vector);
			if(new_error > current_error) {
				error_increased = true;
			} else {
				current_error = new_error;
			}
			epochs++;
		}
		System.out.println(epochs);
		for(int i=0;i<weights_vector.length;i++) {
			System.out.print(String.format("%.5f", weights_vector[i]) + " ");
		}
		System.out.print("\n");
		System.out.println(String.format("%.5f", computeEvaluationSetError(evaluation_items, weights_vector)));
		System.out.println(String.format("%.5f", computeTestSetAccuracy(test_items, weights_vector)));
	}
	
	private static double computeEvaluationSetError(ArrayList<DataItem> evaluation_items, double[] weights_vector) {
		double total_error = 0;
		for(int i=0;i<evaluation_items.size();i++) {
			NeuralNetwork n = new NeuralNetwork(weights_vector, evaluation_items.get(i).get_x1(), 
					evaluation_items.get(i).get_x2(), evaluation_items.get(i).get_y());
			total_error = total_error + n.getError();
		}
		return total_error;
	}
	
	private static double computeTestSetAccuracy(ArrayList<DataItem> test_items, double[] weights_vector) {
		int total_correct = 0;
		int total_test_items = test_items.size();
		for(int i=0;i<test_items.size();i++) {
			NeuralNetwork n = new NeuralNetwork(weights_vector, test_items.get(i).get_x1(), 
					test_items.get(i).get_x2(), test_items.get(i).get_y());
			double[] results = n.getResults();
			double prediction = results[results.length-1];
			if(prediction >= 0.5) {
				prediction = 1;
			} else {
				prediction = 0;
			}
			if(prediction == test_items.get(i).get_y()) {
				total_correct++;
			}
		}
		return (double)total_correct/total_test_items;
	}
}