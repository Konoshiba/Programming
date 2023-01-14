package Entity;

public enum EnumGender {
    MALE("Он"),
    FEMALE("Она"),
    NEUTRAL("Оно"),
    PLURAL("Они");
    private String gender;
    EnumGender(final String gender){
        this.gender = gender;
    }
    public String getGender(){return gender;}
    @Override
    public String toString(){
        return getGender();
    }
}
