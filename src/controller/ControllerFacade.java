package src.controller;

public class ControllerFacade{
    private ControllerFacade(){}
    private static ControllerFacade instance;

    public static synchronized ControllerFacade getInstance(){
        if(instance == null){
            instance = new ControllerFacade();
        }
        return instance;
    }

    private final ReviewController reviewController = new ReviewController();
    private final UserController userController = new UserController();

}