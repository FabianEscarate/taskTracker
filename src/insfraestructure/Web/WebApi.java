package insfraestructure.Web;

import spark.Service;

public class WebApi {
     public static void run() {

        Service api = Service.ignite();
        //port
        api.port(3001);

        // routes
        api.get("/", (req, res) -> "Welcome to the Task Tracker Web Interface!");

        // listen
        System.out.println("API server running on http://localhost:3001");
    }
}
