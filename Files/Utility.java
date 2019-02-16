import java.io.*;
import java.util.*;

public class Utility{
  public static void checkCommand(String input, LinkedList<Movie> list, Queue<Customer> queue) {
      switch(input){
			case "h":
			case "H":
        System.out.println();
        Collections.sort(list);
        Commands.help();
        break;

			case "i":
			case "I":
        System.out.println();
        Collections.sort(list);
        Commands.inquire(list);
        break;

			case "l":
			case "L":
        System.out.println();
				Collections.sort(list);
        Commands.listAlphabetically(list);
        break;

			case "a":
			case "A":
        System.out.println();
				Collections.sort(list);
        Commands.add(list);
        break;

      case "d":
      case "D":
        System.out.println();
        Collections.sort(list);
        Commands.delete(list);
        break;

			case "m":
			case "M":
        System.out.println();
				Collections.sort(list);
        Commands.modify(list);
        break;

			case "o":
			case "O":
        System.out.println();
				Collections.sort(list);
        Commands.order(list);
        break;

			case "r":
			case "R":
        System.out.println();
				Collections.sort(list);
        Commands.rtn(list);
        break;

			case "s":
			case "S":
        System.out.println();
				Collections.sort(list);
        Commands.sell(list, queue);
        break;

			case "q":
			case "Q":
        System.out.println();
				Collections.sort(list);
        Commands.quit();
        break;

			default:
				System.out.println("'" + input + "' is not a valid input, try again."); break;
		}
	}

	public static String[] restoreHelper(File file, Scanner readFile){
    String[] sArr = new String[3];

		try{
      String line = readFile.nextLine();
			int breakIndex = line.indexOf("|");
      String arrayArgOne = line.substring(0, breakIndex);
			line = line.substring(breakIndex + 1);
			breakIndex = line.indexOf("|");
			String arrayArgTwo = line.substring(0, breakIndex);
			line = line.substring(breakIndex + 1);
			breakIndex = line.indexOf("|");
			String arrayArgThree = line.substring(0, breakIndex);
			sArr[0] = arrayArgOne;
			sArr[1] = arrayArgTwo;
			sArr[2] = arrayArgThree;
		} catch(Exception e){
			e.printStackTrace();
		}

		return sArr;
	}

	public static void restoreWaitQueue(Queue<Customer> queue) {
    File file = new File("WaitList.txt");

    try {
      Scanner readFile = new Scanner(new FileInputStream(file));
      while(readFile.hasNextLine()) {
        String[] stringArray = restoreHelper(file, readFile);
        queue.add(new Customer(stringArray[0], stringArray[1], stringArray[2]));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void restoreList(LinkedList<Movie> list) {
		File file = new File("MovieList.txt");

		try {
			Scanner readFile = new Scanner(new FileInputStream(file));
			while(readFile.hasNextLine()) {
				String[] stringArray = restoreHelper(file, readFile);
        list.add(new Movie(stringArray[0], tryParse(stringArray[1]), tryParse(stringArray[2])));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

  public static Customer createCustomer(){
    System.out.print("Enter the customer's first name: ");
    String firstName = fetchUserInput();
    System.out.print("Enter the customer's last name: ");
    String lastName = fetchUserInput();
    System.out.print("Enter customer's phone number: ");
    String phone = fetchUserInput();

    return new Customer(firstName, lastName, phone);
  }

  public static String fetchUserInput(){
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

      for(int i = 0; i < queue.size(); i++)
        writer.println(((LinkedList<Customer>) queue).get(i));

      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
	}

  public static void writeToFile(LinkedList<Movie> list) {
    try {
      Collections.sort(list);
      PrintWriter writer = new PrintWriter("MovieList.txt");

      for(int i = 0; i < list.size(); i++)
        writer.println(list.get(i));

      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
	}
}
