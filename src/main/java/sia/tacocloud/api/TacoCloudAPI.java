package sia.tacocloud.api;

import org.springframework.web.client.RestTemplate;
import sia.tacocloud.Ingredient;

public class TacoCloudAPI {
    RestTemplate rest = new RestTemplate();

    public Ingredient getIngredientById(String ingredientId) {
        return rest.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class, ingredientId);
    }

    public void updateIngredient(Ingredient ingredient) {
        rest.put("http://localhost:8080/ingredients/{id}", ingredient, ingredient.getId());
    }

    public void deleteIngredient(Ingredient ingredient) {
        rest.delete("http://localhost:8080/ingredients/{id}", ingredient.getId());
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return rest.postForObject("http://localhost:8080/ingredients/", ingredient, Ingredient.class);
    }
}
