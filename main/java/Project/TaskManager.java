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
                    System.out.println("Loading existing tasks from file...");
                    Task[] taskArray = objectMapper.readValue(file, Task[].class);
                    tasks = new ArrayList<>(Arrays.asList(taskArray));
                    System.out.println("Existing tasks loaded successfully.");
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

        for (Task task: tasks){
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

    public static void updateTaskDescription(int updatingTaskid, String updateDescription) throws IOException {
        if (updatingTaskid <= 0) {
            System.out.println("Please enter valid ID, The id must be a positive integer");
            return;
        }

        Task tasktoUpdate = null;

        for (Task task: tasks) {
            if (task.getId() == updatingTaskid) {
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

    public static void changeTaskStatus(int taskID) throws IOException {
        if (taskID <= 0) {
            System.out.println("Please enter valid ID, The id must be a positive integer");
            return;
        }

        Task taskToChangeStatus = null;
        for (Task task: tasks) {
            if (task.getId() == taskID) {
                taskToChangeStatus = task;
                break;
            }
        }

        if (taskToChangeStatus != null) {
            taskToChangeStatus.setStatus("mark-in-progress");
            objectMapper.writeValue(new File(FILE_PATH), tasks);
        } else {
            System.out.println("Please enter existing task ID");
        }

    }
}
