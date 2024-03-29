package ca.uwo.csteam14;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileWriter;

/**
 * Data class used for reading and updating JSON files, managing POIs and bookmarks, and generating new POI IDs.
 * <br /><br />
 * The JSON files are read and POIs are stored in LinkedList objects when {@link #Data() Data()} is called.
 * POIs and bookmarks are added with the {@link #addPOI(POI, LinkedList<POI>) addPOI} method and POIs are removed with the {@link #removePOI(POI, LinkedList) removePOI} method.
 * <br /><br />
 * <b>Example Use:</b>
 * <pre>
 * {@code
 *     new Data();
 *     addPOI(p, userCreatedPOIs);
 *     removePOI(p2, bookmarks);
 * }
 * </pre>
 * <b>Example Output:</b> No output, but userCreatedPOIs, and bookmarks list are updated accordingly along with the associated JSON files.
 * @version 1.0.0
 * @author Robert Beemer
 * @author Arjuna Kadirgamar
 * @author Jason Shew
 * @author Daniel Gomes
 * @author Joshua Cini
 */
public class Data extends LinkedList<POI> {
    /**
     * A LinkedList object containing all built-in POIs.
     */
    protected static LinkedList<POI> builtInPOIs;
    /**
     * A LinkedList object containing all user-created POIs.
     */
    protected static LinkedList<POI> userCreatedPOIs;
    /**
     * A LinkedList object containing all bookmarks.
     */
    protected static LinkedList<POI> bookmarks;

    /**
     * Defines initial user-created POI ID starting from 4000000.
     */
    protected static int userPOIStartID = 4000000;

    /**
     * Defines initial user POI capacity as 999999.
     */
    protected static int userPOICapacity = 999999;

    /**
     * Defines initial developer-created POI ID starting from 5000000.
     */
    protected static int devPOIStartID = 5000000;

    /**
     * Defines initial developer POI capacity as 999999.
     */
    protected static int devPOICapacity = 999999;

    /**
     * Data Constructor
     * Initializes the linked lists
     * reads data from json files and adds the data to respective linked lists
     */

