package org.example.desinfector;

public class CoronaDesinfector {
    @InjectByTy
    private Announcer announcer;
    @InjectByTy
    private Policeman policeman;

    public CoronaDesinfector() {
    }

    public void start(Room room) {
        announcer.announce("Начинаеться дезинфекция, все вон!");
        policeman.makePeopleLeaveRoom();
        desinfect(room);
        announcer.announce("Возвращайтесь те кто осмелится!");
    }

    private void desinfect(Room room) {
        System.out.println("Зачитывается молитва по зачистке!");
    }

}
