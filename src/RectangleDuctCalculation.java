/**
 * @author Чашников Михаил
 * @version dated 31 may 2017
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RectangleDuctCalculation extends JPanel {
    public RectangleDuctCalculation() {

        setLayout(null);

        JLabel flowRateLabel = new JLabel("Расход");
        flowRateLabel.setBounds(25, 30, 150, 20);
        add(flowRateLabel);

        JTextField flowRate = new JTextField();
        flowRate.setBounds(115, 30, 150, 20);
        add(flowRate);

        JLabel flowRateErrorLabel = new JLabel("Неверный ввод. Введите положительное число.");
        flowRateErrorLabel.setBounds(45, 55, 400, 20);
        flowRateErrorLabel.setForeground(Color.red);
        add(flowRateErrorLabel);
        flowRateErrorLabel.setVisible(false);

        JLabel flowRateDimension = new JLabel("куб.м/ч");
        flowRateDimension.setBounds(310, 30, 150, 20);
        add(flowRateDimension);

        JLabel widthLabel = new JLabel("Ширина");
        widthLabel.setBounds(25, 80, 150, 20);
        add(widthLabel);

        JTextField width = new JTextField();
        width.setBounds(115, 80, 150, 20);
        add(width);

        JLabel widthErrorLabel = new JLabel("Неверный ввод. Введите положительное число.");
        widthErrorLabel.setBounds(45, 105, 400, 20);
        widthErrorLabel.setForeground(Color.red);
        add(widthErrorLabel);
        widthErrorLabel.setVisible(false);

        JLabel widthDimension = new JLabel("м");
        widthDimension.setBounds(310, 80, 150, 20);
        add(widthDimension);

        JLabel heightLabel = new JLabel("Высота");
        heightLabel.setBounds(25, 130, 150, 20);
        add(heightLabel);

        JTextField height = new JTextField();
        height.setBounds(115, 130, 150, 20);
        add(height);

        JLabel heightErrorLabel = new JLabel("Неверный ввод. Введите положительное число.");
        heightErrorLabel.setBounds(45, 155, 400, 20);
        heightErrorLabel.setForeground(Color.red);
        add(heightErrorLabel);
        heightErrorLabel.setVisible(false);

        JLabel heightDimension = new JLabel("м");
        heightDimension.setBounds(310, 130, 150, 20);
        add(heightDimension);

        JButton calculate = new JButton("Расчитать");
        calculate.setBounds(55, 175, 270, 50);
        add(calculate);

        JLabel diameterLabel = new JLabel("Экв. диаметр");
        diameterLabel.setBounds(25, 255, 150, 20);
        add(diameterLabel);

        JTextField diameter = new JTextField();
        diameter.setBounds(115, 255, 150, 20);
        add(diameter);

        JLabel diameterDimension = new JLabel("м");
        diameterDimension.setBounds(310, 255, 150, 20);
        add(diameterDimension);

        JLabel speedLabel = new JLabel("Скорость");
        speedLabel.setBounds(25, 303, 150, 20);
        add(speedLabel);

        JTextField speed = new JTextField();
        speed.setBounds(115, 303, 150, 20);
        add(speed);

        JLabel speedDimension = new JLabel("м/с");
        speedDimension.setBounds(310, 303, 150, 20);
        add(speedDimension);

        JLabel pressureLabel = new JLabel("Потеря давл.");
        pressureLabel.setBounds(25, 351, 150, 20);
        add(pressureLabel);

        JTextField pressure = new JTextField();
        pressure.setBounds(115, 351, 150, 20);
        add(pressure);

        JLabel pressureDimension = new JLabel("Па");
        pressureDimension.setBounds(310, 351, 150, 20);
        add(pressureDimension);

        JButton reset = new JButton("Сброс");
        reset.setBounds(55, 425, 270, 50);
        add(reset);

        setVisible(true);

        calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                diameter.setText("");
                speed.setText("");
                pressure.setText("");

                flowRateErrorLabel.setVisible(false);
                widthErrorLabel.setVisible(false);
                heightErrorLabel.setVisible(false);

                String sF = flowRate.getText();
                String sW = width.getText();
                String sH = height.getText();

                double f = 0;
                double w = 0;
                double h = 0;

                try {
                    f = Double.parseDouble(sF);
                }
                catch (Exception ex) {
                    flowRateErrorLabel.setVisible(true);
                }

                try {
                    w = Double.parseDouble(sW);
                }
                catch (Exception ex) {
                    widthErrorLabel.setVisible(true);
                }

                try {
                    h = Double.parseDouble(sH);
                }
                catch (Exception ex) {
                    heightErrorLabel.setVisible(true);
                }

                if (f <= 0) flowRateErrorLabel.setVisible(true);
                else {
                    if (w <= 0) widthErrorLabel.setVisible(true);
                    else {
                        if (h <= 0) heightErrorLabel.setVisible(true);
                        else {
                            double d = CalculateDiameter(w, h);
                            double s = CalculateSpeed(f, d);
                            diameter.setText(Double.toString(d));
                            speed.setText(Double.toString(s));
                            pressure.setText(Double.toString(CalculatePressure(s, d)));
                        }
                    }
                }


            }
        });

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flowRateErrorLabel.setVisible(false);
                widthErrorLabel.setVisible(false);
                heightErrorLabel.setVisible(false);
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
