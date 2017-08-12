package vo.cvcompany.com.myapplication.Module;

/**
 * Created by kh on 8/10/2017.
 */

public class Description {
    private String id ;
    private String name;
    private String description;

    public Description(){

    }

    public Description(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
