import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.fintech.qa.homework.utils.BeforeUtils;
import ru.fintech.qa.homework.utils.db.jdbc.DBService;

import java.sql.SQLException;

public class DBTests {

    @BeforeAll
    public static void setup() {
        BeforeUtils.createData();
    }

    @Test
    void animalQuantityTest() throws SQLException {
        final int expected = 10;
        int actual = DBService.executeQueryGetCountRows("animal");
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void tryAddId1_10(final int id) throws SQLException {
        boolean actual = DBService.addAnimalWithId(id);
        Assertions.assertFalse(actual);
    }

    @Test
    void addWorkmanNullName() throws SQLException {
        boolean actual = DBService.addWorkmanWithNullName();
        Assertions.assertFalse(actual);
    }

    @Test
    void placesQuantityIfAddOneRow() throws SQLException {
        final int expected = 6;
        DBService.addPlaces();
        int actual = DBService.executeQueryGetCountRows("places");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void threeSpecificZoos() throws SQLException {
        String[] expected = {"Центральный", "Северный", "Западный"};
        String[] actual = DBService.getColumn("zoo", "\"name\"");
        Assertions.assertArrayEquals(expected, actual);
    }
}
