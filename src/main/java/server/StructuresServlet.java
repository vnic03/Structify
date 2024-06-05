package server;

import java.io.IOException;
import com.google.gson.Gson;
import structs.Queue;
import structs.Stack;
import structs.array.ArrayQueue;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import structs.array.ArrayStack;


public class StructuresServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Queue<Integer> queue = new ArrayQueue<>(10);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(5);

        Stack<Character> stack = new ArrayStack<>(6);
        stack.push('R');
        stack.push('E');
        stack.push('G');
        stack.push('A');
        stack.push('L');
        stack.push('E');

        Object[] structs = new Object[2];
        structs[0] = queue;
        structs[1] = stack;

        String json = gson.toJson(structs);

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);

        resp.getWriter().println(json);
    }
}

