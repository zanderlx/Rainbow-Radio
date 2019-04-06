package csulb.cecs327.DFS;

public class Page
{
    /**
     * Class Members:
     * pageNumber - an integer value
     * guid - a long integer value
     */
    int pageNumber;
    long guid;

    /**
     * Page - a constructor.
     * @param pageNumber - an integer argument
     * @param guid - a long integer argument
     */
    public Page(int pageNumber, long guid)
    {
        this.pageNumber = pageNumber;
        this.guid = guid;
    }

    /**
     * getPageNumber - is a getter function.
     * @return - the pageNumber (class member)
     */
    public int getPageNumber()
    {
        return pageNumber;
    }

    /**
     * getGUID - is a getter function.
     * @return - the guid (class member)
     */
    public long getGUID()
    {
        return guid;
    }
}