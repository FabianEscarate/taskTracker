package insfraestructure.Web;

import config.EnvConfig;
import insfraestructure.Logger.LogBackLogger;
import spark.Service;

public class WebApi {
    private static EnvConfig envConfig = EnvConfig.getInstance();
    private static Integer PORT = envConfig.getInt("PORT_API");
    private static LogBackLogger logger = new LogBackLogger(WebApi.class);

    public static void run() {

        Service api = Service.ignite();
        // port
        api.port(PORT);

        // routes
        api.get("/", (req, res) -> "Welcome to the Task Tracker Web Interface!");

        // listen
        logger.info(
            String.format("Api server running on http://localhost:%d", PORT)
        );
    }
}
