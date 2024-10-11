import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReadyQueueTester {
    @Test
    public void constructorsTest() {
        ReadyQueue rq1 = new ReadyQueue();
        assertEquals(0,rq1.getNumProcesses());
        ReadyQueue rq2 = new ReadyQueue(new Process(1,"test", 222));
        assertEquals(1,rq2.getNumProcesses());
    }

    @Test
    public void getFirstTest() {
        ReadyQueue rq2 = new ReadyQueue(new Process(1,"test", 222));
        assertEquals(1, rq2.getFirst().getPid());
    }
    @Test
    public void addAtEnd(){
        ReadyQueue rq1 = new ReadyQueue();
        rq1.addProcess(new Process(5,"test", 222));
        rq1.addProcess(new Process(7,"test", 222));
        assertEquals(2,rq1.getNumProcesses());
        assertEquals(5, rq1.getFirst().getPid());

    }

    @Test
    public void removeProcess() throws NoSuchMethodException {
        ReadyQueue rq1 = new ReadyQueue();
        try {
            rq1.removeProcess();
            fail();
        } catch (NoSuchElementException | NoSuchMethodException noe) {
            // expected
        }
        rq1.addProcess(new Process(1,"test", 222));
        rq1.addProcess(new Process(2,"test", 222));
        Process.Status state = rq1.removeProcess();
        assertEquals(1,rq1.getNumProcesses());
        assertEquals(Process.Status.READY, state);
        assertEquals(2, rq1.getFirst().getPid());
    }

    @Test
    public void contextSwitchTest(){
        ReadyQueue rq1 = new ReadyQueue();
        rq1.addProcess(new Process(5,"test", 222));
        rq1.addProcess(new Process(7,"test", 222));
        assertEquals(5, rq1.getFirst().getPid());
        rq1.contextSwitch();
        assertEquals(7, rq1.getFirst().getPid());
        assertEquals(2, rq1.getNumProcesses());
    }

    @Test
    public void bigTest() throws NoSuchMethodException {
        ReadyQueue rq1 = new ReadyQueue();
        rq1.addProcess(new Process(1,"test", 222));
        rq1.addProcess(new Process(2,"test", 222));
        rq1.addProcess(new Process(3,"test", 222));
        rq1.addProcess(new Process(4,"test4", 222));
        assertEquals(1, rq1.getFirst().getPid());
        assertEquals(2, rq1.thirdFromBack().getPid());
        rq1.terminate("test4");
        rq1.contextSwitch();
        rq1.contextSwitch();
        // 3 4 1 2
        rq1.removeProcess();
        rq1.addProcess(new Process(5,"test", 222));
        // 4 1 2 5
        assertEquals(4, rq1.getFirst().getPid());
        assertEquals(1, rq1.thirdFromBack().getPid());
        rq1.addProcess(new Process(6,"test", 222));
        assertEquals(2, rq1.thirdFromBack().getPid());
    }

    @Test
    public void constructorsTest2() {
        ReadyQueue rq1 = new ReadyQueue();
        assertEquals(0,rq1.getNumProcesses());
        ReadyQueue rq2 = new ReadyQueue(new Process(1,"test", 222));
        assertEquals(1,rq2.getNumProcesses());
        assertEquals(1, rq2.getFirst().getPid());
    }

    @Test
    public void getFirstTest2() throws NoSuchMethodException {
        ReadyQueue rq2 = new ReadyQueue(new Process(1,"test", 222));
        assertEquals(1, rq2.getFirst().getPid());
        rq2.addProcess(new Process(2,"test",  333));
        rq2.contextSwitch();
        assertEquals(2, rq2.getFirst().getPid());
        rq2.removeProcess();
        assertEquals(1, rq2.getFirst().getPid());
    }
    @Test
    public void addAtEnd2() throws NoSuchMethodException {
        ReadyQueue rq1 = new ReadyQueue();
        rq1.addProcess(new Process(5,"test", 222));
        rq1.addProcess(new Process(7,"test", 222));
        assertEquals(2,rq1.getNumProcesses());
        assertEquals(5, rq1.getFirst().getPid());
        rq1.removeProcess();
        assertEquals(7, rq1.getFirst().getPid());
    }

    @Test
    public void removeProcess2() throws NoSuchMethodException {
        ReadyQueue rq1 = new ReadyQueue();
        try {
            rq1.removeProcess();
            fail();
        } catch (NoSuchElementException | NoSuchMethodException noe) {
            // expected
        }
        rq1.addProcess(new Process(1,"test", 222));
        rq1.addProcess(new Process(2,"test", 222));
        Process.Status state = rq1.removeProcess();
        assertEquals(1,rq1.getNumProcesses());
        assertEquals(Process.Status.READY, state);
        assertEquals(2, rq1.getFirst().getPid());
        rq1.removeProcess();
        assertEquals(0,rq1.getNumProcesses());
    }

    @Test
    public void contextSwitchTest2() throws NoSuchMethodException {
        ReadyQueue rq1 = new ReadyQueue();
        rq1.addProcess(new Process(5,"test", 222));
        rq1.addProcess(new Process(7,"test", 222));
        assertEquals(5, rq1.getFirst().getPid());
        rq1.contextSwitch();
        assertEquals(7, rq1.getFirst().getPid());
        assertEquals(2, rq1.getNumProcesses());
        rq1.addProcess(new Process(9,"test", 222));
        rq1.contextSwitch();
        rq1.removeProcess();
        assertEquals(9, rq1.getFirst().getPid());
    }

}
