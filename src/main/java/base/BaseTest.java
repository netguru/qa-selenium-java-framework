package base;

import managers.Context;
import managers.PageObjectManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseTest {

    protected static final Logger log = LogManager.getLogger(Logger.class.getName());
    protected static Context context;
    protected static PageObjectManager pages;

}
