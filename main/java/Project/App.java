package Project;

import java.io.IOException;
import java.util.Scanner;

import static Project.TaskManager.*;

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

            case "update" :
                System.out.println("Updating Task");

                if (args.length < 3){
                    System.out.println("Enter the description of the task to be updated");
                    break;
                }
                int updatingTaskid = Integer.parseInt(args[1]);

                String updateDescription = args[2];

                updateTaskDescription(updatingTaskid, updateDescription);
                break;

            case "mark-in-progress" :
                System.out.println("Marking task in progress");

                if (args.length == 2) {
                    changeStatus(command, Integer.parseInt(args[1]));
                } else {
                    System.out.println("Enter the task number of which the status to be updated");
                    break;
                }
                break;

            case "mark-done" :
                System.out.println("Marking task as Done");

                if (args.length == 2) {
                    changeStatus(command, Integer.parseInt(args[1]));
                } else {
                    System.out.println("Enter the task number of which the status to be updated");
                    break;
                }
                break;

            default:
                System.out.println("Enter valid operation");
                break;
        }

    }

    private static void changeStatus(String command, int taskID) throws IOException {

        changeTaskStatus(command, taskID);
    }

}
