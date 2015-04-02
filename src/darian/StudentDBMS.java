package darian;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Darian
 *
 *	Created: 3/25/2015
 *	Last Modified: 3/31/2015
 *
 *	The goal of this assignment is to mimic a database.
 *	The functionality allows for you to add student records, list the records,
 *	modify the records. and delete any records of your choosing.
 *
 * 	A Hash Map is used to hold the student records, and each student record
 * 	must adhere to these rules:
 * 
 * 	The student name must be implemented as one string
 * 	The student ID must be a string of 9 digits (no other characters allowed)
 * 		and no two students can have the same student ID
 * 	The year of study must be among these: freshman, sophomore, junior, or senior
 * 	The gender must be among these: male, female
 */

class Student { // Start Student class
	
	
	// Member Variables for the Student's info
	private String name;
	private String year;
	private String gender;
	private int id; 
	
	
	// Helpers that ensure that restricts the records to a certain form
	private static int NextId;
	public static final String [] typesOfYear = {"FRESHMAN", "SOPHOMORE","JUNIOR","SENIOR"};
	public static final String [] typesOfGender = {"FEMALE","MALE"};
	
	// Constructor for Student class. id is not needed because it will be autoincremented
	// and beyond the reach of the user for safety
	public Student(String n, int y, int g) {
		id = NextId;
		name = n;
		year = typesOfYear[y];
		gender = typesOfGender[g];
		
		// Increments id so that 
		NextId++;
	}
	
	public void modifyRecord()
	{
		String choice;
		//while(choice.equalsIgnoreCase())
		System.out.println("\nWhat would you like to modify?\n");
	}
	
	public void printRecord()
	{
		System.out.printf("\nID: %09d\n", id);
		System.out.println("Name: " + name);
		System.out.println("Year: " + year);
		System.out.println("Gender: " + gender + "\n");
	}
	
	// Getter and Setter methods for the Student class
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String n){
		name = n;
	}
	
	public String getYear(){
		return year;
	}
	public void setYear(int y){
		year = typesOfYear[y];
	}
	
	public String getGender(){
		return gender;
	}
	public void setGender(int g){
		gender = typesOfGender[g];
	}
	
} // Ends the Student class



public class StudentDBMS { // Starts the Student DBMS class
	
	
	private static final String notInRangeMsg = "\nInvalid choice. Please choose an option within the given range\n";
	private static final String notProperIDMsg = "\nInvalid id. Please enter a 9 digit id number";
	private static final String databaseEmptyMsg = "There are currently no records in the database\n";
	private static final String idNotFoundMsg = "The record with that ID was not found in the database.";
	private static final String deleteSuccessfulMsg = "Successfully removed ID from the database.\n";
	
	// Regex patterns that ensure that the value given is within a a certain range
	// This one ensures that the pattern is a single digit between zero and four
	private static final String zeroToFourPattern = "[0-4]{1}";
	
	// This one ensures that the pattern is a single digit between zero and three
	private static final String zeroToThreePattern = "[0-3]{1}";
	
	// This one ensures that the pattern is a single digit between zero and two
	private static final String zeroToTwoPattern = "[0-2]{1}";
	
	// This one ensures that the pattern is a single digit between zero and one
	private static final String zeroToOnePattern = "[0-1]{1}";
	
	// This one ensures that the pattern consists of nine integers, used for the ID
	private static final String digitPattern = "[0-9]{9}";
	
	// The options for modifying a student record
	private static final String [] studentRecordOPTIONS = {"Name", "Year of Study", "Gender"};
	
	// The options for the menu
	private static final String [] OPTIONS = {"Add Records", "List Records", "Modify Records", "Delete Records", "Exit Program"};  
	
