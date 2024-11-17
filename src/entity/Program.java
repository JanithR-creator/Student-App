package entity;

public class Program {
    private long id;
    private String name;
    private int credit;

    public Program() {
    }

    public Program(long id, String name, int credit) {
        this.id = id;
        this.name = name;
        this.credit = credit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
