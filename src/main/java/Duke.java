import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("-----------------------------------------------");
        System.out.println("| Hi this is Thesh. What can I do for you? |");
        System.out.println("-----------------------------------------------");
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;
        while (sc.hasNext()) {
            String command = sc.nextLine();
            System.out.println("    ____________________________________________________________");
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(String.format("      %d. %s", i + 1, tasks[i]));
                }
            } else if (command.startsWith("mark")) {
                try {
                    String taskNumberString = command.split(" ")[1];
                    int taskNumber = Integer.parseInt(taskNumberString);
                    tasks[taskNumber - 1].markComplete();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println(String.format("      %s", tasks[taskNumber - 1]));
                } catch (MarkToggleException e) {
                    System.out.println(e);
                } catch (NumberFormatException e) {
                    System.out.println("    please only input a number after 'mark'.");
                } catch (NullPointerException e) {
                    System.out.println(String.format("There is only %d tasks in the list.", taskCount));
                }
            } else if (command.startsWith("unmark")) {
                try {
                    String taskNumberString = command.split(" ")[1];
                    int taskNumber = Integer.parseInt(taskNumberString);
                    tasks[taskNumber - 1].markIncomplete();
                    System.out.println("    OK, I've marked this task as not done yet");
                    System.out.println(String.format("      %s", tasks[taskNumber - 1]));
                } catch (MarkToggleException e) {
                    System.out.println(e);
                } catch (NumberFormatException e) {
                    System.out.println("    please only input a number after 'unmark'.");
                } catch (NullPointerException e) {
                    System.out.println(String.format("There is only %d tasks in the list.", taskCount));
                }
            } else {
                Task newTask = null;
                if (command.startsWith("todo")) {
                    //if command.equals()
                    command = command.replace("todo ", "");
                    newTask = new ToDo(command);

                } else if (command.startsWith("deadline")) {
                    command = command.replace("deadline ", "");
                    String[] commands = command.split(" /by ");
                    if (commands.length != 2) {
                        System.out.println("    Sorry, please give a deadline in this format:\n    " +
                                "  'deadline <action> /by <deadline>'");
                        continue;
                    }
                    newTask = new Deadline(commands[0], commands[1]);
                } else if (command.startsWith("event")) {
                    command = command.replace("event ", "");
                    String[] commands = command.split(" /at ");
                    if (commands.length != 2) {
                        System.out.println("    Sorry, please give an event in this format:\n    " +
                                "  'event <event> /at <timing>'");
                        continue;
                    }
                    newTask = new Event(commands[0], commands[1]);
                } else {
                    System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    continue;
                }
                tasks[taskCount++] = newTask;
                System.out.println("    Got it. I've added this task:");
                System.out.print("      ");
                System.out.println(newTask);
                System.out.println(String.format("    Now you have %d tasks in the list.", taskCount));
            }
            System.out.println("    ____________________________________________________________");
        }
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
