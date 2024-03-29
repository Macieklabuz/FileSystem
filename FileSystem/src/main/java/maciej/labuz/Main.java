package maciej.labuz;

import maciej.labuz.filesystem.Context;
import maciej.labuz.filesystem.Directory;

import java.util.Scanner;

public class Main {

    static Directory root;
    static Context context;

    static{
        context = new Context();

    }

    public static void main(String[] args) throws InterruptedException {
        root = ExampleDelivery.generateExampleTree();

        context.setCurrent(root);
        CommandCollection commandCollection = ExampleDelivery.generateShell();

        ConsoleRunner consoleRunner = new ConsoleRunner(
                new Scanner(System.in), context, new Interpreter(commandCollection));
        consoleRunner.run();
    }
}