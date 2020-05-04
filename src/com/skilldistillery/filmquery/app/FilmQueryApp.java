package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();

	}

	private void launch() throws SQLException {
		Scanner kb = new Scanner(System.in);

		startUserInterface(kb);

		kb.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {

		System.out.println("Begin Menu Loop...");
		boolean mainMenu = true;

		while (mainMenu) {

			System.out.println("======================================");
			System.out.println("\nPick a number from the menu below:");
			System.out.println("1. Look up a film by its ID");
			System.out.println("2. Look up a film by a search keyword");
			System.out.println("3. Exit this application");
			System.out.println();
			System.out.println("======================================");
			System.out.print("\nEnter your selection: ");

			try {
				int userInput = input.nextInt();
				switch (userInput) {
				case 1:
					System.out.print("Enter film ID: ");
					int filmID = input.nextInt();
					Film result = (db.findFilmById(filmID));
					if (result != null) {
						result.displayInfo();
						filmSubMenu();
					}

					break;
				case 2:
					System.out.print("Enter your search keyword: ");
					String keyword = input.next();
					List<Film> results = db.findFilmByKeyword(keyword);

					if (results.size() > 0) {
						for (Film film : results) {
							film.displayInfo();
							System.out.println();
							filmSubMenu();
						}
					} else {
						System.out.println("\nYour keyword returned no results.\n");
					}

					break;
				case 3:
					System.out.println("Later");
					mainMenu = false;
					break;
				default:
					System.out.println("\n");
					System.out.println("You jacked something up... Try again\n");

				}

			}

			catch (InputMismatchException ex) {
				System.out.println("\nya done messed up dude...");
				System.out.println("Please restart the application");
				break;
			}
		}

	}

	public void filmSubMenu() {
		System.out.println("\nBegin Sub-Menu Loop...");

		System.out.println("======================================");
		System.out.println("\nPick a number from the menu below:");
		System.out.println("1. Return to main menu:");
		System.out.println("2. Get them film Deets");
		System.out.println();
		System.out.println("======================================");
		System.out.print("\nEnter your selection: ");
		Scanner kb = new Scanner(System.in);
		boolean subMenu = true;
		while (subMenu) {
			int subChoice = kb.nextInt();
			switch (subChoice) {

			case 1:
				System.out.println("\nReturning to main menu...\n");
				subMenu = false;
				break;

			case 2:
				System.out.println("\nSorry, I ran out of time...\n");
				System.out.println("\nReturning to main menu...\n");
				subMenu = false;
				break;

			default:
				break;
			}

		}
	}

}