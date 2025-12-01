import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UIAddressFrameTest {

        private FrameFixture window;

        @Before
        public void setUp() {
            AddressFrame frame = GuiActionRunner.execute(AddressFrame::new);
            window = new FrameFixture(frame);
            window.show();
        }

        @After
        public void tearDown() {
            window.cleanUp();
        }

        @Test
        public void testLeaveStreetEmpty() {
            window.textBox("city").setText("LA");
            window.textBox("State").setText("CA");
            window.textBox("zipCode").setText("12345");
            window.textBox("buildingType").setText("Apartment");

            window.button("Continue").click();

            window.optionPane()
                    .requireVisible()
                    .requireMessage("Please enter your Street name.");
            window.optionPane().okButton().click();
        }

}
