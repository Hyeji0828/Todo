

import java.awt.Taskbar;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.TodoDao;
import dto.Todo;

/**
 * Servlet implementation class ForwardServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MainServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Retrieve todos from DB
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		
		TodoDao dao = new TodoDao();
		List<Todo> list = dao.getTodos();
		
		// Todo-Doing-Done
		List<Todo> todoList = new ArrayList<>();
		List<Todo> doingList = new ArrayList<>();
		List<Todo> doneList = new ArrayList<>();
		
		int len = list.size();
		for (int i=0; i<len; i++) {
			Todo todo = list.get(i);
			
			// Todo
			if(todo.getType().equals("TODO")) {
				todoList.add(todo);
			}
			// Doing
			else if(todo.getType().equals("DOING")) {
				doingList.add(todo);
			}
			//Done
			else {
				doneList.add(todo);
			}
		}
		
		// Set Attribute
		request.setAttribute("todoList", todoList);
		request.setAttribute("doingList", doingList);
		request.setAttribute("doneList", doneList);
		
		// Forward to 'main.jsp'
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main.jsp");
		requestDispatcher.forward(request, response);
			
		
	}

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setCharacterEncoding("utf-8");
//		resp.setContentType("application/json");
//		
//		TodoDao dao = new TodoDao();
//		List<Todo> list = dao.getTodos();
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		String json = objectMapper.writeValueAsString(list);
//		
//		PrintWriter out = resp.getWriter();
//		out.println(json);
//		out.close();
//
//	}

}
