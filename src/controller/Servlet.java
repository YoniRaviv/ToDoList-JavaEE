package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "/Servlet/*")
public class Servlet extends HttpServlet {
    public static final String pkg = "controller";

    public Servlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = request.getPathInfo();
        String vec[] = str.split("/");
        String controller = vec[1];
        String action = vec[2];

        String controllerClassName = controller.substring(0, 1).toUpperCase() + controller.substring(1) + "Controller";
        try {
            Class clazz = Class.forName(pkg + "." + controllerClassName);
            Object object = clazz.newInstance();
            Method method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(object, request, response);

            getServletContext().getRequestDispatcher("/" + action + ".jsp").forward(request, response);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();

        }
    }
}
