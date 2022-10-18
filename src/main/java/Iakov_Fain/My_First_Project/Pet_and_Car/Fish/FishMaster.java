package Iakov_Fain.My_First_Project.Pet_and_Car.Fish;

public class FishMaster {
    public static void main(String[] args) {
        Fish myFish = new Fish();
        String fishReaction;
        myFish.dive(2);
        myFish.dive(97);
        myFish.dive(3);
        myFish.sleep();
       /* myFish.say("Привет");*/
        fishReaction = myFish.say("Привет!");
        System.out.println(fishReaction);
    }
}
