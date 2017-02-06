package akl.com.cced19;

/**
 * Created by Mohamed Akl on 1/20/2017.
 */

public class Student {
    private int id;
    private String name;
    private String email;
    private Table mTable;

    public Student(int mId,String mName,String mEmail)
    {
        name = mName;
        id = mId;
        email=mEmail;
    }

    public Student(){

    }

    public Table getTable() {
        return mTable;
    }

    public void setTable(Table table) {
        mTable = table;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
