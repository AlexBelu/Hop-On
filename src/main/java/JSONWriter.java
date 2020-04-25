import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONWriter {
    private static FileWriter file;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        // JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
        JSONObject obj = new JSONObject();

        JSONArray users = new JSONArray();
        JSONObject user1 = new JSONObject();
        user1.put("username", "Liana Aruncutean");
        user1.put("password", "ponei");
        user1.put("role", "Customer");
        users.add(user1);
        JSONObject user2 = new JSONObject();
        user2.put("username", "Alexandru Belu");
        user2.put("password", "troian");
        user2.put("role", "Pilot");
        users.add(user2);
        JSONObject user3 = new JSONObject();
        user3.put("username", "Andrei Bisoc");
        user3.put("password", "spartan");
        user3.put("role", "Customer");
        users.add(user3);
        obj.put("UsersList", users);
        try {

            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("D:/Facultate/An 2 - Liana/Semestrul 2/FIS/Hop-On/Hop-On/jsonFile.json");
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
