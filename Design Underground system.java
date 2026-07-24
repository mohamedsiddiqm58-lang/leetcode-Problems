import java.util.HashMap;

class UndergroundSystem {

    // Stores customer check-in details
    private HashMap<Integer, CheckIn> checkInMap;

    // Stores total travel time and trip count for each route
    private HashMap<String, Route> routeMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckIn checkIn = checkInMap.get(id);

        String routeKey = checkIn.station + "-" + stationName;
        int travelTime = t - checkIn.time;

        Route route = routeMap.getOrDefault(routeKey, new Route());
        route.totalTime += travelTime;
        route.tripCount++;

        routeMap.put(routeKey, route);

        checkInMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "-" + endStation;
        Route route = routeMap.get(routeKey);

        return (double) route.totalTime / route.tripCount;
    }

    // Class to store check-in information
    static class CheckIn {
        String station;
        int time;

        CheckIn(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    // Class to store route statistics
    static class Route {
        int totalTime = 0;
        int tripCount = 0;
    }
}
