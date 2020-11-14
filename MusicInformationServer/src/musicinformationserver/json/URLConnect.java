package musicinformationserver.json;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class URLConnect {
    public URLConnect() {

    }

    public String getSings(String sing) {
        try {
            URL url = new URL("http://nhom62-api-server.herokuapp.com/info/sing?name=" + URLEncoder.encode(sing, "UTF-8"));
            System.err.println("link: " + url.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();
            if (responsecode == 200) {
                Scanner sc = new Scanner(url.openStream());
                String inline = "";
                while (sc.hasNext()) {
                    inline += sc.nextLine();
                }
                sc.close();
                return inline;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getSings(sing);
    }
}
