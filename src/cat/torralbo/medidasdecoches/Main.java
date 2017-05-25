package cat.torralbo.medidasdecoches;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.subMenus(menu.principal());

    }
}
