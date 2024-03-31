package user;

import java.util.Scanner;

/**
 * 
 */
public class User {
    
    private String name;
    private String email;
    private String username;
    private String password;
    private String conditions;
    private boolean shareHealth;
    
    public User(String name, String email, String username, String password, boolean shareHealth, String conditions) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.shareHealth = shareHealth;
        this.conditions = conditions;
    }
    
    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the login page.");
        
        System.out.print("Enter your username: ");
        String inputUsername = scanner.nextLine();
        
        System.out.print("Enter your password: ");
        String inputPassword = scanner.nextLine();
        
        scanner.close();
        
        // Check if the input username and password match the stored values
        return inputUsername.equals(username) && inputPassword.equals(password);
    }
}