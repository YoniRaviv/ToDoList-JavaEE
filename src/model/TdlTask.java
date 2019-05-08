package model;


public class TdlTask {

    private String taskTitle;
    private String taskDescription;
    private String userEmail;
    private int id;

    public TdlTask() {
    }

    public TdlTask(String taskTitle, String taskDescription, String userEmail) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String toString() {
        return "TdlTask{" +
                ", taskTitle='" + taskTitle + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                '}';
    }
}
