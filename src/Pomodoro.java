import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Point;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.io.File;


public class Pomodoro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtTempo;
	private Point initialClick;
	private File imgLogo = new File("src/img/tomato32.png");

	/**
	 * Launch the application.
	 */	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pomodoro frame = new Pomodoro();
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
	public Pomodoro() {
		
		getContentPane().setBackground(new Color(255, 198, 198));
		getContentPane().setForeground(new Color(0, 0, 0));
		setForeground(new Color(255, 198, 198));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 419, 40);
		setUndecorated(true);
		
		// Adiciona o MouseListener e MouseMotionListener
	    addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            initialClick = e.getPoint();
	            getComponentAt(initialClick);
	        }
	    });

	    addMouseMotionListener(new MouseAdapter() {
	        @Override
	        public void mouseDragged(MouseEvent e) {
	            // Obtém a localização atual da janela
	            int thisX = getLocation().x;
	            int thisY = getLocation().y;

	            // Calcula a nova posição da janela
	            int xMoved = e.getX() - initialClick.x;
	            int yMoved = e.getY() - initialClick.y;

	            int X = thisX + xMoved;
	            int Y = thisY + yMoved;

	            // Move a janela para a nova posição
	            setLocation(X, Y);
	        }
	    });
		
	    ImageIcon imagem = new ImageIcon(imgLogo.getAbsolutePath());
		getContentPane().setLayout(null);
		JLabel lblImagem = new JLabel(imagem);
		lblImagem.setBounds(4, 4, 32, 32);
		getContentPane().add(lblImagem);					
				
		txtTempo = new JTextField();
		txtTempo.setBounds(40, 10, 90, 20);
		txtTempo.setText("30:00");
		getContentPane().add(txtTempo);
		txtTempo.setColumns(10);
		Temporizador tmp = new Temporizador();
			
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.setBounds(135, 8, 75, 23);
		btnIniciar.setBackground(new Color(255, 198, 198));
				
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnIniciar.getText() == "Iniciar") {
					try {
						int tMinuto = Integer.parseInt(txtTempo.getText().substring(0, txtTempo.getText().indexOf(":")));
						System.out.printf("bt min: %d%n", tMinuto);
						int tSegundo = Integer.parseInt(txtTempo.getText().substring(txtTempo.getText().indexOf(":")+1, txtTempo.getText().length()));
						System.out.printf("bt seg: %d%n", tSegundo);
						if ((tSegundo < 0) || (tSegundo > 59)) {
							JOptionPane.showMessageDialog(null, "O valor informado para o segundo deve ser um valor entre 0 e 59.");
				            System.out.println("Erro: O valor informado para o segundo deve ser um valor entre 0 e 59.");
				            return;
						}
					} catch (Exception exc) {
						JOptionPane.showMessageDialog(null, "Por favor verique o tempo informado.");
			            System.out.println("Erro: " + exc.getMessage());
			            return;
					}
					tmp.iniciar(txtTempo, btnIniciar, "Iniciar");
					btnIniciar.setText("Pausar");
				}
				else {
					tmp.pausar();
					btnIniciar.setText("Iniciar");
				}
			}
		});
		/*
		btnIniciar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
            	btnIniciar.setBackground(Color.PINK);
            }            
            @Override
            public void mouseMoved(MouseEvent e) {
            	btnIniciar.setBackground(Color.CYAN); 
            }
		});
		*/
		getContentPane().add(btnIniciar);
		
		JButton btnConfiguracoes = new JButton("Configurações");
		btnConfiguracoes.setBounds(215, 8, 120, 23);
		btnConfiguracoes.setBackground(new Color(255, 198, 198));
		btnConfiguracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Configuracoes frmConfiguracoes = new Configuracoes();
				//frmConfiguracoes.pack();
				frmConfiguracoes.setVisible(true);
			}
		});
		getContentPane().add(btnConfiguracoes);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(340, 8, 70, 23);
		btnSair.setBackground(new Color(255, 198, 198));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
		getContentPane().add(btnSair);
	}

}
