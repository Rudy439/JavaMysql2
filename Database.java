import sk.kosickaakademia.mysql.entity.entiti.City;
import sk.kosickaakademia.mysql.entity.entiti.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Database {
    private String url="jdbc:mysql://itsovy.sk:3306/world_x";
    private String username = "mysqluser";
    private String password = "Kosice2021!";

    public List<City> showCities(String country) {
        String query = "SELECT city.Name , JSON_EXTRACT(Info,'$.Population') AS Population " +
                " FROM city "+
                " INNER JOIN country ON country.code = city,CountryCode"+
                " WHERE country.name LIKE ? ORDER BY Population DESC";
        List<City> list = new ArrayList<>();

        try {
            Connection conn = getConnection();
            if(conn!=null){

                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1,country);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String name = rs.getString("Name");
                    int pop = rs.getInt ("Population");
                    City city = new City(name,pop);
                    list.add(city);



                }
                conn.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;

    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url,username,password);
        return conn;
    }

    public Country getCountryInfo(String contry){
        String query = "SELECT country.name, country.code, city.name" +
                       "JSON_UNQUOTE(JSON_EXTRACT(doc, '$.Population')" +
                       " FROM country" +
                       "INNER JOIN city ON country.Capital = city.ID" +
                       "INNER JOIN countryinfo ON country.code = countryinfo._id " +
                       " WHERE country.name like ? ";
        Country countryInfo = null;

        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,country);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String code3=rs.getString("country.code");
                String capitalCity=rs.getString("city.name");
                String continent=rs.getString("continent");
                int area=rs.getInt("Area");
                countryInfo = new Country(country,code3,capitalCity,area,continent);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return countryInfo;


    }

}
