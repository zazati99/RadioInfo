/**
 * Items for JComboBox
 */
public class ComboBoxItem
{
    /**
     * Title of item
     */
    private String title;

    /**
     * Listener that will be called when this item is chosen
     */
    private ItemChosenListener listener;

    /**
     * Constructor
     */
    public ComboBoxItem(String title)
    {
        this.title = title;
    }

    /**
     * Calls the item chosen method in the listener
     */
    public void choose()
    {
        if (listener != null)
        {
            listener.itemChosen();
        }
    }

    /**
     * Adds an item chosen listener to the item
     * @param listener
     */
    public void addItemChosenListener(ItemChosenListener listener)
    {
        this.listener = listener;
    }

    @Override
    public String toString()
    {
        return title;
    }
}
