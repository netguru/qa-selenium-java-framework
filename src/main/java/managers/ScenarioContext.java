package managers;

import utilities.ContextType;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private Map<String, Object> scenarioContext;

    ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

    public void setContext(ContextType key, Object value) {
        scenarioContext.put(key.toString(), value);
    }

    public Object getContext(ContextType key) {
        return scenarioContext.get(key.toString());
    }

    public Boolean isContains(ContextType key) {
        return scenarioContext.containsKey(key.toString());
    }
}
