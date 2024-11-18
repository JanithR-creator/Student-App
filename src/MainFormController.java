import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MainFormController {
    public AnchorPane context;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public Button btnSubmit;
    public TableView table;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colDelete;
    public TableColumn colUpdate;

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("updateBtn"));

        loadAll();
    }

    public void submitOnAction(ActionEvent actionEvent) {

        Student student = new Student(Long.parseLong(txtId.getText()), txtName.getText(), txtAddress.getText());

        if (btnSubmit.getText().equals("Save Student")) {
            try (Session session = HibernateUtil.getSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(student);
                transaction.commit();
                loadAll();
                btnSubmit.setText("Save Student");
                new Alert(Alert.AlertType.INFORMATION, "Student Saved.").show();
            }
        } else {
            try (Session session = HibernateUtil.getSession()) {
                Transaction transaction = session.beginTransaction();
                Student selectedStudent = session.get(Student.class, student.getId());

                if (selectedStudent == null) {
                    new Alert(Alert.AlertType.WARNING, "Student Not Found.").show();
                    return;
                }

                selectedStudent.setName(student.getName());
                selectedStudent.setAddress(student.getAddress());
                transaction.commit();
                loadAll();

                new Alert(Alert.AlertType.INFORMATION, "Student Updated.").show();
            }
        }
    }

    public void loadAll() {
        try (Session session = HibernateUtil.getSession()) {
            ObservableList<StudentTM> tms = FXCollections.observableArrayList();
            List<Student> selectedStudentList = session.createQuery("FROM Student").list();
            for (Student tempStudent : selectedStudentList) {
                Button deleteBtn = new Button("Delete");
                Button updateBtn = new Button("Update");
                tms.add(new StudentTM(tempStudent.getId(), tempStudent.getName(), tempStudent.getAddress(),
                        updateBtn, deleteBtn));

                deleteBtn.setOnAction(e -> {
                    try (Session deleteSession = HibernateUtil.getSession()) {
                        Transaction transaction = deleteSession.beginTransaction();
                        Student student = deleteSession.find(Student.class, tempStudent.getId());
                        deleteSession.delete(student);
                        transaction.commit();
                        new Alert(Alert.AlertType.INFORMATION, "Student Deleted.").show();
                        loadAll();
                    }
                });

                updateBtn.setOnAction(e -> {
                    txtId.setText(String.valueOf(tempStudent.getId()));
                    txtName.setText(tempStudent.getName());
                    txtAddress.setText(tempStudent.getAddress());
                    btnSubmit.setText("Update Student");
                });

            }
            table.setItems(tms);
        }
    }
}
