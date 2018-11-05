package managers;

import utilities.ContexType;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private Map<String, Object> scenarioContext;

    ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

    public void setContext(ContexType key, Object value) {
        scenarioContext.put(key.toString(), value);
    }

    public Object getContext(ContexType key) {
        return scenarioContext.get(key.toString());
    }

    public Boolean isContains(ContexType key) {
        return scenarioContext.containsKey(key.toString());
    }
}
