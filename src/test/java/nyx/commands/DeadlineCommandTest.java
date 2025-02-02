package nyx.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;
import nyx.exceptions.InvalidUsageException;

public class DeadlineCommandTest {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        // Initialize or mock your dependencies
        taskList = new TaskList(new ArrayList<>());
        storage = new Storage() {
            @Override
            public void saveTaskData(String data) {
                // Mock storage
            }
        };
        ui = new Ui() {
            @Override
            public void displayString(String string) {
                // Mock UI display
            }
        };
    }

    @Test
    public void testExecute_validDeadline_taskCreated() {
        String input = "deadline submit report -by 2025-02-19";
        DeadlineCommand command = new DeadlineCommand(input);

        assertDoesNotThrow(() -> command.execute(taskList, storage, ui),
                "Expected no exception for valid command format.");

        // Verify the deadline task was added
        assertEquals(1, taskList.getTaskCount());
    }

    @Test
    public void testExecute_noByDelimiter_exceptionThrown() {
        String input = "deadline submit report 2025-02-19"; // Missing " -by "
        DeadlineCommand command = new DeadlineCommand(input);

        assertThrows(InvalidUsageException.class, () -> command.execute(taskList, storage, ui),
                "Expected an InvalidUsageException for missing '-by' delimiter.");
        assertEquals(0, taskList.getTaskCount(),
                "No tasks should be added to TaskList if an exception occurs.");
    }

    @Test
    public void testExecute_malformedDate_exceptionThrown() {
        String input = "deadline submit report -by 19-02-2025"; // Wrong date format
        DeadlineCommand command = new DeadlineCommand(input);

        assertThrows(InvalidUsageException.class, () -> command.execute(taskList, storage, ui),
                "Should throw InvalidUsageException if date format is not YYYY-MM-DD.");
        assertEquals(0, taskList.getTaskCount(),
                "No tasks should be added to TaskList if an exception occurs.");
    }
}
