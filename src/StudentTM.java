import javafx.scene.control.Button;

public class StudentTM {
    private long id;
    private String name;
    private String address;
    private Button deleteBtn;
    private Button updateBtn;

    public StudentTM() {
    }

    public StudentTM(long id, String name, String address, Button deleteBtn, Button updateBtn) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.deleteBtn = deleteBtn;
        this.updateBtn = updateBtn;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public void setDeleteBtn(Button deleteBtn) {
        this.deleteBtn = deleteBtn;
    }

    public Button getUpdateBtn() {
        return updateBtn;
    }

    public void setUpdateBtn(Button updateBtn) {
        this.updateBtn = updateBtn;
    }
}
