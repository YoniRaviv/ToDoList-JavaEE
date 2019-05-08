package model;
/*
* Created by:
* Koby Korsia - 203538483
* Yonathan Raviv - 200059574
* */
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;



public class HibernateToDoListDAO implements IToDoListDAO {

    public static final HibernateToDoListDAO INSTANCE = new HibernateToDoListDAO();
    private static SessionFactory myFactory;


    public static HibernateToDoListDAO getInstance() {
        return INSTANCE;
    }

    private HibernateToDoListDAO() {
    }


    @Override
    public void addNewUser(Users user) {
        Session session = myFactory.openSession();
        Users generatedUser = user;
        Query query=  session.createQuery("from Users where email=?");
// TODO: which function for checking is better???
//        Users user3 = (Users) session.get(Users.class, 2);
//        if (user3 != null)
//            System.out.println("exists!!");

        //check if the user already exists in the database
        Users user1 =(Users)query.setString(0,user.getEmail()).uniqueResult();
        if(user1 != null) {
            System.out.println("User exists!!!");
        }else{
            try {
            //Insert user
                session.beginTransaction();
                session.save(user);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                if (session.getTransaction() != null)
                    session.getTransaction().rollback();

            } finally {
                if (session != null)
                    session.close();
            }
        }
    }

    @Override
    public List<Users> getUsers()  {
        Session session = myFactory.openSession();
        try {
            Query query = session.createQuery("from Users u");
            List queryList = query.list();
            if(queryList != null && queryList.isEmpty())
                return null;
            else
                return (List<Users>)queryList;
        }
        catch ( Exception e ) {

        } finally {
            if (session != null)
                session.close();
        }
        return null;
    }

    @Override
    public Users getUser(String name) {
        Session session = myFactory.openSession();
        try {
            Query query = session.createQuery("from Users u where u.name='"+name+"'");
            List queryList = query.list();
            if(queryList != null && queryList.isEmpty())
                return null;
            else
                return (Users) queryList.get(0);
        }
        catch ( Exception e ) {

        } finally {
            if (session != null)
                session.close();
        }
        return null;
    }

    @Override
    public boolean deleteUser(Users user) {
        Session session = myFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            return true;
        }
        catch ( HibernateException e ) {
            if ( session.getTransaction() != null )
                session.getTransaction().rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return false;

    }

    @Override
    public void addTask(TdlTask task) {
        Session session = myFactory.openSession();

        /*
        * TODO: check if a task exists in the DB?? ASK IF NEEDED!
        * */
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        }
        catch ( HibernateException e ) {
            if ( session.getTransaction() != null )
                session.getTransaction().rollback();
        } finally {
            if (session != null)
                session.close();
        }

    }

    @Override
    public boolean deleteTask(TdlTask task) {
        Session session = myFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(task);
            session.getTransaction().commit();
            return true;
        }
        catch ( HibernateException e ) {
            if ( session.getTransaction() != null )
                session.getTransaction().rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return false;

    }

    @Override
    public boolean updateTask(TdlTask task) {
        Session session = myFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(task);
            session.getTransaction().commit();
            return true;
        }
        catch ( HibernateException e ) {
            if ( session.getTransaction() != null )
                session.getTransaction().rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return false;

    }

    @Override
    public List<TdlTask> getTasks(String userEmail) {

        Session session = myFactory.openSession();
        try {
            Query query = session.createQuery("from TdlTask i WHERE i.userEmail='"+userEmail+"'");
            /*
            * TODO: fix this in the view part of project  -  get user email for two diffrent class.
            * */
            List queryList = query.list();
            if(queryList != null && queryList.isEmpty())
                return null;
            else
                return (List<TdlTask>)queryList;
        }
        catch ( HibernateException e ) {
            //throw new ToDoListPlatformException("Unable to get items list from the database");
        } finally {
            if (session != null)
                session.close();
        }
        return null;
    }

    //
   public static void main(String[] var0) {
            Configuration configuration = new Configuration().configure();
            myFactory = configuration.buildSessionFactory();
    /*
    *
    * SESSION NUMBER 1 - RUN FIRST TO CREATE DATABASE ROWS
    *
    * */
            //HibernateToDoListDAO.getInstance().addNewUser(new Users("sd@ff.com", "1234", "sdi"));
           // HibernateToDoListDAO.getInstance().addNewUser(new Users("user2@ff.com", "4321", "testo"));
            //HibernateToDoListDAO.getInstance().addTask(new TdlTask("test task","this is the first test description","abc@fr.com"));
            //HibernateToDoListDAO.getInstance().addTask(new TdlTask("test task2","lorem ipsum dollor", "sd@ff.com"));
       /*
       *
       * SESSION NUMBER 2 - UNCOMMENT AND RUN AFTER RUNNING THE FIRST SEESION ABOVE
       * */
       //HibernateToDoListDAO.getInstance().deleteUser(new Users("sd@ff.com", "1234", "sdi"));
       //HibernateToDoListDAO.getInstance().updateTask(new TdlTask("task updated", "task description updated"));
       //HibernateToDoListDAO.getInstance().deleteTask(new TdlTask("test task2",""));
       List<TdlTask> userTasks = HibernateToDoListDAO.getInstance().getTasks("sd@ff.com");

       for (TdlTask tasks: userTasks) {
           System.out.println(tasks);
       }
             myFactory.close();
  }
}
