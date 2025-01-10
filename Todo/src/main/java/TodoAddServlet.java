

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.protobuf.TextFormatParseInfoTree;

import dao.TodoDao;
import dto.Todo;

/**
 * Servlet implementation class TodoAddServlet
 */
@WebServlet("/add")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
//
//	/**
//	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrieve Parameters
		String title = request.getParameter("title");
		String name = request.getParameter("name");
		String[] sequenceString = request.getParameter("sequence").split("/");
		int sequence = Integer.parseInt(sequenceString[0]);
		
		// Add todo into DB
		Todo todo = new Todo((long) 0, name, null, sequence, title, "TODO");
		
		TodoDao dao = new TodoDao();
		int addResult = dao.addTodo(todo);
		if (addResult < 1) {
			System.out.println("Add Todo Fail");
		}
		
		// Redirect to MainServlet
		response.sendRedirect("main");
		
	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//
//		
//	}

}
