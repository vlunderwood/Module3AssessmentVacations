
/**
 * @author valei - vlunderwood
 * CIS175 - Fall 2023
 * Sep 11, 2023
 */

import java.util.List;
import java.util.Scanner;
import controller.ResortHelper;
import model.ListResort;

public class StartProgram {
	static Scanner in = new Scanner(System.in);
	static ResortHelper r = new ResortHelper();
	
	//method that prompts the user for information to add resort
	private static void addResort() {
		System.out.print("Enter a resort: ");
		String resort = in.nextLine();
		System.out.print("Enter a city: ");
		String city = in.nextLine();
		System.out.print("Enter a country: ");
		String country = in.nextLine();
		System.out.print("Enter a price: ");
		Double price = in.nextDouble();
		ListResort toAdd = new ListResort(resort, city, country, price);
		r.insertResort(toAdd);

	}

	//method that prompts the user for information to delete resort
	private static void deleteResort() {
		System.out.print("Enter the resort to delete: ");
		String resort = in.nextLine();
		System.out.print("Enter the city to delete: ");
		String city = in.nextLine();
		System.out.print("Enter the country to delete: ");
		String country = in.nextLine();
		System.out.print("Enter the price to delete: ");
		Double price = in.nextDouble();

		ListResort toDelete = new ListResort(resort, city, country, price);
		r.deleteResorts(toDelete);
	}

	//method that prompts the user for information to edit resort
	private static void editResort() {
		System.out.println("How would you like to search? ");
		System.out.println("Enter 1 to search by Resort");
		System.out.println("Enter 2 to search by City");
		System.out.println("Enter 3 to search by Country");
		System.out.println("Enter 4 to search by Price");
		int searchBy = in.nextInt();
		in.nextLine();
		List<ListResort> resortNames;
		//if statement to select option
		if (searchBy == 1) {
			System.out.print("Enter the resort name: ");
			String resortName = in.nextLine();
			resortNames = r.searchByResort(resortName);
		} else if (searchBy == 2) {
			System.out.print("Enter the city: ");
			String cityName = in.nextLine();
			resortNames = r.searchByCity(cityName);
		} else if (searchBy == 3) {
			System.out.print("Enter the country: ");
			String countryName = in.nextLine();
			resortNames = r.searchByCountry(countryName);
		} else {
			System.out.print("Enter the price: ");
			Double priceName = in.nextDouble();
			resortNames = r.searchByPrice(priceName);
		}
		//prints if option is found in the database and prints it out on the console
		if (!resortNames.isEmpty()) {
			System.out.println("Found Results.");
			for (ListResort l : resortNames) {
				System.out.println(l.getId() + " Vacation Spot - " + l.getResort() + " at " + l.getCity() + ", "
						+ l.getCountry() + " $" + l.getPrice());
			}
			//Option to to choose the ID of the resort to edit
			System.out.print("Choose ID to edit: ");
			int idToEdit = in.nextInt();

			//Lists the options to edit the resort
			ListResort toEdit = r.searchById(idToEdit);
			System.out.println("Retrieved " + toEdit.getResort() + " from " + toEdit.getCity() + ", "
					+ toEdit.getCountry() + " $" + toEdit.getPrice());
			System.out.println("Enter 1 to update Resort");
			System.out.println("Enter 2 to update City");
			System.out.println("Enter 3 to update Country");
			System.out.println("Enter 4 to update Price");
			int update = in.nextInt();
			in.nextLine();

			//if statement to enter an update
			if (update == 1) {
				System.out.print("Enter new Resort: ");
				String newResort = in.nextLine();
				toEdit.setResort(newResort);
			} else if (update == 2) {
				System.out.print("Enter new City: ");
				String newCity = in.nextLine();
				toEdit.setCity(newCity);

			} else if (update == 3) {
				System.out.print("Enter new Country: ");
				String newCountry = in.nextLine();
				toEdit.setCountry(newCountry);

			} else {
				System.out.print("Enter new price: ");
				Double newPrice = in.nextDouble();
				toEdit.setPrice(newPrice);
			}

			r.updateResort(toEdit);

		} else

		{
			//prints if no results are found in the database
			System.out.println("No results found. Please try again.");
		}

	}

	//runs the initial method
	public static void main(String[] args) {
		vacationMenu();

	}
	
	//first method that prompts the user for information to add, edit, delete, & view vacations
	public static void vacationMenu() {
		boolean menuLoop = true;
		System.out.println("*** Vacation resorts are listed here! ***");
		while (menuLoop) {
			System.out.println("*  Select an option:");
			System.out.println("*  1 - Add a resort");
			System.out.println("*  2 - Edit a resort");
			System.out.println("*  3 - Delete a resort");
			System.out.println("*  4 - View the list of resorts");
			System.out.println("*  5 - You hate vacations and would like to leave.");
			System.out.print("*  Enter option here: ");
			int selection = in.nextInt();
			in.nextLine();

			//if statement to select option
			if (selection == 1) {
				addResort();
			} else if (selection == 2) {
				editResort();
			} else if (selection == 3) {
				deleteResort();
			} else if (selection == 4) {
				viewList();
			} else {
				r.cleanUp();
				System.out.println("Thank you and Goodbye!");
				menuLoop = false;
			}

		}

	}

	//method that prints header and runs the print method from the POJO
	private static void viewList() {
		List<ListResort> allItems = r.showAllResorts();
		System.out.println();
		System.out.println();
		System.out.printf("**************************************************************************************%n");
		System.out.printf("                                  Valerie's Vacations                                   %n");
		System.out.printf("**************************************************************************************%n");
		System.out.println();

		for (ListResort singleItem : allItems) {

			System.out.println(singleItem.returnResortDetails());
			System.out
					.printf("--------------------------------------------------------------------------------------%n");

		}
		System.out.println();
		System.out.println();

	}

}