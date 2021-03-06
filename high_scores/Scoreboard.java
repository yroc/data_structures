/**
 * Scoreboard encapsulates a scoreboard that lists a game's high scores in
 * descending order (highest to lowest).

 * A scoreboard is limited to a certain number of high scores that can
 * be saved; once that limit is reached, a new score only qualifies
 * for the scoreboard if it is strictly higher than the lowest high
 * score on the board.
 *
 * The length of the desired scoreboard may depend on the game,
 * perhaps 10, 50, or 500. Since that limit may vary, we allow it to
 * be specified as a parameter to our Scoreboard constructor.
 */

public class Scoreboard
{
    // Instance variables
    private GameEntry[] board;      // Declare a variable to reference an array
                                    // of GameEntry objects
    private int numEntries;         // The actual number of entries on the board
    private int indexOfLast;        // Index of the lowest GameEntry (or -1 if
                                    // board is empty)

    // Constructor
    /**
     * Constructs an empty Scoreboard with the given capacity.
     *
     * @param capacity The capacity (number of spots) of this Scoreboard.
     */
    public Scoreboard(int capacity)
    {
        this.numEntries = 0;
        this.board = new GameEntry[capacity];
        this.indexOfLast = this.numEntries - 1;
    }

    // Update Methods
    /**
     * Adds the given GameEntry to this Scoreboard if the GameEntry qualifies
     * as a high score. Otherwise does nothing.
     *
     * @param e The given GameEntry.
     */
    public void add(GameEntry e)
    {
        // is the new entry e really a high score?
        if (this.numEntries < this.board.length ||
            e.getScore() > this.board[this.indexOfLast].getScore())
            {
                if (this.numEntries < this.board.length)
                    {
                        this.numEntries++;
                        this.indexOfLast++;
                    }
                // shift any lower scores rightward to make room for
                // the new entry
                int newPos = this.indexOfLast; // eventual position of GameEntry
                while (newPos > 0 && this.board[newPos - 1].getScore() < e.getScore())
                    {
                        this.board[newPos] = this.board[newPos - 1];
                        newPos--;
                    }
                this.board[newPos] = e;
            }
    }

    /**
     * Removes and returns the GameEntry from this Scoreboard at the given place
     * (where &ldquo;place&rdquo; is greater than or equal to 1&mdash;1st place,
     * 2nd place, etc., that is, place is not zero-indexed).
     *
     * @param place The place to remove and return.
     * @return The GameEntry in the given place.
     * @throws IndexOutOfBoundsException if GameEntry exists at the given place.
     */
    public GameEntry remove(int place) throws IndexOutOfBoundsException
    {
        int i = place - 1;  // convert place to array index (zero-based)
        if (i < 0 || i >= this.numEntries)
            {
                throw new IndexOutOfBoundsException("Invalid index: " + i);
            }
        GameEntry hand = this.board[i];
        while (i < this.indexOfLast)
            {
                this.board[i] = this.board[i + 1];
                i++;
            }
        this.board[this.indexOfLast] = null;
        this.numEntries--;

        return hand;
    }

    /**
     * Returns a string representation of this Scoreboard.
     *
     * @return The string representation of this Scoreboard.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");
        for (int j = 0; j < numEntries; j++)
            {
                if (j > 0)
                    {
                        sb.append(", "); // separate entries by commas
                    }
            sb.append(board[j]);
            }
        sb.append("]");
        return sb.toString();
    }
}