	//The main data structure used for holding the records
	private static HashMap <Integer, Student> allStudents = new HashMap();
	
	
	static Scanner scanner = new Scanner (System.in);
	
	
	public static void addRecord()
	{
		// Ensures that the user uses a valid option
		boolean validChoice = false;
		
		// Variables that will be used to instantiate the Student object
		String name = null;
		String year = null;
		String gender = null;
		
		
		// Gets the student's name
		System.out.print("\nEnter the student's name: ");
		name = scanner.nextLine();
		name = name.trim();
		
		// Will loop until a proper year is chosen
		while(!validChoice)
		{
			System.out.print("\nEnter the option for the year of the student: ");
			printMenu(Student.typesOfYear);
			System.out.print("\nSelect Your Choice :=> ");
			year = scanner.nextLine();
			
			// Removes white spaces before and after the choice for the pattern
			year = year.trim();
			
			// Returns true if the year is within the valid range of menu options
			validChoice = year.matches(zeroToThreePattern);
			
			// If the year is not in the correct range, print the appropriate error message
			if(!validChoice)
				System.out.println(notInRangeMsg);
		}
		
		validChoice = false;
		
		// Will loop until a proper gender is chosen
		while(!validChoice)
		{
			System.out.print("\nEnter the option for the gender of the student: ");
			printMenu(Student.typesOfGender);
			System.out.print("\nSelect Your Choice :=> ");
			gender = scanner.nextLine();
					
			// Removes white spaces before and after the choice for the pattern
			gender = gender.trim();
					
			// Returns true if the year is within the valid range of menu options
			validChoice = gender.matches(zeroToOnePattern);
					
			// If the year is not in the correct range, print the appropriate error message
			if(!validChoice)
				System.out.println(notInRangeMsg);
		}
		
		// Changes all of the strings to integers for the Student constructor
		Student newStudentToAdd = new Student(name, Integer.parseInt(year), Integer.parseInt(gender));
		
		
		allStudents.put(newStudentToAdd.getId(), newStudentToAdd);
		System.out.println("Successfully added the following student: \n");
		newStudentToAdd.printRecord();		
	}
	
	public static void listRecords()
	{
		// Nothing to list
		if(allStudents.isEmpty())
		{
			System.out.println(databaseEmptyMsg);
		}
		else 
		{
		
			// Iterator that will go through all of the records in the HashMap
			Iterator mapPrinter = allStudents.entrySet().iterator();
		
			System.out.println("Printing all records:");
			// Loops as long as there is another record
			while (mapPrinter.hasNext())
			{
				Map.Entry hashMapEntry = (Map.Entry) mapPrinter.next();
				((Student) hashMapEntry.getValue()).printRecord();
			}
		}
	}
	
	public static void modifyRecord()
	{
		int validID = -1;
		boolean validChoice = false;
		String menuChoice = null;
		String supposedID = null;
		String newName = null;
		String newYear = null;
		String newGender = null;
		
		// Nothing to modify
		if(allStudents.isEmpty())
		{
			System.out.println(databaseEmptyMsg);
		}
		else 
		{
			// Will loop until a proper id is given
			while(!validChoice)
			{
				System.out.print("\nEnter the ID of the student: ");
				supposedID = scanner.nextLine();
								
				// Removes white spaces before and after the choice for the pattern
				supposedID = supposedID.trim();
							
				// Returns true if the supposedID is within the valid range of IDs
				validChoice = supposedID.matches(digitPattern);
							
				// If the supposedID is not of the correct form, print the appropriate error message
				if(!validChoice)
					System.out.println(notProperIDMsg);
			}
		
			validID = Integer.parseInt(supposedID);
		
			if(allStudents.containsKey(validID))
			{
				allStudents.get(validID).printRecord();
				validChoice = false;
			
				// Will loop until a proper choice is chosen
				while(!validChoice)
				{
					System.out.print("\nEnter the option of the field you would like to modify: ");
					printMenu(studentRecordOPTIONS);
					System.out.print("\nSelect Your Choice :=> ");
					menuChoice = scanner.nextLine();
					
					// Removes white spaces before and after the choice for the pattern
					menuChoice = menuChoice.trim();
					
					// Returns true if the choice is within the valid range of menu options
					validChoice = menuChoice.matches(zeroToTwoPattern);
					
					// If the choice is not in the correct range, print the appropriate error message
					if(!validChoice)
						System.out.println(notInRangeMsg);
				}
				
				validChoice = false;
				
				if(menuChoice.equals("0"))
				{
					System.out.print("\nPlease enter a new name: ");
					newName = scanner.nextLine();
					allStudents.get(validID).setName(newName);
				}
				else if (menuChoice.equals("1"))
				{
					// Will loop until a proper year is chosen
					while(!validChoice)
					{
						System.out.print("\nEnter the option for the year of the student: ");
						printMenu(Student.typesOfYear);
						System.out.print("\nSelect Your Choice :=> ");
						newYear = scanner.nextLine();
						
						// Removes white spaces before and after the choice for the pattern
						newYear = newYear.trim();
						
						// Returns true if the year is within the valid range of menu options
						validChoice = newYear.matches(zeroToThreePattern);
						
						// If the year is not in the correct range, print the appropriate error message
						if(!validChoice)
							System.out.println(notInRangeMsg);
					}
					
					// Prepares the user's choice to modify the record
					int newYearIntVersion = Integer.parseInt(newYear);
					allStudents.get(validID).setYear(newYearIntVersion);
				}
				else if (menuChoice.equals("2"))
				{
					// Will loop until a proper gender is chosen
					while(!validChoice)
					{
						System.out.print("\nEnter the option for the gender of the student: ");
						printMenu(Student.typesOfGender);
						System.out.print("\nSelect Your Choice :=> ");
						newGender = scanner.nextLine();
								
						// Removes white spaces before and after the choice for the pattern
						newGender = newGender.trim();
								
						// Returns true if the gender is within the valid range of menu options
						validChoice = newGender.matches(zeroToOnePattern);
								
						// If the gender is not in the correct range, print the appropriate error message
						if(!validChoice)
							System.out.println(notInRangeMsg);
					}
					// Prepares the user's choice to modify the record					
					int newGenderIntVersion = Integer.parseInt(newGender);
					allStudents.get(validID).setGender(newGenderIntVersion);
					
				}
			
				System.out.println("Successfully Updated Record!");
				allStudents.get(validID).printRecord();
			}
			else
				System.out.println(idNotFoundMsg);
		}
	}
	
