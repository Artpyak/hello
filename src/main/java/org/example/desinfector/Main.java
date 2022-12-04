package org.example.desinfector;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        HashMap<Class, Class> ifc2ImplClass = new HashMap<>(Map.of(
                Policeman.class, AngryPoliceman.class,
                Announcer.class, CoronaAnnouncer.class,
                CoronaDesinfector.class, CoronaDesinfector.class
        ));
        ApplicationContext context = Application.run("org.example", ifc2ImplClass);
        CoronaDesinfector desinfector = context.getObject(CoronaDesinfector.class);
        desinfector.start(new Room());
    }
}
