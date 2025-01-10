

import java.io.IOException;
import java.io.PrintWriter;
import java.util.IllegalFormatCodePointException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TodoDao;
import dto.Todo;

/**
 * Servlet implementation class TodoTypeServlet
 */
@WebServlet("/type")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		// Retrieve Parameters
		Long id = Long.parseLong(request.getParameter("id"));
		out.println("<h1>"+id+"</h1>");
		
		// Update todo from DB
		TodoDao dao = new TodoDao();
		
		Todo todo = dao.getTodo(id);
		if (todo == null) {
			System.out.println("Get Todo Fail");
			return;
		}
			
		String type = "TODO";
		if (todo.getType().equals("TODO")) {
			type = "DOING";
		}
		else {
			type = "DONE";
		}	
	
		int updateResult = dao.updateTodo(todo, type);
		if (updateResult < 1) {
			System.out.println("Update Todo Fail");
		}
		
		// Redirect to MainServlet
		response.sendRedirect("main");
	}


}
