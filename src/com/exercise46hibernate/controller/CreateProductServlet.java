package com.exercise46hibernate.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.exercise46hibernate.model.Product;

/**
 * @author jorge
 * @version 1.0
 * Servlet implementation class CreateProductServlet
 */
@WebServlet("/CreateProductServlet")
public class CreateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html charset='utf-8'");
		PrintWriter output = response.getWriter();
		
		Product myProduct = new Product();
		myProduct.setNameProduct(request.getParameter("txtNameProduct"));
		myProduct.setPriceProduct(Double.parseDouble(request.getParameter("txtPriceProduct")));
		
		//Crear el objeto de configuracion
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		//Crear el session factory
		SessionFactory sessionfactory = cfg.buildSessionFactory();
		
		//Abrimos la sesion
		Session session = sessionfactory.openSession();
		
		//Se inicia el request
		Transaction t = session.beginTransaction();
		
		//Iniciar el proceso de persistencia
		//CRUD-Create
		session.persist(myProduct);
		
		//Cerramos la conexion
		session.close();
		
		System.out.println("Se guardaron los datos");
		
		output.close();
	}

}
