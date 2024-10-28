package id.application.todolist.data;

public class Data {
    String id, title, date;
    int icon;
    public Data(String id,String title, int icon, String date){
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }
}
