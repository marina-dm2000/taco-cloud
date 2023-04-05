package sia.tacocloud.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sia.tacocloud.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий ингредиентов на основе JdbcTemplate
 */
@Repository // создается как bean-компонент в контексте приложения Spring
public class JdbcIngredientRepository implements IngredientRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @return коллекция объектов
     */
    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query(
                "select id, name, type from Ingredient",
                this::mapRowToIngredient);
    }

    /**
     * поиск по id
     * @param id
     * @return если запрос не находит ничего, возвращает пустое значение, иначе первую строку результата
     */
    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> results = jdbcTemplate.query(
                "select id, name, type from Ingredient where id=?",
                this::mapRowToIngredient,
                id
        );
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    /**
     * добавление новой записи с помощью JdbcTemplate
     * @param ingredient сохранение объекта Ingredient
     * @return
     */
    @Override
    public Ingredient save(Ingredient ingredient) {
        // создает новые или изменяет существующие записи в БД
        jdbcTemplate.update(
                "insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString()
        );
        return ingredient;
    }

    /**
     * отображение каждой записи из набора результатов в объект
     * @param row
     * @param rowNum
     * @return
     * @throws SQLException
     */
    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
        return new Ingredient(
                row.getString("id"),
                row.getString("name"),
                Ingredient.Type.valueOf(row.getString("type"))
        );
    }
}
