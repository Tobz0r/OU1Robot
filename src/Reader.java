import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.Collection;
import java.util.Map;

/**
 * Created by sejiaw on 2016-09-23.
 */
public class Reader {

    public Reader(){

    }

    public void readPath() throws FileNotFoundException, JSONException {
        int nPoints;
        JSONObject obj = new JSONObject("Path-around-table.json");

        Position[] path;
// read the path from the file
        nPoints = data.size();
        path = new Position[nPoints];
        int index = 0;
        for (Map<String, Object> point : data)
        {
            Map<String, Object> pose = (Map<String, Object>)point.get("Pose");
            Map<String, Object> aPosition = (Map<String, Object>)pose.get("Position");
            double x = (Double)aPosition.get("X");
            double y = (Double)aPosition.get("Y");
            path[index] = new Position(x, y);
            index++;
        }

    }
}
