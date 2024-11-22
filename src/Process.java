import java.util.HashSet;
import java.util.Set;

public class Process {

    enum Status {
        // throw an exception if it is not one of these three values
        READY,
        WAITING,
        TERMINATED
    }
    private int pid;
    private String name;
    private Status state;
    private int timeSlice;
    private int timeRemaining;
    private Set<String> files;


    // constructor
    public Process (int pid, String name, int timeRemaining) {
        this.pid = pid;
        this.name = name;
        state = Status.READY;
        this.timeRemaining = timeRemaining;
        timeSlice = 10;
        files = new HashSet<>();
    }


    // adds a new filename to the file data structure
    public void openFile(String newFile) {
        files.add(newFile);
    }
    // remove the filename specified from the files data
    public void closeFile(String newFile) {
        if (!files.contains(newFile)) {
            throw new IllegalArgumentException();
        }
        files.remove(newFile);
    }


    // getter for state
    public Process.Status getState() {
        return state;
    }
    // setter for state
    public void setState(Process.Status newState) {
        state = newState;
    }
    // getter for pid
    public int getPid() {
        return pid;
    }
    // getter for name
    public String getName() {
        return name;
    }
    // getter for timeSlice
    public int getTimeSlice() {
        return timeSlice;
    }
    // getter for timeRemaining
    public int getTimeRemaining() {
        return timeRemaining;
    }



    // simulates running the process for timeSlice ms
    public void run() {
        timeRemaining = timeRemaining - timeSlice;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Process ");
        sb.append(pid + " ");
        sb.append(name + " ");
        sb.append(state + " ");
        sb.append(timeRemaining + " ");
        sb.append(files.size());

        return sb.toString();
    }


}
