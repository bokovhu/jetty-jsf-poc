package me.bokov.jettyjsfpoc.jsf;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class HelloBean {

    public String getHello () {
        return "Hi from bean!";
    }

}
