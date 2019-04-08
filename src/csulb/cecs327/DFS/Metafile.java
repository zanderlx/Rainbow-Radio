package csulb.cecs327.DFS;

import java.util.ArrayList;
import java.util.List;

public class Metafile
{
    /**
     * Class Members:
     * name - a string value
     * pages - an array list of Page objects
     */
    String name;
    ArrayList<Page> pages = new ArrayList<Page>();

    /**
     * Metafile - a constructor.
     * @param name - a string argument
     */
    public Metafile(String name)
    {
        this.name = name;
    }

    /**
     * addPage - a function that adds a Page object to pages.
     */
    public void addPage(long guid)
    {
        pages.add(new Page(pages.size()+1, guid));
    }

    /**
     * getName - a getter function.
     * @return - name (class member)
     */
    public String getName()
    {
        return name;
    }

    /**
     * setName - a setter function.
     * @param name - a string argument
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * getNumOfPages - a getter function.
     * @return - number of elements in pages (class member)
     */
    public int getNumOfPages()
    {
        return pages.size();
    }

    /**
     * getPage - a getter function.
     * @param pageNumber - an integer argument
     * @return - desired Page object from pages (class member)
     */
    public Page getPage(int pageNumber)
    {
        return pages.get(pageNumber-1);
    }

    /**
     * printListOfPages - is a function that prints the info of all objects in pages.
     */
    public void printListOfPages()
    {
        System.out.printf("\n%-5s%-15s%-15s\n", "", "Page Number", "GUID");
        for (int i = 0; i < pages.size(); i++)
        {
            Page temp = pages.get(i);

            System.out.printf("%-5s%-15s%-15d\n", "", temp.getPageNumber(), temp.getGUID());
        }
        System.out.println("");
    }
}