/**
 * @author Чашников Михаил
 * @version dated 01 june 2017
 *
 * Программа производит расчет средней скорости движения воздуха в круглом или в прямоугольном воздуховоде.
 * Для расчета вводятся значение расхода воздуха и размер воздуховода, для круглого -- диаметр, для прямоугольного --
 * высота и ширина.
 * При расчете прямоугольного воздуховода производится промежуточный расчет эквивалентного диаметра.
 *
 * Выбор типа воздуховода осуществляется с помощью соответствующих кнопок в верхней части окна.
 * Для очистки полей ввода и вывода предусмотрена кнопка "Сброс".
 *
 * При вводе в соответсвующие поля отрицательных или нулевых значений, а также символов, отличных от цифр,
 * выводится сообщение об ошибке.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {

    public static void main(String[] args){
        new MainWindow();
    }

    MainWindow(){
        setTitle("Программа расчета");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(150, 150, 400, 650);

        CircleDuctCalculation circleDuctCalculation = new CircleDuctCalculation();
        RectangleDuctCalculation rectangleDuctCalculation = new RectangleDuctCalculation();

        JButton circle = new JButton("Круглый");
        circle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rectangleDuctCalculation.setVisible(false);
                add(circleDuctCalculation, BorderLayout.CENTER);
                circleDuctCalculation.setVisible(true);

            }
        });

        JButton rectangle = new JButton("Прямоугольный");
        rectangle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                circleDuctCalculation.setVisible(false);
                add(rectangleDuctCalculation, BorderLayout.CENTER);
                rectangleDuctCalculation.setVisible(true);
            }
        });
        JButton exit = new JButton("Выход");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel bp = new JPanel();
        bp.setLayout(new GridLayout()); // for button panel
        bp.add(circle);
        bp.add(rectangle);
        //bp.add(exit);

        setLayout(new BorderLayout()); // for main window
        add(bp, BorderLayout.NORTH);

        setVisible(true);

        add(rectangleDuctCalculation, BorderLayout.CENTER);
        rectangleDuctCalculation.setVisible(false);
        add(circleDuctCalculation, BorderLayout.CENTER);
    }
}
