import javax.swing.JTextField;

public class Temporizador {
	int Minuto = 0;
	int Segundo = 0;
	int Iniciado = 0;
	 
	public void setTemporizador(int iMinuto, int iSegundo) {
		Minuto = iMinuto;
		Segundo = iSegundo;
	}
	
	public void decrementa(int iMinuto, int iSegundo) {
		if (Minuto + Segundo > 0) {
			if ((Minuto < iMinuto) && (iMinuto > 0)) {
				Minuto = 0;
				Segundo = 0;
			}
			else if ((Minuto >= iMinuto) && (iMinuto > 0)) {
				Minuto = Minuto - iMinuto;
			}
			
			if ((Minuto + Segundo > 0) && (iSegundo > 0)) {
				if (Segundo == 0) {
					Minuto = Minuto - 1;
					Segundo = 60;
				}
				Segundo = Segundo - iSegundo;				
			}
		}
	}
	
	public String txtFormatado() {
		return String.format("%02d:%02d", Minuto, Segundo);
	}
	
	public String toString() {
		return txtFormatado();
	}
	
	public void iniciar(JTextField txt) {
		Iniciado = 1;
		System.out.println("iniciado");
		
		int tMinuto = Integer.parseInt(txt.getText().substring(0, txt.getText().indexOf(":")));
		System.out.printf("bt min: %d%n", tMinuto);
		int tSegundo = Integer.parseInt(txt.getText().substring(txt.getText().indexOf(":")+1, txt.getText().length()));
		System.out.printf("bt seg: %d%n", tSegundo);
		setTemporizador(tMinuto, tSegundo);
		
		new Thread() {

		    @Override
		    public void run() {
		    	
				while (Iniciado == 1) {
					try {
		                Thread.sleep(1000);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
					decrementa(0, 1);
					if (Minuto + Segundo == 0) {
						Iniciado = 0;
					}
					System.out.println(txtFormatado());
					txt.setText(txtFormatado());
				}

		    }
		  }.start();		
	}
	
	public void pausar() {
		Iniciado = 0;
		System.out.println("pausado");
	}

}
