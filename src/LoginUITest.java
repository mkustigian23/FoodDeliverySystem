import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginUITest {

    private FrameFixture window;

    @Before
    public void setUp() {
        LoginFrame frame = GuiActionRunner.execute(LoginFrame::new);
        window = new FrameFixture(frame);
        window.show(); // show the frame
    }

    @After
    public void tearDown() {
        window.cleanUp(); // closes the frame after each test
    }

    @Test
    public void should_clear_all_fields_when_reset_is_clicked() {
        // Fill in the fields
        window.textBox("userNameField").setText("testUser");
        window.textBox("passwordField").setText("testPass");
        window.radioButton("Female").click();
        window.comboBox("date").selectItem("15");
        window.comboBox("month").selectItem("May");
        window.comboBox("year").selectItem("2000");
        window.checkBox("terms").check();

        // Click Reset
        GuiActionRunner.execute(() -> window.button("Reset").target().doClick());


        // Verify all fields are cleared / reset to default
        assertEquals("", window.textBox("userNameField").text());
        assertEquals("", window.textBox("passwordField").text());
        assertTrue(window.radioButton("Male").target().isSelected());   // default male
        assertTrue(!window.radioButton("Female").target().isSelected()); // default female deselected
        assertEquals("1", window.comboBox("date").selectedItem());
        assertEquals("Jan", window.comboBox("month").selectedItem());
        assertEquals("1995", window.comboBox("year").selectedItem());
        assertTrue(!window.checkBox("terms").target().isSelected()); // terms unchecked
    }
}
