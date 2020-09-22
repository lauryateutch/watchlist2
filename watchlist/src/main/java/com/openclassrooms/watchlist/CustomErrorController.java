package com.openclassrooms.watchlist;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping("/error")
public ModelAndView handleError(HttpServletResponse response){
        int code= response.getStatus();
        //Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        System.out.println("Eror with code"+ code+"Happened!");

        return  new ModelAndView("error");
    }

}
