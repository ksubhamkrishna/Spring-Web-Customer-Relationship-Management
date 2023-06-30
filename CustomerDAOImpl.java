package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	// Remove @Transactional from here and give the annotation on Service Layer to
	// Start and end the transaction by injecting CustomerDAO class in the service
	// layer and calling the Data Access method there.
	public List<Customer> getCustomers() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Customer> getQuery = currentSession.createQuery("from Customer" + " Order by lastName ASC",
				Customer.class);

		// execute query and get result list
		List<Customer> customers = getQuery.getResultList();

		// return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save/update the customer ... finally LOL
		currentSession.saveOrUpdate(theCustomer);

	}

	@Override
	public Customer getCustomer(int theId) {

		// get the current hibernate session
		Session currenSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using the primary key
		Customer theCustomer = currenSession.get(Customer.class, theId);

		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {

		/*
		 * // get the curret hibernate session Session currentSession =
		 * sessionFactory.getCurrentSession();
		 * 
		 * // get the customer with id as theId Customer theCustomer =
		 * currentSession.get(Customer.class, theId);
		 * 
		 * // delete object with primary key currentSession.delete(theCustomer);
		 */

		// or Using Standard Hibernate Query Language for Deleting Customer

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// delete the object with primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id = : customerId");

		theQuery.setParameter("customerId", theId);

		theQuery.executeUpdate();
	}

}
