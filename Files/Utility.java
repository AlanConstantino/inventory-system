import java.io.*;
import java.util.*;

public class Utility {
	public static void checkCommand(String input, LinkedList<Movie> list, Queue<Customer> queue) {
		System.out.println();
		Collections.sort(list);

		switch (input) {
			case "h":
			case "H":
				Commands.help();
				break;

			case "i":
			case "I":
				Commands.inquire(list);
				break;

			case "l":
			case "L":
				Commands.listAlphabetically(list);
				break;

			case "a":
			case "A":
				Commands.add(list);
				break;

			case "d":
			case "D":
				Commands.delete(list);
				break;

			case "m":
			case "M":
				Commands.modify(list);
				break;

			case "o":
			case "O":
				Commands.order(list);
				break;

			case "r":
			case "R":
				Commands.rtn(list);
				break;

			case "s":
			case "S":
				Commands.sell(list, queue);
				break;

			case "q":
			case "Q":
				Commands.quit();
				break;

			default:
				System.out.println("'" + input + "' is not a valid input, try again.");
				break;
		}
	}

	public static String[] restoreHelper(File file, Scanner readFile) {
		String[] stringArray = new String[3];

		try {
			String line = readFile.nextLine();
			int breakIndex = line.indexOf("|");
			String arrayArgZero = line.substring(0, breakIndex);

			line = line.substring(breakIndex + 1);
			breakIndex = line.indexOf("|");
			String arrayArgOne = line.substring(0, breakIndex);

			line = line.substring(breakIndex + 1);
			breakIndex = line.indexOf("|");
			String arrayArgTwo = line.substring(0, breakIndex);

			stringArray[0] = arrayArgZero;
			stringArray[1] = arrayArgOne;
			stringArray[2] = arrayArgTwo;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stringArray;
	}

	public static void restoreWaitQueue(Queue<Customer> queue) {
		try {
			File file = new File("WaitList.txt");
			Scanner readFile = new Scanner(new FileInputStream(file));

			while (readFile.hasNextLine()) {
				String[] stringArray = restoreHelper(file, readFile);
				queue.add(new Customer(stringArray[0], stringArray[1], stringArray[2]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void restoreList(LinkedList<Movie> list) {
		try {
			File file = new File("MovieList.txt");
			Scanner readFile = new Scanner(new FileInputStream(file));

			while (readFile.hasNextLine()) {
				String[] stringArray = restoreHelper(file, readFile);
				list.add(new Movie(stringArray[0], tryParse(stringArray[1]), tryParse(stringArray[2])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String fetchUserInput() {
		Scanner read = new Scanner(System.in);
		return read.nextLine();
	}

	public static Integer tryParse(String text) {
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public static void writeToWaitFile(Queue<Customer> queue) {
		try {
			PrintWriter writer = new PrintWriter("WaitList.txt");

			for (int i = 0; i < queue.size(); i++) {
				writer.println(((LinkedList<Customer>) queue).get(i));
			}

			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void writeToFile(LinkedList<Movie> list) {
		try {
			Collections.sort(list);
			PrintWriter writer = new PrintWriter("MovieList.txt");

			for (int i = 0; i < list.size(); i++) {
				writer.println(list.get(i));
			}

			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
