package service.core;

import java.io.Serializable;

/**
 * Data Class that contains client information
 *
 * @author Rem
 *
 */
public class ClientInfo implements Serializable {

    // Ensure that your class has a version ID for serialization
    private static final long serialVersionUID = 1L;

    public static final char MALE = 'M';
    public static final char FEMALE = 'F';

    public String name;
    public char gender;
    public int age;
    public double height;
    public double weight;
    public boolean smoker;
    public boolean medicalIssues;

    // Constructor with fields
    public ClientInfo(String name, char sex, int age, double height, double weight, boolean smoker, boolean medicalIssues) {
        this.name = name;
        this.gender = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.smoker = smoker;
        this.medicalIssues = medicalIssues;
    }

    // Default constructor
    public ClientInfo() {
    }
}
