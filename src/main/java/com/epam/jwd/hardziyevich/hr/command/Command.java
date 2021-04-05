package com.epam.jwd.hardziyevich.hr.command;

public interface Command {

    ResponseContext execute(RequestContext requestContext);

    static Command of(String name){
        return CommandManager.of(name);
    }

}
