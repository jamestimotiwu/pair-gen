class TestGroupArrange
{
    public static void main( String [ ] args )
    {
        Person a = new Person("p", "1");
        Person b = new Person("p", "2");
        Person c = new Person("p", "3");
        Person d = new Person("p", "4");
        Person e = new Person("p", "5");
        Person f = new Person("p", "6");
        Person g = new Person("p", "7");
        Person h = new Person("p", "8");
        Person i = new Person("p", "9");
        Person j = new Person("p", "10");
        Person k = new Person("p", "11");
        Person l = new Person("p", "12");
        
        GroupArrange grp = new GroupArrange();
        grp.addPerson(a);
        grp.addPerson(b);
        grp.addPerson(c);
        grp.addPerson(d);
        grp.addPerson(e);
        grp.addPerson(f);
        
        grp.addPerson(g);
        grp.addPerson(h);
        grp.addPerson(i);
        grp.addPerson(j);
        grp.addPerson(k);
        grp.addPerson(l);
        
        grp.generateAll();
        grp.arrange();
        for(int s = 0; s < ((grp.getNumberOfMembers()*(grp.getNumberOfMembers() - 1))/2); s++)
            grp.removeBadList();
        System.out.println(grp.toString());
    }
}