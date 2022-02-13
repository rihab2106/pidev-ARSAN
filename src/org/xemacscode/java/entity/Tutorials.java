package entity;

public class Tutorials {
    private int id_tutorial;
    private Users user;
    private Trophies trophy;
    private String path,content;

    public Tutorials() {
    }

    public Tutorials(Users user, Trophies trophy, String path, String content) {
        this.user = user;
        this.trophy = trophy;
        this.path = path;
        this.content = content;
    }

    public Tutorials(int id_tutorial, Users user, Trophies trophy, String path, String content) {
        this.id_tutorial = id_tutorial;
        this.user = user;
        this.trophy = trophy;
        this.path = path;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Tutorials{" +
                "id_tutorial=" + id_tutorial +
                ", user=" + user +
                ", trophy=" + trophy +
                ", path='" + path + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public int getId_tutorial() {
        return id_tutorial;
    }

    public void setId_tutorial(int id_tutorial) {
        this.id_tutorial = id_tutorial;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Trophies getTrophy() {
        return trophy;
    }

    public void setTrophy(Trophies trophy) {
        this.trophy = trophy;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
