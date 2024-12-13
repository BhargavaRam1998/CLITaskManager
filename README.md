# CLITaskManager
Project to manage tasks using CLI commands

Project URL - https://roadmap.sh/projects/task-tracker


This project helps one to save the tasks in JSON file.

The operations supported:
1. Add Tasks
2. Update description of tasks
3. Delete tasks
4. Mark task to in progress
5. Mark task status to done
6. List all the tasks
7. List completed tasks
8. List tasks to be completed
9. List tasks which are in progress.

How to run the program.

## Steps

1. Pull the project to local

2. Run the below command -

   mvn clean compile
3. Command to add task

   mvn exec:java -Dexec.mainClass="Project.App" -Dexec.args="add \"GO TO COSTCO\""

NOTE -change the task description accordingly.

4. Command to update task
   mvn exec:java -Dexec.mainClass="Project.App" -Dexec.args="update 1 \"Clean Home and bathroom\""

NOTE - rovide with task id number and new description.


5. Command to delete task

mvn exec:java -Dexec.mainClass="Project.App" -Dexec.args="delete 1"

NOTE - provide the task id number to be deleted.


6. Command to list all the tasks


mvn exec:java -Dexec.mainClass="Project.App" -Dexec.args="list"

7. Command to list all the tasks completed

mvn exec:java -Dexec.mainClass="Project.App" -Dexec.args="list done"

8. Command to list all the tasks in progress


mvn exec:java -Dexec.mainClass="Project.App" -Dexec.args="list In-progress" 

9. Command to list all the tasks to be completed

mvn exec:java -Dexec.mainClass="Project.App" -Dexec.args="list todo" 