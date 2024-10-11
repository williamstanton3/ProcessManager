public class scratch {
    public static void main(String[] args) throws NoSuchMethodException {
        ProcessManager pm = ProcessManager.getInstance();

        Process p1 = new Process(1, "bill", 80);
        Process p2 = new Process(2, "stanton", 30);
        Process p3 = new Process(3, "james", 50);
        ReadyQueue r1 = new ReadyQueue(p1);
        r1.addProcess(p2);
        r1.addProcess(p3);
        r1.addProcess(p2);
        r1.removeProcess();
        System.out.println(r1.getNumProcesses());

        System.out.println(r1.toString());


    }
}
