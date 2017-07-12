/*
 * Groups people into pairs without repeating
 * @author James Timotiwu
 */
import java.util.*;
public class GroupArrange {
    private ArrayList<Person> list1 = new ArrayList<Person>();
    private ArrayList<ArrayList<Person>> plist = new ArrayList<ArrayList<Person>>();
    private ArrayList<ArrayList<ArrayList<Person>>> arranged = new ArrayList<ArrayList<ArrayList<Person>>>();
    
    /**
     * Adds a Person
     * @param Person the Person
     */
    public void addPerson(Person p) {
        list1.add(p);
    }
    
    /**
     * Returns number of Members
     */
    public int getNumberOfMembers() {
        return list1.size();
    }
    
    /**
     * Returns list of all Members
     */
    public String getListOfMembers() {
        String list = "Members: \n";
        Iterator<Person> itr = list1.iterator();
        while(itr.hasNext()) {
            list = list + itr.next() + "\n";
        }
        
        return list;
    }

    /**
     * Generates combinations
     */
    public void generateAll() {
        int count = 0;
        //iterate through size of list
        for(int i = 0; i < (list1.size() - 1); i++) {
            //iterate through size of list with initial set to one to ensure that combinations are not repeated
            for(int j = i + 1; j < (list1.size()); j++ ) {
                plist.add(new ArrayList<Person>());             //add combination to the list of combinations
                (plist.get(count)).add(list1.get(i));           //set first person in combination
                (plist.get(count)).add(list1.get(j));           //set second person in combination
                count++;
            }
        }
        //System.out.println(plist);
    }
    
    /**
     * Arranges all the combinations into weekly pairs
     */
    public void arrange() {
        Collections.shuffle(plist);                             //shuffle combination list randomly
        Iterator<ArrayList<Person>> itr = plist.iterator();
        
        //iterate through list of combinations and assign them into individual weeks
        while(itr.hasNext()) {
            ArrayList<ArrayList<Person>> lst1 = new ArrayList<ArrayList<Person>>();     //new list of combinations created for a single week of pairs

            //iterate until weekly list has enough amount of pairs (everyone is paired)
            while((lst1.size() <= (list1.size()/2)) && itr.hasNext()) {
                ArrayList<Person> a = itr.next();
                if(lst1.isEmpty()) {
                    lst1.add(a);
                    itr.remove();                               //remove pair from list once placed on the weekly schedule
                }
                else if(checkIfContain(lst1, a)) {
                    lst1.add(a);
                    itr.remove();                               //remove pair from list once placed on the weekly schedule
                }
            }
            arranged.add(lst1);                                 //weekly list to rest of weeks
            itr = plist.iterator();
        }
        
        //System.out.println("Arranged: " + arranged);
    }
    
    /**
     * Checks if person is already in the weekly combination
     */
    public boolean checkIfContain(ArrayList<ArrayList<Person>> l1, ArrayList<Person> l2) {
        for(int i = 0; i < l1.size(); i++) {
            if((l1.get(i)).contains(l2.get(0)))
                return false;
            if((l1.get(i)).contains(l2.get(1)))
                return false;
        }
        return true;
    }
    
    /**
     * Removes and replaces lists that did not successfully have everyone paired
     */
    public void removeBadList() {
        Iterator<ArrayList<ArrayList<Person>>> itr = arranged.iterator();
        while(itr.hasNext()) {
            ArrayList<ArrayList<Person>> a = itr.next();
            Iterator<ArrayList<Person>> itr1 = a.iterator();
            if(a.size() != (list1.size()/2)) {
                while(itr1.hasNext()) {
                    plist.add(itr1.next());
                }
                itr.remove();
            }
        }
    }
    
    /**
     * Returns a string of all pairs under the week in an organized fashion
     */
    public String toString() {
        String allweeks = "";
        Iterator<ArrayList<ArrayList<Person>>> itr = arranged.iterator();
        int weekCount = 1;
        while(itr.hasNext()) {
            
            String pairs = "";
            Iterator<ArrayList<Person>> itr1 = (itr.next()).iterator();
            while(itr1.hasNext()) {
                pairs = pairs + itr1.next() + "\n";
            }
            allweeks = allweeks + "\n Week " + weekCount + ": \n" + pairs;
            weekCount++;
        }
        return allweeks;
    }
    
    /**
     * Main command line user interface
     */
    public void run() {
        System.out.print("Arrange People Into Pairs: \n"
                             + "Options: \n"
                             + "-a [FirstName] [LastName] \n"
                             + "-r to arrange \n"
                             + "-re to rearrange \n"
                             + "-g to get number of people \n"
                             + "-l to show arranged list \n");
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.nextLine();
        System.out.println(cmd);
        String[] cmdp = cmd.split(" ");
        if(cmdp[0].equals("-a")) {
            if(cmdp.length != 3)
                System.out.println("You need a first name and last name: -a [FirstName] [LastName] ");
            else
                addPerson(new Person(cmdp[1], cmdp[2]));
        }
        else if(cmdp[0].equals("-r")) {
            long starttime, endtime;
            starttime = System.currentTimeMillis();
            generateAll();
            arrange();
            for(int s = 0; s < ((getNumberOfMembers()*(getNumberOfMembers() - 1))/2); s++) {
                arrange();
                removeBadList();
            }
            endtime = System.currentTimeMillis();
            System.out.println("Generated and arranged in: " + (endtime - starttime) + "ms");
        }
        else if(cmdp[0].equals("-re")) {
            arrange();
            for(int s = 0; s < ((getNumberOfMembers()*(getNumberOfMembers() - 1))/2); s++)
                removeBadList(); 
        }
        else if(cmdp[0].equals("-g")) {
            System.out.println(getNumberOfMembers());
            System.out.println(getListOfMembers());
        }
        else if(cmdp[0].equals("-l")) {
            System.out.println(toString());
        }
        else
            System.out.println("Unknown command");
        
        run();
    }
    
    public static void main( String [ ] args )
    {
        GroupArrange a = new GroupArrange();
        a.run();
    }
}