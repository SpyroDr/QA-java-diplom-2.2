import java.util.ArrayList;
import java.util.List;

public class IngridientsJson {
    private boolean success;
    private List<DataIngridientsJson> data;

    public List<DataIngridientsJson> getGroups() {
        return data;
    }

    public ArrayList<String> get_idAll() {
        ArrayList<String> id = new ArrayList<>();
        for (int i = 0; i < getGroups().size(); i++) {
            id.add(getGroups().get(i).get_id());
        }
        return id;
    }
}
