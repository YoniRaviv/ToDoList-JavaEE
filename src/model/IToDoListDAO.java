package model;


import java.util.List;

public interface IToDoListDAO {


    //User service functions
    public void addNewUser(Users user);
    public Users getUser(String name);
    public List<Users> getUsers();
    public boolean deleteUser(Users user);

    //Task service functions
    public void addTask(TdlTask task);
    public boolean deleteTask(TdlTask task);
    public boolean updateTask(TdlTask task);
    public List<TdlTask> getTasks(String userName);



}
