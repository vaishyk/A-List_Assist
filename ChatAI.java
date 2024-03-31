package chatAI;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.InputStream;
import java.io.OutputStreamWriter;
public class ChatAI {
	
	public ChatAI() {
		
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


	public static void main(String[] args) {
		System.out.println("What's up Vaishy? It's Snoop D O double G and I'm here to talk with you about anything you want! \nWe can discuss any mental health problems you may be having. I'm here to listen you, and provide advice. \nWhat would you like to talk about today? ");
		Scanner prompt = new Scanner(System.in);
		String input = prompt.nextLine();
		System.out.println(Gemini(input));

	}
}