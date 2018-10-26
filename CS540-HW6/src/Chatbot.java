import java.util.*;
import java.io.*;

public class Chatbot {
	private static String filename = "./WARC201709_wid.txt";

	private static ArrayList<Integer> readCorpus() {
		ArrayList<Integer> corpus = new ArrayList<Integer>();
		try {
			File f = new File(filename);
			Scanner sc = new Scanner(f);
			while (sc.hasNext()) {
				if (sc.hasNextInt()) {
					int i = sc.nextInt();
					corpus.add(i);
				} else {
					sc.next();
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File Not Found.");
		}
		return corpus;
	}

	static public void main(String[] args) {
		ArrayList<Integer> corpus = readCorpus();
		int flag = Integer.valueOf(args[0]);

		if (flag == 100) {
			int w = Integer.valueOf(args[1]);
			flag100Impl(corpus, w);
		} else if (flag == 200) {
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);
			flag200Impl(corpus, n1, n2);
		} else if (flag == 300) {
			int h = Integer.valueOf(args[1]);
			int w = Integer.valueOf(args[2]);
			flag300Impl(corpus, h, w);
		} else if (flag == 400) {
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);
			int h = Integer.valueOf(args[3]);
			flag400Impl(corpus, n1, n2, h);

		} else if (flag == 500) {
			int h1 = Integer.valueOf(args[1]);
			int h2 = Integer.valueOf(args[2]);
			int w = Integer.valueOf(args[3]);
			flag500Impl(corpus, h1, h2, w);
		} else if (flag == 600) {
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);
			int h1 = Integer.valueOf(args[3]);
			int h2 = Integer.valueOf(args[4]);
			flag600Impl(corpus, n1, n2, h1, h2);
		} else if (flag == 700) {
			int seed = Integer.valueOf(args[1]);
			int t = Integer.valueOf(args[2]);
			int h1 = 0, h2 = 0;

			Random rng = new Random();
			int[] monogram_count = constructMonogramCount(corpus);
			int[][] diagram_count = constructDiagramCount(corpus);

			if (seed != -1)
				rng.setSeed(seed);

			if (t == 0) {
				double r = rng.nextDouble();
				h1 = getWordFromMonogram(monogram_count, r);
				System.out.println(h1);
				if (h1 == 9 || h1 == 10 || h1 == 12) {
					return;
				}
				r = rng.nextDouble();
				h2 = getWordFromDigram(diagram_count, r, h1);
				System.out.println(h2);
			} else if (t == 1) {
				h1 = Integer.valueOf(args[3]);
				double r = rng.nextDouble();
				h2 = getWordFromDigram(diagram_count, r, h1);
				System.out.println(h2);
			} else if (t == 2) {
				h1 = Integer.valueOf(args[3]);
				h2 = Integer.valueOf(args[4]);
			}

			while (h2 != 9 && h2 != 10 && h2 != 12) {
				double r = rng.nextDouble();
				int w = 0;
				w = getWordFromTrigram(corpus, r, h1, h2);
				if(w == -1) {
					w = getWordFromDigram(diagram_count, r, h2);
				}
				System.out.println(w);
				h1 = h2;
				h2 = w;
			}
		}

