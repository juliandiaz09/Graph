import java.util.List;

public class MainDistance {

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.testDistance();

        List<Person> person1 = controller.recommendationsFriendsDistance(controller.juan, 100);
        List<Person> person2 = controller.recommendationsFriendsDistance(controller.ana, 290);
        List<Person> person3 = controller.recommendationsFriendsDistance(controller.pedro, 10);
        List<Person> person4 = controller.recommendationsFriendsDistance(controller.carlos, 60);
        List<Person> person5 = controller.recommendationsFriendsDistance(controller.maria, 49);

        listPerson(person1, controller.juan);
        listPerson(person2, controller.ana);
        listPerson(person3, controller.pedro);
        listPerson(person4, controller.carlos);
        listPerson(person5, controller.maria);

    }

    private static void listPerson(List<Person> listPerson, Person aux) {
        System.out.println("Personas cercanas a " + aux.getName() + ":");
        for (Person person: listPerson) {
            System.out.println("- " + person.getName());
        }

        System.out.println("-----------------------");
    }
}
