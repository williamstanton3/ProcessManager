
public class ProcessManager {

    private ReadyQueue readyQueue;
    private static int nextPid; // increments every time a process is created

    private static ProcessManager manager;


    // as part of the singleton pattern, the constructor is private
    private ProcessManager() {
        readyQueue = new ReadyQueue();
        nextPid = 1;
        //manager = null;
    }

    // gets the next id and increment the nextPid counter
    private int getNextId() {
        int nextAvailablePid = nextPid;
        nextPid++;
        return nextAvailablePid;
    }

    // creates a process with the given name and time remaining, and add it to the end of the ReadyQueue
    public Process createProcess(String name, int timeRemaining) {
        Process newProcess = new Process(getNextId(), name, timeRemaining);
        readyQueue.addProcess(newProcess);
        return newProcess;
    }

    public void process(int time) throws NoSuchMethodException {
        while (time > 0) {
            Process p1 = readyQueue.getFirst();
            if (p1.getState().equals(Process.Status.TERMINATED)) {
                readyQueue.removeProcess();
            }
            else if (p1.getState().equals(Process.Status.WAITING)) {
                readyQueue.contextSwitch();
            }
            else if (p1.getState().equals(Process.Status.READY)) {
                // decrease the process's timeRemaining by timeSlice
                p1.run();
                if (p1.getTimeRemaining() == 0) {
                    p1.setState(Process.Status.TERMINATED);
                    readyQueue.removeProcess();
                }
                // if timeRemaining is still greater than zero
                else {
                    readyQueue.contextSwitch();
                }
                time = time - p1.getTimeSlice();
            }
        }
    }

    // prints all the pids of the processes in the ready queue
    public void shortPrint() {
        for (int i = 0; i < readyQueue.getNumProcesses(); i++) {
            System.out.print(readyQueue.getFirst().getPid() + " ");
            readyQueue.contextSwitch();
        }
        System.out.println();
    }

    public void fullPrint() {
        System.out.println(readyQueue.toString());
    }



    public static ProcessManager getInstance() {
        if (manager == null) {
            manager = new ProcessManager();
        }
        return manager;
    }

    public void terminate(String name) {
        readyQueue.terminate(name);
    }


}
