import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Михаил on 31.05.2017.
 */
public class RectangleDuctCalculation extends JPanel {
    public RectangleDuctCalculation() {

        setLayout(null);

        JTextField flowRate = new JTextField();
        flowRate.setBounds(25, 30, 150, 20);
        add(flowRate);

        JLabel flowRateLabel = new JLabel("Расход");
        flowRateLabel.setBounds(225, 30, 150, 20);
        add(flowRateLabel);

        JTextField width = new JTextField();
        width.setBounds(25, 90, 150, 20);
        add(width);

        JLabel widthLabel = new JLabel("Ширина");
        widthLabel.setBounds(225, 90, 150, 20);
        add(widthLabel);

        JTextField height = new JTextField();
        height.setBounds(25, 130, 150, 20);
        add(height);

        JLabel heightLabel = new JLabel("Высота");
        heightLabel.setBounds(225, 130, 150, 20);
        add(heightLabel);

        JButton calculate = new JButton("Расчитать");
        calculate.setBounds(55, 175, 270, 50);
        add(calculate);

        JTextField diameter = new JTextField();
        diameter.setBounds(25, 257, 150, 20);
        add(diameter);

        JLabel diameterLabel = new JLabel("Экв. диаметр");
        diameterLabel.setBounds(225, 257, 150, 20);
        add(diameterLabel);

        JTextField speed = new JTextField();
        speed.setBounds(25, 310, 150, 20);
        add(speed);

        JLabel speedLabel = new JLabel("Скорость");
        speedLabel.setBounds(225, 310, 150, 20);
        add(speedLabel);

        JTextField pressure = new JTextField();
        pressure.setBounds(25, 364, 150, 20);
        add(pressure);

        JLabel pressureLabel = new JLabel("Потеря давления");
        pressureLabel.setBounds(225, 364, 150, 20);
        add(pressureLabel);

        JButton reset = new JButton("Сброс");
        reset.setBounds(55, 425, 270, 50);
        add(reset);

        setVisible(true);

        calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double d = CalculateDiameter(Double.parseDouble(width.getText()), Double.parseDouble(height.getText()));
                double s = CalculateSpeed(Double.parseDouble(flowRate.getText()), d);
                diameter.setText(Double.toString(d));
                speed.setText(Double.toString(s));
                pressure.setText(Double.toString(CalculatePressure(s, d)));
            }
        });
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flowRate.setText("");
                width.setText("");
                height.setText("");
                diameter.setText("");
                speed.setText("");
                pressure.setText("");
            }
        });
    }

    public static double CalculateSpeed(double flowRate, double diameter){
        return (4 * flowRate / (3600 * 3.14 * Math.pow(diameter, 2)));
    }

    public static double CalculatePressure(double speed, double diameter){
        double Re = 1.225 * speed * diameter / (1.78 * Math.pow(10,-5));
        double r = 0.11 * Math.pow((0.1 / diameter + 68 / Re ), 0.25);
        return r * 1.225 * Math.pow(speed, 2) / (diameter * 2);
    }

    public static double CalculateDiameter (double width, double height){
        return (2 * width * height) / (width + height);
    }
}
