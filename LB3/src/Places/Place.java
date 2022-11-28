package Places;

import Entity.*;

public abstract class Place extends Entity {
    private String name;

    public Place(EnumGender gender, String name) {
        super(gender, name);
    }

}
