import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Configuracoes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtFoco;
	private JTextField edtIntervalo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Configuracoes frame = new Configuracoes();
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
	public Configuracoes() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 254, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 192), 1, true));
		panel.setBounds(10, 11, 220, 94);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tempo:");
		lblNewLabel_1.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Foco:");
		lblNewLabel.setBounds(20, 36, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Intervalo:");
		lblNewLabel_2.setBounds(134, 36, 81, 14);
		panel.add(lblNewLabel_2);
		
		edtFoco = new JTextField();
		edtFoco.setText("30:00");
		edtFoco.setBounds(20, 56, 70, 20);
		panel.add(edtFoco);
		edtFoco.setColumns(10);
		
		edtIntervalo = new JTextField();
		edtIntervalo.setText("05:00");
		edtIntervalo.setColumns(10);
		edtIntervalo.setBounds(134, 56, 70, 20);
		panel.add(edtIntervalo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 192), 1, true));
		panel_1.setBounds(10, 116, 218, 47);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(16, 11, 89, 23);
		panel_1.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(114, 11, 89, 23);
		panel_1.add(btnCancelar);
	}
}
