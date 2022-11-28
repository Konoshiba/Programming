package Humans;

import Entity.*;
public abstract class Human extends Alive {
    protected Human(EnumGender gender, String name) { super(gender, name); }
}
