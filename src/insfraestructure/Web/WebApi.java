package insfraestructure.Web;

import config.EnvConfig;
import spark.Service;

public class WebApi {
    private static EnvConfig envConfig = EnvConfig.getInstance();
    private static Integer PORT = envConfig.getInt("PORT_API");

    public static void run() {

        Service api = Service.ignite();
        // port
        api.port(PORT);

        // routes
        api.get("/", (req, res) -> "Welcome to the Task Tracker Web Interface!");

        // listen
        System.out.println(
            String.format("Api server running on http://localhost:%d", PORT)
        );
    }
}
