public class Driver {
    public static void main(String[] args) throws NoSuchMethodException {
        // DONE: figure out how to get a ProcessManager Instance
        // name the varialble: manager
        // this should only be one line
        ProcessManager manager = ProcessManager.getInstance();

        // the following lines create processes for the simulation
        // and add them to the readyQueue
        manager.createProcess("p1", 20);
        // I am keeping pointers to some processes so that I can
        // simuate sending them into different state
        manager.createProcess("p2", 10);
        Process p3 = manager.createProcess("p3", 100);
        Process p4 = manager.createProcess("p4", 30);
        Process p5 = manager.createProcess("p5", 50);
        manager.createProcess("p6", 40);
        System.out.println("Initial short print, the following line should say: 1 2 3 4 5 6");
        manager.shortPrint();
        System.out.println("Initial state of process queue (full print):");
        manager.fullPrint();

        System.out.println();
        manager.process(40);
        System.out.println("after process(40) the following line should say: 5 6 1 3 4");
        manager.shortPrint();
        p4.openFile("file1");
        p4.openFile("file2");
        p5.openFile("anotherfile");
        System.out.println();
        System.out.println("now open files, and in the following lines, p1, p3 and p4 should have 10ms less time than original");
        System.out.println("and p4 should show 2 open files and p5 one open file");
        manager.fullPrint();

        p4.setState(Process.Status.WAITING);
        p3.setState(Process.Status.TERMINATED);
        manager.createProcess("p7",90);
        // now should be 5(50) 6(40) 1(10) 3(T) 4(W20) 7(90)
        manager.process(50);
        // now should be 6(30) 4(w20) 7(80) 5(30)
        System.out.println();
        System.out.println("after add, state changes, and process(50), the following line should say: 6 4 7 5");
        manager.shortPrint();
        p4.closeFile("file2");
        Process p8 = manager.createProcess("p8", 40);
        System.out.println();
        System.out.println("after closing a file adding process p8");
        System.out.println("in the following lines, the process-waiting time pairs are:");
        System.out.println("6-30, 4-20, 7-80, 5-30, 8-40 and 4 should have state WAITING");
        System.out.println("and 4 should now only have one file open");
        manager.fullPrint();


        p4.setState(Process.Status.READY);
        p8.setState(Process.Status.WAITING);
        // now should be 6(30) 4(20) 7(80) 5(30) 8(W40)
        manager.process(100);
        // now should be 5(10) 8(W40) 7(50)
        p8.setState(Process.Status.TERMINATED);
        manager.createProcess("p9",10);
        System.out.println();
        System.out.println("after more commands, now there should be 4 processes, p8 should show TERMINATED state");
        System.out.println("but should still be in the queue");
        System.out.println("the process-waiting time pairs are:");
        System.out.println("5-10, 8-40, 7-50, and 9-10");
        manager.fullPrint();

        System.out.println();
        System.out.println("after terminating p7:");
        // now terminate a process
        manager.terminate("p7");
        manager.fullPrint();
    }
}
