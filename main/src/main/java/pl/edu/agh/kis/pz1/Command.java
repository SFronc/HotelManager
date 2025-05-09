package pl.edu.agh.kis.pz1;

/**
 * Abstrakcyjna komenda do wykonywania operacji na obiektach
 * @param <T> Typ obiektow na ktorych ma dzialac dana komenda
 */
public abstract class Command<T> {
    protected final String invokeName;

    protected Command(String invokeName) {
        this.invokeName = invokeName;
    }

    public String getInvokeName() {
        return invokeName;
    }

    /**
     * Metoda wykonujaca dana komende
     * @return True w razie powodzenia, False w przeciwnym razie
     */
    public abstract boolean execute(T target);
}

