package com.xadmin.medicalManagemet.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.medicalManagemet.bean.Medicine;
import com.xadmin.medicalManagemet.dao.MedicineDao;

/**
 * Servlet implementation class MedicineServlet
 */
@WebServlet("/")
public class MedicineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private MedicineDao MediDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		MediDao = new MedicineDao();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch(action)
		{
		
		case "/new":
		    showNewForm(request, response);
			break;
			
		case "/insert":
			try {
				insertMedicines(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/delete":
			try {
				deleteMedicines(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/edit":
			try {
				showEditForm(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/update":
			try {
				updateMedicine(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
			default:
			try {
				ListMedicine(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
		}
	}
		
		private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			RequestDispatcher dispatcher = request.getRequestDispatcher("medicine-form.jsp");
			dispatcher.forward(request, response);
			
		}
		
		//insert Medicine ------++++++++++++--------------
		private void insertMedicines(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
			String MediName = request.getParameter("MediName");
			String MediDetails = request.getParameter("MediDetails");
			String ForDisease = request.getParameter("ForDisease");
//			Medicine newMedi = new Medicine(MediName,MediDetails,ForDisease);
			
//			MediDao.insertMedicines(newMedi);
//			response.sendRedirect("list");
			
		}
		
		//delete Medicine Method 
		private void deleteMedicines(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				MediDao.deleteMedicines(id);
			}catch(Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("list");
		}
		
		//Edit 
		private void showEditForm(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
			int id = Integer.parseInt(request.getParameter("id"));
			Medicine existingMedicines;
			try {
				existingMedicines = MediDao.selectmedicines(id);
				RequestDispatcher dispatcher = request.getRequestDispatcher("medicine-form.jsp");
				request.setAttribute("medicines", existingMedicines);
				dispatcher.forward(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//update medicines 
		private void updateMedicine(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
			
		   int id = Integer.parseInt(request.getParameter("id"));
		    String MediName = request.getParameter("MediName");
			String MediDetails = request.getParameter("MediDetails");
			String ForDisease = request.getParameter("ForDisease");
			
			Medicine medi = new Medicine(id, MediName, MediDetails, ForDisease);
			MediDao.updateMedicines(medi);
			response.sendRedirect("list");
		}
		
		//default section 
		private void ListMedicine(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
			try {
				List<Medicine> medilist = MediDao.selectAllMedicines();
				request.setAttribute("medilist", medilist);
				RequestDispatcher dispatcher = request.getRequestDispatcher("medicine_list.jsp");
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}




