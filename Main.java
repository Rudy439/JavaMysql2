import sk.kosickaakademia.mysql.entity.entiti.City;
import sk.kosickaakademia.mysql.entity.output.Output;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        Output out = new Output();
        String name = "Italy";
        database.getCountryInfo("Italy");
        List<City> list=database.showCities(name);
        out.printCities(list);
        
  database.insertNewMonument("AUT" , "Wien","Karlskirche");
    }
}
