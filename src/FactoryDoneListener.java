/**
 * Interface with a funciton that will be called when something is done
 */
public interface FactoryDoneListener
{
    /**
     * Should be called when a factory is done
     * @param returnValue The return value of the factory
     */
    void factoryDone(Object returnValue);

    /**
     * Should be called when there is an error with the factory
     * @param errorMessage The error message from the factory
     */
    void factoryFailed(String errorMessage);
}
