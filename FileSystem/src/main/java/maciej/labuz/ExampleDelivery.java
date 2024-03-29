package maciej.labuz;


import maciej.labuz.commands.*;
import maciej.labuz.filesystem.Directory;
import maciej.labuz.filesystem.TextFile;

public abstract class ExampleDelivery {
    public static Directory generateExampleTree(){
        Directory root= new Directory();
        root.setName("root");

       // Directory _null = new Directory();
        //_null.setName("null");

        Directory usr = new Directory();
        usr.setName("usr");

        Directory dev = new Directory();
        dev.setName("dev");

        Directory docs = new Directory();
        docs.setName("docs");

        Directory admin = new Directory();
        admin.setName("admin");

        Directory sdocs = new Directory();
        sdocs.setName("docs");

        TextFile file = new TextFile();
        file.setName("file.txt");
        file.setContent("here is message");

        //TextFile pomarancza=new TextFile();
        //pomarancza.setName("second message.txt");
        //pomarancza.setContent("here is second message.");

            try {
            //root.addChild(_null);

                root.addChild(dev);
                root.addChild(usr);
                root.addChild(docs);

            //admin.addChild(sdocs);
                usr.addChild(admin);
                docs.addChild(file);

        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return root;
    }

    public static CommandCollection generateShell(){
        CommandCollection commandCollection = new CommandCollection();

        Command ls = new LS();
        Command cd = new ChangeDirectory();
        Command mkdir = new MKDIR();
        Command more = new MORE();
        Command tree = new TREE();
        Command cp = new CP();


        Command mv = new MV();



        Command touch = new TOUCH();
        Command rm = new RM();



        commandCollection.addCommand(ls);


            commandCollection.addCommand(cd);
            commandCollection.addCommand(mkdir);
        commandCollection.addCommand(more);
        commandCollection.addCommand(tree);
        commandCollection.addCommand(cp);
        commandCollection.addCommand(mv);
        commandCollection.addCommand(touch);


        commandCollection.addCommand(rm);

        return commandCollection;
    }
}