    public Data() {
        JSONParser parser = new JSONParser();
        builtInPOIs = new LinkedList<>();
        userCreatedPOIs = new LinkedList<>();
        bookmarks = new LinkedList<>();

        try {
            Object obj1 = parser.parse(new FileReader("./data/builtin.json"));
            Object obj2 = parser.parse(new FileReader("./data/user.json"));
            Object obj3 = parser.parse(new FileReader("./data/bookmarks.json"));
            JSONObject jObject1 = (JSONObject) obj1;
            JSONObject jObject2 = (JSONObject) obj2;
            JSONObject jObject3 = (JSONObject) obj3;
            JSONArray POIArray1 = (JSONArray) jObject1.get("BuiltInPOIs");
            JSONArray POIArray2 = (JSONArray) jObject2.get("UserPOIs");
            JSONArray POIArray3 = (JSONArray) jObject3.get("Bookmarks");

            Iterator var8;
            Object o;
            JSONObject point;
            long POIId;
            int id;
            String name;
            String building;
            String buildingCode;
            String floor;
            long roomNum;
            int roomNumber;
            String category;
            String description;
            String mapFileName;
            long x;
            int mapX;
            long y;
            int mapY;
            boolean builtIn;
            POI newPoint;
            if (POIArray1 != null) {
                for (var8 = POIArray1.iterator(); var8.hasNext(); builtInPOIs.add(newPoint)) {
                    o = var8.next();
                    point = (JSONObject) o;
                    POIId = (Long) point.get("POIId");
                    id = (int) POIId;
                    name = (String) point.get("POIName");
                    building = (String) point.get("building");
                    buildingCode = (String) point.get("buildingCode");
                    floor = (String) point.get("floor");
                    roomNum = (Long) point.get("roomNumber");
                    roomNumber = (int) (roomNum);
                    category = (String) point.get("category");
                    description = (String) point.get("description");
                    mapFileName = (String) point.get("map");
                    x = (Long) point.get("mapX");
                    mapX = (int) x;
                    y = (Long) point.get("mapY");
                    mapY = (int) y;
                    builtIn = (Boolean) point.get("built-in");
                    newPoint = new POI(id);
                    newPoint.setCategory(category);
                    newPoint.setMap(mapFileName);
                    newPoint.setX(mapX);
                    newPoint.setY(mapY);
                    newPoint.setName(name);
                    newPoint.setBuilding(building);
                    newPoint.setCode(buildingCode);
                    newPoint.setFloor(floor);
                    newPoint.setRoomNumber(roomNumber);
                    newPoint.setDescription(description);
                    newPoint.setBuiltIn(builtIn);
                    switch (floor) {
                        case "Ground Floor" -> newPoint.setMap(buildingCode + "0F.png");
                        case "First Floor" -> newPoint.setMap(buildingCode + "1F.png");
                        case "Second Floor" -> newPoint.setMap(buildingCode + "2F.png");
                        case "Third Floor" -> newPoint.setMap(buildingCode + "3F.png");
                        case "Fourth Floor" -> newPoint.setMap(buildingCode + "4F.png");
                    }
                }
            }

            if (POIArray2 != null) {
                for (var8 = POIArray2.iterator(); var8.hasNext(); userCreatedPOIs.add(newPoint)) {
                    o = var8.next();
                    point = (JSONObject) o;
                    POIId = (Long) point.get("POIId");
                    id = (int) POIId;
                    name = (String) point.get("POIName");
                    building = (String) point.get("building");
                    buildingCode = (String) point.get("buildingCode");
                    floor = (String) point.get("floor");
                    roomNum = (Long) point.get("roomNumber");
                    roomNumber = (int) (roomNum);
                    category = (String) point.get("category");
                    description = (String) point.get("description");
                    mapFileName = (String) point.get("map");
                    x = (Long) point.get("mapX");
                    mapX = (int) x;
                    y = (Long) point.get("mapY");
                    mapY = (int) y;
                    builtIn = (Boolean) point.get("built-in");
                    newPoint = new POI(id);
                    newPoint.setCategory(category);
                    newPoint.setMap(mapFileName);
                    newPoint.setX(mapX);
                    newPoint.setY(mapY);
                    newPoint.setName(name);
                    newPoint.setBuilding(building);
                    newPoint.setCode(buildingCode);
                    newPoint.setFloor(floor);
                    newPoint.setRoomNumber(roomNumber);
                    newPoint.setDescription(description);
                    newPoint.setBuiltIn(builtIn);
                    switch (floor) {
                        case "Ground Floor" -> newPoint.setMap(buildingCode + "0F.png");
                        case "First Floor" -> newPoint.setMap(buildingCode + "1F.png");
                        case "Second Floor" -> newPoint.setMap(buildingCode + "2F.png");
                        case "Third Floor" -> newPoint.setMap(buildingCode + "3F.png");
                        case "Fourth Floor" -> newPoint.setMap(buildingCode + "4F.png");
                    }
                }
            }

            if (POIArray3 != null) {
                for (var8 = POIArray3.iterator(); var8.hasNext(); bookmarks.add(newPoint)) {
                    o = var8.next();
                    point = (JSONObject) o;
                    POIId = (Long) point.get("POIId");
                    id = (int) POIId;
                    name = (String) point.get("POIName");
                    building = (String) point.get("building");
                    buildingCode = (String) point.get("buildingCode");
                    floor = (String) point.get("floor");
                    roomNum = (Long) point.get("roomNumber");
                    roomNumber = (int) (roomNum);
                    category = (String) point.get("category");
                    description = (String) point.get("description");
                    mapFileName = (String) point.get("map");
                    x = (Long) point.get("mapX");
                    mapX = (int) x;
                    y = (Long) point.get("mapY");
                    mapY = (int) y;
                    builtIn = (Boolean) point.get("built-in");
                    newPoint = new POI(id);
                    newPoint.setCategory(category);
                    newPoint.setMap(mapFileName);
                    newPoint.setX(mapX);
                    newPoint.setY(mapY);
                    newPoint.setName(name);
                    newPoint.setBuilding(building);
                    newPoint.setCode(buildingCode);
                    newPoint.setFloor(floor);
                    newPoint.setRoomNumber(roomNumber);
                    newPoint.setDescription(description);
                    newPoint.setBuiltIn(builtIn);
                    switch (floor) {
                        case "Ground Floor" -> newPoint.setMap(buildingCode + "0F.png");
                        case "First Floor" -> newPoint.setMap(buildingCode + "1F.png");
                        case "Second Floor" -> newPoint.setMap(buildingCode + "2F.png");
                        case "Third Floor" -> newPoint.setMap(buildingCode + "3F.png");
                        case "Fourth Floor" -> newPoint.setMap(buildingCode + "4F.png");
                    }
                }
            }
        } catch (Exception var30) {
            var30.printStackTrace();
        }
    }

