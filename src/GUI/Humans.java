package GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

public class Humans {

    static ResultSet result = null;
    static Connection con;
    static PreparedStatement state;


    String firstName, lastName;
    int age;
    String gender;
    String ethnicity, religion;
    float height, weight;
    boolean elementary, highSchool, master, employed;
    double salary;
    boolean alive = true, healthy = true;


    public Humans() {
    }

    public Humans(String fName, String lName, int age, String gender, String ethnicity, String religion, float height, float weight, boolean elementary, boolean highSchool, boolean master, boolean employed, double salary, boolean alive, boolean healthy) {
        super();
        this.setFirstName(fName);
        this.setLastName(lName);
        this.setAge(age);
        this.setGender(gender);
        this.setEthnicity(ethnicity);
        this.setReligion(religion);
        this.setHeight(height);
        this.setWeight(weight);
        this.setElementary(elementary);
        this.setHighSchool(highSchool);
        this.setMaster(master);
        this.setEmployed(employed);
        this.setSalary(salary);
        this.setAlive(alive);
        this.setHealthy(healthy);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isElementary() {
        return elementary;
    }

    public void setElementary(boolean elementary) {
        this.elementary = elementary;
    }

    public boolean isHighSchool() {
        return highSchool;
    }

    public void setHighSchool(boolean highSchool) {
        this.highSchool = highSchool;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    public boolean isEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isHealthy() {
        return healthy;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }

   // Humans(firstname, lastname, age, gender, ethnicity, religion, height, weight, alive, health)
    public static class HumanGenerator {
        private static String[] mFNames = {"Aaron", "Andrew", "Adolf", "Alan", "Benedict", "Barry", "Bill", "Brad", "Curt", "Corey", "Clint", "Donald", "Drew", "Dick", "Darius", "Joseph"};
        private static String[] fFNames = {"Alex", "Anelia", "Ada", "Aria", "Bella", "Stella", "Zoe", "Natalie", "Skylar", "Claire", "Violet", "Samantha", "Emilia", "Naomi", "Karolinka"};
        private static String[] lNames = {"Wolfs", "Kingston", "Dawson", "Martin", "Wick", "Milkov"};
        private static String[] ethnicities = {"Caucasian", "African", "Asian", "Indian"};
        private static String[] religions = {"Atheist", "Catholic", "Orthodox", "Judaic", "Buddhist", "Hindus"};
        private static String[] genders = {"Male", "Female"};

        public static Humans generate() {
            int arrayIndex = ThreadLocalRandom.current().nextInt(0, 1);
            String gender = genders[arrayIndex];
            String fName;
            if (gender.equalsIgnoreCase("Male")) {
                arrayIndex = ThreadLocalRandom.current().nextInt(0, mFNames.length);
                fName = mFNames[arrayIndex];
            }
            else {
                arrayIndex = ThreadLocalRandom.current().nextInt(0, fFNames.length);
                fName = fFNames[arrayIndex];
            }
            arrayIndex = ThreadLocalRandom.current().nextInt(0, lNames.length);
            String lName = lNames[arrayIndex];
            int age = ThreadLocalRandom.current().nextInt(18, 85);
            int h = ThreadLocalRandom.current().nextInt(150, 200);
            int h2 = ThreadLocalRandom.current().nextInt(0, 9);
            float height = (float) h + (float)(h2/10);
            int w = ThreadLocalRandom.current().nextInt(45, 110);
            int w2 = ThreadLocalRandom.current().nextInt(0, 9);
            float weight = (float) w + (float)(w2/10);
            arrayIndex = ThreadLocalRandom.current().nextInt(0, ethnicities.length);
            String ethnicity = ethnicities[arrayIndex];
            arrayIndex = ThreadLocalRandom.current().nextInt(0, religions.length);
            String religion = religions[arrayIndex];

            boolean elementary = ThreadLocalRandom.current().nextBoolean();
            boolean highschool;
            if(elementary == true) {
                highschool = true;
            } else { highschool = false; }
            boolean master;
            if(highschool == true) {
                master = true;
            } else { master = false; }

            boolean employed = ThreadLocalRandom.current().nextBoolean();
            double salary = 0.0;
            int b;
            /*if(employed == true) {
                try {
                    con = DBConnect.getConnection();

                    String sql = "SELECT MinimalWage FROM economy WHERE id=4;";

                    state = con.prepareStatement(sql);
                    result = state.executeQuery();

                    while(result.next()) {
                        salary = result.getDouble("MinimalWage");
                        b =ThreadLocalRandom.current().nextInt(0, 440);
                        salary = salary + (float) b;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }*/
            salary = 550.0;

            boolean alive = ThreadLocalRandom.current().nextBoolean();
            boolean health;
            if(alive == true){
                health = true;
            }
            else {
                health = false;
            }
            return new Humans(fName, lName, age, gender, ethnicity, religion, height, weight, elementary, highschool, master, employed, salary, alive, health);
        }
    }
}
