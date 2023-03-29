package SourseFiles;

public enum TicketType {
    VIP,
    USUAL,
    BUDGETARY;
    public static String typeList() {
        StringBuilder types = new StringBuilder();
        for (TicketType type : values()) {
            types.append(type.name()).append(";");
        }
        return types.substring(0, types.length());
    }
}
