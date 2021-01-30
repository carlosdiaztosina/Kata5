package kata5;

import java.io.IOException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class Kata5 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("org.sqlite.JDBC");
        List<Mail> mailList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:data/US500.db");
            Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery("SELECT * FROM people");
            while (set.next()) {
                String mail = set.getString("email");
                System.out.println(mail);
                mailList.add(new Mail(mail));
            }
            
            Histogram<String> histogram = MailHistogramBuilder.build(mailList);
            HistogramDisplay histogramDisplay = new HistogramDisplay("Histogram...", histogram);
            histogramDisplay.execute();
            
        }
    }
    
}
