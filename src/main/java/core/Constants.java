package core;

import io.github.cdimascio.dotenv.Dotenv;

public class Constants {

    static Dotenv dotenv = Dotenv.configure()
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

            public static final String URL = dotenv.get("PROJECT_URL");
            public static final String CHROME = "chrome";
            public static final String FIREFOX = "firefox";
            public static final String IEXPLORER = "iexplorer";
            public static final String HEADLESS = "headless";


}//end class
