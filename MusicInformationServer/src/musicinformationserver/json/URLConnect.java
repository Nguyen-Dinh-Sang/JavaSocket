package musicinformationserver.json;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Scanner;

public class URLConnect {

    public enum TYPE {SONGS, SONGINFO, SINGS, SINGINFO}

    public URLConnect() {
        System.out.println("Create URL Connect");
    }

    public String getData(String search, TYPE type) {
        URL url;
        try {
            switch (type) {
                case SINGS : {
                    url = new URL("https://nhom62-api-server.herokuapp.com/search/sing?name=" + URLEncoder.encode(search, "UTF-8"));
                    return getDataFomURL(url);
                }

                case SONGS : {
                    url = new URL("https://nhom62-api-server.herokuapp.com/search/song?name=" + URLEncoder.encode(search, "UTF-8"));
                    return getDataFomURL(url);
                }

                case SINGINFO : {
                    url = new URL(search);
                    return getDataFomURL(url);
                }

                case SONGINFO : {
                    url = new URL(search);
                    return getDataFomURL(url);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return getData(search, type);
    }

    private String getDataFomURL(URL url) {
        try {
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
                System.out.println(inline);
                return inline;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getDataFomURL(url);
    }
}
