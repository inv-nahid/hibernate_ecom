package org.example.crud;

import org.example.Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        // Create SessionFactory with all entity classes
        SessionFactory sessionFactory = new Configuration().configure()
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Orders.class)
                .addAnnotatedClass(OrderDetails.class)
                .addAnnotatedClass(Users.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Create 2 Users
            Users user1 = new Users("alice_smith", "pass123", "alice@example.com", Users.Role.CUSTOMER);
            Users user2 = new Users("bob_jones", "pass456", "bob@example.com", Users.Role.ADMIN);
            session.save(user1);
            session.save(user2);

            // Create 2 Categories
            Category category1 = new Category("Electronics", "Electronic devices and gadgets");
            Category category2 = new Category("Clothing", "Apparel and accessories");
            session.save(category1);
            session.save(category2);

            // Create 4 Products
            Product product1 = new Product("Laptop", new BigDecimal("999.99"), 5, category1);
            Product product2 = new Product("Smartphone", new BigDecimal("599.99"), 10, category1);
            Product product3 = new Product("T-Shirt", new BigDecimal("19.99"), 50, category2);
            Product product4 = new Product("Jeans", new BigDecimal("49.99"), 30, category2);
            session.save(product1);
            session.save(product2);
            session.save(product3);
            session.save(product4);

            // Create 2 Orders
            Orders order1 = new Orders(LocalDateTime.now(), new BigDecimal("1599.98"), user1);
            Orders order2 = new Orders(LocalDateTime.now().plusDays(1), new BigDecimal("69.98"), user2);
            session.save(order1);
            session.save(order2);

            // Create 2 OrderDetails
            OrderDetails orderDetail1 = new OrderDetails(2, new BigDecimal("999.99"), order1, product1); // 2 Laptops
            OrderDetails orderDetail2 = new OrderDetails(1, new BigDecimal("19.99"), order2, product3); // 1 T-Shirt
            session.save(orderDetail1);
            session.save(orderDetail2);

            // Commit the transaction
            session.getTransaction().commit();
            System.out.println("All data saved successfully to the 'ecom' database!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}