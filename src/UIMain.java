import java.awt.Dimension;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import uicustom.frame.MainFrame;
import uicustom.panel.MainPanel;
import uicustom.screen.MainScreen;

import static java.util.stream.Collectors.toMap;

public class UIMain {

    public static void main(String[] args) {
    
        final var gameConfig = Stream.of(args).collect(toMap(
            k -> k.split(";")[0],
            v -> v.split(";")[1]
    ));
    var mainScreen = new MainScreen(gameConfig);
    mainScreen.buildMainScreen();
}
}
