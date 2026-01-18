package ca.bcit.comp2522.bank;


/**
 * Name: A class that stores a person's first and last name
 * with few simple methods.
 *
 * @author Giant, Brian
 * @version 1.0
 */
public class Name
{
    /** The person's first name and last name*/
    private final String first;
    private final String last;

    /* The maximum of name; must be fewer than 45 characters */
    private static final int MAX_CHARACTER_LENGTH = 45;

    /**
     * Instantiate a name value with first and last name.
     * @param first first name
     * @param last last name
     */
   public Name(final String first, final String last)
    {
        validateName(first);
        validateName(last);

        this.first = first;
        this.last = last;
    }

    /**
     * Validates a name string.
     *
     * @param name the name to validate
     * @throws IllegalArgumentException if the name is null, blank,
     *         too long, or a restricted value
     */
    private static void validateName(final String name)
    {
        if(name == null || name.isBlank() || name.length() >= MAX_CHARACTER_LENGTH || name.toLowerCase().contains("admin"))
        {
            throw new IllegalArgumentException("Name is not valid: " + name);
        }
    }

    /**
     * Returns the first name.
     *
     * @return the first name
     */
    public String getFirst()
    {

        return first;
    }

    /**
     * Returns the last name.
     *
     * @return the last name
     */
    public String getLast()
    {

        return last;
    }

    /**
     * Returns the uppercase initial of a name.
     *
     * @param name the name to extract the initial from
     * @return the uppercase first character
     */
    private char getInitial(final String name) {

        return Character.toUpperCase(name.charAt(0));
    }

    /**
     * Returns the initials of the name.
     *
     * @return the initials
     */
    public String getInitials()
    {

        return getInitial(first) + "." + getInitial(last) + ".";
    }

    /**
     * Capitalizes the first letter of a name and lowercases the rest.
     *
     * @param name the name to format
     * @return the formatted name
     */
    private String capitalizeInitial(final String name)
    {

        return getInitial(name) + name.substring(1).toLowerCase();
    }

    /**
     * Returns the full name in proper case.
     *
     * @return the full name
     */
    public String getFullName()
    {
        return capitalizeInitial(first) + " " + capitalizeInitial(last);
    }

    /**
     * Returns the full name reversed using string builder.
     *
     * @return the reversed name
     */
    public String getReverseName()
    {
       return new StringBuilder(last).reverse().toString()
               + " "
               + new StringBuilder(first).reverse().toString();
    }
}
