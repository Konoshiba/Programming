package SourseFiles;

public enum VenueType {
    BAR,
    LOFT,
    MALL;
    public static String venuelList(){
        StringBuilder fuelList = new StringBuilder();
        for (VenueType types: values()){
            fuelList.append(types.name()).append(";");
        }
        return fuelList.substring(0, fuelList.length());
    }
}