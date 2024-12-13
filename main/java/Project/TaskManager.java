package Project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskManager {

    private static final String FILE_PATH = "src/main/java/Project/tasks.json";

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())  // Register the JavaTimeModule
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);  // Disable timestamps for date fields

    private static List<Task> tasks = new ArrayList<>();

    static {
        loadTasksFromFile();
    }

    public static void loadTasksFromFile() {

        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                Task[] taskArray = objectMapper.readValue(file, Task[].class);
                tasks = new ArrayList<>(Arrays.asList(taskArray));
            } else {
                file.createNewFile();
                System.out.println("File not found. Creating a new file at " + FILE_PATH);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

    }

    public static void addTask(String description) {

        int id = tasks.isEmpty() ? 1 : tasks.get(tasks.size() - 1).getId() + 1;

        Task newtask = new Task(id, description, "todo");

        tasks.add(newtask);

        try {
            objectMapper.writeValue(new File(FILE_PATH), tasks);
        } catch (IOException e) {
            System.out.println("Error saving task");
        }

    }

    public static void deleteTask(int id) throws IOException {

        if (id <= 0) {
            System.out.println("Please enter valid ID, The id must be a positive integer");
            return;
        }
        Task taskToRemove = null;

        for (Task task : tasks) {
            if (task.getId() == id) {
                taskToRemove = task;
                break;
            }
        }

        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            objectMapper.writeValue(new File(FILE_PATH), tasks);
        } else {
            System.out.println("Please enter an existing task ID");
        }

    }

    public static void updateTaskDescription(int updatingTaskID, String updateDescription) throws IOException {
        if (updatingTaskID <= 0) {
            System.out.println("Please enter valid ID, The id must be a positive integer");
            return;
        }

        Task tasktoUpdate = null;

        for (Task task : tasks) {
            if (task.getId() == updatingTaskID) {
                tasktoUpdate = task;
                break;
            }
        }

        if (tasktoUpdate != null) {
            tasktoUpdate.setDescription(updateDescription);
            objectMapper.writeValue(new File(FILE_PATH), tasks);
        } else {
            System.out.println("Please enter an existing task ID");
        }


    }

    public static void changeTaskStatus(String command, int taskID) throws IOException {
        if (taskID <= 0) {
            System.out.println("Please enter valid ID, The id must be a positive integer");
            return;
        }

        Task taskToChangeStatus = null;
        for (Task task : tasks) {
            if (task.getId() == taskID) {
                taskToChangeStatus = task;
                break;
            }
        }

        if (taskToChangeStatus != null) {
            if (command.equals("mark-in-progress")) {
                taskToChangeStatus.setStatus("In progress");
                objectMapper.writeValue(new File(FILE_PATH), tasks);
            } else if (command.equals("mark-done")) {
                taskToChangeStatus.setStatus("done");
                objectMapper.writeValue(new File(FILE_PATH), tasks);
            }
        } else {
            System.out.println("Please enter existing task ID");
        }

    }

    public static void listTasks() {
        for (Task task : tasks) {
            System.out.println(task.getDescription());
        }
    }

    public static void listTasksBasedonStatus(String status) {

        if (status.equals("done")) {
            // Filter tasks with status "done" and print their descriptions using streams
            System.out.println("List of completed tasks");
            tasks.stream()
                    .filter(task -> "done".equals(task.getStatus()))  // Filter tasks with status "done"
                    .map(Task::getDescription)  // Map to task descriptions
                    .forEach(System.out::println);
        } else if (status.equals("todo")) {
            System.out.println("List of tasks to be completed");
            // Filter tasks with status "done" and print their descriptions using streams
            tasks.stream()
                    .filter(task -> "todo".equals(task.getStatus()))  // Filter tasks with status "done"
                    .map(Task::getDescription)  // Map to task descriptions
                    .forEach(System.out::println);
        } else if (status.equals("In-progress")) {
            System.out.println("List of in progress tasks");
            // Filter tasks with status "done" and print their descriptions using streams
            tasks.stream()
                    .filter(task -> "In progress".equals(task.getStatus()))  // Filter tasks with status "done"
                    .map(Task::getDescription)  // Map to task descriptions
                    .forEach(System.out::println);
        }

    }

}
