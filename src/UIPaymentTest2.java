import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UIPaymentTest2 {

    private FrameFixture window;

    @Before
    public void setUp() {
        // Launch frame on EDT
        Address exampleAddress = new Address();
        PaymentFrame frame = GuiActionRunner.execute(() -> new PaymentFrame(50, exampleAddress));
        window = new FrameFixture(frame);
        window.show();
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void testZipCodeTooShortShowsError() {
        // Enter invalid CVV
        window.textBox("First Name").setText("");
        window.textBox("Last Name").setText("");
        window.textBox("Card Number").setText("");
        window.textBox("Expiration").setText("");
        window.textBox("CVV").setText("");

        // Click pay now
        window.button("Pay Now").click();

        window.optionPane()
                .requireVisible()
                .requireMessage("Please enter your first name.");
        window.optionPane().okButton().click();
    }
}

