package com.kilany.LibraryManagementSystem.security;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SecurityController {
    @GetMapping("/login")
    public String loginEndpoint() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Login</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f4f4f4;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "            align-items: center;\n" +
                "            height: 100vh;\n" +
                "        }\n" +
                "\n" +
                "        .login-container {\n" +
                "            background-color: #fff;\n" +
                "            border-radius: 8px;\n" +
                "            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\n" +
                "            padding: 20px;\n" +
                "            width: 300px;\n" +
                "        }\n" +
                "\n" +
                "        .login-title {\n" +
                "            text-align: center;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "\n" +
                "        .form-group {\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "\n" +
                "        .form-group label {\n" +
                "            display: block;\n" +
                "            font-weight: bold;\n" +
                "            margin-bottom: 5px;\n" +
                "        }\n" +
                "\n" +
                "        .form-group input {\n" +
                "            width: 100%;\n" +
                "            padding: 10px;\n" +
                "            border: 1px solid #ccc;\n" +
                "            border-radius: 4px;\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        .form-group button {\n" +
                "            width: 100%;\n" +
                "            padding: 10px;\n" +
                "            background-color: #007bff;\n" +
                "            color: #fff;\n" +
                "            border: none;\n" +
                "            border-radius: 4px;\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "\n" +
                "        .form-group button:hover {\n" +
                "            background-color: #0056b3;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"login-container\">\n" +
                "        <h2 class=\"login-title\">Login</h2>\n" +
                "        <form action=\"/login\" method=\"post\">\n" +
                "            <div class=\"form-group\">\n" +
                "                <label for=\"username\">Username</label>\n" +
                "                <input type=\"text\" id=\"username\" name=\"username\" required>\n" +
                "            </div>\n" +
                "            <div class=\"form-group\">\n" +
                "                <label for=\"password\">Password</label>\n" +
                "                <input type=\"password\" id=\"password\" name=\"password\" required>\n" +
                "            </div>\n" +
                "            <div class=\"form-group\">\n" +
                "                <button type=\"submit\">Login</button>\n" +
                "            </div>\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
    }

}
