package by.it.loader;


import by.it.pojos.Address;
import by.it.pojos.Person;
import by.it.pojos.PoneNumber;
import by.it.util.HibernateUtil;
import by.it.util.SFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonLoader {


    private static final Scanner scan = new Scanner(System.in);
    private static final HibernateUtil hibernateUtil = new HibernateUtil();

    public static void main(String[] args) throws Exception {
/*
      final EntityManager em = HibernateUtil.getEntityManager();
 Person person=new Person(null,35,"Yuli","Slabko");
 em.getTransaction().begin();
 em.persist(person);
 em.getTransaction().commit();
 HibernateUtil.close();
*/

        getGreeting();

        while (true) {
            String command = scan.nextLine();
            if (command.equals("add")) {
                final Integer id = getNewPerson();
                System.out.println("Person added with ID=" + id);
            }
            if (command.equals("find")) {
                final Person read = getReadPerson();
                if (read != null) {
                    System.out.println(read);
                }
            }
            if (command.equals("update")) {
                getUpdatePerson();
            }
            if (command.equals("delete")) {
                getDeletePerson();

            }
            if (command.equals("end")) {
                System.out.println("Closing program!");
                break;
            }
        }
    }

    private static void getUpdatePerson() throws SQLException {
        Session session = SFactory.getOpenSession();
        while (true) {
        System.out.println("Enter ID:");
        Integer id = getId();
        final Person read = hibernateUtil.read(id, session);
                if (read != null) {
                final Person updatePerson = getUpdate(read, id);
                if (updatePerson != null) {
                    System.out.println(updatePerson);
                    break;
                }
            }
            if (read == null) {
                System.out.println("Try again Enter some else ID");
                //            System.out.println("You wont add new Person for ID=" + id + "? \nEnter 'yes' if true");
                //            String s = scan.nextLine();
                //            if (s.equals("yes")) {
                //                getNewPersonWithIdInput(id);
                //            } else System.out.println("Without update ID=" + id);
            }
        }
        session.close();
    }

//    private static void getNewPersonWithIdInput(Integer id) throws SQLException {
//        System.out.println("Enter Age:");
//        Integer intAge = getScanNumbers();
//        System.out.println("Enter name:");
//        String name = scan.next();
//        System.out.println("Enter surname:");
//        String surname = scan.next();
//        final PoneNumber poneNumber = addPhoneNumberToPerson();
//        final Address address = getEnterAddress();
//        PreparedStatement preparedStatement = SFactory.getOpenConnection();
//        hibernateUtil.createWithId(id, intAge, name, surname,
//                poneNumber,address,preparedStatement);
//        System.out.println("You add new Person for ID=" + id);
//        SFactory.getOpenConnection().close();
//    }

    private static void getDeletePerson() {
        System.out.println("Enter ID:");
        Integer id = getId();
        Session session = SFactory.getOpenSession();
        hibernateUtil.delete(id, session);
        System.out.println("Person with ID= " + id + "  -- deleted!");
        session.close();
    }

    private static Person getUpdate(Person read, Integer id) {
        Integer ageP = read.getAge();
        System.out.println("Age of this Person: " + ageP +
                "\nEnter new Age:");
        Integer age = getScanNumbers();
        String nameP = read.getName();
        System.out.println("Name of this Person: " + nameP +
                "\nEnter new Name:");
        String name = scan.next();
        String surnameP = read.getSurname();
        System.out.println("Surname of this Person: " + surnameP +
                "\nEnter new Surname:");
        String surname = scan.next();

        PoneNumber poneNumber = read.getPoneNumber();
        System.out.println("Phone Number of this Person: " + poneNumber +
                "\nEnter new Phone Number:");
        Integer newPhoneNumber = getScanNumbers();
        Address address = read.getAddress();
        System.out.println("Address of this Person: " + address +
                "\nEnter new Phone Address:");
        Address enterAddress = getEnterAddress();
        Session session = SFactory.getOpenSession();
        Person update = hibernateUtil.update(id, age, name, surname,
                newPhoneNumber, enterAddress, session);
        session.close();
        return update;
    }

    private static Person getReadPerson() {
        System.out.println("Enter ID:");
        Integer id = getId();
        Session session = SFactory.getOpenSession();
        Person read = hibernateUtil.read(id, session);
        session.close();
        return read;
    }

    private static Integer getId() {
        return getScanNumbers();
    }

    private static Integer getScanNumbers() {
        int value = 0;
        boolean isValid = false;
        while (!isValid) {
            if (scan.hasNextInt()) {
                value = scan.nextInt();
                isValid = true;
            } else {
                System.out.println("Error! Invalid number. Try again.");
            }
            scan.nextLine();
        }
        return value;
    }

    private static Integer getNewPerson() {
        System.out.println("Enter Age:");
        Integer intAge = getScanNumbers();
        System.out.println("Enter name:");
        String name = scan.next();
        System.out.println("Enter surname:");
        String surname = scan.next();
        System.out.println("Enter Phone number:");
        PoneNumber poneNumber = addPhoneNumberToPerson();
        System.out.println("Enter Address:");
        Address address = getEnterAddress();
        Session session = SFactory.getOpenSession();
        Person person = new Person(null, intAge, name, surname, poneNumber, address);
        Integer integer = hibernateUtil.create(person, session);
        session.close();
        return integer;
    }

    private static Address getEnterAddress() {
        System.out.println("Enter City:");
        String city = scan.next();
        System.out.println("Enter Village:");
        String village = scan.next();
        System.out.println("Enter Postcode:");
        Integer postcode = getScanNumbers();
        return new Address(city, village, postcode);
    }

    private static PoneNumber addPhoneNumberToPerson() {
        PoneNumber poneNumber = new PoneNumber();
        poneNumber.setPhoneNumber(getScanNumbers());
        Session s = SFactory.getOpenSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.save(poneNumber);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
        return poneNumber;
    }

    private static void getGreeting() {
        System.out.println("                Person program \nEnter commands: " +
                "\n____________________________________________" +
                "\n- add    (add Person to base)" +
                "\n- find   (find Person with ID)" +
                "\n- update (update Person with ID)" +
                "\n- delete (delete Person with ID)" +
                "\n- end    (exit)" +
                "\n____________________________________________" +
                "\n=>");
    }
}
