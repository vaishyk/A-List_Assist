package main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import user.User;

public class Main {

	public static void main(String[] args) {
		
		//post("C:\\Users\\nasee\\git\\MemoryMingle\\MemoryMingle\\test-file");
		//post("test-video.mp4");
		//post("///C:/Users/nasee/git/MemoryMingle/MemoryMingle/test-video.mp4");
		
		
		Scanner scanner = new Scanner(System.in);
		
        String n = createAccount(scanner);
        
        System.out.println("\nWelcome to the celebrity chat service!");
        System.out.println("Choose a celebrity to talk with:");
        System.out.println("1. Snoop Dogg");
        System.out.println("2. Zendaya");
        System.out.println("3. Neymar Júnior");
        System.out.println("4. Taylor Swift");
        System.out.println("5. Shah Rukh Khan");
        // Add more celebrities as needed
        
        System.out.print("Enter the number of the celebrity you want to talk to: ");
        
        int choice = scanner.nextInt();
        // Consume newline character
        
        String celebName = null;
        switch (choice) {
            case 1:
                celebName = "Snoop Dogg";
                break;
            case 2:
                celebName = "Zendaya";
                break;
            case 3:
                celebName = "Neymar Júnior";
                break;
            case 4:
            	celebName = "Taylor Swift";
            	break;
            case 5:
            	celebName = "Shah Rukh Khan";
            	break;
            default:
                System.out.println("Invalid choice.");
                scanner.close();
                return;
        }
        
        talkTo(celebName, n);
        
        scanner.close();
    }
    
    public static void talkTo(String celebName, String userName) {
    	Scanner scan = new Scanner(System.in);
    	
        System.out.println("\nConnecting you to " + celebName + "...");
        // Code to initiate the chat with the selected celebrity goes here
        System.out.println("You are now chatting with " + celebName + ".");
        System.out.println("\nWhat's up " +  userName + "? It's Snoop D O double G and I'm here to talk with you about anything you want! \nWe can discuss any mental health problems you may be having. I'm here to listen you, and provide advice. \nWhat would you like to talk about today? ");
		Scanner prompt = new Scanner(System.in);
		String input = prompt.nextLine();
		System.out.println(Gemini(input));
        //enter video filename for demo
		String fileName = scan.next();
		//post(fileName);
		post("/Users/vaishy/Downloads/snoopdogg.mp4");
		System.out.println("Respond to Snopp Dogg here");
		String response = scan.next();
    }
    
    public static String createAccount(Scanner scanner) {       
        System.out.println("Welcome to the account creation page.");
        
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        
        System.out.print("Choose a username: ");
        String username = scanner.nextLine();
        
        System.out.print("Choose a password: ");
        String password = scanner.nextLine();
        
        System.out.print("Would you like to share your health conditions? (yes/no): ");
        String shareChoice = scanner.nextLine();
        
        String conditions = "";
        boolean shareHealth = false;
        if (shareChoice.equalsIgnoreCase("yes")) {
            shareHealth = true;
            System.out.println("Enter your health conditions or choose from the following options: ");
            System.out.println("1. Anxiety");
            System.out.println("2. Depression");
            System.out.println("3. Post-traumatic stress disorder");
            System.out.println("4. Obsessive-compulsive disorder");
            System.out.println("5. Bipolar Disorder");
            System.out.println("5. Body dysmorphic disorder");
            // Add more options as needed
            System.out.print("Enter your choice (or type your conditions): ");
            conditions = scanner.nextLine();
        }
        
        System.out.println("\nAccount created successfully for user: " + username);
                
        // Create User object with the provided information
        User user = new User(name, email, username, password, shareHealth, conditions);
        return name;
    }  
    
    public static void post(String fileName) {
        // Replace "your_video_file_path.mp4" with the actual path to your video file
        
        File fileVideo = new File(fileName);
        String videoFilePath = fileVideo.getAbsolutePath();
        //fix
        System.out.println(videoFilePath);
        //String videoFilePath = absolute;

        // Use the appropriate command based on your operating system and media player
        String os = System.getProperty("os.name").toLowerCase();
        String command = "";

        if (os.contains("win")) {
            // Windows
            command = "cmd /c start vlc " + videoFilePath;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            // Linux or MacOS
            command = "vlc " + videoFilePath;
        } else {
            System.out.println("Unsupported operating system");
            System.exit(1);
        }

        try {
            // Execute the command
            ProcessBuilder processBuilder = new ProcessBuilder	(command.split(" "));
            Process process = processBuilder.start();

            // Wait for the process to finish (optional)
            int exitCode = process.waitFor();
            //System.out.println("Video player exited with code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    //Video videoPost = new Video(fileName);
    //profile.getProfileFeed().addVideo(videoPost);
}
    public static String Gemini(String x) {
	    String url = "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent?key=AIzaSyAsIZ7U934OKyFifKJPDJDdua4aJyPCqd4";
       try {
           URL obj = new URL(url);
           HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
           connection.setRequestMethod("POST");
           connection.setRequestProperty("Content-Type", "application/json");
           connection.setDoOutput(true);
           OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
           writer.write("{\"contents\":[{\"role\": \"user\",\"parts\":[{\"text\": \"" + x + "\"}]}]}");
           writer.flush();
           writer.close();
           InputStream responseStream = connection.getInputStream();
           Scanner s = new Scanner(responseStream);
           StringBuilder response = new StringBuilder();
           while (s.hasNext()) {
               response.append(s.nextLine());
           }
           // Using regular expressions to extract the desired response
           Pattern pattern = Pattern.compile("\"text\": \"(.*?)\"");
           Matcher matcher = pattern.matcher(response.toString());
           if (matcher.find()) {
               return matcher.group(1);
           } else {
               return "Response not found";
           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }
}