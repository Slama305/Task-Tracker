import java.util.HashMap;
import java.util.Scanner;

public class Tasks extends Users {
    private HashMap<Integer, String> tasks;
    private HashMap<Integer, String> completedTasks;
    private HashMap<Integer, String> uncompletedTasks;
    private int taskIdCntr;

    public Tasks(String name, String pass, int id, HashMap<Integer, String> tasks, HashMap<Integer, String> completedTasks, HashMap<Integer, String> uncompletedTasks) {
        super(name, pass, id);
        this.tasks = tasks;
        this.taskIdCntr = tasks.size();
        this.completedTasks = completedTasks;
        this.uncompletedTasks = uncompletedTasks;
    }

    public HashMap<Integer, String> getTasks() {
        return tasks;
    }

    public void setTasks(HashMap<Integer, String> tasks) {
        this.tasks = tasks;
    }

    public HashMap<Integer, String> getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(HashMap<Integer, String> completedTasks) {
        this.completedTasks = completedTasks;
    }

    public HashMap<Integer, String> getUncompletedTasks() {
        return uncompletedTasks;
    }

    public void setUncompletedTasks(HashMap<Integer, String> uncompletedTasks) {
        this.uncompletedTasks = uncompletedTasks;
    }

    // add task
    public void addTask() {
        Scanner in = new Scanner(System.in);
        boolean flag;
        do {
            System.out.print("Enter New Task: ");
            String newTask = in.nextLine();
            tasks.put(++taskIdCntr, newTask);
            uncompletedTasks.put(taskIdCntr, newTask);
            System.out.println("Do you want to add another task? 'y / n'");
            char check = in.next().charAt(0);
            in.nextLine();
            flag = check == 'y' || check == 'Y';
        } while (flag);

        System.out.println("Tasks added successfully!\n");
    }

    // Show tasks
    public void displayTasks() {
        System.out.println("\n__________________________________________");
        System.out.println("________________YOUR TASKS________________");
        System.out.println("__________________________________________");

        for (Integer id : tasks.keySet()) {
            System.out.println(id + " : " + tasks.get(id));
        }
        System.out.println("\n");
    }

    public void displayCompTasks() {
        System.out.println("\n__________________________________________");
        System.out.println("__________YOUR COMPLETED TASKS____________");
        System.out.println("__________________________________________");

        for (Integer id : completedTasks.keySet()) {
            System.out.println(id + " : " + completedTasks.get(id));
        }
        System.out.println("\n");
    }

    public void displayUnCompTasks() {
        System.out.println("\n__________________________________________");
        System.out.println("___________YOUR UNCOMPLETED TASKS_________");
        System.out.println("__________________________________________");

        for (Integer id : uncompletedTasks.keySet()) {
            System.out.println(id + " : " + uncompletedTasks.get(id));
        }
        System.out.println("\n");
    }

    // Delete task
    public void deleteTask() {
        Scanner in = new Scanner(System.in);
        displayTasks();
        System.out.print("________________________________" +
                "\nEnter Task ID to delete: ");
        int taskId = in.nextInt();
        if (tasks.containsKey(taskId)) {
            tasks.remove(taskId);
            uncompletedTasks.remove(taskId);
            System.out.println("Task deleted successfully!");
        } else {
            System.out.println("Task not found!");
        } System.out.println("\n");
    }

    // Modify task
    public void modifyTask() {
        Scanner in = new Scanner(System.in);
        displayTasks();
        System.out.print("________________________________" +
                "\nEnter Task ID to modify: ");
        int taskId = in.nextInt();
        in.nextLine();
        if (tasks.containsKey(taskId)) {
            System.out.print("Enter new task : ");
            String newTask = in.nextLine();
            tasks.put(taskId, newTask);
            uncompletedTasks.remove(taskId);
            uncompletedTasks.put(taskId, newTask);
            System.out.println("Task modified successfully!");
        } else {
            System.out.println("Task not found!");
        } System.out.println("\n");
    }

    public void completedTask() {
        Scanner in = new Scanner(System.in);
        displayTasks();
        System.out.print("________________________________" +
                "\nEnter Task ID is completed: ");
        int taskId = in.nextInt();
        in.nextLine();
        if (tasks.containsKey(taskId)) {
            String compTask = tasks.get(taskId) + " ✅ ";
            tasks.put(taskId, compTask);
            completedTasks.put(taskId, compTask);
            uncompletedTasks.remove(taskId);
            System.out.println("Well Done !");
        } else {
            System.out.println("Task not found!");
        } System.out.println("\n");
    }
}
