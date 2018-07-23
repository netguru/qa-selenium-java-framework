package base;

import managers.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class TestingBase {

    protected static final Logger log = LogManager.getLogger(Logger.class.getName());
    protected static Context context;

}
