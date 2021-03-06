/**
 * @author Чашников Михаил
 * @version dated 01 june 2017
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

public class CircleDuctCalculation extends JPanel {
    public CircleDuctCalculation() {

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
        flowRateDimension.setBounds(300, 30, 150, 20);
        add(flowRateDimension);

        JLabel diameterLabel = new JLabel("Диаметр");
        diameterLabel.setBounds(25, 110, 150, 20);
        add(diameterLabel);

        JTextField diameter = new JTextField();
        diameter.setBounds(115, 110, 150, 20);
        add(diameter);

        JLabel diameterErrorLabel = new JLabel("Неверный ввод. Введите положительное число.");
        diameterErrorLabel.setBounds(45, 135, 400, 20);
        diameterErrorLabel.setForeground(Color.red);
        add(diameterErrorLabel);
        diameterErrorLabel.setVisible(false);

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
                speed.setText("");
                pressure.setText("");
                flowRateErrorLabel.setVisible(false);
                diameterErrorLabel.setVisible(false);
                String sF = flowRate.getText();
                String sD = diameter.getText();
                double f = 0;
                double d = 0;
                try {
                    f = Double.parseDouble(sF);
                }
                catch (Exception ex){
                    flowRateErrorLabel.setVisible(true);
                }
                try {
                    d = Double.parseDouble(sD);
                }
                catch (Exception ex){
                    diameterErrorLabel.setVisible(true);
                }
                if (f <= 0) flowRateErrorLabel.setVisible(true);
                else {
                    if (d <= 0) diameterErrorLabel.setVisible(true);
                    else{
                        double s = CalculateSpeed(f, d);

                        BigDecimal bS = new BigDecimal(s, MathContext.DECIMAL32);
                        BigDecimal bP = new BigDecimal(CalculatePressure(s,d), MathContext.DECIMAL32);

                        speed.setText(bS.toString());
                        pressure.setText(bP.toString());
                    }
                }
            }
        });

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flowRateErrorLabel.setVisible(false);
                diameterErrorLabel.setVisible(false);
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
