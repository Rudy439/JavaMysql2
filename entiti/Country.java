package sk.kosickaakademia.mysql.entity.entiti;

public class Country {
    private String name;
    private String code3;
    private String capitalCity;
    private String continent;
    private int area;




       public Country(String name, String code3, String capitalCity, int area, String continent) {
        this.name = name;
        this.code3 = code3;
        this.capitalCity = capitalCity;
        this.area = area;
        this.continent = continent;


    }

    public String getName() {
        return name;
    }

    public String getCode3() {
        return code3;
    }

    public String getCapitalCity() {
        return capitalCity;
    }

    public int getArea() {
        return area;
    }

    public String getContinent() {
        return continent;
    }


}

