package helpers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ViewModelHelper {
    public static JsonObject createViewModel(Object object, Class classToSerialize) {
        return new Gson().toJsonTree(object, classToSerialize).getAsJsonObject();
    }
}
