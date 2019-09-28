package base;

import managers.Context;
import managers.JSExecutor;
import managers.PageObjectManager;
import managers.ZAPManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseTest {

    protected static final Logger log = LogManager.getLogger(Logger.class.getName());
    protected static Context context;
    protected static PageObjectManager pages;
    protected static ZAPManager zapManager;
    protected static JSExecutor jsExecutor;
}
