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
}