    /**
     * This method is responsible for accessing POIs that have been selected by the user.
     *
     * @param currentFloor the floor that is currently on the map
     * @param layerName    the name of the layer that is being shown
     * @return an Arraylist with the clicked on POIs
     */

    public static ArrayList<POI> getLayerPOIs(String currentFloor, String layerName) {
        ArrayList<POI> result = new ArrayList<>();
        for (POI p : builtInPOIs) {
            if (layerName.contains(p.category) && p.map.contains(currentFloor))
                result.add(p);
            if (layerName.contains("Labs") && p.description != null && p.description.contains("Computer lab") && p.map.contains(currentFloor))
                result.add(p);
            if (p.description != null && layerName.contains("Accessibility") && p.description.contains("Accessible facility") && p.map.contains(currentFloor))
                result.add(p);
            if (layerName.contains("Bookmark") && p.map.contains(currentFloor) && Data.containsPOI(Data.bookmarks, p))
                result.add(p);
        }
        for (POI p : userCreatedPOIs) {
            if (layerName.contains(p.category) && p.map.contains(currentFloor))
                result.add(p);
            if (layerName.contains("Bookmark") && p.map.contains(currentFloor) && Data.containsPOI(Data.bookmarks, p))
                result.add(p);
        }
        return result;
    }

