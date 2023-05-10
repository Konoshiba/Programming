package data;

import org.jetbrains.annotations.Nullable;

public enum TicketType {
    VIP,
    USUAL,
    BUDGETARY;

    @Nullable
    public static TicketType checkElement(String el) {
        for (TicketType tt: TicketType.values()) {
            if (el.toUpperCase().equals(tt.name()))
                return tt;
        }
        return null;
    }
}
