package factory;

import model.User;

import static utils.WebElementManipulatorAndGenerator.generateRandomIntervalNumber;

public class UserFactory {

    public static User getRandomUser() {
        switch (generateRandomIntervalNumber(1, 3)) {
            case 1:
                return new User("Augustyna ", "Nowicka", "Modrzewiowa 106", "Koszalin", "Polska", "Poland", "75-644", "728 960 735", "wiciorny@gmail.com");
            case 2:
                return new User("Teofil", "Kaczmarek", "Danusi 43 ", "Brzesko", "Poland", "Poland", "43-382", "662 182 675", "rhavyn@outlook.com");

            case 3:
                return new User("Wiktor", "Nowakowski", "Kutrzeby 11", "Warszawa", "Poland", "Poland", "30-102", "789-565-001", "nowak@gmail.com");

            default:
                return new User("Alicja", "Kowalska", "Wadowicka 11", "Wrocław", "Poland", "Poland", "21-893", "512-987-555", "ali_kowa@onet.pl");
        }
    }
}
