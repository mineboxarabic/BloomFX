package root.Objects;

import java.time.LocalDate;
import java.util.Vector;

public class Task 
{
    private String title;
    private String description;
    private LocalDate date;
    private String time;
    private int priority;
    private boolean status;
    private Vector<String> categories;
    private int id;
    private static int idCounter = 0;
    //Create getters and setters for all of the above
    public void onDateUpdate(){

    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public LocalDate getDate()
    {
        return date;
    }
    public void setDate(LocalDate date)
    {
        this.date = date;
        onDateUpdate();
    }
    public String getTime()
    {
        return time;
    }
    public void setTime(String time)
    {
        this.time = time;
    }
    public int getPriority()
    {
        return priority;
    }
    public void setPriority(int priority)
    {
        this.priority = priority;
    }
    public boolean getStatus()
    {
        return status;
    }
    public void setStatus(boolean status)
    {
        this.status = status;
    }
    public Vector<String> getCategory()
    {
        return categories;
    }
    public void setCategory(Vector<String> category)
    {
        this.categories = category;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }


    int idGenerator()
    {
        idCounter++;
        return idCounter;
    }
    public Task(String title){

        id = this.idGenerator();
        date = LocalDate.now();
        time = "00:00";
        priority = 0;
        status = false;
        categories = new Vector<String>();
        categories.add("Default");
        this.title = title;
    }
    public Task(String title, String description, LocalDate date, String time, int priority, Vector<String> category)
    {
        id = this.idGenerator();
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.priority = priority;
        this.status = false;
        this.categories = category;
    }
    public Task(String title, String description, LocalDate date, String time, int priority,Boolean statu, Vector<String> category)
    {
        id = this.idGenerator();
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.priority = priority;
        this.status = statu;
        this.categories = category;
    }
}