		return;
	}

	private static void flag100Impl(ArrayList<Integer> corpus, int w) {
		int count = 0;
		for (int i = 0; i < corpus.size(); i++) {
			if (w == corpus.get(i)) {
				count++;
			}
		}
		System.out.println(count);
		System.out
				.println(String.format("%.7f", count / (double) corpus.size()));
	}

	private static void flag200Impl(ArrayList<Integer> corpus, int n1, int n2) {
		int[] count = new int[4700];
		int total_count = 0;
		for (int i = 0; i < corpus.size(); i++) {
			count[corpus.get(i)]++;
			total_count++;
		}
		searchIndexAndPrintStats(n1, n2, count, total_count);
	}

	private static void flag300Impl(ArrayList<Integer> corpus, int h, int w) {
		int count = 0;
		ArrayList<Integer> words_after_h = new ArrayList<Integer>();

		for (int i = 1; i < corpus.size(); i++) {
			if (corpus.get(i - 1) == h) {
				words_after_h.add(corpus.get(i));
				if (corpus.get(i) == w) {
					count++;
				}
			}
		}
		System.out.println(count);
		System.out.println(words_after_h.size());
		System.out.println(
				String.format("%.7f", count / (double) words_after_h.size()));
	}

	private static void flag400Impl(ArrayList<Integer> corpus, int n1, int n2,
			int h) {
		int[] count = new int[4700];
		int total_count = 0;
		for (int i = 1; i < corpus.size(); i++) {
			if (corpus.get(i - 1) == h) {
				count[corpus.get(i)]++;
				total_count++;
			}
		}
		searchIndexAndPrintStats(n1, n2, count, total_count);
	}

	private static void flag500Impl(ArrayList<Integer> corpus, int h1, int h2,
			int w) {
		int count = 0;
		ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();

		for (int i = 2; i < corpus.size(); i++) {
			if (h1 == corpus.get(i - 2) && h2 == corpus.get(i - 1)) {
				words_after_h1h2.add(corpus.get(i));
				if (corpus.get(i) == w) {
					count++;
				}
			}
		}
		System.out.println(count);
		System.out.println(words_after_h1h2.size());
		if (words_after_h1h2.size() == 0)
			System.out.println("undefined");
		else
			System.out.println(String.format("%.7f",
					count / (double) words_after_h1h2.size()));
	}

	private static void flag600Impl(ArrayList<Integer> corpus, int n1, int n2,
			int h1, int h2) {
		int[] count = new int[4700];
		int total_count = 0;
		for (int i = 2; i < corpus.size(); i++) {
			if (h1 == corpus.get(i - 2) && h2 == corpus.get(i - 1)) {
				count[corpus.get(i)]++;
				total_count++;
			}
		}

		if (total_count == 0) {
			System.out.println("undefined");
			return;
		}
		searchIndexAndPrintStats(n1, n2, count, total_count);
	}

	private static int[] constructMonogramCount(ArrayList<Integer> corpus) {
		int[] count = new int[4701];
		int total_count = 0;
		for (int i = 0; i < corpus.size(); i++) {
			count[corpus.get(i)]++;
			total_count++;
		}
		count[4700] = total_count;
		return count;
	}

	private static int[][] constructDiagramCount(ArrayList<Integer> corpus) {
		int[][] count = new int[4700][4701];
		for (int i = 1; i < corpus.size(); i++) {
			count[corpus.get(i - 1)][corpus.get(i)]++;
			count[corpus.get(i-1)][4700]++;
		}
		return count;
	}

	private static int getWordFromMonogram(int[] count, double r) {
		int total_count = count[4700];
		double l_value = 0;
		int i = 0;
		while (count[i] == 0) {
			i++;
		}
		double r_value = (double) count[i] / total_count;
		i++;
		while (r_value < r) {
			l_value = r_value;
			r_value = r_value + (double) (count[i]) / total_count;
			i++;
		}
		return i-1;
	}

	private static int getWordFromDigram(int[][] count, double r, int h1) {
		int total_count = count[h1][4700];
		double l_value = 0;
		int i = 0;
		while (count[h1][i] == 0) {
			i++;
		}
		double r_value = (double) count[h1][i] / total_count;
		i++;
		while (r_value < r) {
			l_value = r_value;
			r_value = r_value + (double) (count[h1][i]) / total_count;
			i++;
		}
		return i-1;
	}

	private static int getWordFromTrigram(ArrayList<Integer> corpus, double r, int h1,
			int h2) {
		int total_count = 0;
		int[] count = new int[4700];
		for (int i = 2; i < corpus.size(); i++) {
			if(corpus.get(i-2) == h1 && corpus.get(i-1) == h2) {
				count[corpus.get(i)]++;
				total_count++;
			}
		}
		
		if(total_count == 0) {
			return -1;
		}
		
		double l_value = 0;
		int i = 0;
		while (count[i] == 0) {
			i++;
		}
		double r_value = (double) count[i] / total_count;
		i++;
		while (r_value < r) {
			l_value = r_value;
			r_value = r_value + (double) (count[i]) / total_count;
			i++;
		}
		return i-1;
	}

	private static void searchIndexAndPrintStats(int n1, int n2, int[] count,
			int total_count) {
		double random_number = (double) n1 / n2;
		double l_value = 0;
		int i = 0;
		while (count[i] == 0) {
			i++;
		}

		double r_value = (double) count[i] / total_count; 
		i++;
		while (r_value < random_number) {
			l_value = r_value;
			r_value = r_value + (double) (count[i]) / total_count;
			i++;
		}

		// Print output
		System.out.println(i - 1);
		System.out.println(String.format("%.7f", l_value));
		System.out.println(String.format("%.7f", r_value));
	}
}