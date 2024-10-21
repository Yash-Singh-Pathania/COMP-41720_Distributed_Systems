package service.core;

/**
 * Data class that contains client information.
 *
 * @author Rem
 *
 */
public class ClientInfo {

    public static final char MALE = 'M';
    public static final char FEMALE = 'F';

    // Default constructor required for serialization
    public ClientInfo() {
    }

    public ClientInfo(String name, char sex, int age, double height, double weight, boolean smoker, boolean medicalIssues) {
        this.name = name;
        this.gender = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.smoker = smoker;
        this.medicalIssues = medicalIssues;
    }

    // Public fields as per provided guidance
    public String name;
    public char gender;
    public int age;
    public double height;
    public double weight;
    public boolean smoker;
    public boolean medicalIssues;
}