    /**
     * Adds the given POI to the given LinkedList and updates the associated JSON file.
     *
     * @param p   the POI to add
     * @param lst the LinkedList to add the POI to
     * @return a boolean value indicating whether the POI was successfully added or not
     */
    public static boolean addPOI(POI p, LinkedList<POI> lst) {
        POI temp = new POI(p);
        lst.removeIf(poi -> poi.isEqualTo(p));
        boolean result = lst.add(temp);

        if (result) {
            // write to user.json
            JSONObject obj = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            for (POI data : lst) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("POIId", data.id);
                jsonObject.put("POIName", data.name);
                jsonObject.put("building", data.building);
                jsonObject.put("buildingCode", data.code);
                jsonObject.put("floor", data.floor);
                jsonObject.put("roomNumber", data.roomNumber);
                jsonObject.put("category", data.category);
                jsonObject.put("description", data.description);
                jsonObject.put("map", data.map);
                jsonObject.put("mapX", data.positionX);
                jsonObject.put("mapY", data.positionY);
                jsonObject.put("built-in", data.isBuiltIn);
                jsonArray.add(jsonObject);
            }

            String filePath = "./data/";
            if (lst == Data.userCreatedPOIs) {
                obj.put("UserPOIs", jsonArray);
                filePath += "user.json";
            } else if (lst == Data.builtInPOIs) {
                obj.put("BuiltInPOIs", jsonArray);
                filePath += "builtin.json";
            } else if (lst == Data.bookmarks) {
                obj.put("Bookmarks", jsonArray);
                filePath += "bookmarks.json";
            }

            try (FileWriter file = new FileWriter(filePath)) {
                file.write(obj.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Removes the given POI from the given LinkedList and updates the associated JSON file.
     *
     * @param p   the POI to remove
     * @param lst the LinkedList to remove the POI from
     * @return a boolean value indicating whether the POI was successfully removed or not
     * @throws IOException if an I/O error occurs
     */
    public static boolean removePOI(POI p, LinkedList<POI> lst) throws IOException {
        int count = 0;
        Iterator<POI> iter = lst.iterator();
        while (iter.hasNext()) {
            POI poi = iter.next();
            if (p.isEqualTo(poi)) {
                iter.remove();
                count++;
            }
        }
        boolean result = count > 0; // returns a value indicating the result of removal

        if (result) {
            JSONObject obj = new JSONObject();
            // Create a new json array of json objects that hold objects from the linked list of POIs
            JSONArray jsonArray = new JSONArray();
            if (lst.size() > 0) {
                for (POI data : lst) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("mapX", data.positionX);
                    jsonObject.put("mapY", data.positionY);
                    jsonObject.put("POIName", data.name);
                    jsonObject.put("POIId", data.id);
                    jsonObject.put("map", data.map);
                    jsonObject.put("description", data.description);
                    jsonObject.put("category", data.category);
                    jsonObject.put("buildingCode", data.code);
                    jsonObject.put("floor", data.floor);
                    jsonObject.put("building", data.building);
                    jsonObject.put("built-in", data.isBuiltIn);
                    jsonObject.put("roomNumber", data.roomNumber);
                    jsonArray.add(jsonObject);
                }
            }

            String filePath = "./data/";
            if (lst == Data.userCreatedPOIs) {
                obj.put("UserPOIs", jsonArray);
                filePath += "user.json";
            } else if (lst == Data.builtInPOIs) {
                obj.put("BuiltInPOIs", jsonArray);
                filePath += "builtin.json";
            } else if (lst == Data.bookmarks) {
                obj.put("Bookmarks", jsonArray);
                filePath += "bookmarks.json";
            }
            // Write the Json file
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(obj.toJSONString());
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * Method for accessing the linked list of built-in POIs.
     *
     * @return LinkedList of built-in POIs
     */
    public LinkedList<POI> getBuiltInPOIs() {
        return builtInPOIs;
    }

    /**
     * Method for accessing the list of user created POIs.
     *
     * @return LinkedList of user-created POIs
     */
    public LinkedList<POI> getUserCreatedPOIs() {
        return userCreatedPOIs;
    }

    /**
     * Method for accessing the list of bookmarked POIs.
     *
     * @return LinkedList of bookmarked POIs
     */
    public LinkedList<POI> getBookmarks() {
        return bookmarks;
    }

    /**
     * Method for finding a specific POI object on a current floor with a given coordinate set.
     *
     * @param currentFloor floor the POI resides on
     * @param x            POI's x co-ordinate
     * @param y            POI's y co-ordinate
     * @return POI object P with co-ordinates (x,y) on floor currentFloor
     */
    public POI getPOI(String currentFloor, int x, int y) {
        for (POI p : builtInPOIs) {
            if (p.map.contains(currentFloor) && p.positionX == x && p.positionY == y) {
                return p;
            }
        }
        for (POI p : userCreatedPOIs) {
            if (p.map.contains(currentFloor) && p.positionX == x && p.positionY == y) {
                return p;
            }
        }
        return null;
    }

    /**
     * This method checks if the given LinkedList contains the given POI.
     *
     * @param list the LinkedList that is being searched
     * @param poi  the POI that is being searched for
     * @return a boolean indicating if the linked list contains the POI or doesn't contain the POI
     */
    public static boolean containsPOI(LinkedList<POI> list, POI poi) {
        if (list != null && list.size() > 0) {
            for (POI p : list) {
                if (p != null && p.isEqualTo(poi)) return true;
            }
        }
        return false;
    }

    /**
     * Generates and returns an appropriate, unused POI ID based on who is creating the POI.
     *
     * @param creator who is creating the POI
     * @return an unused, appropriate ID for a potential new POI
     */
    public static int generatePOIID(String creator) {
        int largestID = -1;
        switch (creator.toLowerCase()) {
            case "user" -> {
                largestID = userPOIStartID;
                for (POI p : userCreatedPOIs) {
                    if (p.id > largestID) {
                        largestID = p.id;
                        if (largestID == userPOIStartID + userPOICapacity) {
                            userPOIStartID *= 10;
                            userPOICapacity = userPOICapacity * 10 + 9;
                            largestID = userPOIStartID;
                        }
                    }
                }
            }
            case "dev" -> {
                largestID = devPOIStartID;
                for (POI p : builtInPOIs) {
                    if (p.id > largestID) {
                        largestID = p.id;
                        if (largestID == devPOIStartID + devPOICapacity) {
                            devPOIStartID *= 10;
                            devPOICapacity = devPOICapacity * 10 + 9;
                            largestID = devPOIStartID;
                        }
                    }
                }
            }
        }
        return largestID + 1;
    }

    /**
     * Removes all elements in a given LinkedList and writes the updated list to a JSON file.
     *
     * @param lst the LinkedList of POIs to remove elements from
     * @return a boolean value indicating if the list is empty after removing elements
     * @throws IOException if there is an error writing to the JSON file
     */
    public static boolean nuke(LinkedList<POI> lst) throws IOException {

        Iterator<POI> iter = lst.iterator();
        if (lst != bookmarks) {
            while (iter.hasNext()) {
                POI poi = new POI(iter.next());
                iter.remove();
                if (Data.containsPOI(bookmarks, poi))
                    Data.removePOI(poi, bookmarks); // deletes bookmarked POIs
            }
        } else {
            while (iter.hasNext()) {
                iter.next();
                iter.remove();
            }
        }
        boolean result = lst.size() == 0;
        if (result) {
            JSONObject obj = new JSONObject();
            // Creates a new json array of json objects that hold objects from the linked list of POIs
            JSONArray jsonArray = new JSONArray();

            String filePath = "./data/";
            if (lst == Data.userCreatedPOIs) {
                obj.put("UserPOIs", jsonArray);
                filePath += "user.json";
            } else if (lst == Data.builtInPOIs) {
                obj.put("BuiltInPOIs", jsonArray);
                filePath += "builtin.json";
            } else if (lst == Data.bookmarks) {
                obj.put("Bookmarks", jsonArray);
                filePath += "bookmarks.json";
            }
            // Write the Json file
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(obj.toJSONString());
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Resets the application's data by removing all POIs from the bookmarks and userCreatedPOIs lists and saving the empty lists to JSON files.
     *
     * @return true if the bookmarks and userCreatedPOIs lists are empty after the reset, false otherwise
     * @throws IOException if there is an error writing to the JSON files
     */
    public static boolean reset() throws IOException {

        // Remove all POIs from bookmarks list
        Iterator<POI> iter1 = bookmarks.iterator();
        while (iter1.hasNext()) {
            iter1.next();
            iter1.remove();
        }

        // Save bookmarks list as empty JSON file
        JSONObject obj1 = new JSONObject();
        JSONArray jsonArray1 = new JSONArray();
        obj1.put("Bookmarks", jsonArray1);
        try (FileWriter fileWriter = new FileWriter("./data/bookmarks.json")) {
            fileWriter.write(obj1.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove all POIs from userCreatedPOIs list
        Iterator<POI> iter2 = userCreatedPOIs.iterator();
        while (iter2.hasNext()) {
            iter2.next();
            iter2.remove();
        }

        // Save userCreatedPOIs list as empty JSON file
        JSONObject obj2 = new JSONObject();
        JSONArray jsonArray2 = new JSONArray();
        obj2.put("UserPOIs", jsonArray2);
        try (FileWriter fileWriter = new FileWriter("./data/user.json")) {
            fileWriter.write(obj2.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return true if both lists are empty after reset, false otherwise
        return bookmarks.size() + userCreatedPOIs.size() == 0;
    }

}
