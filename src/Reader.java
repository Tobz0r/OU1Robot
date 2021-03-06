import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.deploy.util.ArrayUtil;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Parser for json file to a mapobjekt
 * @author Tobias Estefors, Thom Renström
 */
public class Reader {

    private Position[] path;

    public Reader() {

    }

    /**
     * Parses a jsonfile and adds it to an array
     * @param filePath the path to a jsonfile
     * @return an array with Position objects of the file
     * @throws IOException
     */
    public Position[] getPath(String filePath) throws IOException {
        int nPoints;

        File pathFile = new File(filePath);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(pathFile)));
        ObjectMapper mapper = new ObjectMapper();
// read the path from the file
        Collection<Map<String, Object>> data =
                (Collection<Map<String, Object>>) mapper.readValue(in, Collection.class);
        nPoints = data.size();
        path = new Position[nPoints];
        int index = 0;
        for (Map<String, Object> point : data) {
            Map<String, Object> pose = (Map<String, Object>) point.get("Pose");
            Map<String, Object> aPosition = (Map<String, Object>) pose.get("Position");
            double x = (Double) aPosition.get("X");
            double y = (Double) aPosition.get("Y");
            path[index] = new Position(x, y);
            index++;

        }
        return path;
    }

}
