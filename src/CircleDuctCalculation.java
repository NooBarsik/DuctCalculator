/**
 * @author Чашников Михаил
 * @version dated 31 may 2017
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CircleDuctCalculation extends JPanel {
    public CircleDuctCalculation() {

        setLayout(null);

        JLabel flowRateLabel = new JLabel("Расход");
        flowRateLabel.setBounds(25, 30, 150, 20);
        add(flowRateLabel);

        JTextField flowRate = new JTextField();
        flowRate.setBounds(115, 30, 150, 20);
        add(flowRate);

        JLabel flowRateDimension = new JLabel("куб.м/ч");
        flowRateDimension.setBounds(300, 30, 150, 20);
        add(flowRateDimension);

        JLabel diameterLabel = new JLabel("Диаметр");
        diameterLabel.setBounds(25, 110, 150, 20);
        add(diameterLabel);

        JTextField diameter = new JTextField();
        diameter.setBounds(115, 110, 150, 20);
        add(diameter);

        JLabel diameterDimension = new JLabel("м");
        diameterDimension.setBounds(300, 110, 150, 20);
        add(diameterDimension);

        JButton calculate = new JButton("Расчитать");
        calculate.setBounds(55, 175, 270, 50);
        add(calculate);

        JLabel speedLabel = new JLabel("Скорость");
        speedLabel.setBounds(25, 270, 150, 20);
        add(speedLabel);

        JTextField speed = new JTextField();
        speed.setBounds(115, 270, 150, 20);
        add(speed);

        JLabel speedDimension = new JLabel("м/с");
        speedDimension.setBounds(310, 270, 150, 20);
        add(speedDimension);

        JLabel pressureLabel = new JLabel("Потеря давл.");
        pressureLabel.setBounds(25, 350, 150, 20);
        add(pressureLabel);

        JTextField pressure = new JTextField();
        pressure.setBounds(115, 350, 150, 20);
        add(pressure);

        JLabel pressureDimension = new JLabel("Па");
        pressureDimension.setBounds(310, 350, 150, 20);
        add(pressureDimension);

        JButton reset = new JButton("Сброс");
        reset.setBounds(55, 425, 270, 50);
        add(reset);

        setVisible(true);

        calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double s = CalculateSpeed(Double.parseDouble(flowRate.getText()),
                        Double.parseDouble(diameter.getText()));
                speed.setText(Double.toString(s));
                pressure.setText(Double.toString(CalculatePressure(s, Double.parseDouble(diameter.getText()))));
            }
        });
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flowRate.setText("");
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

}
