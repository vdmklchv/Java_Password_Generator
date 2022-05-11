import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int uppercase = sc.nextInt();
        int lowercase = sc.nextInt();
        int digits = sc.nextInt();
        int length = sc.nextInt();


        StringBuilder password = new StringBuilder();


        CharType currentCharType = null;
        String currentElement = null;


        for (int i = 0; i < length; i++) {
            if (uppercase > 0) {
                String newElement = getRandomElement(CharType.UPPER);
                while (Objects.equals(newElement, currentElement)) {
                    newElement = getRandomElement(CharType.UPPER);
                }
                currentElement = newElement;
                password.append(currentElement);
                uppercase--;
                currentCharType = CharType.UPPER;
                continue;
            }
            if (lowercase > 0) {
                String newElement = getRandomElement(CharType.LOWER);
                while (Objects.equals(newElement, currentElement)) {
                    newElement = getRandomElement(CharType.LOWER);
                }
                currentElement = newElement;
                password.append(currentElement);
                lowercase--;
                currentCharType = CharType.LOWER;
                continue;
            }
            if (digits > 0) {
                String newElement = getRandomElement(CharType.DIGIT);
                while (Objects.equals(newElement, currentElement)) {
                    newElement = getRandomElement(CharType.DIGIT);
                }
                currentElement = newElement;
                password.append(currentElement);
                digits--;
                currentCharType = CharType.DIGIT;
            }
        }

        int remaining = length - password.length();

        for (int j = 0; j < remaining; j++) {
            CharType newType = getRandomType();
            while (Objects.equals(newType, currentCharType)) {
                newType = getRandomType();
            }
            currentCharType = newType;
            String newElement = getRandomElement(currentCharType);
            while (Objects.equals(newElement, currentElement)) {
                newElement = getRandomElement(currentCharType);
            }
            currentElement = newElement;
            password.append(currentElement);
        }

        System.out.println(password);
    }

    private static String getRandomElement(CharType type) {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        int index;
        switch (type) {
            case DIGIT:
                index = (int) (Math.random() * numbers.length());
                return String.valueOf(numbers.charAt(index));
            case UPPER:
                index = (int) (Math.random() * chars.length());
                return String.valueOf(chars.charAt(index)).toUpperCase();
            case LOWER:
                index = (int) (Math.random() * chars.length());
                return String.valueOf(chars.charAt(index));
            default:
                System.out.println("Something wrong with characters");
                return null;
        }
    }

    private static CharType getRandomType() {
        int randIndex = (int) (Math.random() * 3);
        switch (randIndex) {
            case 0:
                return CharType.UPPER;
            case 1:
                return CharType.LOWER;
            case 2:
                return CharType.DIGIT;
            default:
                System.out.println("Illegal type");
                return null;
        }
    }

    enum CharType {
        UPPER, LOWER, DIGIT
    }
}