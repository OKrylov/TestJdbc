import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new SingleConnectionDataSource("jdbc:sqlite:sample.db", true);
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        Map<String, Object> params = new HashMap<>();
        params.put("name", "Oleg");

        List<Client> clients = jdbcTemplate.query("SELECT * FROM Client WHERE FIRST_NAME = :name", params, (resultSet, i) -> {
            Client client = new Client();
            client.setId((int) resultSet.getLong(1));
            client.setBirthDate(resultSet.getDate(2).toLocalDate());
            client.setFirstName(resultSet.getString(3));
            client.setLastName(resultSet.getString(4));
            return client;
        });

        System.out.println(clients);
    }
}
