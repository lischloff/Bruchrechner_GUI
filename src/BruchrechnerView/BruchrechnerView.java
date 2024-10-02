package BruchrechnerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BruchrechnerView {
    private JTextField zaehler1;
    private JTextField nenner1;
    private JTextField zaehler2;
    private JTextField nenner2;
    private JComboBox<String> rechenoperant;
    private JLabel istGleich;
    private JLabel zaehlerErgebnis;
    private JLabel nennerErgebnis;
    private JButton berechnenBtn;
    private JPanel View;
    private Calculator calculator;

    public BruchrechnerView() {
        // Initialisiere das Hauptpanel mit GridBagLayout
        View = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Abstände zwischen den Komponenten

        // Initialisiere die Eingabefelder und andere Komponenten
        zaehler1 = new JTextField(10);
        nenner1 = new JTextField(10);
        zaehler2 = new JTextField(10);
        nenner2 = new JTextField(10);
        rechenoperant = new JComboBox<>(new String[]{"+", "-", "*", "/"});
        istGleich = new JLabel("=");
        zaehlerErgebnis = new JLabel("0");
        nennerErgebnis = new JLabel("1");
        berechnenBtn = new JButton("Berechnen");
        calculator = new Calculator(); // Calculator-Instanz erstellen

        // Füge die Komponenten zum Panel hinzu
        gbc.gridx = 0;
        gbc.gridy = 0;
        View.add(zaehler1, gbc);

        gbc.gridy = 1;
        View.add(nenner1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        View.add(rechenoperant, gbc);
        gbc.gridheight = 1;

        gbc.gridx = 2;
        gbc.gridy = 0;
        View.add(zaehler2, gbc);

        gbc.gridy = 1;
        View.add(nenner2, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        View.add(istGleich, gbc);
        gbc.gridheight = 1;

        gbc.gridx = 4;
        gbc.gridy = 0;
        View.add(zaehlerErgebnis, gbc);

        gbc.gridy = 1;
        View.add(nennerErgebnis, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        View.add(berechnenBtn, gbc);

        // ActionListener für den Berechnen-Button
        berechnenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Überprüfe, ob die Felder leer sind
                    if (zaehler1.getText().isEmpty() || nenner1.getText().isEmpty() ||
                            zaehler2.getText().isEmpty() || nenner2.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(View, "Bitte füllen Sie alle Felder aus.", "Fehler", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Lese die Eingabewerte
                    int z1 = Integer.parseInt(zaehler1.getText());
                    int n1 = Integer.parseInt(nenner1.getText());
                    int z2 = Integer.parseInt(zaehler2.getText());
                    int n2 = Integer.parseInt(nenner2.getText());

                    // Überprüfe, ob die Nenner 0 sind (Division durch Null verhindern)
                    if (n1 == 0 || n2 == 0) {
                        JOptionPane.showMessageDialog(View, "Der Nenner darf nicht 0 sein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String operator = (String) rechenoperant.getSelectedItem();
                    int[] ergebnis = new int[2];

                    // Berechne das Ergebnis basierend auf dem ausgewählten Operator
                    switch (operator) {
                        case "+":
                            ergebnis = calculator.addFractions(z1, n1, z2, n2);
                            break;
                        case "-":
                            ergebnis = calculator.subtractFractions(z1, n1, z2, n2);
                            break;
                        case "*":
                            ergebnis = calculator.multiplyFractions(z1, n1, z2, n2);
                            break;
                        case "/":
                            ergebnis = calculator.divideFractions(z1, n1, z2, n2);
                            break;
                    }

                    // Setze das Ergebnis in die Labels
                    zaehlerErgebnis.setText(String.valueOf(ergebnis[0]));
                    nennerErgebnis.setText(String.valueOf(ergebnis[1]));
                } catch (NumberFormatException ex) {
                    // Zeige eine Fehlermeldung, wenn die Eingaben keine gültigen Zahlen sind
                    JOptionPane.showMessageDialog(View, "Bitte geben Sie gültige Zahlen ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                } catch (ArithmeticException ex) {
                    // Zeige eine Fehlermeldung bei einer Division durch Null
                    JOptionPane.showMessageDialog(View, ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        // Erstelle ein JFrame mit Titel "BruchrechnerView"
        JFrame frame = new JFrame("BruchrechnerView");
        // Setze den Inhalt des Frames auf die View der BruchrechnerView-Klasse
        frame.setContentPane(new BruchrechnerView().View);
        // Setze die Fenstergröße auf 600x400 Pixel
        frame.setSize(600, 400);
        // Beende das Programm, wenn das Fenster geschlossen wird
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Mache das Fenster sichtbar
        frame.setVisible(true);
    }
}
