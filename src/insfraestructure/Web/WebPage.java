package insfraestructure.Web;

import config.EnvConfig;
import insfraestructure.Logger.LogBackLogger;
import spark.Service;
public class WebPage {
    private static EnvConfig envConfig = EnvConfig.getInstance();
    private static Integer PORT = envConfig.getInt("PORT_WEB");
    private static LogBackLogger logger = new LogBackLogger(WebPage.class);

    public static void run() {

        Service web = Service.ignite();
        //port
        web.port(PORT);

        // routes
        web.get("/", (req, res) -> "Welcome to the Task Tracker Web Interface!");

        // listen
        logger.info(
            String.format("Web server running on http://localhost:%d", PORT)
        );
    }
}