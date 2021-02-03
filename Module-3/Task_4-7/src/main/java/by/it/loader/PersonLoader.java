package by.it.loader;

import by.it.pojos.Person;
import by.it.util.HibernateUtil;
import by.it.util.SFactory;
import org.hibernate.Session;

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
        System.out.println("Enter ID:");
        Integer id = getId();
        Session session = SFactory.getOpenSession();
        final Person read = hibernateUtil.read(id, session);
        if (read != null) {
            final Person updatePerson = getUpdate(read, id);
            if (updatePerson != null) {
                System.out.println(updatePerson);
            }
        }
        if (read == null) {
            System.out.println("You wont add new Person for ID=" + id + "? \nEnter 'yes' if true");
            String s = scan.nextLine();
            if (s.equals("yes")) {
                getNewPersonWithIdInput(id);
            } else System.out.println("Without update ID=" + id);
        }
        session.close();
    }

    private static void getNewPersonWithIdInput(Integer id) throws SQLException {
        System.out.println("Enter Age:");
        Integer intAge = getScanNumbers();
        System.out.println("Enter name:");
        String name = scan.next();
        System.out.println("Enter surname:");
        String surname = scan.next();
        PreparedStatement preparedStatement = SFactory.getOpenConnection();
        hibernateUtil.createWithId(id, intAge, name, surname, preparedStatement);
        System.out.println("You add new Person for ID=" + id);
        SFactory.getOpenConnection().close();
    }

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
        String age = scan.next();
        Integer intAge = null;
        try {
            intAge = Integer.valueOf(age);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String nameP = read.getName();
        System.out.println("Name of this Person: " + nameP +
                "\nEnter new Name:");
        String name = scan.next();
        String surnameP = read.getSurname();
        System.out.println("Surname of this Person: " + surnameP +
                "\nEnter new Surname:");
        String surname = scan.next();
        Session session = SFactory.getOpenSession();
        Person update = hibernateUtil.update(id, intAge, name, surname, session);
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
        Session session = SFactory.getOpenSession();
        Person person = new Person(null,intAge,name,surname);
        Integer integer = hibernateUtil.create(person, session);
        session.close();
        return integer;
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
