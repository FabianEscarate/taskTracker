package insfraestructure.Web;

import spark.Service;
public class WebPage {
    public static void run() {

        Service web = Service.ignite();
        //port
        web.port(3000);

        // routes
        web.get("/", (req, res) -> "Welcome to the Task Tracker Web Interface!");

        // listen
        System.out.println("Web server running on http://localhost:3000");
    }
}