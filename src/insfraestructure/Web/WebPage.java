package insfraestructure.Web;

import java.util.HashMap;
import java.util.Map;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;

import spark.ModelAndView;
import spark.Service;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import spark.template.freemarker.FreeMarkerEngine;
import config.EnvConfig;
import insfraestructure.Logger.LogBackLogger;

public class WebPage {
    private static EnvConfig envConfig = EnvConfig.getInstance();
    private static Integer PORT = envConfig.getInt("PORT_WEB");
    private static LogBackLogger logger = new LogBackLogger(WebPage.class);

    public static void run() throws IOException {

        Service web = Service.ignite();
        // port
        web.port(PORT);

        // freemarker configuration
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDirectoryForTemplateLoading(new File("src/insfraestructure/Web/presenters"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // set freemarker engine
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(cfg);

        // routes
        web.get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("nombre", "Fabian Escárate");
            model.put("mensaje", "¡Bienvenido a mi presentación!");
            return new ModelAndView(model, "home.ftl");
        }, freeMarkerEngine);

        // listen
        logger.info(String.format("Web server running on http://localhost:%d", PORT));
    }
}