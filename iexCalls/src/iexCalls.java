

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class iexCalls {

    private static HttpURLConnection connection;


    public static void main(String args[]){
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try {
            //sk_a1279da1a0124ed4a70b366f7ef63c02
            URL url = new URL(
            "https://cloud.iexapis.com/v1/stock/AAPL/quote?token=pk_2651739bfa384218ae2b69959adb6c2a");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);

            if(status > 299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }
            else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            System.out.print(responseContent.toString());
        }
        catch (MalformedURLException e){
                e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            connection.disconnect();
        }
    }
}
