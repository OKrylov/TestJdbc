import model.Client;
import model.Phone;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();


//            session.beginTransaction();

//            List<Client> clients = session.createQuery("FROM Client").list();
//            System.out.println(clients);

            Transaction tx = session.beginTransaction();

            Client client = new Client();
            client.setFirstName("Oleg");
            client.setLastName("Krylov");
            client.setBirthDate(LocalDate.of(1992, 6, 29));
//
//
            Phone phone = new Phone();
            phone.setPhoneNumber("777");
            phone.setPhoneType("Mobile");
//
            client.addPhone(phone);
//
            session.save(client);

            Client client1 = new Client();
            client1.setFirstName("Helen");
            client1.setLastName("Petrova");
            client1.setBirthDate(LocalDate.of(1991, 8, 12));
//
//
            Phone phone1 = new Phone();
            phone1.setPhoneNumber("999");
            phone1.setPhoneType("Mobile");
//
            client1.addPhone(phone1);
            session.saveOrUpdate(client1);

//        ContactEntity contactEntity = new ContactEntity();
//
//        contactEntity.setBirthDate(new java.util.Date());
//        contactEntity.setFirstName("Nick");
//        contactEntity.setLastName("VN");
//
//        session.save(contactEntity);
//            session.getTransaction().commit();

            tx.commit();

            session.close();
        } finally {
            HibernateSessionFactory.shutdown();
        }
    }
}
