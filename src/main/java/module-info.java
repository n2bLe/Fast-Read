module com.nubll.fastread {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.nubll.fastread to javafx.fxml;
    exports com.nubll.fastread;
}