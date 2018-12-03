import java.util.ArrayList;
import java.util.Random;

public class Ice {
	public static void main(String args[]) {
		if (args.length == 0) {
			System.out.println("java FLAG [arg1 arg2]");
			System.exit(0);
		}
		int flag = Integer.parseInt(args[0]);
		switch (flag) {
		case 100:
			if (args.length > 1) {
				System.out.println(
						"Flag 100 does not take in additional arguments");
				return;
			}
			handleFlag100();
			break;
		case 200:
			if (args.length > 1) {
				System.out.println(
						"Flag 200 does not take in additional arguments");
				return;
			}
			handleFlag200();
			break;
		case 300:
			if (args.length != 3) {
				System.out.println("Flag 300 needs 3 arguments");
			}
			double b0 = Double.parseDouble(args[1]);
			double b1 = Double.parseDouble(args[2]);
			handleFlag300(b0, b1);
			break;
		case 400:
			if (args.length != 3) {
				System.out.println("Flag 400 needs 3 arguments");
			}
			b0 = Double.parseDouble(args[1]);
			b1 = Double.parseDouble(args[2]);
			handleFlag400(b0, b1);
			break;
		case 500:
			if (args.length != 3) {
				System.out.println("Flag 500 needs 3 arguments");
			}
			double learning_rate = Double.parseDouble(args[1]);
			int iterations = Integer.parseInt(args[2]);
			handleFlag500(learning_rate, iterations);
			break;
		case 600:
			if (args.length > 1) {
				System.out.println(
						"Flag 600 does not take in additional arguments");
				return;
			}
			handleFlag600();
			break;
		case 700:
			if (args.length > 2) {
				System.out.println(
						"Flag 700 takes in one additional argument only");
				return;
			}
			int year = Integer.parseInt(args[1]);
			handleFlag700(year);
			break;
		case 800:
			if (args.length != 3) {
				System.out.println("Flag 800 needs 3 arguments");
				return;
			}
			learning_rate = Double.parseDouble(args[1]);
			iterations = Integer.parseInt(args[2]);
			handleFlag800(learning_rate, iterations);
			break;
		case 900:
			if (args.length != 3) {
				System.out.println("Flag 900 needs 3 arguments");
				return;
			}
			learning_rate = Double.parseDouble(args[1]);
			iterations = Integer.parseInt(args[2]);
			handleFlag900(learning_rate, iterations);
			break;
		}
	}

	private static void handleFlag100() {
		ArrayList<IceData> iceList = Utils.getIceDataFromFile();
		for (int i = 0; i < iceList.size(); i++) {
			IceData item = iceList.get(i);
			System.out
					.println((int)item.getYear() + " " + item.getNumberOfDaysIce());
		}
	}

	private static void handleFlag200() {
		ArrayList<IceData> iceList = Utils.getIceDataFromFile();
		IceDataStats stats = new IceDataStats(iceList);
		System.out.println(stats.getNumberOfPoints());
		System.out.println(String.format("%.2f", stats.getMean()));
		System.out.println(String.format("%.2f", stats.getStandardDeviation()));
	}

	private static void handleFlag300(double b0, double b1) {
		ArrayList<IceData> iceList = Utils.getIceDataFromFile();
		System.out.println(String.format("%.2f",
				new ParameterizedIceStats(iceList, b0, b1).getMSE()));
	}

	private static void handleFlag400(double b0, double b1) {
		ArrayList<IceData> iceList = Utils.getIceDataFromFile();
		Gradients g = new ParameterizedIceStats(iceList, b0, b1).getGradients();
		System.out.println(String.format("%.2f", g.getFirstGradient()));
		System.out.println(String.format("%.2f", g.getSecondGradient()));
	}

	private static void handleFlag500(double learning_rate, int iterations) {
		double b0 = 0.0;
		double b1 = 0.0;
		ArrayList<IceData> iceList = Utils.getIceDataFromFile();
		ParameterizedIceStats stats = new ParameterizedIceStats(iceList, b0,
				b1);
		int t = 0;
		do {
			Gradients g = stats.getGradients();
			double new_b0 = b0 - (learning_rate * g.getFirstGradient());
			double new_b1 = b1 - (learning_rate * g.getSecondGradient());
			stats = new ParameterizedIceStats(iceList, new_b0, new_b1);
			b0 = new_b0;
			b1 = new_b1;
			System.out.println(t + 1 + " " + String.format("%.2f", new_b0) + " "
					+ String.format("%.2f", new_b1) + " "
					+ String.format("%.2f", stats.getMSE()));
			t++;
		} while (t < iterations);
	}

	private static void handleFlag600() {
		ArrayList<IceData> iceList = Utils.getIceDataFromFile();

		ParameterizedIceStats parameterized_stats = new ParameterizedIceStats(
				iceList, 0.0, 0.0);
		Parameters p = parameterized_stats.getClosedFormSolution();
		System.out.println(String.format("%.2f", p.getFirstParameter()) + " "
				+ String.format("%.2f", p.getSecondParameter()) + " "
				+ String.format("%.2f", parameterized_stats.getMSE()));
	}
	
	private static void handleFlag700(int year) {
		ArrayList<IceData> iceList = Utils.getIceDataFromFile();

		ParameterizedIceStats parameterized_stats = new ParameterizedIceStats(
				iceList, 0.0, 0.0);
		Parameters p = parameterized_stats.getClosedFormSolution();
		double prediction = p.getFirstParameter() + year*p.getSecondParameter();
		System.out.println(String.format("%.2f", prediction));
	}
	
	private static void handleFlag800(double learning_rate, int iterations) {
		double b0 = 0.0;
		double b1 = 0.0;
		ArrayList<IceData> iceList = Utils.getIceDataFromFile(true);
		ParameterizedIceStats stats = new ParameterizedIceStats(iceList, b0,
				b1);
		int t = 0;
		do {
			Gradients g = stats.getGradients();
			double new_b0 = b0 - (learning_rate * g.getFirstGradient());
			double new_b1 = b1 - (learning_rate * g.getSecondGradient());
			stats = new ParameterizedIceStats(iceList, new_b0, new_b1);
			b0 = new_b0;
			b1 = new_b1;
			System.out.println(t + 1 + " " + String.format("%.2f", new_b0) + " "
					+ String.format("%.2f", new_b1) + " "
					+ String.format("%.2f", stats.getMSE()));
			t++;
		} while (t < iterations);
	}
	
	private static void handleFlag900(double learning_rate, int interations) {
		double b0 = 0.0;
		double b1 = 0.0;
		ArrayList<IceData> iceList = Utils.getIceDataFromFile(true);
		int n = iceList.size();
		int t=0;
		Random rand = new Random();
		do {
			// Pick a random number between 0 to n-1
			IceData item = iceList.get(rand.nextInt(n));
			double g1 = 2*(b0 + b1*item.getYear() - item.getNumberOfDaysIce());
			double g2 = 2*(b0 + b1*item.getYear() - item.getNumberOfDaysIce()) * item.getYear();
			double new_b0 = b0 - learning_rate*g1;
			double new_b1 = b1 - learning_rate*g2;
			ParameterizedIceStats stats = new ParameterizedIceStats(iceList, new_b0, new_b1);
			b0 = new_b0;
			b1 = new_b1;
			System.out.println(t + 1 + " " + String.format("%.2f", new_b0) + " "
					+ String.format("%.2f", new_b1) + " "
					+ String.format("%.2f", stats.getMSE()));
			t++;
		} while (t < interations);
	}
}