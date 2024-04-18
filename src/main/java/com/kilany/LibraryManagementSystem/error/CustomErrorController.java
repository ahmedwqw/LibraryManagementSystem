package com.kilany.LibraryManagementSystem.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
//@Controller
public class CustomErrorController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    @RequestMapping("/error")
    public String handleError() {
        logger.error("An error occurred while handling the error.");
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Error 404 - Page Not Found</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f0f0f0;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "            align-items: center;\n" +
                "            height: 100vh;\n" +
                "        }\n" +
                "\n" +
                "        .error-container {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        h1 {\n" +
                "            font-size: 3em;\n" +
                "            color: #333;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            font-size: 1.2em;\n" +
                "            color: #666;\n" +
                "            margin-bottom: 40px;\n" +
                "        }\n" +
                "\n" +
                "        a {\n" +
                "            text-decoration: none;\n" +
                "            color: #007bff;\n" +
                "            font-weight: bold;\n" +
                "        }\n" +
                "\n" +
                "        a:hover {\n" +
                "            text-decoration: underline;\n" +
                "        }\n" +
                "\n" +
                "        .image-container {\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "\n" +
                "        img {\n" +
                "            max-width: 100%;\n" +
                "            height: auto;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"error-container\">\n" +
                "        <div class=\"image-container\">\n" +
                "            <img src=\"https://blog.fluidui.com/assets/images/posts/imageedit_1_9273372713.png\" alt=\"Error 404\">\n" +
                "        </div>\n" +
                "        <h1>Error 404 - Page Not Found</h1>\n" +
                "        <p>Oops! The page you're looking for could not be found.</p>\n" +
                "        <a href=\"/api/books\">Go back to All books </a>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
    }

    public String getErrorPath() {
        return "/error";
    }
}
