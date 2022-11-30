package root.Objects;

import java.util.Vector;
public class Tasks extends Vector<Task>
{

    public Tasks()
    {
        
    }
    public void addTask(Task task)
    {
        System.out.println("Task added");
        add(task);
    }
    public void removeTask(Task task)
    {
        System.out.println("Task removed");
        remove(task);
    }

}
