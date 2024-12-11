package Project;

import java.io.IOException;
import java.util.Scanner;

import static Project.TaskManager.addTask;
import static Project.TaskManager.deleteTask;

public class App
{
    public static void main( String[] args ) throws IOException {
        Scanner input = new Scanner(System.in);

        if (args == null || args.length == 0) {
            System.out.println("Enter proper command");
            return;
        }

        String command = args[0];

        switch (command){
            case "add" :
                System.out.println("Adding Task");
                if (args.length < 2){
                    System.out.println("Enter the task description");
                    break;
                }
                String description = args[1];

                addTask(description);
                break;
            case "delete" :
                System.out.println("Deleting Task");
                if (args.length < 2){
                    System.out.println("Enter the task id to be deleted");
                    break;
                }
                int id = Integer.parseInt(args[1]);

                deleteTask(id);
                break;

            default:
                System.out.println("Enter valid operation");
                break;
        }

    }
}
