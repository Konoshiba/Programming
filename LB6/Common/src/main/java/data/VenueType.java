package data;

import org.jetbrains.annotations.Nullable;

public enum VenueType {
    BAR,
    LOFT,
    MALL;

    @Nullable
    public static VenueType checkElement(String el) {
        for (VenueType vt: VenueType.values()) {
            if (el.toUpperCase().equals(vt.name()))
                return vt;
        }
        return null;
    }
}
