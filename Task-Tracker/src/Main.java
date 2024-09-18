import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final Gson gson = new Gson();
    private static HashMap<String, Tasks> usersMap = new HashMap();
    private static final Type usersType = new TypeToken<HashMap<String, Tasks>>() {}.getType(); //by search

    public static void main(String[] args) {
        loadData();

        System.out.println("******************************************");
        System.out.println("**       Welcome to Task Tracker        **");
        System.out.println("******************************************");

        System.out.print("Enter UserName: ");
        String userName = in.nextLine();
        Tasks myTasks;

        // Sign in or register
        if (usersMap.containsKey(userName)) {
            System.out.print("Enter Password: ");
            String password = in.nextLine();
            myTasks = usersMap.get(userName);

            if (!myTasks.getPassword().equals(password)) {
                System.out.println("Incorrect password....");
                return;
            }
        } else {
            System.out.print("User not found. Please register.\nEnter Password: ");
            String password = in.nextLine();
            myTasks = new Tasks(userName, password, 1, new HashMap(), new HashMap(), new HashMap());
            usersMap.put(userName, myTasks);
            System.out.println("User registered successfully!");
        }

        System.out.println(" \uD83D\uDC4B Welcome " + userName);

        int choice;
        do {
            System.out.println("Press 1 to add a new task ➕");
            System.out.println("Press 2 to display all tasks  \uD83D\uDCCB");
            System.out.println("Press 3 to display completed tasks \uD83D\uDFE2");
            System.out.println("Press 4 to display uncompleted tasks ⏳ ");
            System.out.println("Press 5 to delete a task ❌");
            System.out.println("Press 6 to update a task ✏️");
            System.out.println("Press 7 to mark a task as completed ✅");
            System.out.println("Press 8 to exit the program");
            System.out.println("\n");
            choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    myTasks.addTask();
                    break;
                case 2:
                    myTasks.displayTasks();
                    break;
                case 3:
                    myTasks.displayCompTasks();
                    break;
                case 4:
                    myTasks.displayUnCompTasks();
                    break;
                case 5:
                    myTasks.deleteTask();
                    break;
                case 6:
                    myTasks.modifyTask();
                    break;
                case 7:
                    myTasks.completedTask();
                    break;
                case 8:
                    System.out.println("Exit the program...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
            saveData();

            if (choice != 8 && !check()) {
                break;
            }

        } while (choice != 8);

        in.close();
    }

    private static void loadData() {
        try (FileReader reader = new FileReader("users.json")) {
            usersMap = gson.fromJson(reader, usersType);
            if (usersMap == null) {
                usersMap = new HashMap();
            }
        } catch (IOException e) {
            System.out.println("No existing data found or error loading data.");
            usersMap = new HashMap();
        } catch (JsonSyntaxException e) {
            System.out.println("Error reading JSON data: " + e.getMessage());
            usersMap = new HashMap();
        }
    }

    private static void saveData() {
        try (FileWriter writer = new FileWriter("users.json")) {
            gson.toJson(usersMap, writer);
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    private static boolean check() {
        System.out.println("🤔 Do you want to continue? (Y/N)");
        String answer = in.nextLine();
        return answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("Y");
    }
}
