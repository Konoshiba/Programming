package Places;

import Entity.*;

public abstract class Place extends Entity {

    public Place(EnumGender gender, String name) {
        super(gender, name);
    }



    public static class Dinner extends Place implements InfActionWithFood{
        public Dinner(){ super(EnumGender.MALE, "Обед"); }
        @Override
        public String actWithFood(Verb verb, EnumFood ... obj) {
            String result = "";
            result+=(this.toString() + " ");
            result+=verb.male() + " с ";

            for (int i = 0; i < obj.length; i++) {
                result+=(obj[i].getFood() + " ");
            }
            return result;
        }
        @Override
        public String toString() {
            return getName();
        }
    }
    public static class FarewellDinner extends Place{
        public FarewellDinner(){ super(EnumGender.MALE, "Прощальный обед"); }
    }
    public static class Bureau extends Place{
        public Bureau(){ super(EnumGender.MALE, "Бюро путешествий"); }
    }
    public static class Feast extends Place {
        public Feast(){ super(EnumGender.MALE, "Маленький пир"); }
    }

    public static class Hallway extends Place{
        public Hallway(){ super(EnumGender.FEMALE, "Прихожая"); }
    }
    public static class Hutor extends Place{
        public Hutor(){ super(EnumGender.MALE, "Крестьянский хутор"); }
    }

    public static class Sailing extends Place {
        public Sailing(){ super(EnumGender.MALE, "Парусный спорт"); }
    }
    public static class Table extends Place {
        public Table() {
            super(EnumGender.MALE, "Стол");
        }
    }
        public static class Water extends Entity {
            public Water(){ super(EnumGender.FEMALE, "Вода"); }
        }
        public static class Window extends Place{
            public Window(){ super(EnumGender.NEUTRAL, "Окно"); }
        }
        public static class YachtClub extends Place {
            public YachtClub(){ super(EnumGender.MALE, "Яхтклуб"); }
        }
    public static class Boat extends Place {
        public Boat(){ super(EnumGender.MALE, "Пароход"); }
    }

}
