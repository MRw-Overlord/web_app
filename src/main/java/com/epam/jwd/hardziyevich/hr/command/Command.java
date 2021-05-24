package com.epam.jwd.hardziyevich.hr.command;

public interface Command {

    static Command of(String name) {
        return CommandManager.of(name);
    }

    ResponseContext execute(RequestContext requestContext);

}
