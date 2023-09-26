package market;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MockInputHandler implements InputHandler{

    private Queue<String> inputs;

    public MockInputHandler(String... inputValues) {
        inputs = new LinkedList<>(Arrays.asList(inputValues));
    }

    public String inputLine(String prompt) {
        if(inputs.isEmpty()) {
            throw new IllegalStateException("No more input values provided.");
        }
        return inputs.poll();
    }
}
