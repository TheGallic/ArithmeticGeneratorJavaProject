package com.daviddu64;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Main extends JFrame {

	private JPanel contentPane;
	private int userScore = 0;// Determine le score du joueur
	private int userSteps = 0; // Le nombre d'exercices déjà fait
	private long userAnswer = 0;// La réponse du joueur
	private int gameNBR = 0;// Le nombre de chiffres pour faire l'operation
	private String gameOperator = "";// L'opérateur choisie
	private int gameSteps = 0;// Le nombre d'exercices choisie
	private int thisSteps = 0;// L'étape actuelle
	private JLabel lblOperator;
	private JLabel lblScore;
	private JLabel lblSteps;
	private JLabel lblResultat;
	private Random random;
	private long finalOperation = 0;
	private JButton btnValidate;
	private JButton btnPlay;
	private JButton btnStop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setTitle("Générateur de calcul mental");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Choisissez le nombre de chiffres:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 218, 24);
		contentPane.add(lblNewLabel);

		JSpinner spinnerNbr = new JSpinner();
		spinnerNbr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerNbr.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerNbr.setBounds(238, 15, 64, 20);
		((DefaultEditor) spinnerNbr.getEditor()).getTextField().setEditable(false);
		contentPane.add(spinnerNbr);

		JLabel lblChoisissezLoprateur = new JLabel("Choisissez l'opérateur:");
		lblChoisissezLoprateur.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChoisissezLoprateur.setBounds(10, 46, 218, 24);
		contentPane.add(lblChoisissezLoprateur);

		JSpinner spinnerOperator = new JSpinner();
		spinnerOperator.setModel(new SpinnerListModel(new String[] { "+", "-", "/", "*" }));
		spinnerOperator.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerOperator.setBounds(238, 50, 64, 20);
		((DefaultEditor) spinnerOperator.getEditor()).getTextField().setEditable(false);
		contentPane.add(spinnerOperator);

		JLabel lblNombreDtapesDe = new JLabel("Nombre d'étapes de l'exercice:");
		lblNombreDtapesDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreDtapesDe.setBounds(10, 81, 218, 24);
		contentPane.add(lblNombreDtapesDe);

		JSpinner spinnerSteps = new JSpinner();
		spinnerSteps.setModel(new SpinnerNumberModel(5, 5, 50, 5));
		spinnerSteps.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerSteps.setBounds(238, 85, 64, 20);
		((DefaultEditor) spinnerSteps.getEditor()).getTextField().setEditable(false);
		contentPane.add(spinnerSteps);

		lblSteps = new JLabel("Etapes: 0 sur 5");
		lblSteps.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSteps.setBounds(312, 18, 143, 42);
		contentPane.add(lblSteps);

		lblScore = new JLabel("Score: 0");
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblScore.setBounds(312, 63, 143, 42);
		contentPane.add(lblScore);

		JLabel lblnewlabel6 = new JLabel("Opération: ");
		lblnewlabel6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblnewlabel6.setBounds(10, 160, 80, 24);
		contentPane.add(lblnewlabel6);

		lblOperator = new JLabel("1*1");
		lblOperator.setBorder(new LineBorder(Color.BLACK));
		lblOperator.setBounds(new Rectangle(1, 1, 1, 1));
		lblOperator.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperator.setForeground(Color.BLUE);
		lblOperator.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOperator.setBounds(10, 195, 218, 24);
		contentPane.add(lblOperator);

		JFormattedTextField ftfResultat = new JFormattedTextField();
		ftfResultat.setHorizontalAlignment(SwingConstants.LEFT);
		ftfResultat.setText("0");
		ftfResultat.setFont(new Font("Tahoma", Font.BOLD, 14));
		ftfResultat.setBounds(238, 196, 64, 24);
		contentPane.add(ftfResultat);

		btnPlay = new JButton("Jouer");

		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPlay.setBounds(10, 116, 143, 26);
		contentPane.add(btnPlay);

		lblResultat = new JLabel("Résultat: ");
		lblResultat.setForeground(Color.BLUE);
		lblResultat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblResultat.setBounds(10, 230, 445, 24);
		contentPane.add(lblResultat);

		btnStop = new JButton("Stop");

		btnStop.setEnabled(false);
		btnStop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStop.setBounds(159, 116, 143, 26);
		contentPane.add(btnStop);

		btnValidate = new JButton("Valider la réponse");

		btnValidate.setEnabled(false);
		btnValidate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnValidate.setBounds(312, 195, 143, 27);
		contentPane.add(btnValidate);

		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPlay.setEnabled(false);
				btnStop.setEnabled(true);
				btnValidate.setEnabled(true);
				gameNBR = Integer.valueOf(spinnerNbr.getValue().toString());
				gameOperator = spinnerOperator.getValue().toString();
				gameSteps = Integer.valueOf(spinnerSteps.getValue().toString());
				playGame("First");
			}
		});

		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPlay.setEnabled(true);
				btnStop.setEnabled(false);
				btnValidate.setEnabled(false);
				stopGame();
			}
		});

		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = ftfResultat.getText();
				// On verifie si le champ est vide est si c'est un chiffre
				boolean isNumeric = str.matches("[+-]?\\d*(\\.\\d+)?");
				if (isNumeric == false || str.isEmpty() == true) {
					lblResultat.setText("Saisissez un chiffre");
					lblResultat.setForeground(Color.red);
				} else {
					gameNBR = Integer.valueOf(spinnerNbr.getValue().toString());
					gameOperator = spinnerOperator.getValue().toString();
					gameSteps = Integer.valueOf(spinnerSteps.getValue().toString());
					userAnswer = Long.valueOf(ftfResultat.getText());
					playGame("Next");
				}
			}
		});

	}

	public void playGame(String gameStates) {
		// on creer un thread pour rafraichir les elements
		new Thread(new Runnable() {
			public void run() {

				if (gameStates == "First") {
					// On genere une nouvelle opération
					getResultat();
					return;
				} else if (gameStates == "Next") {
					if (finalOperation == userAnswer) {
						lblResultat.setText("Vous avez gagné, voici le résultat: " + finalOperation);
						lblResultat.setForeground(Color.green);
						userScore += 1;
						lblScore.setText("Score: " + userScore);

					} else {
						lblResultat.setText("Vous avez perdu, voici le résultat: " + finalOperation);
						lblResultat.setForeground(Color.red);
					}

					if (thisSteps == gameSteps) {
						JOptionPane.showMessageDialog(null, "La partie est finie, voici vitre score: " + userScore);
						stopGame();
						return;
					} else {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						lblResultat.setText("Jouer");
						lblResultat.setForeground(Color.blue);
						// On genere une nouvelle opération
						getResultat();

						return;
					}
				}
			}

			// on lance le thread
		}).start();
	}

	public void stopGame() {
		btnPlay.setEnabled(true);
		btnStop.setEnabled(false);
		btnValidate.setEnabled(false);
		userScore = 0;
		userSteps = 0;
		userAnswer = 0;
		gameNBR = 0;
		gameOperator = "";
		gameSteps = 0;
		thisSteps = 0;
		lblOperator.setText("1*1");
		lblScore.setText("Score: 0");
		lblSteps.setText("Etapes: 0 sur 0");
		lblResultat.setText("Résultat: ");
		lblResultat.setForeground(Color.blue);
	}

	public int getMaxNumber() {
		int maxRandomNumber = 0;
		switch (gameNBR) {
		case 1:
			maxRandomNumber = 9;
			break;
		case 2:
			maxRandomNumber = 99;
			break;
		case 3:
			maxRandomNumber = 999;
			break;
		case 4:
			maxRandomNumber = 9999;
			break;
		case 5:
			maxRandomNumber = 99999;
			break;
		}
		return maxRandomNumber;
	}

	public void getResultat() {
		random = new Random();
		int maxNumber = getMaxNumber();
		int random1 = 0;
		int random2 = 0;

		//
		thisSteps += 1;
		lblSteps.setText("Etapes: " + thisSteps + " sur " + gameSteps);
		// On genere un nombre aléatoire
		random1 = random.nextInt(0, maxNumber);
		random2 = random.nextInt(0, maxNumber);
		switch (gameOperator) {
		case "+":
			finalOperation = random1 + random2;
			lblOperator.setText(random1 + " + " + random2);
			break;
		case "-":
			finalOperation = random1 - random2;
			lblOperator.setText(random1 + " - " + random2);
			break;
		case "/":
			finalOperation = random1 / random2;
			lblOperator.setText(random1 + " / " + random2);
			break;
		case "*":
			finalOperation = random1 * random2;
			lblOperator.setText(random1 + " * " + random2);
			break;
		}
	}
}
