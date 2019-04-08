package csulb.cecs327.DFS;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Metadata
{
    /**
     * Class Members:
     * files - an array list of Metafile objects
     */
    ArrayList<Metafile> files = new ArrayList<Metafile>();

    /**
     * addFile - is a function that adds a Metafile object to files.
     * @param filename - a string argument
     */
    public void addFile(String filename)
    {
        Metafile newFile = new Metafile(filename);
        files.add(newFile);
    }

    /**
     * printListOfFiles - is a function that prints info of all the objects in files.
     */
    public void printListOfFiles()
    {
        System.out.printf("\n%-15s%-15s\n", "Filename", "Number of Pages");
        for (int i = 0; i < files.size(); i++)
        {
            Metafile temp = files.get(i);

            System.out.printf("%-15s%-15d\n", temp.getName(), temp.getNumOfPages());

            if (temp.getNumOfPages() > 0)
                temp.printListOfPages();
        }
        System.out.println("");
    }

    /**
     * getFile - is a function that retrieves a specified file in files.
     * @param filename - a string argument
     * @return - the desired Metafile object
     */
    public Metafile getFile(String filename)
    {
        for (int i = 0; i < files.size(); i++)
        {
            Metafile temp = files.get(i);

            if (temp.getName().equals(filename))
            {
                return temp;
            }
        }
        return null;
    }

    /**
     * fileExists - is a function that determines whether or not the specified file exists.
     * @param filename - a string argument
     * @return - the resulting boolean value
     */
    public boolean fileExists(String filename)
    {
        for(int i = 0; i < files.size(); i++)
        {
            Metafile temp = files.get(i);

            if (temp.getName().equals(filename))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * deleteFile - is a function that removes a specified file in files.
     * @param filename - a string argument
     */
    public void deleteFile(String filename)
    {
        ListIterator<Metafile> listIterator = files.listIterator();

        while (listIterator.hasNext())
        {
            Metafile temp = listIterator.next();

            if (temp.getName().equals(filename))
                listIterator.remove();
        }
    }

    /**
     * clear - is a function that clears the metadata.
     */
    public void clear()
    {
        files.clear();
    }
}