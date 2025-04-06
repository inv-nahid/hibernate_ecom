package org.example.crud;

import org.example.Entity.OrderDetails;
import org.example.Entity.Orders;
import org.example.Entity.Product;
import org.example.Entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Insert {
    private SessionFactory sessionfactory = null;
    public Insert(SessionFactory sessionfactory){
        this.sessionfactory=sessionfactory;
        Session session = sessionfactory.getCurrentSession();
        session.beginTransaction();

//        Category category = new Category("L-Arginine","Preworkout");

        // Fetch existing category (assume "protein" already exists)
//        Category category = session.get(Category.class, 2); // 1 = protein's ID

        // Now pass the values to Product constructor
//        Product p1 = new Product("Asitis", new BigDecimal("990"), 30, category);
//        Product p2 = new Product("Muscleblaze", new BigDecimal("900"), 20, category);
//        Product p3 = new Product("Wellcore", new BigDecimal("1500"), 15, category);
//
//        session.persist(p1);
//        session.persist(p2);
//        session.persist(p3);

//        Users user = new Users("Kunal Sharma","kunal123","asd@gmail.com", Users.Role.ADMIN);
//        Users user1 = new Users("Joseph jones","jos123","ks@gmail.com", Users.Role.CUSTOMER);
//
//        session.persist(user);
//        session.persist(user1);

//
        Orders o1 = session.get(Orders.class,1);
        Orders o2 = session.get(Orders.class,2);

        Product p1 = session.get(Product.class,1);
        Product p2 = session.get(Product.class,2);


        OrderDetails od1 = new OrderDetails(20,new BigDecimal(500),o1,p1);
        OrderDetails od2 = new OrderDetails(30,new BigDecimal(400),o2,p2);

        session.persist(od1);
        session.persist(od2);
        session.getTransaction().commit();
        session.close();
    }
}
