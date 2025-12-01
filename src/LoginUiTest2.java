import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JOptionPaneFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class LoginUiTest2 {

    private FrameFixture window;

    @Before
    public void setUp() {
        // Launch the LoginFrame on the EDT
        LoginFrame frame = GuiActionRunner.execute(LoginFrame::new);
        window = new FrameFixture(frame);
        window.show(); // show the frame for testing
    }

    @After
    public void tearDown() {
        window.cleanUp(); // close the frame after each test
    }

    @Test
    public void emptyPassword() {
        // Set username
        window.textBox("userNameField").setText("customerDemo");
        // Leave password empty
        window.textBox("passwordField").setText("");
        // Accept terms
        window.checkBox("terms").check();

        // Click Submit on EDT
        GuiActionRunner.execute(() -> window.button("Submit").target().doClick());

        // Wait for EDT to process
        window.robot().waitForIdle();

        // Capture and verify the JOptionPane
        JOptionPaneFixture optionPane = window.optionPane();
        optionPane.requireVisible();
        optionPane.requireMessage("Invalid username or password");

        // Close the JOptionPane
        optionPane.okButton().click();
    }

}
