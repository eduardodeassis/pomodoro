import javax.swing.JTextField;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Temporizador {
	int Minuto = 0;
	int Segundo = 0;
	int Iniciado = 0;
	private File arqBeep = new File("src/som/warning-sound-6686.wav");
	
    private void beep() {
    	AudioInputStream din = null;
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(arqBeep.getAbsoluteFile());
            AudioFormat baseFormat = in.getFormat();
            AudioFormat decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                    baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
                    false);
            din = AudioSystem.getAudioInputStream(decodedFormat, in);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            if(line != null) {
                line.open(decodedFormat);
                byte[] data = new byte[4096];
                // Start
                line.start();

                int nBytesRead;
                while ((nBytesRead = din.read(data, 0, data.length)) != -1) {
                    line.write(data, 0, nBytesRead);
                }
                // Stop
                line.drain();
                line.stop();
                line.close();
                din.close();
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if(din != null) {
                try { din.close(); } catch(IOException e) { }
            }
        }
    }
	 
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
						beep();
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