	public static void deleteRecord()
	{
		boolean validChoice = false;
		String supposedID = null;
		
		// Nothing to delete
		if(allStudents.isEmpty())
		{
			System.out.println(databaseEmptyMsg);
		}
		else 
		{
			// Will loop until a proper id is chosen
			while(!validChoice)
			{
				System.out.print("\nEnter the ID of the student: ");
				supposedID = scanner.nextLine();
								
				// Removes white spaces before and after the choice for the pattern
				supposedID = supposedID.trim();
							
				// Returns true if the supposedID is within the valid range of IDs
				validChoice = supposedID.matches(digitPattern);
							
				// If the supposedID is not of the correct form, print the appropriate error message
				if(!validChoice)
					System.out.println(notProperIDMsg);
			}
		
			int validID = Integer.parseInt(supposedID);
		
			if(allStudents.containsKey(validID))
			{
				Object removedItem = allStudents.remove(validID);
				System.out.println(deleteSuccessfulMsg);
			}
			else
				System.out.println(idNotFoundMsg);
		}
	}
	
	public static void prepareToExit()
	{
		// Nothing to delete
		if(allStudents.isEmpty())
		{
			System.out.println(databaseEmptyMsg);
		}
		else 
		{
			int id = 0;
		
			// Iterator that will go through all of the records in the HashMap
			Iterator mapPrinter = allStudents.entrySet().iterator();
				
			System.out.println("Deleting all records");
			// Loops as long as there is another record
			while (mapPrinter.hasNext())
			{
				Map.Entry hashMapEntry = (Map.Entry) mapPrinter.next();
				id = (int) hashMapEntry.getKey();
				allStudents.remove(id);
			}
			System.out.println("\nAll records have been deleted\n");
		}
		
		System.out.println("Thank you for using the Student Database Management System!");
	}
	
	
	public static void printMenu( String [] menuOptions)
	{
		System.out.println();
		for(int i = 0; i < menuOptions.length ; i++)
		{
			System.out.println(i + ". " + menuOptions[i]);
		}
	}
	
	
	public static void menu(){
		
		// This will be the menu choice the user picks. It is initialized 
		// to some random variable.
		String choice = "z";
		
		
		// As long as the person hasn't chosen the exit command, which is currently 4.
		while(!choice.equals("4"))
		{
			// Prints the menu and it's Options
			System.out.println("===== STUDENT DATABASE MANAGEMENT =====\n");
			printMenu(OPTIONS);
			
			
			System.out.print("\nSelect Your Choice :=> ");
			choice = scanner.nextLine();
			
			// Removes white spaces before and after the choice for the pattern
			choice = choice.trim();
			
			// Returns true if the choice is within the valid range of menu options
			boolean validChoice = choice.matches(zeroToFourPattern);
			
			if(validChoice)
			{
				switch(Integer.parseInt(choice)){
				case 0: addRecord();
					break;
				case 1: listRecords();
					break;
				case 2: modifyRecord();
					break;
				case 3: deleteRecord();
					break;
				case 4: prepareToExit();
					break;
					
				// Should not be reachable because choice has already been checked
				// Implemented just in case
				default: System.out.println(notInRangeMsg); 
					break;
				
				}
			}
			else
				System.out.println(notInRangeMsg);	
		}
	}

	
	public static void main(String[] args) {
		menu();		
	}

}// Ends the Student DBMS class
