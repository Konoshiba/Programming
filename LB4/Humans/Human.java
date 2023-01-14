package Humans;

import Entity.*;
public abstract class Human extends Alive {
    protected Human(EnumGender gender, String name) { super(gender, name); }
    public static class All extends Human{
        public All(){ super(EnumGender.PLURAL, "Все"); }
    }
    public static class He extends Human{
        public He(){ super(EnumGender.MALE, "Он"); }
    }
    public static class She extends Human{
        public She(){ super(EnumGender.FEMALE, "Она"); }
    }
    public static class It extends Human{
        public It(){ super(EnumGender.NEUTRAL, "Оно"); }
    }
}