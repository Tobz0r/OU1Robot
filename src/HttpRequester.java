

import org.json.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpRequester {

    private final String robot_url="http://localhost:50000";


    // HTTP GET request
    public void requestGET() throws Exception {

        URL obj = new URL(robot_url+"/lokarria/localization");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        if(con.getResponseCode()!=200){
            System.out.println("GET ERROR");
        }
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject jsonresult=new JSONObject(response.toString());
        String x1 = jsonresult.getJSONObject("Pose").getJSONObject("Position").getString("X");
        String y1 = jsonresult.getJSONObject("Pose").getJSONObject("Position").getString("Y");
        Position myPosition=new Position(Double.parseDouble(x1),Double.parseDouble(y1));
    }

    // HTTP POST request
    public void requestPOST(double TargetAngularSpeed, double TargetLinearSpeed) throws Exception {
        URL obj = new URL(robot_url+"/lokarria/differentialdrive");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type","application/json");
        con.setRequestProperty("Accept", "text/json");
        // Send post request
        JSONObject param = new JSONObject();
        param.put("TargetAngularSpeed",TargetAngularSpeed);
        param.put("TargetLinearSpeed", TargetLinearSpeed);
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(param.toString());
        wr.flush();
        wr.close();
        if (con.getResponseCode()!=204){
            System.out.println("POST ERROR!!");
        }
    }

}