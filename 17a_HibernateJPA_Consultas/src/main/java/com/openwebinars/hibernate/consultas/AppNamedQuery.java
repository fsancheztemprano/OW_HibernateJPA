package com.openwebinars.hibernate.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.openwebinars.hibernate.consultas.model.Customer;
import com.openwebinars.hibernate.consultas.model.Employee;

/**
 * Consultas (JPQL, con nombre, nativas, ....)
 * www.openwebinars.net
 * @LuisMLopezMag
 */
public class AppNamedQuery 
{
    @SuppressWarnings("unchecked")
	public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Consultas");
        
        EntityManager em = emf.createEntityManager();
        

       /* Query query = em.createNamedQuery("Customer.findAll");

        List<Customer> listCustomer = query.getResultList();
        for(Customer c : listCustomer) 
        	printCustomer(c);
        */
        
        
        /*Query query2 = em.createNamedQuery("Customer.findByName");
        query2.setParameter("name","%Iberia%");

        List<Customer> listCustomer2 = query2.getResultList();
        for(Customer c : listCustomer2) 
        	printCustomer(c);
        */
        
        Query query3 = em.createNamedQuery("Customer.findByEmployee");
        query3.setParameter("employee", em.find(Employee.class, 1370));
        List<Customer> listCustomer3 = query3.getResultList();
        for(Customer c : listCustomer3) 
        	printCustomer(c);
        
        

        
        em.close();
        emf.close();
        
    }
    
    public static void printCustomer(Customer c) {
    	System.out.print(c.getCustomerName() + " (");
    	System.out.print(c.getContactFirstName() + " " + c.getContactLastName()+ ") (");
    	System.out.println(c.getCity() + ", " + c.getCountry() + ")");
    }
}
