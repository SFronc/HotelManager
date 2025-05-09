package pl.edu.agh.kis.pz1;


import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

public class CommandLoader<T> {
    private final Set<Command<T>> commands = new HashSet<>();

    public Set<Command<T>> loadCommands(String path){
        if(path.isEmpty()){
            path = System.getProperty("user.dir");
            System.out.println("Using user.dir ("+path+")");
        }
        try(URLClassLoader ucl = new URLClassLoader(new URL[] {new File(path).toURI().toURL()})){
            File dir = new File(path);

            if(!dir.exists() || !dir.isDirectory()){
                System.out.println("Directory does not exist");
                return null;
            }

            if(dir.listFiles() == null){
                return null;
            }

            for(File f : dir.listFiles()){
                if(f.getName().endsWith(".class")){
                    String className = f.getName().replace(".class", "");
                    Class<?> c = ucl.loadClass(className);

                    if(Command.class.isAssignableFrom(c)){
                        Command<T> command = (Command<T>) c.getDeclaredConstructor().newInstance();

                        commands.add(command);
                        System.out.println("Loaded command: " + command.getInvokeName());
                    }
                }
            }
            return commands;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
