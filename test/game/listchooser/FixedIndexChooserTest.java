package game.listchooser;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FixedIndexChooserTest {

    @Test
    void testChooseCorrectArmy() {
        FixedIndexChooser<String> chooser = new FixedIndexChooser<>(1);
        List<String> armies = List.of("army1", "army2", "army3");
        assertEquals("army2", chooser.choose("choose your army", armies));
    }

   
}
