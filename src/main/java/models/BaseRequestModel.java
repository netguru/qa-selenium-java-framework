package models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import helpers.ViewModelHelper;

public class BaseRequestModel {
    public JsonObject getViewModel() {
        return ViewModelHelper.createViewModel(this, this.getClass());
    }
}
