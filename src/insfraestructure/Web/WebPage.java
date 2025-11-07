package insfraestructure.Web;

import config.EnvConfig;
import spark.Service;
public class WebPage {
    private static EnvConfig envConfig = EnvConfig.getInstance();

    private static Integer PORT = envConfig.getInt("PORT_WEB");

    public static void run() {

        Service web = Service.ignite();
        //port
        web.port(PORT);

        // routes
        web.get("/", (req, res) -> "Welcome to the Task Tracker Web Interface!");

        // listen
        System.out.println(
            String.format("Web server running on http://localhost:%d", PORT)
        );
    }
}