import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UIPaymentTest {

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
        window.textBox("First Name").setText("John");
        window.textBox("Last Name").setText("Smith");
        window.textBox("Card Number").setText("1234567891234567");
        window.textBox("Expiration").setText("03/22");
        window.textBox("CVV").setText("12");

        // Click pay now
        window.button("Pay Now").click();

        window.optionPane()
                .requireVisible()
                .requireMessage("CVV must be 3 or 4 digits.");
        window.optionPane().okButton().click();
    }
}
