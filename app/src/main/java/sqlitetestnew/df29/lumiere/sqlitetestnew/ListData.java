package sqlitetestnew.df29.lumiere.sqlitetestnew;

/**
 * Created by Lumiere on 2/23/2016.
 */
public class ListData {

    String Description;
    String title;
    int imgResId;

    public ListData (String name, String email){
        this.title = name;
        this.Description = email;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }
}
