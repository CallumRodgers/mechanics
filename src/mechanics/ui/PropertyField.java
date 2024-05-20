package mechanics.ui;

import javax.swing.*;

public class PropertyField extends JSpinner {

    public PropertyField(double min, double max) {
        setModel(new SpinnerNumberModel(1, min, max, 0.01));
    }

    public void setOnChangeAction(Runnable l) {
        addChangeListener(e -> {
            l.run();
        });
    }
}
