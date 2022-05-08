import java.util.ArrayList;
import java.util.List;

public class Publisher implements ISubject{

    private List<IObserver> observers;

    public Publisher(ArrayList<IObserver> observerArrayList){
        this.observers=observerArrayList;
    }
    public void notifying(String message){
        for (IObserver observer:observers) {
            observer.sendStateMessage(message);
        }
    }
}
