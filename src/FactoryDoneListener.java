/**
 * Interface with a funciton that will be called when something is done
 */
public interface FactoryDoneListener
{
    /**
     * Should be called when a facoty is done
     * @param returnValue The return value of the factory
     */
    void factoryDone(Object returnValue);
}
